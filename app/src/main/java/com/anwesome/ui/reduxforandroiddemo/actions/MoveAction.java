package com.anwesome.ui.reduxforandroiddemo.actions;

import android.graphics.PointF;

import com.anwesome.ui.redux.Action;

/**
 * Created by anweshmishra on 14/04/17.
 */
public class MoveAction extends Action{
    private int index;
    private float x,y;
    public MoveAction(int i,float x,float y) {
        super("MOVE");
        this.index = i;
        this.x = x;
        this.y = y;
    }
    public PointF getPoint() {
        return new PointF(x,y);
    }
    public int getIndex() {
        return index;
    }
}
