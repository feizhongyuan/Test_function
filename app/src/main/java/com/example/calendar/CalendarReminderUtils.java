package com.example.calendar;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.provider.CalendarContract;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by fei .
 * Created by Date 2019/6/24 15:08
 */

public class CalendarReminderUtils {
    private static String CALENDER_URL = "content://com.android.calendar/calendars";
    private static String CALENDER_EVENT_URL = "content://com.android.calendar/events";
    private static String CALENDER_REMINDER_URL = "content://com.android.calendar/reminders";

    private static String CALENDARS_NAME = "maqueeliao";
    private static String CALENDARS_ACCOUNT_NAME = "MAQUEELIAO@maqueeliao.com";
    private static String CALENDARS_ACCOUNT_TYPE = "com.android.maqueeliao";
    private static String CALENDARS_DISPLAY_NAME = "麻雀e疗账户";

    private static String CALENDAR_ERROR = "Calendar_error";
    private static String EVENT_ERROR = "Event_error";
    private static String REMIND_ERROR = "Remind_error";

    /**
     * 检查是否已经添加了日历账户，如果没有添加先添加一个日历账户再查询
     * 获取账户成功返回账户id，否则返回-1
     */
    private static int checkAndAddCalendarAccount(Context context) {
        int oldId = checkCalendarAccount(context);
        if( oldId >= 0 ){
            return oldId;
        }else{
            long addId = addCalendarAccount(context);
            if (addId >= 0) {
                return checkCalendarAccount(context);
            } else {
                return -1;
            }
        }
    }
    /**
     * 检查是否存在现有账户，存在则返回账户id，否则返回-1
     */
    private static int checkCalendarAccount(Context context) {
        Cursor userCursor = context.getContentResolver().query(Uri.parse(CALENDER_URL), null, null, null, null);
        try {
            if (userCursor == null) { //查询返回空值
                return -1;
            }
            int count = userCursor.getCount();
            if (count > 0) { //存在现有账户，取第一个账户的id返回
                userCursor.moveToFirst();
                Log.e("TAG","------第一个账户ID："+userCursor.getInt(userCursor.getColumnIndex(CalendarContract.Calendars._ID)));
                return userCursor.getInt(userCursor.getColumnIndex(CalendarContract.Calendars._ID));
            } else {
                return -1;
            }
        } finally {
            if (userCursor != null) {
                userCursor.close();
            }
        }
    }

