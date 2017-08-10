# Android-DragRecyclerView
基于RecyclerView实现的可拖动列表，支持Grid及List风格。A draggable list based on the RecyclerView implementation that supports the Grid and List styles.

### Demo

![demo](http://i.imgur.com/0l8Dnbx.gif)


### Usage

##### Setup 1
    compile 'cn.cyan.dragrecyclerview:dragrecyclerview:1.0.3'

##### Setup 2

    <cn.cyan.dragrecyclerview.DragRecyclerView
       android:id="@+id/drv"
       android:layout_width="match_parent"
       android:layout_height="match_parent"/>

##### Setup 3

    dragRecyclerView
                .dragEnable(true)
                .showDragAnimation(true)
                .setDragAdapter(adapter(data)) // The adapter must implement OnItemChangeListener
                .bindEvent(onItemTouchEvent);

### License

    MIT licensed

