package by.itacademy.androidclasses.dz1;

import by.itacademy.androidclasses.R;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Dz1Activity extends Activity implements View.OnClickListener {

    private TextView textView1;
    private TextView textView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dz1);

        textView1 = findViewById(R.id.textView1);
        textView2 = findViewById(R.id.textView2);

        textView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeText();
                changeColor();
            }
        });

        textView2.setOnClickListener(textView2ClickListener);
    }

    @Override
    public void onClick(View v) {
        changeText();
        changeColor();
    }

    private View.OnClickListener textView2ClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            changeColor();
            changeText();
        }
    };

    public void changeColor() {
        ColorDrawable cd1 = (ColorDrawable) textView1.getBackground();
        ColorDrawable cd2 = (ColorDrawable) textView2.getBackground();
        int colorCode1 = cd1.getColor();
        int colorCode2 = cd2.getColor();
        textView1.setBackgroundColor(colorCode2);
        textView2.setBackgroundColor(colorCode1);
    }

    public void changeText() {
        CharSequence text1 = textView1.getText();
        CharSequence text2 = textView2.getText();
        textView2.setText(text1);
        textView1.setText(text2);
    }
}
