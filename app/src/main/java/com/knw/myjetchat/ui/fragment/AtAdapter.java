package com.knw.myjetchat.ui.fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.knw.myjetchat.R;

import java.util.List;

public class AtAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private List<String> atList;
    private onAtClickListener listener;

    public interface  onAtClickListener {
        void onAtClick(String at);
    }

    public AtAdapter(List<String> atList, onAtClickListener listener) {
        this.atList = atList;
        this.listener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.at_item,parent,false);
        AtViewHolder viewHolder = new AtViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
      String at = atList.get(position);
      AtViewHolder viewHolder = (AtViewHolder) holder;
      viewHolder.atTextView.setText(at);
      viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              if(listener!=null)
              {
                  listener.onAtClick(at);
              }
          }
      });
    }

    @Override
    public int getItemCount() {
        return atList.size();
    }

    static class AtViewHolder extends RecyclerView.ViewHolder {

        TextView atTextView;

        public   AtViewHolder( View itemView) {
            super(itemView);
            atTextView = itemView.findViewById(R.id.textAt);
        }
    }
}
