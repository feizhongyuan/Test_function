package com.example.network;

import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Settings;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.calendar.MainActivity;
import com.example.calendar.R;

public class NetworkActivity extends FragmentActivity implements NetBroadcastReceiver.NetEvent{
    public static NetBroadcastReceiver.NetEvent evevt;
    /**
     * 网络类型
     */
    private int netMobile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network);
        evevt = this;
        boolean b = inspectNet();
        if (b){
            Toast.makeText(this, "当前有网络", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(NetworkActivity.this, "温馨提示:请检查网络连接!", Toast.LENGTH_SHORT).show();
            NetWorkStatus();
        }

        if (b){
            Toast.makeText(this, "当前有网络", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 初始化时判断有没有网络
     */
    public boolean inspectNet() {
        netMobile = NetUtil.getNetWorkState(NetworkActivity.this);
         if (netMobile == 1) {
             Log.e("NetWork","inspectNet：连接wifi");
         } else if (netMobile == 0) {
             Log.e("NetWork","inspectNet：连接移动数据");
         } else if (netMobile == -1) {
             Log.e("NetWork","inspectNet：当前没有网络");
         }
        return isNetConnect();

    }
    /**
     * 网络变化之后的类型
     */
    @Override
    public void onNetChange(int netMobile) {
        // TODO Auto-generated method stub
        this.netMobile = netMobile;
        isNetConnect();
        
    }
    /**
     * 判断有无网络 。
     *
     * @return true 有网, false 没有网络.
     */
    public boolean isNetConnect() {
        if (netMobile == 1) {
            return true;
        } else if (netMobile == 0) {
            return true;
        } else if (netMobile == -1) {
            return false;
        }
        return false;
    }

    public static boolean isConnect(Context context) {
        // 获取手机所有连接管理对象（包括对 Wi-Fi,net等连接的管理）
        ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            // 获取网络连接管理的对象
            NetworkInfo info = connectivity.getActiveNetworkInfo();
            if (info != null) {
                // 判断当前网络是否已经连接
                if (info.getState() == NetworkInfo.State.CONNECTED) {
                    return true;
                }
            }
        }
        return false;
    }

    //打开网络设置
    private void NetWorkStatus() {

        ConnectivityManager connectivity = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        connectivity.getActiveNetworkInfo();


        new AlertDialog.Builder(this)
                .setTitle("没有可用的网络")
                .setMessage("是否对网络进行设置")
                .setPositiveButton("是", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
//                        Intent mIntent = new Intent("/");
//                        ComponentName comp = new ComponentName( "com.android.settings", "com.android.settings.WirelessSettings");
//                        mIntent.setComponent(comp);
//                        mIntent.setAction("android.intent.action.VIEW");
//                        startActivityForResult(mIntent,0); // 如果在设置完成后需要再次进行操作，可以重写操作代码，在这里不再重写

//提示设置网络
// 整体:
// startActivity(new Intent(Settings.ACTION_WIRELESS_SETTINGS));
// WIFI:
 startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
// 流量:
// startActivity(new Intent(android.provider.Settings.ACTION_DATA_ROAMING_SETTINGS));

                    }
                })
                .setNegativeButton("否", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        NetworkActivity.this.finish();
                    }
                })
                .show();

    }
}

