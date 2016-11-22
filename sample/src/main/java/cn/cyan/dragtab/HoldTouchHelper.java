package cn.cyan.dragtab;

import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

/**
 * Desc : 拦截 recyclerView 单击/长按事件
 * User : Cyan(baocq@maritech.cn)
 * New  : 2016/11/21 13:09
 */

public class HoldTouchHelper {

    private RecyclerView recyclerView;
    private OnItemTouchEventListener onItemTouchEventListener;
    private GestureDetectorCompat detector;


    private HoldTouchHelper(RecyclerView view, OnItemTouchEventListener listener) {
        this.recyclerView = view;
        this.onItemTouchEventListener = listener;

        GestureDetector.SimpleOnGestureListener simpleOnGestureListener = new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                if (onItemTouchEventListener != null) {
                    View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                    if (child != null) {
                        RecyclerView.ViewHolder vh = recyclerView.getChildViewHolder(child);
                        onItemTouchEventListener.onItemClick(recyclerView, vh, vh.getAdapterPosition());
                    }
                }
                return true;
            }

            @Override
            public void onLongPress(MotionEvent e) {
                View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                if (child != null) {
                    RecyclerView.ViewHolder vh = recyclerView.getChildViewHolder(child);
                    onItemTouchEventListener.onLongPress(recyclerView, vh, vh.getAdapterPosition());
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

    public interface OnItemTouchEventListener {
        void onLongPress(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, int position);

        void onItemClick(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, int position);
    }

    public static void bind(RecyclerView view, OnItemTouchEventListener listener) {
        new HoldTouchHelper(view, listener);
    }
}
