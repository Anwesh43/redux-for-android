package com.anwesome.ui.redux;

/**
 * Created by anweshmishra on 14/04/17.
 */
public interface Reducer {
    State onReduce(State state,Action action);
    State getDefaultState();
}
