package com.anwesome.ui.redux;

/**
 * Created by anweshmishra on 14/04/17.
 */
public final class State {
    private Object object;
    public State(Object object) {
        this.object = object;
    }
    public Object get() {
        return object;
    }

}
