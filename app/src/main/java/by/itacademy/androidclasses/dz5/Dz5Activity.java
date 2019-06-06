package by.itacademy.androidclasses.dz5;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import by.itacademy.androidclasses.R;

public class Dz5Activity extends Activity {

    private ServiceConnection serviceConnection;
    private BroadcastReceiver localWifiReceiver;
    private Dz5MyService myService;

    private TextView wifiTextView;
    private String wifiOn;
    private String wifiOff;
    private int backgroundWifiOn;
    private int colorWhite;
    private int colorBlack;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dz5);

        wifiTextView = findViewById(R.id.textViewWifi);
        wifiOn = getResources().getString(R.string.textViewOnWifi);
        wifiOff = getResources().getString(R.string.textViewOffWifi);
        backgroundWifiOn = getResources().getColor(R.color.colorBackgroundWifiOn);
        colorWhite = getResources().getColor(R.color.colorWhite);
        colorBlack = getResources().getColor(R.color.colorBlack);

        serviceConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {

                myService = ((Dz5MyService.MyBinder) service).getService();

                if (myService.statusWifi()) {
                    setWifiTextViewOn();
                } else {
                    setWifiTextViewOff();
                }
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
            }
        };

        localWifiReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

                if (intent.getBooleanExtra("WIFI CONNECTION", false)) {
                    setWifiTextViewOn();
                } else {
                    setWifiTextViewOff();
                }
            }
        };
    }

    @Override
    protected void onStart() {
        super.onStart();

        Intent intent = new Intent(this, Dz5MyService.class);
        bindService(intent, serviceConnection, BIND_AUTO_CREATE);

        LocalBroadcastManager.getInstance(this).registerReceiver(localWifiReceiver,
                new IntentFilter(Dz5MyService.WIFI_ACTION));
    }

    @Override
    protected void onStop() {
        super.onStop();

        LocalBroadcastManager.getInstance(this).unregisterReceiver(localWifiReceiver);
        unbindService(serviceConnection);
    }

    private void setWifiTextViewOn() {
        wifiTextView.setText(wifiOn);
        wifiTextView.setTextColor(colorWhite);
        wifiTextView.setBackgroundColor(backgroundWifiOn);
    }

    private void setWifiTextViewOff() {
        wifiTextView.setText(wifiOff);
        wifiTextView.setTextColor(colorBlack);
        wifiTextView.setBackgroundColor(colorWhite);
    }
}