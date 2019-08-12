package com.example.network;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;

public class NetBroadcastReceiver extends BroadcastReceiver {

    public NetEvent evevt = NetworkActivity.evevt;

    @Override
    public void onReceive(Context context, Intent intent) {

        // TODO Auto-generated method stub
        // 如果相等的话就说明网络状态发生了变化
        if (intent.getAction().equals(ConnectivityManager.CONNECTIVITY_ACTION)) {
            int netWorkState = NetUtil.getNetWorkState(context);
            // 接口回调传过去状态的类型
            evevt.onNetChange(netWorkState);
        }
    }

    public interface NetEvent{
        public void onNetChange(int netMobile);
    }
}
