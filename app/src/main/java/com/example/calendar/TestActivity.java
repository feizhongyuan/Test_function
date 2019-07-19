package com.example.calendar;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class TestActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_add;
    private Button btn_delete;

    private String startTime = "2019-7-2 18:20";

    String[] permissions = new String[]{
            Manifest.permission.READ_CALENDAR,
            Manifest.permission.WRITE_CALENDAR,
            Manifest.permission.SET_ALARM,
//            Manifest.permission.VIBRATE,
//            Manifest.permission.WAKE_LOCK,
    };

    // 声明一个集合，在后面的代码中用来存储用户拒绝授权的权
    List<String> mPermissionList = new ArrayList<>();
    private SimpleDateFormat dateFormat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        initPermission();

        dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

        initView();
    }

    private void initPermission() {
        for (int i = 0; i < permissions.length; i++) {
            if (ContextCompat.checkSelfPermission(this, permissions[i]) != PackageManager.PERMISSION_GRANTED) {
                mPermissionList.add(permissions[i]);
            }
        }
        if (mPermissionList.isEmpty()) {//未授予的权限为空，表示都授予了
            Toast.makeText(this, "已经授权", Toast.LENGTH_LONG).show();
        } else {//请求权限方法
            String[] permissions = mPermissionList.toArray(new String[mPermissionList.size()]);//将List转为数组
            ActivityCompat.requestPermissions(this, permissions, 2);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 1){
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this, "权限已申请", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(this, "权限已取消", Toast.LENGTH_SHORT).show();
            }
        }else if (requestCode == 2){
            for (int i = 0; i < grantResults.length; i++) {
                if (grantResults[i] != PackageManager.PERMISSION_GRANTED){
//                    判断是否禁止勾选后不再询问
                    boolean showRequestPermission = ActivityCompat.shouldShowRequestPermissionRationale(this, permissions[i]);
                    if (showRequestPermission){
                        Toast.makeText(this, "权限未申请", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }
        
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        
    }

    private void initView() {

        btn_add = (Button) findViewById(R.id.btn_add);
        btn_add.setOnClickListener(this);
        btn_delete = (Button) findViewById(R.id.btn_delete);
        btn_delete.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_add:
                for (int i = 0; i < 5; i++) {
                    long time = 0;
                    try {
                        time = dateFormat.parse(startTime).getTime();
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    long oneDay = 1000 * 60 * 60 * 24;
                    long days = (time + oneDay * (i + 1))/1000;
                    Log.e("TAG","每天------"+days);

                    CalendarReminderUtils.addCalendarEvent(this, "提醒", "备注",
                            days, "20190710",1, new CalendarReminderUtils.onCalendarListener() {
                                @Override
                                public void onSuccess(String reminderId) {
                                    Toast.makeText(TestActivity.this, "添加日程成功"+reminderId, Toast.LENGTH_SHORT).show();
                                }

                                @Override
                                public void onFaild(String error_code) {
                                    Toast.makeText(TestActivity.this, ""+error_code, Toast.LENGTH_SHORT).show();
                                }
                            });
                }

                break;
            case R.id.btn_delete:
                CalendarReminderUtils.deleteCalendarEvent2(this,"提醒","备注");
                break;
                default:
                    break;
        }
    }
}
