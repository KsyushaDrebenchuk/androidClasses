package by.itacademy.androidclasses;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import by.itacademy.androidclasses.dz1.Dz1Activity;
import by.itacademy.androidclasses.dz2.Dz2Activity;
import by.itacademy.androidclasses.dz3.Dz3SwitchingScreenActivity;
import by.itacademy.androidclasses.dz4.Dz4Activity;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void click(View v) {
        switch (v.getId()) {
            case R.id.button_dz1: {
                startActivity(new Intent(MainActivity.this, Dz1Activity.class));
                break;
            }
            case R.id.button_dz2: {
                startActivity(new Intent(MainActivity.this, Dz2Activity.class));
                break;
            }
            case R.id.button_dz3: {
                startActivity(new Intent(MainActivity.this, Dz3SwitchingScreenActivity.class));
                break;
            }
            case R.id.button_dz4: {
                Intent intent = new Intent(MainActivity.this, Dz4Activity.class);
                startActivityForResult(intent, 1);
                overridePendingTransition(R.anim.translate1, R.anim.translate2);
                break;
        }
    }
}