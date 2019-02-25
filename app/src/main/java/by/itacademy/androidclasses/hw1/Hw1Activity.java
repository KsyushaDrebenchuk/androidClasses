package by.itacademy.androidclasses.hw1;

import androidx.appcompat.app.AppCompatActivity;
import by.itacademy.androidclasses.R;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Hw1Activity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hw1);
    }

    public void changeText() {
        TextView textView1 = findViewById(R.id.textView1);
        TextView textView2 = findViewById(R.id.textView2);
        CharSequence text1 = textView1.getText();
        CharSequence text2 = textView2.getText();
        textView2.setText(text1);
        textView1.setText(text2);
    }

    public void changeColor() {
        TextView textView1 = findViewById(R.id.textView1);
        TextView textView2 = findViewById(R.id.textView2);
        ColorDrawable cd1 = (ColorDrawable) textView1.getBackground();
        ColorDrawable cd2 = (ColorDrawable) textView2.getBackground();
        int colorCode1 = cd1.getColor();
        int colorCode2 = cd2.getColor();
        textView1.setBackgroundColor(colorCode2);
        textView2.setBackgroundColor(colorCode1);
    }

    @Override
    public void onClick(View v) {
        changeText();
        changeColor();
    }
}
