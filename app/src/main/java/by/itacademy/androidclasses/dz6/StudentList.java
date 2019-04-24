package by.itacademy.androidclasses.dz6;

import java.util.ArrayList;
import java.util.List;

public class StudentList {

    private static StudentList instance;
    private List<Student> studentList = new ArrayList<>();

    private StudentList() {
    }

    public static StudentList getInstance() {
        if (instance == null) {
            instance = new StudentList();
            instance.initializeList();
        }
        return instance;
    }

    private void initializeList() {
        studentList.add(new Student("Jack", "Vorobey", "https://i.pinimg.com/236x/d0/1c/d7/d01cd7c1d1eabe11cbd0fa1ef778f9ec--johnny-depp-characters-captain-jack-sparrow.jpg"));
        studentList.add(new Student("Neo", "", "https://steamcdn-a.akamaihd.net/steamcommunity/public/images/avatars/d6/d639c9836b16c858be0ce6f63a6b1b0d5a7ccd72_full.jpg"));
        studentList.add(new Student("Vito", "Corleone", "http://www.vothouse.ru/img/points/100_kino-geroev/10.jpg"));
        studentList.add(new Student("Darth", "Vader", "http://www.vothouse.ru/img/points/100_kino-geroev/02.jpg"));
        studentList.add(new Student("Edward", "Scissorhands", "http://www.vothouse.ru/img/points/100_kino-geroev/37.jpg"));
        studentList.add(new Student("Harry", "Potter", "http://www.vothouse.ru/img/points/100_kino-geroev/36.jpg"));
        studentList.add(new Student("Gandalf", "", "https://hardcoversandheroines.files.wordpress.com/2013/04/images-5.jpeg"));
    }

    public void addToList(Student student) {
        studentList.add(student);
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    public void editStudent(int index, String imageURL, String name, String surname) {
        Student student = studentList.get(index);
        student.setImageUrl(imageURL);
        student.setName(name);
        student.setSurname(surname);
    }

    public void deleteStudent(int position) {
        studentList.remove(position);
    }
}