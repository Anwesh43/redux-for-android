package com.anwesome.ui.reduxforandroiddemo.actions;

import android.graphics.PointF;

import com.anwesome.ui.redux.Action;

/**
 * Created by anweshmishra on 14/04/17.
 */
public class AddAction extends Action {
    private float x,y;
    public AddAction(float x,float y) {
        super("ADD");
        this.x = x;
        this.y = y;
    }
    public PointF getPoint() {
        return new PointF(x,y);
    }
}
