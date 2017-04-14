package com.anwesome.ui.reduxforandroiddemo;

import android.graphics.PointF;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by anweshmishra on 14/04/17.
 */
public class ImageContainer {
    private List<PointF> points = new ArrayList<>();
    public void setPointForIndex(int index,PointF pointF) {
        points.set(index,pointF);
    }
    public void addPoint(PointF point) {
        points.add(point);
    }
    public List<PointF> getPoints() {
        return points;
    }
}
