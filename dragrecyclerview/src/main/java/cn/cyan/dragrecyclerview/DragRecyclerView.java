package cn.cyan.dragrecyclerview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;

/**
 * User : Cyan(newbeeeeeeeee@gmail.com)
 * Date : 2016/9/28
 */
public class DragRecyclerView extends RecyclerView {

    private OnItemChangeListener adapter;
    private boolean dragEnable;
    private boolean showDragAnimation;


    public DragRecyclerView(Context context) {
        super(context);
        init(context, null);
    }

    public DragRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        if (attrs != null) {
            TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.DragRecyclerView);
            dragEnable = ta.getBoolean(R.styleable.DragRecyclerView_drag_enable, true);
            showDragAnimation = ta.getBoolean(R.styleable.DragRecyclerView_show_drag_animation, true);
            ta.recycle();
        } else {
            dragEnable = true;
            showDragAnimation = true;
        }
    }

    ItemTouchHelper touchHelper = new ItemTouchHelper(new ItemTouchHelper.Callback() {
        @Override
        public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
            if (!dragEnable) {
                return ItemTouchHelper.ACTION_STATE_IDLE;
            }
            if (recyclerView.getLayoutManager() instanceof GridLayoutManager) {
                final int dragFlag = ItemTouchHelper.UP | ItemTouchHelper.DOWN | ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
                return makeMovementFlags(dragFlag, ItemTouchHelper.ACTION_STATE_IDLE);
            } else {
                final int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
                final int swipeFlags = ItemTouchHelper.ACTION_STATE_IDLE;
                return makeMovementFlags(dragFlags, swipeFlags);
            }
        }

        @Override
        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
            int oldPosition = viewHolder.getAdapterPosition();
            int targetPosition = target.getAdapterPosition();
            adapter.onItemMoved(oldPosition, targetPosition);
            return false;
        }

        @Override
        public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {

        }

        @Override
        public boolean isItemViewSwipeEnabled() {
            return true;
        }

        @Override
        public boolean isLongPressDragEnabled() {
            return false;
        }

        @Override
        public void onChildDraw(Canvas c, RecyclerView recyclerView, ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
            if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
                final float alpha = 1 - Math.abs(dX) / (float) viewHolder.itemView.getWidth();
                viewHolder.itemView.setAlpha(alpha);
                viewHolder.itemView.setTranslationX(dX);
            } else {
                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
            }
        }

        @Override
        public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
            if (actionState != ItemTouchHelper.ACTION_STATE_IDLE) {
                viewHolder.itemView.setBackgroundColor(Color.LTGRAY);
                if (showDragAnimation) zoomView(viewHolder.itemView);
            }
            super.onSelectedChanged(viewHolder, actionState);
        }

        @Override
        public void clearView(RecyclerView recyclerView, ViewHolder viewHolder) {
            super.clearView(recyclerView, viewHolder);
            viewHolder.itemView.setAlpha(1.0f);
            viewHolder.itemView.setBackgroundColor(Color.WHITE);
            if (showDragAnimation) revertView(viewHolder.itemView);
        }

        @Override
        public boolean canDropOver(RecyclerView recyclerView, ViewHolder current, ViewHolder target) {
            return adapter.onItemDrop(target.getAdapterPosition());
        }
    });

    private ScaleAnimation zoomAnimation = new ScaleAnimation(1.0f, 1.1f, 1.0f, 1.1f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
    private ScaleAnimation revertAnimation = new ScaleAnimation(1.1f, 1.0f, 1.1f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);

    private void zoomView(final View v) {
        v.setAnimation(zoomAnimation);
        zoomAnimation.setFillAfter(true);
        zoomAnimation.setDuration(200);
        zoomAnimation.start();
    }

    private void revertView(final View v) {
        v.setAnimation(revertAnimation);
        revertAnimation.setFillAfter(true);
        revertAnimation.setDuration(400);
        revertAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                v.clearAnimation();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
        revertAnimation.start();
    }

    public DragRecyclerView dragEnable(boolean dragEnable) {
        this.dragEnable = dragEnable;
        return this;
    }

    public DragRecyclerView showDragAnimation(boolean showDragAnimation) {
        this.showDragAnimation = showDragAnimation;
        return this;
    }


    public DragRecyclerView setDragAdapter(OnItemChangeListener dragBaseAdapter) {
        if (dragBaseAdapter instanceof Adapter) {
            this.adapter = dragBaseAdapter;
            touchHelper.attachToRecyclerView(this);
            super.setAdapter((Adapter) adapter);
        } else {
            throw new IllegalArgumentException();
        }
        return this;
    }


    public DragRecyclerView bindEvent(HoldTouchHelper.OnItemTouchEvent onItemTouchEvent) {
        HoldTouchHelper.bind(this, onItemTouchEvent);
        return this;
    }


    public void startDrag(ViewHolder viewHolder) {
        touchHelper.startDrag(viewHolder);
    }

    public void startDrag(int position) {
        touchHelper.startDrag(getChildViewHolder(getChildAt(position)));
    }

}
