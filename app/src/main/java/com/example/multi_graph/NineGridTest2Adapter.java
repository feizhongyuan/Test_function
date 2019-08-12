package com.example.multi_graph;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.calendar.R;
import com.example.multi_graph.view.FirstNineGridLayout;

import java.util.List;

/**
 * Created by HMY on 2016/8/6
 */
public class NineGridTest2Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private List<NineGridModel> mList;
    protected LayoutInflater inflater;

    public NineGridTest2Adapter(Context context) {
        mContext = context;
        inflater = LayoutInflater.from(context);
    }

    public void setList(List<NineGridModel> list) {
        mList = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        RecyclerView.ViewHolder holder = null;
        if (viewType == 1) {
            View convertView = inflater.inflate(R.layout.item_nine_grid_1, parent, false);
            holder = new ViewHolder(convertView);
        } else {
            View convertView = inflater.inflate(R.layout.item_nine_grid_2, parent, false);
            holder = new ViewHolder1(convertView);
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ViewHolder) {
            ViewHolder viewHolder = (ViewHolder) holder;
            viewHolder.layout.setIsShowAll(mList.get(position).isShowAll);
            viewHolder.layout.setUrlList(mList.get(position).urlList);
            viewHolder.tv_name.setText(mList.get(position).getName());
            viewHolder.tv_time.setText(mList.get(position).getTime());
            viewHolder.tv_content.setText(mList.get(position).getContent());
        }else if (holder instanceof ViewHolder1){
            ViewHolder1 viewHolder1 = (ViewHolder1) holder;
            viewHolder1.tv_name.setText(mList.get(position).getName());
            viewHolder1.tv_time.setText(mList.get(position).getTime());
            viewHolder1.tv_content.setText(mList.get(position).getContent());
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (mList.get(position).urlList.size() > 0) {
            return 1;
        } else {
            return 2;
        }
    }

    @Override
    public int getItemCount() {
        return getListSize(mList);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        FirstNineGridLayout layout;
        private final ImageView img_head;
        private final TextView tv_name;
        private final TextView tv_time;
        private final TextView tv_content;

        public ViewHolder(View itemView) {
            super(itemView);
            layout = (FirstNineGridLayout) itemView.findViewById(R.id.layout_nine_grid);
            img_head = itemView.findViewById(R.id.img_head);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_time = itemView.findViewById(R.id.tv_time);
            tv_content = itemView.findViewById(R.id.tv_content);

        }
    }

    public class ViewHolder1 extends RecyclerView.ViewHolder {
        private final ImageView img_head;
        private final TextView tv_name;
        private final TextView tv_time;
        private final TextView tv_content;

        public ViewHolder1(View itemView) {
            super(itemView);
            img_head = itemView.findViewById(R.id.img_head);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_time = itemView.findViewById(R.id.tv_time);
            tv_content = itemView.findViewById(R.id.tv_content);
        }
    }

    private int getListSize(List<NineGridModel> list) {
        if (list == null || list.size() == 0) {
            return 0;
        }
        return list.size();
    }
}
