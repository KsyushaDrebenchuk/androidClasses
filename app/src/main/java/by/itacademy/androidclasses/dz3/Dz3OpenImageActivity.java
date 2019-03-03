package by.itacademy.androidclasses.dz3;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import androidx.annotation.Nullable;
import by.itacademy.androidclasses.R;
import jp.wasabeef.picasso.transformations.CropCircleTransformation;

public class Dz3OpenImageActivity extends Activity implements Callback {

    private ImageView imageView;
    private EditText editText;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dz3_openimage);

        imageView = findViewById(R.id.imageView_ViaLink);
        editText = findViewById(R.id.editText);
        progressBar = findViewById(R.id.progressBar);

        Button buttonUploadImage = findViewById(R.id.buttonUploadImage);

        editText.setText("https://pbs.twimg.com/media/DJRD406XgAA0Avp.jpg");

        buttonUploadImage.setOnClickListener(buttonUploadImageClickListener);
    }

    private View.OnClickListener buttonUploadImageClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String pictureLink = editText.getText().toString();
            progressBar.setVisibility(View.VISIBLE);
            loadImage(pictureLink);
        }
    };

    private synchronized void loadImage(String pictureLink) {
        Picasso.get()
                .load(pictureLink)
                .transform(new CropCircleTransformation())
                .into(imageView, this);
    }

    @Override
    public void onSuccess() {
        progressBar.setVisibility(View.GONE);
        imageView.setVisibility(View.VISIBLE);
    }

    @Override
    public void onError(Exception e) {
        progressBar.setVisibility(View.GONE);
        imageView.setVisibility(View.VISIBLE);
    }
}