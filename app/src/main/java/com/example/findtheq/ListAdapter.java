package com.example.findtheq;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.findtheq.models.Station;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {

    private Context context;
    private List<Station> list;

    public ListAdapter(Context context, List<Station> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        v= layoutInflater.inflate(R.layout.layout_list_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.name.setText(list.get(position).getName());
        String queueLength = String.valueOf(
                (list.get(position).getQueue().getBike())+
                        (list.get(position).getQueue().getBus())+
                        (list.get(position).getQueue().getCar())+
                        (list.get(position).getQueue().getTuk())+
                        (list.get(position).getQueue().getVan())
        );
        holder.queue.setText(queueLength);
        holder.status.setText(list.get(position).getStatus());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView name, queue, status;



        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.txtStationName);
            queue = itemView.findViewById(R.id.txtQueueLength);
            status = itemView.findViewById(R.id.txtStatus);
        }
    }
}
