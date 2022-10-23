package com.example.findtheq;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.findtheq.models.Station;

import java.util.ArrayList;

public class ListAdapter extends ArrayAdapter<Station> {

    public ListAdapter(Context context, ArrayList<Station> arrayList){
        super(context, R.layout.list_item, arrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        Station stationListView = getItem(position);
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item,parent,false);

        }

        TextView stationname= convertView.findViewById(R.id.stationname);
        TextView address = convertView.findViewById(R.id.address);

        stationname.setText(stationListView.getStationname());
        address.setText(stationListView.getAddress());

        return super.getView(position, convertView, parent);
    }
}
