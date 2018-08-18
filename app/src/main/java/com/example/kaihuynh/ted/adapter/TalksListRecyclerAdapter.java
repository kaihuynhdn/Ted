package com.example.kaihuynh.ted.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kaihuynh.ted.R;
import com.example.kaihuynh.ted.models.Talk;

import java.util.ArrayList;

public class TalksListRecyclerAdapter extends RecyclerView.Adapter<TalksListRecyclerAdapter.ItemViewHolder> {

    private ArrayList<Talk> talks;
    private Context context;
    private int layout;

    final private ListItemClickListener mOnClickListener;

    public interface ListItemClickListener {
        void onListItemClick(int clickItemIndex);
    }

    public TalksListRecyclerAdapter(Context context, int layout, ArrayList<Talk> talks, ListItemClickListener listener) {
        this.context = context;
        this.layout = layout;
        this.talks = talks;
        this.mOnClickListener = listener;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(layout, parent, false);

        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return talks.size();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView title, speaker, time;
        LinearLayout group;

        ItemViewHolder(View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.talk_title_item);
            speaker = itemView.findViewById(R.id.talk_speaker_item);
            time = itemView.findViewById(R.id.talk_time_item);
            group = itemView.findViewById(R.id.linear_layout_item);


            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int clickPosition = getAdapterPosition();
            mOnClickListener.onListItemClick(clickPosition);
        }
    }
}
