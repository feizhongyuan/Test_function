package com.example.test;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.calendar.R;

import org.json.JSONArray;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import static com.example.test.CalendarReminderUtils1.getCalendarEvent;

public class Test1Activity extends AppCompatActivity implements View.OnClickListener {

    private Button add;
    private Button del;

    private String title = "用药提醒";
    private String startTime = "2019-7-10 18:20";//开始时间不能小于当前时间
    private String endTime = "2019-7-20 18:20";
    private String notes = "备注";
    private String repeats = "2";//1 设置隔天  2 设置每天
    private SimpleDateFormat dateFormat;
    private SimpleDateFormat dateFormat1;

    private String reminder_id;

    String[] permissions = new String[]{
            Manifest.permission.READ_CALENDAR,
            Manifest.permission.WRITE_CALENDAR,
            Manifest.permission.SET_ALARM,
//            Manifest.permission.VIBRATE,
//            Manifest.permission.WAKE_LOCK,
    };

    // 声明一个集合，在后面的代码中用来存储用户拒绝授权的权
    List<String> mPermissionList = new ArrayList<>();
    private Button add_two;
    private Button del_two;
    private String dateString;
    private String end;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test1);
        initPermission();
        initView();
        dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        dateFormat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm");


    }

    private String getEnd(String endTime) {
        String set_year = endTime.toString().substring(0, 4);
        String set_month = endTime.toString().substring(5, 6);
        String set_day = endTime.toString().substring(7, 9);

        if (set_month.length() ==1){
            set_month = "0"+set_month;
        }else if (set_day.length() == 1){
            set_day = "0"+set_day;
        }

        String end = set_year + set_month + set_day;

        Log.e("TAG", "set_year-----" + set_year);
        Log.e("TAG", "set_month-----" + set_month);
        Log.e("TAG", "set_day-----" + set_day);

        Log.e("TAG","最终时间---"+end);
        return end.trim();
    }

    private void initView() {
        add = (Button) findViewById(R.id.add);
        del = (Button) findViewById(R.id.del);

        add.setOnClickListener(this);
        del.setOnClickListener(this);


        add_two = (Button) findViewById(R.id.add_two);
        add_two.setOnClickListener(this);
        del_two = (Button) findViewById(R.id.del_two);
        del_two.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add:
                long days = 0;

                int sumDays = getSumDays(startTime, endTime);
                for (int i = 0; i < sumDays; i++) {
                    long time = 0;
                    try {
                        time = dateFormat.parse(startTime).getTime();
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    long oneDay = 1000 * 60 * 60 * 24;
                    days = time + oneDay * (i + 1);
                }

                long time = 0;
                try {
                    time = dateFormat.parse(startTime).getTime();

                } catch (ParseException e) {
                    e.printStackTrace();
                }
//                long oneDay = 1000 * 60 * 60 * 24;
//                days = time + oneDay;

                long l = System.currentTimeMillis();
                Log.e("TAG", "当前时间------" + l);
                Log.e("TAG", "每天------" + time/1000);

                end = getEnd(endTime);


                CalendarReminderUtils1.addCalendarEvent(this, title, notes, time, end,1, new CalendarReminderUtils1.onCalendarListener() {
                    @Override
                    public void onSuccess(String reminderId) {
                        reminder_id = reminderId;
                        Log.e("TAG", "onSuccess----------" + reminderId);
                    }

                    @Override
                    public void onFaild(String error_code) {
                        Log.e("TAG", "onFaild---------" + error_code);
                    }
                });
                break;
            case R.id.del:
//                CalendarReminderUtils1.deleteCalendarEvent2(this, "用药提醒", "备注");

                CalendarReminderUtils1.deleteCalendarEvent4(this, "用药提醒", reminder_id, new CalendarReminderUtils1.onCalendarListener() {
                    @Override
                    public void onSuccess(String reminderId) {
                        Toast.makeText(Test1Activity.this, "删除成功"+reminderId, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFaild(String error_code) {

                    }
                });
                break;
            case R.id.add_two:
                JSONArray calendarEvent = getCalendarEvent(this);

                Log.e("TAG",calendarEvent.toString());

//                弃用
//                Calendar calendar = Calendar.getInstance();
//                // Here we set a start time of Tuesday the 17th, 6pm
//                calendar.set(2019, Calendar.JULY, 05, 18, 0, 0);
//                calendar.setTimeZone(TimeZone.getDefault());
//
//                long start = calendar.getTimeInMillis();
//                // add three hours in milliseconds to get end time of 9pm
//                long end = calendar.getTimeInMillis() + 3 * 60 * 60 * 1000;
//
//                Intent intent = new Intent(Intent.ACTION_INSERT)
//                        .setData(CalendarContract.Events.CONTENT_URI)
//                        .setType("vnd.android.cursor.item/event")
////                        .setType("*/*")//设置类型
//                        .putExtra(CalendarContract.Events.TITLE, "用药提醒")//标题
//                        .putExtra(CalendarContract.Events.DESCRIPTION, "描述")//描述
//                        .putExtra(CalendarContract.Events.EVENT_LOCATION, "地点")//地点
//                        .putExtra(CalendarContract.Events.RRULE, "FREQ=DAILY;INTERVAL=1;UNTIL=201907010")//重复规则
//                        // to specify start time use "beginTime" instead of "dtstart"
//                        //.putExtra(Events.DTSTART, calendar.getTimeInMillis())
//                        .putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, start)//事件开始时间
//                        .putExtra(CalendarContract.EXTRA_EVENT_END_TIME, end)//事件结束时间
//                        // if you want to go from 6pm to 9pm, don't specify all day
//                        //.putExtra(CalendarContract.EXTRA_EVENT_ALL_DAY, true)
//                        .putExtra(CalendarContract.Events.HAS_ALARM, 1)
//                        .putExtra(CalendarContract.Events.AVAILABILITY, CalendarContract.Events.AVAILABILITY_BUSY);//可用性
//                this.startActivity(intent);
                break;
            case R.id.del_two:
                CalendarReminderUtils1.deleteCalendarEvent2(this,"用药提醒","备注");
                break;

            default:
                break;
        }
    }

    private int getSumDays(String start, String end) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        long diff = 0L;
        try {
            Date date_start = dateFormat.parse(start);
            Date date_end = dateFormat.parse(end);

            diff = date_end.getTime() - date_start.getTime();

        } catch (Exception e) {
            e.printStackTrace();
        }
        long days = diff / (1000 * 60 * 60 * 24);//计算两个日期间的总天数
        Log.e("TAG", "总天数:--------" + days);

        return (int) days;
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
        if (requestCode == 1) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "权限已申请", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "权限已取消", Toast.LENGTH_SHORT).show();
            }
        } else if (requestCode == 2) {
            for (int i = 0; i < grantResults.length; i++) {
                if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                    //判断是否勾选禁止后不再询问
                    boolean showRequestPermission = ActivityCompat.shouldShowRequestPermissionRationale(this, permissions[i]);
                    if (showRequestPermission) {
                        Toast.makeText(this, "权限未申请", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    /**
     * 获取现在时间
     *
     * @return 返回时间类型 yyyyMMdd
     */
    public static String getNowDate(String endTime) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        String dateString = null;
        try {
            long time = dateFormat.parse(endTime).getTime();
            dateString = dateFormat.format(time);
        } catch (Exception e) {
            e.printStackTrace();
        }
        ParsePosition pos = new ParsePosition(8);
        return dateString;
    }
}