    /**
     * 添加日历账户，账户创建成功则返回账户id，否则返回-1
     */
    private static long addCalendarAccount(Context context) {
        TimeZone timeZone = TimeZone.getDefault();
        ContentValues value = new ContentValues();
        value.put(CalendarContract.Calendars.NAME, CALENDARS_NAME);
        value.put(CalendarContract.Calendars.ACCOUNT_NAME, CALENDARS_ACCOUNT_NAME);
        value.put(CalendarContract.Calendars.ACCOUNT_TYPE, CALENDARS_ACCOUNT_TYPE);
        value.put(CalendarContract.Calendars.CALENDAR_DISPLAY_NAME, CALENDARS_DISPLAY_NAME);
        value.put(CalendarContract.Calendars.VISIBLE, 1);
        value.put(CalendarContract.Calendars.CALENDAR_COLOR, Color.BLUE);
        value.put(CalendarContract.Calendars.CALENDAR_ACCESS_LEVEL, CalendarContract.Calendars.CAL_ACCESS_OWNER);
        value.put(CalendarContract.Calendars.SYNC_EVENTS, 1);
        value.put(CalendarContract.Calendars.CALENDAR_TIME_ZONE, timeZone.getID());
        value.put(CalendarContract.Calendars.OWNER_ACCOUNT, CALENDARS_ACCOUNT_NAME);
        value.put(CalendarContract.Calendars.CAN_ORGANIZER_RESPOND, 0);

        Uri calendarUri = Uri.parse(CALENDER_URL);
        calendarUri = calendarUri.buildUpon()
                .appendQueryParameter(CalendarContract.CALLER_IS_SYNCADAPTER, "true")
                .appendQueryParameter(CalendarContract.Calendars.ACCOUNT_NAME, CALENDARS_ACCOUNT_NAME)
                .appendQueryParameter(CalendarContract.Calendars.ACCOUNT_TYPE, CALENDARS_ACCOUNT_TYPE)
                .build();

        Uri result = context.getContentResolver().insert(calendarUri, value);
        long id = result == null ? -1 : ContentUris.parseId(result);
        return id;
    }
    /**
     * 添加日历事件
     */
    public static void addCalendarEvent(Context context, String title, String description, long reminderTime,String endtime, int repeats,onCalendarListener callback) {
        if (context == null) {
            return;
        }
        int calId = checkAndAddCalendarAccount(context); //获取日历账户的id
        if (calId < 0) { //获取账户id失败直接返回，添加日历事件失败
            if (null != callback){
                callback.onFaild(CALENDAR_ERROR + "获取账户id失败");
            }
            return;
        }
        Log.e("TAG","----------calId:"+calId);

        //添加日历事件
        Calendar mCalendar = Calendar.getInstance();
        mCalendar.setTimeInMillis(reminderTime);//设置开始时间
        long start = mCalendar.getTime().getTime();
        Log.e("time","-----start:"+start);
        mCalendar.setTimeInMillis(start + 60 * 1000);//设置终止时间，开始时间加1分钟
        long end = mCalendar.getTime().getTime();
        Log.e("time","-----end:"+end);
        ContentValues event = new ContentValues();
        event.put("title", title);
        event.put("description", description);
        event.put("calendar_id", calId); //插入账户的id
//        event.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, start);//重复事件使用这个属性
//        event.putExtra(CalendarContract.EXTRA_EVENT_END_TIME, end);
        event.put(CalendarContract.Events.DTSTART, start);
        event.put(CalendarContract.Events.DTEND, end);
        event.put(CalendarContract.Events.HAS_ALARM, 1);//设置有闹钟提醒
//        event.put(CalendarContract.Events.RRULE,"FREQ=DAILY;INTERVAL=1;UNTIL=20190710");//重复事件 按天重复，重复时间为每天
        String rrule = "FREQ=DAILY;INTERVAL="+repeats+";UNTIL="+endtime+"";
        event.put(CalendarContract.Events.RRULE,rrule);//重复事件 按天重复，重复时间为每天
        event.put(CalendarContract.Events.EVENT_TIMEZONE, TimeZone.getDefault().getID());//这个是时区，必须有
        Uri newEvent = context.getContentResolver().insert(Uri.parse(CALENDER_EVENT_URL), event); //添加事件


        String eventID = newEvent.getLastPathSegment();
        Log.e("TAG","eventID>>>>>>>>>>"+eventID);
        Log.e("TAG","newEvent>>>>>>>>>>"+newEvent);
        if (newEvent == null) { //添加日历事件失败直接返回
            if (null != callback){
                callback.onFaild(EVENT_ERROR + "添加日历事件失败");
            }
            return;
        }
        //事件提醒的设定
        ContentValues values = new ContentValues();
        values.put(CalendarContract.Reminders.EVENT_ID, ContentUris.parseId(newEvent));
        values.put(CalendarContract.Reminders.MINUTES, 1);// 提前previousDate天有提醒
        values.put(CalendarContract.Reminders.METHOD, CalendarContract.Reminders.METHOD_ALERT);
        Uri uri = context.getContentResolver().insert(Uri.parse(CALENDER_REMINDER_URL), values);
        if(uri == null) { //添加事件提醒失败直接返回
            if (null != callback){
                callback.onFaild(REMIND_ERROR + "添加事件提醒失败");
            }
            return;
        }
        if (null != callback){
            callback.onSuccess(String.valueOf(eventID));
            Toast.makeText(context, "添加成功---"+eventID, Toast.LENGTH_SHORT).show();
        }

    }

    /**
     * 删除日历事件   按标题删除
     * @param context
     * @param title     标题
     */
    public static void deleteCalendarEvent(Context context,String title) {
        if (context == null) {
            return;
        }
        Cursor eventCursor = context.getContentResolver().query(Uri.parse(CALENDER_EVENT_URL), null, null, null, null);
        try {
            if (eventCursor == null) { //查询返回空值
                return;
            }
            if (eventCursor.getCount() > 0) {
                //遍历所有事件，找到title跟需要查询的title一样的项
                for (eventCursor.moveToFirst(); !eventCursor.isAfterLast(); eventCursor.moveToNext()) {
                    String eventTitle = eventCursor.getString(eventCursor.getColumnIndex("title"));
                    if (!TextUtils.isEmpty(title) && title.equals(eventTitle)) {
                        int id = eventCursor.getInt(eventCursor.getColumnIndex(CalendarContract.Calendars._ID));//取得id
                        Log.e("TAG","id>>>>>>>>>>"+id);
                        Uri deleteUri = ContentUris.withAppendedId(Uri.parse(CALENDER_EVENT_URL), id);
                        Log.e("TAG","deleteUri>>>>>>>>>>"+deleteUri);
                        int rows = context.getContentResolver().delete(deleteUri, null, null);
                        if (rows == -1) { //事件删除失败
                            return;
                        }
                    }
                }
            }
        } finally {
            if (eventCursor != null) {
                eventCursor.close();
            }
        }
        Toast.makeText(context, "删除成功", Toast.LENGTH_SHORT).show();
    }

