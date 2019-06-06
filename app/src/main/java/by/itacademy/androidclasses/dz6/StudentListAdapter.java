package by.itacademy.androidclasses.dz6;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import by.itacademy.androidclasses.R;

public class StudentListAdapter extends RecyclerView.Adapter<StudentListViewHolder> implements Filterable {

    private List<Student> studentList;
    private List<Student> studentListFiltered;
    private OnItemClickListener listener;

    public void setList(List<Student> studentList) {
        this.studentList = studentList;
        notifyDataSetChanged();
    }

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public StudentListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.item_list_dz6, parent, false);

        final StudentListViewHolder viewHolder = new StudentListViewHolder(view);

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = viewHolder.getAdapterPosition();
                if (listener != null) {
                    if (studentListFiltered == null) {
                        listener.onItemClick(studentList.get(position), position);
                    } else {
                        listener.onItemClick(studentListFiltered.get(position), position);
                    }
                }
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull StudentListViewHolder holder, int position) {
        if (studentListFiltered == null) {
            holder.bind(studentList.get(position), position);
        } else {
            holder.bind(studentListFiltered.get(position), position);
        }
    }

    @Override
    public int getItemCount() {
        if (studentListFiltered == null) {
            return studentList.size();
        } else {
            return studentListFiltered.size();
        }
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public interface OnItemClickListener {
        void onItemClick(Student student, int position);
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    studentListFiltered = studentList;
                } else {
                    List<Student> filteredList = new ArrayList<>();
                    for (Student student : studentList) {
                        if (student.getName().toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(student);
                        }
                    }
                    for (Student student : studentList) {
                        if (student.getSurname().toLowerCase().contains(charSequence)) {
                            if (!filteredList.contains(student))
                                filteredList.add(student);
                        }
                    }
                    studentListFiltered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = studentListFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                studentListFiltered = (ArrayList<Student>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }
}