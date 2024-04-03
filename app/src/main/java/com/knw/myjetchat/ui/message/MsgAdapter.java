package com.knw.myjetchat.ui.message;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.knw.myjetchat.R;
import com.knw.myjetchat.logic.model.Msg;

import de.hdodenhof.circleimageview.CircleImageView;

import java.text.SimpleDateFormat;
import java.util.List;

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
            return new OtherViewHolder(view);
        } else {
            view = inflater.inflate(R.layout.msg_you_item, parent, false);
            return new YouViewHolder(view);
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
        } else if (holder instanceof YouViewHolder) {
            YouViewHolder youHolder = (YouViewHolder) holder;
            youHolder.iconYouMsg.setImageResource(msg.getIconId());
            youHolder.nameYouMsg.setText(msg.getSenderName());
            youHolder.sendYouDataMsg.setText(msg.getTimestamp().toString());
            youHolder.textYouMsg.setText(msg.getContent());
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

        public YouViewHolder(View itemView) {
            super(itemView);
            iconYouMsg = itemView.findViewById(R.id.iconYouMsg);
            nameYouMsg = itemView.findViewById(R.id.nameYouMsg);
            sendYouDataMsg = itemView.findViewById(R.id.sendYouDataMsg);
            textYouMsg = itemView.findViewById(R.id.textYouMsg);
        }
    }

    static class OtherViewHolder extends RecyclerView.ViewHolder {

        CircleImageView iconOtherMsg;
        TextView nameOtherMsg;
        TextView sendOtherDataMsg;
        TextView textOtherMsg;

        public OtherViewHolder(View itemView) {
            super(itemView);
            iconOtherMsg = itemView.findViewById(R.id.iconOtherMsg);
            nameOtherMsg = itemView.findViewById(R.id.nameOtherMsg);
            sendOtherDataMsg = itemView.findViewById(R.id.sendOtherDataMsg);
            textOtherMsg = itemView.findViewById(R.id.textOtherMsg);
        }
    }
}
