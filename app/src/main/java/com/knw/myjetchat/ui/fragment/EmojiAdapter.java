package com.knw.myjetchat.ui;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.knw.myjetchat.MainActivity;
import com.knw.myjetchat.R;

import java.util.List;

public class EmojiAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private List<String> emojiList;
    private OnEmojiClickListener listener;

    public interface OnEmojiClickListener {
        void onEmojiClick(String emoji);
    }
    public EmojiAdapter(List<String> emojiList, OnEmojiClickListener listener) {
        this.emojiList = emojiList;
        this.listener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.emoji_item,parent,false);
        EmojiViewHolder viewHolder=new EmojiViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder( RecyclerView.ViewHolder holder, int position) {
        String emoji=emojiList.get(position);
        EmojiViewHolder viewHolder = (EmojiViewHolder) holder;
        viewHolder.emojiTextView.setText(emoji);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onEmojiClick(emoji);
                }
            }
        });
    }
    @Override
    public int getItemCount() {
        return emojiList.size();
    }
    static class EmojiViewHolder extends RecyclerView.ViewHolder {

        TextView emojiTextView;

     public   EmojiViewHolder( View itemView) {
            super(itemView);
            emojiTextView = itemView.findViewById(R.id.textEmoji);
        }
    }
}