    /**
     * 删除日历事件2      按标题和备注进行删除
     * @param context
     * @param title     标题
     * @param notes     备注
     */
    public static void deleteCalendarEvent2(Context context,String title,String notes) {
        if (context == null) {
            return;
        }
        Cursor eventCursor = context.getContentResolver().query(Uri.parse(CALENDER_EVENT_URL), null, null, null, null);
        try {
            if (eventCursor == null) { //查询返回空值
                return;
            }
            if (eventCursor.getCount() > 0) {
                //遍历所有事件，找到title跟需要查询的title一样的项
                for (eventCursor.moveToFirst(); !eventCursor.isAfterLast(); eventCursor.moveToNext()) {
                    String eventTitle = eventCursor.getString(eventCursor.getColumnIndex("title"));
                    if (!TextUtils.isEmpty(title) && title.equals(eventTitle)) {
//                        int id = eventCursor.getInt(eventCursor.getColumnIndex(CalendarContract.Calendars._ID));//取得id
//                        Uri deleteUri = ContentUris.withAppendedId(Uri.parse(CALENDER_EVENT_URL), id);
//                        int rows = context.getContentResolver().delete(deleteUri, null, null);
                        String selection = "((" + CalendarContract.Events.TITLE + " = ?) AND (" + CalendarContract.Events.DESCRIPTION + " = ?))";
                        String[] selectionArgs = new String[]{title, notes};
                        int rows = context.getContentResolver().delete(Uri.parse(CALENDER_EVENT_URL), selection, selectionArgs);
                        if (rows == -1) { //事件删除失败
                            return;
                        }
                    }
                }
            }
        } finally {
            if (eventCursor != null) {
                eventCursor.close();
            }
        }
        Toast.makeText(context, "删除成功----"+title, Toast.LENGTH_SHORT).show();

    }

    /**
     * 删除日历事件3  按标题和id进行删除
     * @param context
     * @param title     标题
     * @param eventId     id
     * @param callback  回调
     */
    public static void deleteCalendarEvent3(Context context,String title,String eventId,onCalendarListener callback) {
        if (context == null) {
            return;
        }
        Cursor eventCursor = context.getContentResolver().query(Uri.parse(CALENDER_EVENT_URL), null, null, null, null);
        try {
            if (eventCursor == null) { //查询返回空值
                return;
            }
            if (eventCursor.getCount() > 0) {
                eventCursor.moveToFirst();
                int anInt = eventCursor.getInt(eventCursor.getColumnIndex(CalendarContract.Calendars._ID));
                Log.e("TAG","----------anInt:"+anInt);
                    //遍历所有事件，找到title跟需要查询的title一样的项
                    for (eventCursor.moveToFirst(); !eventCursor.isAfterLast(); eventCursor.moveToNext()) {
                        String eventTitle = eventCursor.getString(eventCursor.getColumnIndex("title"));
                        if (!TextUtils.isEmpty(title) && title.equals(eventTitle)) {
                            int id = eventCursor.getInt(eventCursor.getColumnIndex(CalendarContract.Calendars._ID));//取得id
                            Log.e("TAG","delete_id>>>>>>>>>>"+id);
//                            Uri deleteUri = ContentUris.withAppendedId(Uri.parse(CALENDER_EVENT_URL), id);
//                            Log.e("TAG","deleteUri>>>>>>>>>>"+deleteUri);
//                            rows = context.getContentResolver().delete(deleteUri, null, null);

                            String selection = "((" + CalendarContract.Events.TITLE + " = ?) AND (" + CalendarContract.Events._ID + " = ?))";
                            String[] selectionArgs = new String[]{title, eventId};
                            int rows = context.getContentResolver().delete(Uri.parse(CALENDER_EVENT_URL), selection, selectionArgs);
                            if (rows == -1) { //事件删除失败
                                callback.onFaild("删除日程失败");
                                return;
                            }
                        }
                    }
                    if (null != callback){
//                        if (title != null){
                            callback.onSuccess("删除日程成功"+ eventId);
//                        }else {
//                            callback.onSuccess("已删除成功，请勿重复删除");
//                        }
                    }
            }else {
                Toast.makeText(context, "当前日历账户不匹配无法删除", Toast.LENGTH_SHORT).show();
            }
        } finally {
            if (eventCursor != null) {
                eventCursor.close();
            }
        }
    }

