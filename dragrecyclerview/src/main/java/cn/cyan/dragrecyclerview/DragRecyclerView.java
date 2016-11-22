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
 * Desc : 可拖动recyclerView
 * User : Cyan(baocq@maritech.com)
 * New  : 2016/9/21 14:27
 */
public class DragRecyclerView extends RecyclerView {

    /* 适配器 */
    private OnItemChangeListener adapter;
    /* 是否可拖动 */
    private boolean dragEnable;
    /* 是否显示拖动动画 */
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

    ///////////////////////////////////////////////////////////////////////////
    // 拖动监听
    ///////////////////////////////////////////////////////////////////////////
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
            // 原位置
            int oldPosition = viewHolder.getAdapterPosition();
            // 目标位置
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
            // 禁用,使用自定义触摸监听
            return false;
        }

        @Override
        public void onChildDraw(Canvas c, RecyclerView recyclerView, ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
            if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
                //滑动时改变Item的透明度
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
                /** item已经被拽起(托起状态) */
                // 设置背景
                viewHolder.itemView.setBackgroundColor(Color.LTGRAY);
                // 放大动画
                if (showDragAnimation) zoomView(viewHolder.itemView);
            }
            super.onSelectedChanged(viewHolder, actionState);
        }

        @Override
        public void clearView(RecyclerView recyclerView, ViewHolder viewHolder) {
            super.clearView(recyclerView, viewHolder);
            // 还原item的样式
            viewHolder.itemView.setAlpha(1.0f);
            viewHolder.itemView.setBackgroundColor(Color.WHITE);
            // 缩放动画
            if (showDragAnimation) revertView(viewHolder.itemView);
        }

        @Override
        public boolean canDropOver(RecyclerView recyclerView, ViewHolder current, ViewHolder target) {
            /** 被禁用的位置不会被挤兑 */
            return adapter.onItemDrag(target.getAdapterPosition());
        }
    });

    /* 放大动画 */
    private ScaleAnimation zoomAnimation = new ScaleAnimation(1.0f, 1.1f, 1.0f, 1.1f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
    /* 还原动画 */
    private ScaleAnimation revertAnimation = new ScaleAnimation(1.1f, 1.0f, 1.1f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);

    /**
     * 放大
     *
     * @param v 视图
     */
    private void zoomView(final View v) {
        v.setAnimation(zoomAnimation);
        // 动画执行完停留位置
        zoomAnimation.setFillAfter(true);
        // 动画持续时间
        zoomAnimation.setDuration(200);
        // 开始
        zoomAnimation.start();
    }

    /**
     * 还原
     *
     * @param v 视图
     */
    private void revertView(final View v) {
        v.setAnimation(revertAnimation);
        // 动画执行完停留位置
        revertAnimation.setFillAfter(true);
        // 动画持续时间
        revertAnimation.setDuration(400);
        // 执行监听
        revertAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                /** 动画结束后清除效果 */
                v.clearAnimation();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
        // 开始
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
            setAdapter((Adapter) adapter);
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
