package by.itacademy.androidclasses.dz6;

import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import by.itacademy.androidclasses.R;
import by.itacademy.androidclasses.dz6.fragments.AddStudentFragment;
import by.itacademy.androidclasses.dz6.fragments.EditFragment;
import by.itacademy.androidclasses.dz6.fragments.ListOfStudentsFragment;

public class Dz6Activity
        extends AppCompatActivity
        implements ListOfStudentsFragment.OnStudentSelectedListener,
        ListOfStudentsFragment.ButtonAddStudentOnClick,
        EditFragment.OnButtonClickListener,
        AddStudentFragment.OnButtonClickListener {

    public static final String STUDENT = "Student";
    public static final String STUDENT_POSITION = "Position";

    private ListOfStudentsFragment studentsFragment = new ListOfStudentsFragment();
    private EditFragment editFragment;
    private AddStudentFragment addFragment = new AddStudentFragment();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dz6);

        if (savedInstanceState == null) {

            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.container_1, studentsFragment, "students_fragment")
                    .commit();
        }
    }

    @Override
    public void onItemSelected(int position, Student student) {
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

            editFragment = new EditFragment();

            Bundle args = new Bundle();
            args.putParcelable(STUDENT, student);
            args.putInt(STUDENT_POSITION, position);
            editFragment.setArguments(args);

            fragmentTransaction.replace(R.id.container_1, editFragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        } else if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

            editFragment = new EditFragment();

            Bundle args = new Bundle();
            args.putParcelable(STUDENT, student);
            args.putInt(STUDENT_POSITION, position);
            editFragment.setArguments(args);

            fragmentTransaction.replace(R.id.container_2, editFragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }
    }

    @Override
    public void buttonAddStudentOnClick() {
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

            editFragment = new EditFragment();

            fragmentTransaction.replace(R.id.container_1, addFragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        } else if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

            editFragment = new EditFragment();

            fragmentTransaction.replace(R.id.container_2, addFragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }
    }

    @Override
    public void onButtonSelected() {
        ListOfStudentsFragment studentsFragment =
                (ListOfStudentsFragment) getSupportFragmentManager().findFragmentByTag("students_fragment");

        if (studentsFragment != null) {
            studentsFragment.notifyAdapterAboutChanges();
        }
    }
}