package by.itacademy.androidclasses.dz4.diagram;

import java.util.ArrayList;
import java.util.List;

public class Dz4Data {

    private List<Float> list = new ArrayList<>();

    public void addToList() {
        list.add((float) 100);
        list.add((float) 30);
        list.add((float) 90);
        list.add((float) 65);
        list.add((float) 35);
        list.add((float) 70);
        list.add((float) 60);
        list.add((float) 45);
        list.add((float) 10);
        list.add((float) 40);
        list.add((float) 63);
    }

    public List<Float> getList() {
        return list;
    }
}