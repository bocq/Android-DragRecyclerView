package cn.cyan.dragtab.tab;

/**
 * Desc : 按钮
 * User : Cyan(baocq@maritech.cn)
 * New  : 2016/11/21 14:23
 */

public class Tab {

    private String name;
    private boolean dragEnable;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isDragEnable() {
        return dragEnable;
    }

    public void setDragEnable(boolean dragEnable) {
        this.dragEnable = dragEnable;
    }
}
