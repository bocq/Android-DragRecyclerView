# Android-DragRecyclerView
基于RecyclerView实现的可拖动按钮组，支持Grid及List风格。Based on the RecyclerView implementation of the drag button group, support for Grid and List style.

### Demo
![demo](http://i.imgur.com/hi1LrTx.gif)

### Usage

##### Setup 1
    compile 'cn.cyan.dragrecyclerview:dragrecyclerview:1.0.2'

##### Setup 2
    <cn.cyan.dragrecyclerview.DragRecyclerView
       android:id="@+id/drvDemo"
       android:layout_width="match_parent"
       android:layout_height="match_parent"/>
##### Setup 3
*don not forget to set up the layout manager*

    dragRecyclerView
                .dragEnable(true)
                .showDragAnimation(true)
                .bindEvent(onItemTouchEvent)
                .setAdapter(new TabAdapter(this, getTabs()));
### License
    Copyright 2016 Cyan
    
    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at
    
      http://www.apache.org/licenses/LICENSE-2.0
    
    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

