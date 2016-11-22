package cn.cyan.dragtab;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cn.cyan.dragrecyclerview.DragRecyclerView;
import cn.cyan.dragrecyclerview.HoldTouchHelper;
import cn.cyan.dragtab.tab.DividerGridItemDecoration;
import cn.cyan.dragtab.tab.Tab;
import cn.cyan.dragtab.tab.TabAdapter;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initTabs();
    }

    private void initTabs() {
        DragRecyclerView dragRecyclerView = (DragRecyclerView) findViewById(R.id.drvTab);
        // 网格线
        dragRecyclerView.addItemDecoration(new DividerGridItemDecoration());
        // 不固定大小
        dragRecyclerView.setHasFixedSize(false);
        // 布局管理器
        dragRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));

        /** 自定义属性 */
        dragRecyclerView
                .dragEnable(true)
                .showDragAnimation(true)
                .setDragAdapter(new TabAdapter(this, getTabs()))
                .bindEvent(onItemTouchEvent);
    }

    HoldTouchHelper.OnItemTouchEvent onItemTouchEvent = new HoldTouchHelper.OnItemTouchEvent() {
        @Override
        public void onLongPress(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, int position) {
            if (((TabAdapter) recyclerView.getAdapter()).onItemDrag(position)) {
                ((DragRecyclerView) recyclerView).startDrag(position);
            }
        }

        @Override
        public void onItemClick(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, int position) {
            String tab = ((TabAdapter) recyclerView.getAdapter()).getUsing().get(position).getName();
            Toast.makeText(MainActivity.this, tab, Toast.LENGTH_SHORT).show();
        }
    };


    private List<Tab> getTabs() {
        List<Tab> result = new ArrayList<>();
        String[] set = getResources().getStringArray(R.array.tabs);
        for (Object tab : set) {
            Tab item = new Tab();
            item.setName(tab.toString());
            item.setDragEnable(true);
            result.add(item);
        }
        // 添加更多
        Tab addMore = new Tab();
        addMore.setName("添加更多");
        addMore.setDragEnable(false);
        result.add(addMore);
        return result;
    }
}
