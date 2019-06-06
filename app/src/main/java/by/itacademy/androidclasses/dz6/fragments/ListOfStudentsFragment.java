package by.itacademy.androidclasses.dz6.fragments;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import by.itacademy.androidclasses.R;
import by.itacademy.androidclasses.dz6.ButtonView;
import by.itacademy.androidclasses.dz6.Student;
import by.itacademy.androidclasses.dz6.StudentList;
import by.itacademy.androidclasses.dz6.StudentListAdapter;

public class ListOfStudentsFragment extends Fragment {

    private StudentListAdapter adapter = new StudentListAdapter();
    private List<Student> students = StudentList.getInstance().getStudentList();
    private ButtonView buttonAddStudent;

    private OnStudentSelectedListener callbackPosition;
    private ButtonAddStudentOnClick buttonAddOnClick;

    private Handler handler;
    private final int TO_UPDATE_DATA = 1;

    public interface OnStudentSelectedListener {
        void onItemSelected(int position, Student student);
    }

    public interface ButtonAddStudentOnClick {
        void buttonAddStudentOnClick();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnStudentSelectedListener) {
            callbackPosition = (OnStudentSelectedListener) context;
        } else {
            throw new ClassCastException(context.toString()
                    + " must implement OnStudentSelectedListener");
        }
        if (context instanceof ButtonAddStudentOnClick) {
            buttonAddOnClick = (ButtonAddStudentOnClick) context;
        } else {
            throw new ClassCastException(context.toString()
                    + " must implement ButtonAddStudentOnClick");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_listofstudent, container, false);

        buttonAddStudent = view.findViewById(R.id.buttonAddStudent);

        buttonAddStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonAddOnClick.buttonAddStudentOnClick();
            }
        });

        RecyclerView recyclerView = view.findViewById(R.id.recycleView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false));

        adapter.setList(students);
        adapter.setListener(new StudentListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Student student, int position) {
                callbackPosition.onItemSelected(position, student);
            }
        });
        recyclerView.setAdapter(adapter);

        EditText editTextSearch = view.findViewById(R.id.editTextSearch);
        editTextSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                adapter.getFilter().filter(editable);
            }
        });

        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == TO_UPDATE_DATA) {
                    adapter.notifyDataSetChanged();
                }
            }
        };
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onPause() {
        super.onPause();
        adapter.notifyDataSetChanged();
    }

    public void notifyAdapterAboutChanges() {
        handler.sendEmptyMessage(TO_UPDATE_DATA);
    }
}