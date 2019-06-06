package by.itacademy.androidclasses.dz6.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import by.itacademy.androidclasses.R;
import by.itacademy.androidclasses.dz6.Student;
import by.itacademy.androidclasses.dz6.StudentList;

public class AddStudentFragment extends Fragment {

    private EditText editTextUrl;
    private EditText editTextName;
    private EditText editTextSurname;

    private EditFragment.OnButtonClickListener onButtonClickListener;

    public interface OnButtonClickListener {
        void onButtonSelected();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof EditFragment.OnButtonClickListener) {
            onButtonClickListener = (EditFragment.OnButtonClickListener) context;
        } else {
            throw new ClassCastException(context.toString() + " must implement MyListFragment.OnItemSelectedListener");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dz6_add_profile, container, false);

        editTextUrl = view.findViewById(R.id.editTextAddUrlPhoto);
        editTextName = view.findViewById(R.id.editTextAddStudentName);
        editTextSurname = view.findViewById(R.id.editTextAddStudentSurname);
        Button buttonSave = view.findViewById(R.id.buttonSaveProfile);

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveStudent();
                onButtonClickListener.onButtonSelected();
                Toast.makeText(getActivity(), "Added", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

    private void saveStudent() {
        StudentList.getInstance().addToList(new Student(
                editTextName.getText().toString(),
                editTextSurname.getText().toString(),
                editTextUrl.getText().toString()));
    }
}