    /**
     * 删除日历事件4
     * @param context
     * @param title     标题
     * @param eventID   id
     * @param callback  回调
     */
    public static void deleteCalendarEvent4(Context context,String title,String eventID,onCalendarListener callback) {
        if (context == null) {
            return;
        }
        Cursor eventCursor = context.getContentResolver().query(Uri.parse(CALENDER_EVENT_URL), null, null, null, null);
        try {
            if (eventCursor == null) { //查询返回空值
                return;
            }
            if (eventCursor.getCount() > 0) {
                //遍历所有事件，找到title跟需要查询的title一样的项
                for (eventCursor.moveToFirst(); !eventCursor.isAfterLast(); eventCursor.moveToNext()) {
                    String eventTitle = eventCursor.getString(eventCursor.getColumnIndex("title"));
                    if (!TextUtils.isEmpty(title) && title.equals(eventTitle)) {
                        int id = eventCursor.getInt(eventCursor.getColumnIndex(CalendarContract.Calendars._ID));//取得id
//                        if (!TextUtils.isEmpty(eventID) && eventID.equals(id)){

                        Log.e("DEL","id>>>>>>>>>>"+id);
                        long l = Long.parseLong(eventID);
                        Log.e("DEL","eventID>>>>>>>>>>"+l);
                        Uri deleteUri = ContentUris.withAppendedId(Uri.parse(CALENDER_EVENT_URL), l);
                        Log.e("DEL","deleteUri>>>>>>>>>>"+deleteUri);
                        int rows = context.getContentResolver().delete(deleteUri, null, null);
                        if (rows == -1) { //事件删除失败
                            callback.onFaild("删除失败");
                            return;
                        }
//                        }
                    }

                }
                if (null != callback){
//                        if (title != null){
                    callback.onSuccess("删除日程成功");
//                        }else {
//                            callback.onSuccess("已删除成功，请勿重复删除");
//                        }
                }
            }else {
                    Toast.makeText(context, "当前日历账户不匹配无法删除", Toast.LENGTH_SHORT).show();
            }
        } finally {
            if (eventCursor != null) {
                eventCursor.close();
            }
        }
        Toast.makeText(context, "删除成功", Toast.LENGTH_SHORT).show();
    }


    //    查询日历日程
    static JSONArray getCalendarEvent(Context context) {
        String startTime = "";
        String endTime = "";
        String eventTitle = "";
        String description = "";
        String location = "";
        String _id= "";

        JSONArray arr = new JSONArray();
        Cursor eventCursor = context.getContentResolver().query(Uri.parse(CALENDER_EVENT_URL), null, null, null, null);
        while (eventCursor.moveToNext()) {
            JSONObject json = new JSONObject();
            eventTitle = eventCursor.getString(eventCursor.getColumnIndex("title"));
            description = eventCursor.getString(eventCursor.getColumnIndex("description"));
            location = eventCursor.getString(eventCursor.getColumnIndex("eventLocation"));
            _id = eventCursor.getString(eventCursor.getColumnIndex("_id"));

            String format = "yyyy-MM-dd HH:mm:ss";
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            String dtstart = eventCursor.getString(eventCursor.getColumnIndex("dtstart"));
            String dtend = eventCursor.getString(eventCursor.getColumnIndex("dtend"));
            startTime = sdf.format(new Date(new Long(dtstart)));
            endTime = sdf.format(new Date(new Long(dtend)));

            try {
                json.put("eventTitle", eventTitle);
                json.put("description", description);
                json.put("location", location);
                json.put("startTime", startTime);
                json.put("endTime", endTime);
                json.put("_id",_id);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            arr.put(json);
        }
        return arr;
    }

    public static interface onCalendarListener{
        void onSuccess(String reminderId);
        void onFaild(String error_code);
    }

}
