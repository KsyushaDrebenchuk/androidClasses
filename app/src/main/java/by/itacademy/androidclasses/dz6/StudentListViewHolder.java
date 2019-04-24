package by.itacademy.androidclasses.dz6;

import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import by.itacademy.androidclasses.R;

public class StudentListViewHolder extends RecyclerView.ViewHolder {

    private ImageView imageViewPhoto;
    private TextView textViewName;
    private TextView textViewSurname;

    public StudentListViewHolder(@NonNull View itemView) {
        super(itemView);
        imageViewPhoto = itemView.findViewById(R.id.imageViewPhoto);
        textViewName = itemView.findViewById(R.id.textViewName);
        textViewSurname = itemView.findViewById(R.id.textViewSurname);
    }

    public void bind(Student item, int position) {

        if (!TextUtils.isEmpty(item.getImageUrl())) {
            ImageLoaderUtil.loadImage(imageViewPhoto, item.getImageUrl());
        } else {
            imageViewPhoto.setImageDrawable(null);
        }

        if (item.getName() != null) {
            textViewName.setText(item.getName());
        } else {
            textViewName.setText("");
        }

        if (item.getSurname() != null) {
            textViewSurname.setText(item.getSurname());
        } else {
            textViewSurname.setText("");
        }
    }
}