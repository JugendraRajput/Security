package com.jdgames.security;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class Adapter extends ArrayAdapter<Parse> {

    int resource;
    Context context;
    ArrayList<Parse> arrayList;

    public Adapter(Context context, int resource, ArrayList<Parse> arrayList) {
        super(context, resource, arrayList);
        this.resource = resource;
        this.context = context;
        this.arrayList = arrayList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = LayoutInflater.from(getContext()).inflate(resource, parent, false);
        }

        ImageView imageView = view.findViewById(R.id.imageView);
        TextView textView1 = view.findViewById(R.id.textView1);
        TextView textView2 = view.findViewById(R.id.textView2);
        TextView textView3 = view.findViewById(R.id.textView3);

        Parse parse = getItem(position);

        imageView.setImageResource(parse.getImageID());
        textView1.setText(parse.getTitle());
        textView2.setText(parse.getSubtitle());
        textView3.setText(parse.getValue());

        return view;
    }
}
