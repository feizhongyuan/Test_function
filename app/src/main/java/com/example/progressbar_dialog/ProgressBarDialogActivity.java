package com.example.progressbar_dialog;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.calendar.R;

public class ProgressBarDialogActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mBt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress_bar_dialog);
        initView();
    }

    private void initView() {

        mBt = (Button) findViewById(R.id.mBt);
        mBt.setOnClickListener(this);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                ProgressUtil.showProgressDialog(ProgressBarDialogActivity.this, "正在加载中...");
            }
        }, 3000);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mBt:
                ProgressUtil.dismiss();
                break;
                default:
                    break;
        }
    }
}
