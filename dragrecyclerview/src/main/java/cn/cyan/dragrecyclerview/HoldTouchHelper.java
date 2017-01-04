package cn.cyan.dragrecyclerview;

import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

/**
 * User : Cyan(newbeeeeeeeee@gmail.com)
 * Date : 2016/9/28
 */
public class HoldTouchHelper {

    private RecyclerView recyclerView;
    private OnItemTouchEvent onItemTouchEvent;
    private GestureDetectorCompat detector;


    private HoldTouchHelper(RecyclerView view, OnItemTouchEvent event) {
        this.recyclerView = view;
        this.onItemTouchEvent = event;

        GestureDetector.SimpleOnGestureListener simpleOnGestureListener = new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                if (onItemTouchEvent != null) {
                    View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                    if (child != null) {
                        RecyclerView.ViewHolder vh = recyclerView.getChildViewHolder(child);
                        onItemTouchEvent.onItemClick(recyclerView, vh, vh.getAdapterPosition());
                    }
                }
                return true;
            }

            @Override
            public void onLongPress(MotionEvent e) {
                View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                if (child != null) {
                    RecyclerView.ViewHolder vh = recyclerView.getChildViewHolder(child);
                    onItemTouchEvent.onLongPress(recyclerView, vh, vh.getAdapterPosition());
                }
            }
        };

        detector = new GestureDetectorCompat(recyclerView.getContext(), simpleOnGestureListener);

        RecyclerView.OnItemTouchListener onItemTouchListener = new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
                detector.onTouchEvent(e);
                return false;
            }

            @Override
            public void onTouchEvent(RecyclerView rv, MotionEvent e) {
                detector.onTouchEvent(e);
            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
            }
        };
        recyclerView.addOnItemTouchListener(onItemTouchListener);
    }

    public interface OnItemTouchEvent {
        void onLongPress(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, int position);

        void onItemClick(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, int position);
    }

    public static void bind(RecyclerView view, OnItemTouchEvent event) {
        new HoldTouchHelper(view, event);
    }
}
