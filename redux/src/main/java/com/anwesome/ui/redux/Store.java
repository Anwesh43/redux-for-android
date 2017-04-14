package com.anwesome.ui.redux;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by anweshmishra on 14/04/17.
 */
public class Store {
    private State state;
    private Reducer reducer;
    private ConcurrentLinkedQueue<StoreSubscriber> subscribers = new ConcurrentLinkedQueue<>();
    private Store(Reducer reducer) {
        this.reducer = reducer;
        this.state = reducer.getDefaultState();
    }
    public State getState() {
        return state;
    }
    public void dispatch(Action action) {
        state = reducer.onReduce(state,action);

    }
    public void subscribe(StoreSubscriber storeSubscriber) {
        this.subscribers.add(storeSubscriber);
    }
    public static Store createStore(Reducer reducer) {
        return new Store(reducer);
    }
}
