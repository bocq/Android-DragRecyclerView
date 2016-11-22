package cn.cyan.dragrecyclerview;

/**
 * Desc : item改变
 * User : Cyan(baocq@maritech.cn)
 * New  : 2016/9/28 8:41
 */
public interface OnItemChangeListener {

    /* 是否可拖动 */
    boolean onItemDrag(int position);

    /* item移动 */
    void onItemMoved(int form, int target);
}
