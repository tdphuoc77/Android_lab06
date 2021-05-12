package com.example.lab06;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ListPlaceAdapter extends BaseAdapter {
    Context ctx;
    int layoutItem;
    ArrayList<Place> arrayList;
    public ListPlaceAdapter(Context ctx, int layoutItem, ArrayList<Place> arrayList) {
        this.ctx = ctx;
        this.layoutItem = layoutItem;
        this.arrayList = arrayList;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(ctx).inflate(layoutItem, parent, false);
        TextView tvPlace = convertView.findViewById(R.id.tvPlace);
        tvPlace.setText(arrayList.get(position).getId()+": "+arrayList.get(position).getPlace());

        ImageView imgEdit=convertView.findViewById(R.id.imgEdit);

        ImageView imgRemove=convertView.findViewById(R.id.imgRemove);

        return convertView;
    }
}
