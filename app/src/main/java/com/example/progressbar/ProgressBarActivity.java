package com.example.progressbar;

import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.calendar.R;
import com.example.progressbar.progressbar.CircleBarView;
import com.example.progressbar.utils.LinearGradientUtil;

import java.text.DecimalFormat;

public class ProgressBarActivity extends AppCompatActivity implements View.OnClickListener {

    private CircleBarView mCircleBarView;
    private TextView text_progress;
    private TextView text_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress_bar);
        initView();

    }

    private void initView() {
        mCircleBarView = (CircleBarView) findViewById(R.id.mCircleBarView);

        text_progress = (TextView) findViewById(R.id.text_progress);
        text_progress.setOnClickListener(this);
        text_title = (TextView) findViewById(R.id.text_title);
        text_title.setOnClickListener(this);

        mCircleBarView.setProgressNum(800, 5000);
        mCircleBarView.setTextView(text_progress);
        mCircleBarView.setOnAnimationListener(new CircleBarView.OnAnimationListener() {
            @Override
            public String howToChangeText(float interpolatedTime, float progressNum, float maxNum) {
                DecimalFormat decimalFormat=new DecimalFormat("0.00");
                String s = decimalFormat.format(interpolatedTime * progressNum / maxNum * 100) + "%";
                return s;
            }

            @Override
            public void howTiChangeProgressColor(Paint paint, float interpolatedTime, float progressNum, float maxNum) {
//                渐变颜色
                if (progressNum >= maxNum / 2){
                    LinearGradientUtil linearGradientUtil = new LinearGradientUtil(Color.RED,Color.WHITE);
                    paint.setColor(linearGradientUtil.getColor(interpolatedTime));
                }else {
                    LinearGradientUtil linearGradientUtil = new LinearGradientUtil(Color.RED,Color.YELLOW);
                    paint.setColor(linearGradientUtil.getColor(interpolatedTime));
                }
            }
        });
        text_title.setText("健康");


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.text_progress:
            case R.id.text_title:
                mCircleBarView.setProgressNum(400,3000);
                break;
            default:
                break;
        }
    }
}
