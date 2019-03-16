package by.itacademy.androidclasses.dz4.diagram;

import java.util.ArrayList;
import java.util.List;

public class Dz4Calculation {

    private float sumOfValues = 0;
    private Dz4Data dz4Data = new Dz4Data();
    private List<Float> list = new ArrayList<>();
    private List<Float> shareOfSectors = new ArrayList<>();

    public void calculateShare() {
        sum();
        float coefficient = 360 / sumOfValues;
        for (float value : list) {
            float share;
            share = value * coefficient;
            shareOfSectors.add(share);
        }
    }

    private void sum() {
        dz4Data.addToList();
        list = dz4Data.getList();
        for (float value : list) {
            sumOfValues += value;
        }
    }

    public List<Float> getShareOfSectors() {
        return shareOfSectors;
    }
}