package com.example.sideslip;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.calendar.MainActivity;
import com.example.calendar.R;

import java.util.ArrayList;
import java.util.List;

import ren.widget.refresh.SwipeMenuRefreshView;

/**
 * 侧滑删除Demo
 */
public class SideslipActivity extends AppCompatActivity {

    private ListView mListView;
    private SwipeMenuRefreshView mSwipeMenuRefresh;

    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sideslip);
        initView();

        initData();

        initListener();

    }

    private void initListener() {
        mSwipeMenuRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(SideslipActivity.this, "刷新完成", Toast.LENGTH_SHORT).show();
                        mSwipeMenuRefresh.setRefreshing(false);
                    }
                },2000);
            }
        });
    }

    private void initData() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            list.add(i + "条数据");
        }
        final SideslipAdapter sideslipAdapter = new SideslipAdapter(list, this);
        mListView.setAdapter(sideslipAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(SideslipActivity.this, sideslipAdapter.getItem(position), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initView() {
        mListView = (ListView) findViewById(R.id.mListView);
        mSwipeMenuRefresh = (SwipeMenuRefreshView) findViewById(R.id.mSwipeMenuRefresh);
    }
}
