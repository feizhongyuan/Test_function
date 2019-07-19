package com.example.calendar;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.json.JSONArray;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mBt_add;
    private Button mBt_delete;

    private String title = "用药提醒";
    private String startTime = "2019-7-4 20:10";//开始时间不能小于当前时间
    private String endTime = "2019-7-11 18:20";
    private String notes = "备注";
    private String repeats = "2";//1 设置隔天  2 设置每天

    private String reminder_id;
    private String msg = "success";

    private String year;
    private String month;
    private String day;
    private String hour;
    private String minute;



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
    private Button mBt_delete_title;
    private Button mBt_all;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT >= 23) {
            initPermission();
        }
        initView();

        initCalendar();
        dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");


    }

    private void initTime() {
        //      获取日期间隔天数
//        int sumDays = getSumDays(startTime, endTime);

        String end = getEndTrim(endTime);
        Log.e("TAG", "最终时间-----" + end);

        final String numLargeLetter = getNumLargeLetter(4);
        Log.e("TAG", "-------随机数：" + numLargeLetter);


        long firstTime = 0;
        try {
            firstTime = dateFormat.parse(startTime).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        long l = System.currentTimeMillis();
        Log.e("TAG", "当前时间------" + l);
        Log.e("TAG", "设置时间戳------" + firstTime / 1000);

        if ("1".equals(repeats)) {
//      设置隔天
            CalendarReminderUtils.addCalendarEvent(this, title, notes,
                    firstTime, end, 2, new CalendarReminderUtils.onCalendarListener() {
                        @Override
                        public void onSuccess(String reminderId) {
                            reminder_id = numLargeLetter + reminderId;
                            Log.e("TAG", "success---" + reminder_id);
                        }

                        @Override
                        public void onFaild(String error_code) {
                            Log.e("TAG", error_code);
                        }
                    });

        } else if ("2".equals(repeats)) {
//        设置每天
            CalendarReminderUtils.addCalendarEvent(this, title, notes,
                    firstTime, end, 1, new CalendarReminderUtils.onCalendarListener() {
                        @Override
                        public void onSuccess(String reminderId) {
                            reminder_id = numLargeLetter + reminderId;
                            Log.e("TAG", "success---" + reminder_id);
                        }

                        @Override
                        public void onFaild(String error_code) {
                            Log.e("TAG", error_code);
                        }
                    });
        }

    }

    private String getEndTrim(String endTime) {
        String set_year = endTime.toString().substring(0, 4);
        String set_month = endTime.toString().substring(5, 6);
        String set_day = endTime.toString().substring(7, 9);

        String set_hour = endTime.toString().substring(10, 12);
        String set_minute = endTime.toString().substring(13);

        Log.e("TIME", "set_year-----" + set_year);
        Log.e("TIME", "set_month-----" + set_month);
        Log.e("TIME", "set_day-----" + set_day);
        Log.e("TIME", "set_hour-----" + set_hour);
        Log.e("TIME", "set_minute-----" + set_minute);

        if (set_month.length() ==1){
            set_month = "0"+set_month;
        }else if (set_day.length() == 1){
            set_day = "0"+set_day;
        }

        String end = set_year + set_month + set_day;
        return end.trim();
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
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
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
    }


    @SuppressLint("WrongConstant")
    private void initCalendar() {
//          获取当前时间
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        year = String.valueOf(calendar.get(Calendar.YEAR));
        month = String.valueOf(calendar.get(Calendar.MONDAY) + 1);
        day = String.valueOf(calendar.get(Calendar.DATE));
        if (calendar.get(Calendar.AM_PM) == 0) {//24小时制
            hour = String.valueOf(calendar.get(Calendar.HOUR));
        } else {
            hour = String.valueOf(calendar.get(Calendar.HOUR) + 12);
        }
        minute = String.valueOf(calendar.get(Calendar.MINUTE));

        Log.e("TAG", year + "-" + month + "-" + day + " " + hour + ":" + minute);

    }


    private void initView() {
        mBt_add = (Button) findViewById(R.id.mBt_add);
        mBt_delete = (Button) findViewById(R.id.mBt_delete);

        mBt_add.setOnClickListener(this);
        mBt_delete.setOnClickListener(this);
        mBt_delete_title = (Button) findViewById(R.id.mBt_delete_title);
        mBt_delete_title.setOnClickListener(this);
        mBt_all = (Button) findViewById(R.id.mBt_all);
        mBt_all.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mBt_add:
                try {
                    if (dateFormat.parse(startTime).getTime() > System.currentTimeMillis()) {
//                      初始化日程提醒
                        initTime();
                    } else {
                        Toast.makeText(this, "设置时间必须大于当前时间", Toast.LENGTH_SHORT).show();
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.mBt_delete:
//                按标题全部删除
//                CalendarReminderUtils.deleteCalendarEvent2(this,title,notes);
                if (null != reminder_id) {
                    String reminder = reminder_id.substring(4);
                    Log.e("TAG", "-----初始id：" + reminder_id);
                    Log.e("TAG", "-----最终id：" + reminder);
//                    按ID和title删除
                    CalendarReminderUtils.deleteCalendarEvent3(this, title, reminder, new CalendarReminderUtils.onCalendarListener() {
                        @Override
                        public void onSuccess(String success) {
                            Toast.makeText(MainActivity.this, success, Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onFaild(String error_code) {
                            Toast.makeText(MainActivity.this, error_code, Toast.LENGTH_SHORT).show();
                        }
                    });
                } else {
//                    Toast.makeText(this, "请先添加提醒事件", Toast.LENGTH_SHORT).show();
                    Log.e("TAG", "请先添加提醒事件");
                }
                break;
            case R.id.mBt_delete_title:
//                按标题和备注删除
                CalendarReminderUtils.deleteCalendarEvent2(this, title, notes);
                break;
            case R.id.mBt_all:
                JSONArray calendarEvent = CalendarReminderUtils.getCalendarEvent(this);
                Log.e("TAG","All-----"+calendarEvent.toString());
                break;
            default:
            break;
        }
    }

    /**
     * 数字与大写字母混编字符串
     *
     * @param size
     * @return
     */
    public static String getNumLargeLetter(int size) {
        StringBuffer buffer = new StringBuffer();
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            if (random.nextInt(2) % 2 == 0) {//字母
                buffer.append((char) (random.nextInt(27) + 'A'));
            } else {//数字
                buffer.append(random.nextInt(10));
            }
        }
        return buffer.toString();
    }

    //        int sum = 0;
//        int sum1 = 0;
//        int sum2 = 0;
//        for (int i = 0; i < sumDays; i++) {
//            if (repeats.equals("1")) {//设置隔天
//                //当前日期是奇数还是偶数
//                if (Integer.valueOf(set_day.trim()) % 2 == 0) {
//                    if (i % 2 == 0) {//按日期的第一天开始算隔天
//                        Log.e("TAG", "----" + i);
//                        long time = 0;
//                        try {
//                            time = dateFormat.parse(startTime).getTime();
//                        } catch (ParseException e) {
//                            e.printStackTrace();
//                        }
//                        long twoDay = 1000 * 60 * 60 * 24;
//                        long days = time + twoDay * (i + 1);
//                        Log.e("TAG", "隔天------" + days/ 1000);
//
//                        sum++;
//                    }
//                } else if (Integer.parseInt(set_day) % 2 == 1) {
//                    if (i % 2 == 0) {//按日期的第一天开始算隔天
//                        Log.e("TAG","----"+i);
//                            setCalendar("用药提醒", "备注");
//                        long time = 0;
//                        try {
//                            time = dateFormat.parse(startTime).getTime();
//                        } catch (ParseException e) {
//                            e.printStackTrace();
//                        }
//                        long twoDay = 1000 * 60 * 60 * 24;
//                        long days = time + twoDay * (i + 1) ;
//                        Log.e("TAG", "隔天------" + days/ 1000);
//                        CalendarReminderUtils.addCalendarEvent(this, title, notes,
//                                days, 1, new CalendarReminderUtils.onCalendarListener() {
//                                    @Override
//                                    public void onSuccess(String reminderId) {
//                                        reminder_id = numLargeLetter + reminderId;
//                                        Log.e("TAG", "success---" + reminder_id);
//                                    }
//
//                                    @Override
//                                    public void onFaild(String error_code) {
//                                        Log.e("TAG", error_code);
//                                    }
//                                });
//                        sum1++;
//                    }
//                }
//
//
//            } else if (repeats.equals("2")) {//设置每天
//                setCalendar("用药提醒", "备注");
//                long time = 0;
//                try {
//                    time = dateFormat.parse(startTime).getTime();
//                } catch (ParseException e) {
//                    e.printStackTrace();
//                }
//                long oneDay = 1000 * 60 * 60 * 24;
//                long days = time + oneDay * (i + 1) ;
//                Log.e("TAG", "每天------" + days/ 1000);
//
//                sum2++; }
//
//        }

//        Log.e("TAG", "奇数添加了" + sum + "条隔天数据");
//        Log.e("TAG", "偶数添加了" + sum1 + "条隔天数据");
//        Log.e("TAG", "添加了" + sum2 + "条每天数据");
}
