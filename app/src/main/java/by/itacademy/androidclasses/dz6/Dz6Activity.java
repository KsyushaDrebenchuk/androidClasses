package by.itacademy.androidclasses.dz6;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import by.itacademy.androidclasses.R;

public class Dz6Activity extends Activity {

    public static final String STUDENT = "Student";
    public static final String STUDENT_POSITION = "Position";
    private StudentListAdapter adapter = new StudentListAdapter();
    private List<Student> students = StudentList.getInstance().getStudentList();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dz6);

        ButtonView buttonAddStudent = findViewById(R.id.buttonAddStudent);

        buttonAddStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Dz6Activity.this, Dz6ActivityAddStudent.class));
            }
        });

        RecyclerView recyclerView = findViewById(R.id.recycleView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));

        adapter.setList(students);
        adapter.setListener(new StudentListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Student student, int position) {
                Intent intent = new Intent(Dz6Activity.this, Dz6ActivityEdit.class);
                intent.putExtra(STUDENT, student);
                intent.putExtra(STUDENT_POSITION, position);
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(adapter);

        EditText editTextSearch = findViewById(R.id.editTextSearch);
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
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
    }
}