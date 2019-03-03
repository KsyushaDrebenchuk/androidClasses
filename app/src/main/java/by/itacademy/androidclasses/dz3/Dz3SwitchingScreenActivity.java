package by.itacademy.androidclasses.dz3;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import by.itacademy.androidclasses.R;

public class Dz3SwitchingScreenActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dz3_switchingscreen);

        Button buttonToFindImage = findViewById(R.id.buttonDz3_toFindImage);
        Button buttonToScreenDesign = findViewById(R.id.buttonDz3_toScreenDesign);

        buttonOnClick(buttonToFindImage, Dz3OpenImageActivity.class);
        buttonOnClick(buttonToScreenDesign, Dz3ScreenDesign.class);
    }


    public void buttonOnClick(Button button, final Class clazz) {
        View.OnClickListener buttonClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Dz3SwitchingScreenActivity.this, clazz));
            }
        };
        button.setOnClickListener(buttonClickListener);
    }
}