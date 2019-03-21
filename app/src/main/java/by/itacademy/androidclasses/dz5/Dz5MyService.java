package by.itacademy.androidclasses.dz5;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Binder;
import android.os.IBinder;

import androidx.annotation.Nullable;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

public class Dz5MyService extends Service {

    private BroadcastReceiver wifiReceiver;
    private int wifiEnabled = WifiManager.WIFI_STATE_ENABLED;
    private int wifiDisabled = WifiManager.WIFI_STATE_DISABLED;

    private MyBinder binder = new MyBinder();

    public static final String WIFI_CONNECTION = "WIFI CONNECTION";
    public static final String WIFI_ACTION = "by.itacademy.androidclasses.dz5.WIFI_ACTION";

    @Override
    public void onCreate() {
        super.onCreate();

        wifiReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

                if (intent.getIntExtra(WifiManager.EXTRA_WIFI_STATE, -1) == wifiEnabled) {
                    sendMessage(true);
                }

                if (intent.getIntExtra(WifiManager.EXTRA_WIFI_STATE, -1) == wifiDisabled) {
                    sendMessage(false);
                }
            }
        };

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(WifiManager.WIFI_STATE_CHANGED_ACTION);

        this.registerReceiver(wifiReceiver, intentFilter);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(wifiReceiver);
    }

    public class MyBinder extends Binder {
        Dz5MyService getService() {
            return Dz5MyService.this;
        }
    }

    public boolean statusWifi() {
        ConnectivityManager connectivityManager = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();
        return activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
    }

    private void sendMessage(boolean connection) {
        Intent intentLocal = new Intent(WIFI_ACTION);
        intentLocal.putExtra(WIFI_CONNECTION, connection);
        LocalBroadcastManager.getInstance(Dz5MyService.this).sendBroadcast(intentLocal);
    }
}