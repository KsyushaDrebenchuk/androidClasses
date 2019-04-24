package by.itacademy.androidclasses.dz6;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import by.itacademy.androidclasses.R;

public class Dz6ActivityEdit extends Activity {

    private EditText editTextUrl;
    private EditText editTextName;
    private EditText editTextSurname;
    private int studentPosition;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dz6_edit_profile);

        ImageView imageView = findViewById(R.id.imageViewProfilePhoto);
        editTextUrl = findViewById(R.id.editTextUrlPhoto);
        editTextName = findViewById(R.id.editTextStudentName);
        editTextSurname = findViewById(R.id.editTextStudentSurname);

        Button buttonEditProfile = findViewById(R.id.buttonEditProfile);
        Button buttonDeleteProfile = findViewById(R.id.buttonDeleteProfile);

        Intent intent = getIntent();
        if (intent != null) {
            Student student = intent.getParcelableExtra(Dz6Activity.STUDENT);
            studentPosition = intent.getIntExtra(Dz6Activity.STUDENT_POSITION, -1);

            ImageLoaderUtil.loadImage(imageView, student.getImageUrl());
            editTextUrl.setText(student.getImageUrl());
            editTextName.setText(student.getName());
            editTextSurname.setText(student.getSurname());
        }

        buttonEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveEdits(studentPosition);
            }
        });

        buttonDeleteProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteProfile(studentPosition);
            }
        });
    }

    private void saveEdits(int studentPosition) {
        StudentList.getInstance().editStudent(
                studentPosition,
                editTextUrl.getText().toString(),
                editTextName.getText().toString(),
                editTextSurname.getText().toString());
        this.finish();
    }

    private void deleteProfile(int studentPosition) {
        StudentList.getInstance().deleteStudent(studentPosition);
        this.finish();
    }
}