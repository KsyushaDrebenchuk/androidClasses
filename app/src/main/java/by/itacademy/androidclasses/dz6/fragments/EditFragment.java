package by.itacademy.androidclasses.dz6.fragments;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import by.itacademy.androidclasses.R;
import by.itacademy.androidclasses.dz6.Dz6Activity;
import by.itacademy.androidclasses.dz6.ImageLoaderUtil;
import by.itacademy.androidclasses.dz6.Student;
import by.itacademy.androidclasses.dz6.StudentList;

public class EditFragment extends Fragment {

    private EditText editTextUrl;
    private EditText editTextName;
    private EditText editTextSurname;
    private ImageView imageView;
    private int studentPosition;

    private OnButtonClickListener onButtonClickListener;

    public interface OnButtonClickListener {
        void onButtonSelected();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnButtonClickListener) {
            onButtonClickListener = (OnButtonClickListener) context;
        } else {
            throw new ClassCastException(context.toString() + " must implement MyListFragment.OnItemSelectedListener");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dz6_edit_profile, container, false);

        imageView = view.findViewById(R.id.imageViewProfilePhoto);
        editTextUrl = view.findViewById(R.id.editTextUrlPhoto);
        editTextName = view.findViewById(R.id.editTextStudentName);
        editTextSurname = view.findViewById(R.id.editTextStudentSurname);

        Button buttonEditProfile = view.findViewById(R.id.buttonEditProfile);
        Button buttonDeleteProfile = view.findViewById(R.id.buttonDeleteProfile);

        Bundle args = getArguments();
        if (args != null) {

            Student student = args.getParcelable(Dz6Activity.STUDENT);
            studentPosition = args.getInt(Dz6Activity.STUDENT_POSITION, -1);
            setStudentData(student, studentPosition);

            if (student != null) {
                Log.e("BBB", student.toString());
                ImageLoaderUtil.loadImage(imageView, student.getImageUrl());

                editTextUrl.setText(student.getImageUrl());
                editTextName.setText(student.getName());
                editTextSurname.setText(student.getSurname());
            }
        } else {
            Log.e("AAA", "args = null");
        }

        buttonEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveEdits(studentPosition);
                onButtonClickListener.onButtonSelected();
                Toast.makeText(getActivity(), "Saved", Toast.LENGTH_SHORT).show();
            }
        });

        buttonDeleteProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteProfile(studentPosition);
                onButtonClickListener.onButtonSelected();
                Toast.makeText(getActivity(), "Removed", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }


    private void saveEdits(int studentPosition) {
        StudentList.getInstance().editStudent(
                studentPosition,
                editTextUrl.getText().toString(),
                editTextName.getText().toString(),
                editTextSurname.getText().toString());
    }

    private void deleteProfile(int studentPosition) {
        StudentList.getInstance().deleteStudent(studentPosition);
    }

    private void setStudentData(Student student, int studentPos) {
        Log.e("AAA", student.toString() + "setStudentData method");
        ImageLoaderUtil.loadImage(imageView, student.getImageUrl());
        editTextUrl.setText(student.getImageUrl());
        editTextName.setText(student.getName());
        editTextSurname.setText(student.getSurname());

        studentPosition = studentPos;
    }
}