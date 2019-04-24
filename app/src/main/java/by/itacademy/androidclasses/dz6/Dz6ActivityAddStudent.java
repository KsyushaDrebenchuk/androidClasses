package by.itacademy.androidclasses.dz6;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import by.itacademy.androidclasses.R;

public class Dz6ActivityAddStudent extends Activity {

    private EditText editTextUrl;
    private EditText editTextName;
    private EditText editTextSurname;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dz6_add_profile);

        editTextUrl = findViewById(R.id.editTextAddUrlPhoto);
        editTextName = findViewById(R.id.editTextAddStudentName);
        editTextSurname = findViewById(R.id.editTextAddStudentSurname);
        Button buttonSave = findViewById(R.id.buttonSaveProfile);

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveStudent();
            }
        });
    }

    private void saveStudent() {
        StudentList.getInstance().addToList(new Student(
                editTextName.getText().toString(),
                editTextSurname.getText().toString(),
                editTextUrl.getText().toString()));
        this.finish();
    }
}