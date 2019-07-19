package com.example.demo;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.calendar.MainActivity;
import com.example.calendar.R;

public class DemoActivity extends AppCompatActivity {

    private ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);
        initView();
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showClear(DemoActivity.this,"内容展示--------");
            }
        });
    }

    private void initView() {
        img = (ImageView) findViewById(R.id.img);
    }

    /**
     * 显示打印弹窗
     *
     * @param activity
     * @return
     */
    public AlertDialog showClear(final Activity activity,String content) {
        AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(this, R.style.Theme_Transparent));//修改这里
        View view = View.inflate(activity, R.layout.dialog_print, null);
        TextView mText = view.findViewById(R.id.mText);
        ImageView img_back = view.findViewById(R.id.img_back);
        mText.setText(content);
        builder.setView(view);
        builder.setCancelable(true);
        final AlertDialog dialog = builder.create();
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.show();
        return dialog;

    }
}
