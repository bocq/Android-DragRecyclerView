package cn.cyan.dragrecyclerview;


/**
 * User : Cyan(newbeeeeeeeee@gmail.com)
 * Date : 2016/9/28
 */
public interface OnItemChangeListener {

    /* item can be dragged */
    boolean onItemDrag(int position);

    /* item moved */
    void onItemMoved(int form, int target);

    /* item can be dropped */
    boolean onItemDrop(int position);
}
