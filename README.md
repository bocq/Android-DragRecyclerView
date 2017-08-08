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

    Copyright 2016 shadowhisk
    
    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at
    
      http://www.apache.org/licenses/LICENSE-2.0
    
    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

