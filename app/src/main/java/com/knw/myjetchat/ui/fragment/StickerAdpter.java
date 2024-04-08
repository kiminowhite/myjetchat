package com.knw.myjetchat.ui.fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.knw.myjetchat.R;

import java.util.List;

public class StickerAdpter  extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private List<Integer> stickerIdList;
    private OnStickerClickListener listener;
    public interface OnStickerClickListener
    {
        void onStickerClick(Integer stickerId);
    }
    public StickerAdpter(List<Integer> stickerIdList,OnStickerClickListener listener)
    {
        this.stickerIdList=stickerIdList;
        this.listener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.sticker_item,parent,false);
        StickerViewHolder viewHolder = new StickerViewHolder(view);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder( RecyclerView.ViewHolder holder, int position) {
        Integer stickerId = stickerIdList.get(position);
        StickerViewHolder viewHolder = (StickerViewHolder)  holder;
        viewHolder.textSticker.setImageResource(stickerId);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onStickerClick(stickerId);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return stickerIdList.size();
    }

    static class StickerViewHolder extends RecyclerView.ViewHolder {

        ImageView textSticker;

        public   StickerViewHolder( View itemView) {
            super(itemView);
            textSticker = itemView.findViewById(R.id.textSticker);
        }
    }
}
