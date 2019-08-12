package com.example.textview;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.calendar.R;

public class TextViewActivity extends AppCompatActivity {

    private ImageView img;
    private RotateTextView test_rt1;
    private TextView tv_test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_view);
        initView();

        RotateTextView rotateTextView = findViewById(R.id.test_rt1);
        rotateTextView.setText("角标");

        Typeface fromAsset = Typeface.createFromAsset(getAssets(), "iconfont.ttf");
        tv_test.setTypeface(fromAsset);
    }

    private void initView() {
        img = (ImageView) findViewById(R.id.img);
        test_rt1 = (RotateTextView) findViewById(R.id.test_rt1);
        tv_test = (TextView) findViewById(R.id.tv_test);
    }
}
