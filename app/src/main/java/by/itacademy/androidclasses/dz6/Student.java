package by.itacademy.androidclasses.dz6;

import android.os.Parcel;
import android.os.Parcelable;

public class Student implements Parcelable {

    private String name;
    private String surname;
    private String imageUrl;

    public Student(String name, String surname, String imageUrl) {
        this.name = name;
        this.surname = surname;
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    private Student(Parcel in) {
        name = in.readString();
        surname = in.readString();
        imageUrl = in.readString();
    }

    public static final Creator<Student> CREATOR = new Creator<Student>() {
        @Override
        public Student createFromParcel(Parcel in) {
            return new Student(in);
        }

        @Override
        public Student[] newArray(int size) {
            return new Student[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(surname);
        dest.writeString(imageUrl);
    }
}