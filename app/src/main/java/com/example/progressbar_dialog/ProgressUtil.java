package com.example.progressbar_dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.calendar.R;

/**
 * Created by fei .
 * Created by Date 2019/7/31 19:55
 * 耗时对话框工具类
 */

public class ProgressUtil {
    private static AlertDialog mAlertDialog;
    private static ProgressBar progressBar;
    private static TextView tvTip;

    /**
     * 弹出耗时对话框
     *
     * @param context
     */
    public static void showProgressDialog(Context context) {
        if (mAlertDialog == null) {
            mAlertDialog = new AlertDialog.Builder(context, R.style.CustomProgressDialog).create();
        }

        View loadView = LayoutInflater.from(context).inflate(R.layout.custom_prograssbar, null);
        progressBar = loadView.findViewById(R.id.progressBar);
        tvTip = loadView.findViewById(R.id.tvTip);
        progressBar.setMax(100);
        mAlertDialog.setView(loadView, 0, 0, 0, 0);
        mAlertDialog.setCanceledOnTouchOutside(false);

        TextView tvTip = loadView.findViewById(R.id.tvTip);
        tvTip.setText("加载中...");

        mAlertDialog.show();
    }

    public static void showProgressDialog(Context context, String tip) {
        if (TextUtils.isEmpty(tip)) {
            tip = "加载中...";
        }

        if (mAlertDialog == null) {
            mAlertDialog = new AlertDialog.Builder(context, R.style.CustomProgressDialog).create();
        }

        View loadView = LayoutInflater.from(context).inflate(R.layout.custom_prograssbar, null);
        mAlertDialog.setView(loadView, 0, 0, 0, 0);
        mAlertDialog.setCanceledOnTouchOutside(false);

        TextView tvTip = loadView.findViewById(R.id.tvTip);
        tvTip.setText(tip);

        mAlertDialog.show();
    }

    /**
     * 隐藏耗时对话框
     */
    public static void dismiss() {
        if (mAlertDialog != null && mAlertDialog.isShowing()) {
            mAlertDialog.dismiss();
        }
    }

}
