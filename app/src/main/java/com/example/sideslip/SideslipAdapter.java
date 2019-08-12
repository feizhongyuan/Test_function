package com.example.sideslip;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.swipe.SwipeLayout;
import com.example.calendar.R;

import java.util.List;

/**
 * Created by fei .
 * Created by Date 2019/7/19 14:30
 */

public class SideslipAdapter extends BaseAdapter {

    private List<String> list;
    private LayoutInflater inflater;
    private Context context;

    public SideslipAdapter(List<String> list, Context context) {
        this.list = list;
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public String getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.item_sideslip, null);
        TextView text = (TextView) convertView.findViewById(R.id.text);
        TextView collection = (TextView)convertView.findViewById(R.id.collection);
        TextView delete = (TextView)convertView.findViewById(R.id.delete);

        SwipeLayout mSwipeLayout = convertView.findViewById(R.id.mSwipeLayout);

        mSwipeLayout.isSwipeEnabled();
        mSwipeLayout.isClickToClose();

        collection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "将 "+getItem(position) + "加入收藏", Toast.LENGTH_SHORT).show();

            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "将 " + getItem(position) +"移除", Toast.LENGTH_SHORT).show();
                list.remove(position);
                notifyDataSetChanged();
            }
        });

        text.setText(getItem(position));
        return convertView;
    }
}
