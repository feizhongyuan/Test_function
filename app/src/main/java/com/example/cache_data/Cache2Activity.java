package com.example.cache_data;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cache_data.util.ACache;
import com.example.calendar.R;

import java.io.IOException;


import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Cache2Activity extends AppCompatActivity implements View.OnClickListener {

    private Button mBt_save;
    private Button mBt_read;
    private Button mBt_clear;
    private TextView mTv_content;
    private ACache aCache;
    private AdvertBean advertBean;

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1){
                String  obj = (String) msg.obj;
                save(obj);
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cache2);
        initView();

//        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder()
//                .setNameFormat("demo-pool-%d").build();
//        ExecutorService singleThreadPool = new ThreadPoolExecutor(1, 1,
//                0L, TimeUnit.MILLISECONDS,
//                new LinkedBlockingQueue<Runnable>(1024), namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());
//
//        singleThreadPool.execute(()-> System.out.println(Thread.currentThread().getName()));
//        singleThreadPool.shutdown();

        http();
        aCache = ACache.get(this);

    }

    private void http() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient okHttpClient = new OkHttpClient();
                Request request = new Request.Builder()
                        .url("http://www.maqueezu.com/api/mobile/adv/adv-list.do?acid=24")
                        .build();
                Call call = okHttpClient.newCall(request);
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        Log.e("fail",e.getMessage());
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String string = response.body().string();
                        Message message = handler.obtainMessage();
                        message.obj = string;
                        message.what = 1;
                        handler.sendMessage(message);
                    }
                });


            }
        }).start();

//          吸顶
//        if (scrollY > ll_content.getHeight()) {
//            mTab_top.setVisibility(View.VISIBLE);
//            tab.setVisibility(View.INVISIBLE);
//        } else {
//            mTab_top.setVisibility(View.GONE);
//            tab.setVisibility(View.VISIBLE);
//        }

    }

    private void initView() {
        mBt_save = (Button) findViewById(R.id.mBt_save);
        mBt_read = (Button) findViewById(R.id.mBt_read);
        mBt_clear = (Button) findViewById(R.id.mBt_clear);
        mTv_content = (TextView) findViewById(R.id.mTv_content);

        mBt_save.setOnClickListener(this);
        mBt_read.setOnClickListener(this);
        mBt_clear.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mBt_save:
                break;
            case R.id.mBt_read:
                read();
                break;
            case R.id.mBt_clear:
                clear();
                break;
            default:
                break;
        }
    }

    private void clear() {
        aCache.remove("bean");
        Log.e("TAG","清除成功");
    }

    private void read() {
        String bean = aCache.getAsString("bean");
        Log.e("TAG","取成功");
        if (bean == null) {
            Toast.makeText(this, "String cache is null ...", Toast.LENGTH_SHORT)
                    .show();
            mTv_content.setText(null);
            return;
        }
        mTv_content.setText(bean);
    }

    private void save(String s) {
        aCache.put("bean",s);
        Log.e("TAG","存成功");
    }
}
