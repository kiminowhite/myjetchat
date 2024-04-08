package com.knw.myjetchat.ui.message;



import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.knw.myjetchat.MainActivity;
import com.knw.myjetchat.MyApplication;
import com.knw.myjetchat.ProfileActivity;
import com.knw.myjetchat.R;
import com.knw.myjetchat.logic.model.Msg;
import com.knw.myjetchat.logic.model.User;

import de.hdodenhof.circleimageview.CircleImageView;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;
import java.util.TimeZone;

public class MsgAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final List<Msg> msgList;

    public MsgAdapter(List<Msg> msgList) {
        this.msgList = msgList;
    }

    @Override
    public int getItemViewType(int position) {
        Msg msg = msgList.get(position);
        return msg.getType();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view;
        if (viewType == Msg.TYPE_RECEIVED) {
            view = inflater.inflate(R.layout.msg_other_item, parent, false);
            OtherViewHolder viewHolder = new OtherViewHolder(view);
            viewHolder.iconOtherMsg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    User user = new User("Aiges","アイギス", Arrays.asList("Robot","Angel"),
                            "Alive","twitter.com/aiges", TimeZone.getTimeZone("Asia/Tokyo"),R.drawable.aiges);
                    Intent intent = new Intent(v.getContext(), ProfileActivity.class);
                    intent.putExtra("profile",user);
                    v.getContext().startActivity(intent);
                }
            });

            return viewHolder;
        } else {
            view = inflater.inflate(R.layout.msg_you_item, parent, false);
            YouViewHolder viewHolder =new YouViewHolder(view);
            viewHolder.iconYouMsg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //真实情况应该是从拿item的id去数据库查信息,封装到repositroy中
                   // int position = viewHolder.getAdapterPosition();
                   // Msg msg = msgList.get(position);
                   // String senderName = msg.getSenderName();
                    User user = new User("Makoto","結城　理", Arrays.asList("Hero","Leader"),"Away","twitter.com/makoto", TimeZone.getTimeZone("Asia/Tokyo"),R.drawable.leader);
                    Intent intent = new Intent(v.getContext(), ProfileActivity.class);
                    intent.putExtra("profile",user);
                   v.getContext().startActivity(intent);
                }
            });
            return viewHolder;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Msg msg = msgList.get(position);
        if (holder instanceof OtherViewHolder) {
            OtherViewHolder otherHolder = (OtherViewHolder) holder;
            otherHolder.iconOtherMsg.setImageResource(msg.getIconId());
            otherHolder.nameOtherMsg.setText(msg.getSenderName());
            otherHolder.sendOtherDataMsg.setText(msg.getTimestamp().toString());
            otherHolder.textOtherMsg.setText(msg.getContent());
            otherHolder.textOtherMsg.setVisibility(View.VISIBLE);
            otherHolder.imgOtherMsg.setVisibility(View.GONE);

            if(msg.getContent()==null&&msg.getImgSourceId()!=null)
            {
               //text gone image not gone
                otherHolder.textOtherMsg.setVisibility(View.GONE);
                otherHolder.imgOtherMsg.setImageResource(msg.getImgSourceId());
                otherHolder.imgOtherMsg.setVisibility(View.VISIBLE);

            }
            //放下边
            if(msg.getContent()!=null && msg.getImgSourceId()!=null)
            {
                otherHolder.imgOtherMsg.setImageResource(msg.getImgSourceId());
                otherHolder.imgOtherMsg.setVisibility(View.VISIBLE);

            }

        } else if (holder instanceof YouViewHolder) {
            //默认有文字，无图片
            YouViewHolder youHolder = (YouViewHolder) holder;
            youHolder.iconYouMsg.setImageResource(msg.getIconId());
            youHolder.nameYouMsg.setText(msg.getSenderName());
            youHolder.sendYouDataMsg.setText(msg.getTimestamp().toString());
            youHolder.textYouMsg.setText(msg.getContent());
            youHolder.textYouMsg.setVisibility(View.VISIBLE);
            youHolder.imgYouMsg.setVisibility(View.GONE);

            //只有图片
            if(msg.getContent()==null&&msg.getImgSourceId()!=null)
            {
               //text gone image not gone
                youHolder.textYouMsg.setVisibility(View.GONE);
                youHolder.imgYouMsg.setImageResource(msg.getImgSourceId());
                youHolder.imgYouMsg.setVisibility(View.VISIBLE);

            }
            //图片和信息都有
            if(msg.getContent()!=null && msg.getImgSourceId()!=null)
            {
                //text not gone image not gone
              youHolder.imgYouMsg.setImageResource(msg.getImgSourceId());
              youHolder.imgYouMsg.setVisibility(View.VISIBLE);

            }
        } else {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public int getItemCount() {
        return msgList.size();
    }

    static class YouViewHolder extends RecyclerView.ViewHolder {

        CircleImageView iconYouMsg;
        TextView nameYouMsg;
        TextView sendYouDataMsg;
        TextView textYouMsg;
        ImageView imgYouMsg;

        public YouViewHolder(View itemView) {
            super(itemView);
            iconYouMsg = itemView.findViewById(R.id.iconYouMsg);
            nameYouMsg = itemView.findViewById(R.id.nameYouMsg);
            sendYouDataMsg = itemView.findViewById(R.id.sendYouDataMsg);
            textYouMsg = itemView.findViewById(R.id.textYouMsg);
            imgYouMsg = itemView.findViewById(R.id.imgYouMsg);

        }
    }

    static class OtherViewHolder extends RecyclerView.ViewHolder {

        CircleImageView iconOtherMsg;
        TextView nameOtherMsg;
        TextView sendOtherDataMsg;
        TextView textOtherMsg;
        ImageView imgOtherMsg;

        public OtherViewHolder(View itemView) {
            super(itemView);
            iconOtherMsg = itemView.findViewById(R.id.iconOtherMsg);
            nameOtherMsg = itemView.findViewById(R.id.nameOtherMsg);
            sendOtherDataMsg = itemView.findViewById(R.id.sendOtherDataMsg);
            textOtherMsg = itemView.findViewById(R.id.textOtherMsg);
            imgOtherMsg=itemView.findViewById(R.id.imgOtherMsg);
        }
    }
}
