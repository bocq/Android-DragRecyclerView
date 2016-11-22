package cn.cyan.dragtab;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cn.cyan.dragrecyclerview.DragRecyclerView;
import cn.cyan.dragtab.tab.DividerGridItemDecoration;
import cn.cyan.dragtab.tab.Tab;
import cn.cyan.dragtab.tab.TabAdapter;

public class MainActivity extends AppCompatActivity {

    private DragRecyclerView dragRecyclerView;
    private TabAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initTabs();
    }

    private void initTabs() {
        dragRecyclerView = (DragRecyclerView) findViewById(R.id.drvTab);
        adapter = new TabAdapter(this, getTabs());
        // 网格线
        dragRecyclerView.addItemDecoration(new DividerGridItemDecoration());
        // 不固定大小
        dragRecyclerView.setHasFixedSize(false);
        /** 布局管理器 支持linerLayout风格 */
        dragRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        // 是否可拖动、是否显示拖动动画
        dragRecyclerView.setDragEnable(true).setShowDragAnimation(true).setDragAdapter(adapter);
        // 绑定事件
        HoldTouchHelper.bind(dragRecyclerView, onItemTouchEventListener);
    }

    HoldTouchHelper.OnItemTouchEventListener onItemTouchEventListener = new HoldTouchHelper.OnItemTouchEventListener() {
        @Override
        public void onLongPress(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, int position) {
            if (adapter.onItemDrag(position)) {
                dragRecyclerView.startDrag(viewHolder);
            }
        }

        @Override
        public void onItemClick(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, int position) {
            String tab = adapter.getUsing().get(position).getName();
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
