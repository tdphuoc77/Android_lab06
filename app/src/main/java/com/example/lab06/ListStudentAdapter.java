package com.example.lab06;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import android.widget.TextView;

import java.util.ArrayList;

public class ListStudentAdapter extends BaseAdapter {
    Context ctx;
    int layoutItem;
    ArrayList<Student> arrayList;
    public ListStudentAdapter(Context ctx, int layoutItem, ArrayList<Student> arrayList) {
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
        TextView tvName = convertView.findViewById(R.id.tvName);
        tvName.setText(arrayList.get(position).getName());
        return convertView;
    }
}
