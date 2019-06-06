package by.itacademy.androidclasses.dz6;

import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class ImageLoaderUtil {

    public static void loadImage(ImageView imageView, String url) {
        Picasso.get().load(url).into(imageView);
    }
}