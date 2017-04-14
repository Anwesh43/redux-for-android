package com.anwesome.ui.reduxforandroiddemo;

import android.graphics.PointF;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.anwesome.ui.redux.Action;
import com.anwesome.ui.redux.Reducer;
import com.anwesome.ui.redux.State;
import com.anwesome.ui.redux.Store;
import com.anwesome.ui.redux.StoreSubscriber;
import com.anwesome.ui.reduxforandroiddemo.actions.AddAction;
import com.anwesome.ui.reduxforandroiddemo.actions.MoveAction;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Store store;
    private ImageContainer imageContainer = new ImageContainer();
    private State state = new State(imageContainer);
    private Reducer reducer;
    private boolean isDown = false;
    private RelativeLayout parent;
    private int currIndex = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        parent = (RelativeLayout)findViewById(R.id.parent);
        reducer = new Reducer() {
            @Override
            public State onReduce(State state, Action action) {
                ImageContainer imageContainer = (ImageContainer)state.get();
                switch (action.getType()) {
                    case "ADD":
                        if(action instanceof AddAction) {
                            AddAction addAction = (AddAction)action;
                            imageContainer.addPoint(addAction.getPoint());
                        }
                        break;
                    case "MOVE":
                        if(action instanceof MoveAction) {
                            MoveAction moveAction = (MoveAction)action;
                            imageContainer.setPointForIndex(moveAction.getIndex(),moveAction.getPoint());
                        }
                        break;
                    default:
                        break;
                }
                return new State(imageContainer);
            }

            @Override
            public State getDefaultState() {
                return new State(imageContainer);
            }
        };
        store = Store.createStore(reducer);
        store.subscribe(new StoreSubscriber() {
            @Override
            public void onSubscribe() {
                state = store.getState();
                imageContainer = (ImageContainer)state.get();
                arrangeViews();
            }
        });
    }
    public void arrangeViews() {
        parent.removeAllViews();
        for(PointF pointF:imageContainer.getPoints()) {
            ImageView imageView = new ImageView(this);
            float x= pointF.x,y = pointF.y;
            imageView.setX(x);
            imageView.setY(y);
            imageView.setImageResource(R.drawable.sml1);
            parent.addView(imageView,new ViewGroup.LayoutParams(200,200));
        }
    }
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX()-100,y = event.getY()-200;
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                int index = 0;
                for(int i=0;i<parent.getChildCount();i++) {
                    View imageView = parent.getChildAt(i);
                    float left = imageView.getX(),top = imageView.getY(),right = left+imageView.getMeasuredWidth(),bottom = top+imageView.getMeasuredHeight();
                    if(x>=left && x<=right && y>=top && y<=bottom) {
                        isDown = true;
                        currIndex = index;
                        break;
                    }
                    index++;
                }
                if(!isDown) {
                    store.dispatch(new AddAction(x-100,y-100));
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if(isDown) {
                    store.dispatch(new MoveAction(currIndex,x-100,y-100));
                }
                break;
            case MotionEvent.ACTION_UP:
                if(isDown) {
                    isDown = false;
                }
                break;
            default:
                break;
        }
        return true;
    }
}
