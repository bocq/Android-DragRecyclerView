package cn.cyan.dragtab.tab;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

import cn.cyan.dragrecyclerview.OnItemChangeListener;
import cn.cyan.dragtab.R;

/**
 * Desc : 适配器
 * User : Cyan(baocq@maritech.cn)
 * New  : 2016/11/21 14:21
 */

public class TabAdapter extends RecyclerView.Adapter<TabAdapter.TabsViewHolder> implements OnItemChangeListener {
    private Context context;
    private volatile List<Tab> list;

    public TabAdapter(Context context, List<Tab> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public TabsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_tab, parent, false);
        return new TabsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(TabsViewHolder holder, int position) {
        holder.setName(list.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public boolean onItemDrag(int position) {
        return list.get(position).isDragEnable();
    }

    @Override
    public void onItemMoved(int form, int target) {
        if (form < target) {
            // 向后
            for (int i = form; i < target; i++) {
                Collections.swap(list, i, i + 1);
            }
        } else {
            // 向前
            for (int i = form; i > target; i--) {
                Collections.swap(list, i, i - 1);
            }
        }
        notifyItemMoved(form, target);
    }

    class TabsViewHolder extends RecyclerView.ViewHolder {
        ImageView ivIcon;
        TextView tvTab;

        TabsViewHolder(View itemView) {
            super(itemView);
            ivIcon = (ImageView) itemView.findViewById(R.id.ivIcon);
            tvTab = (TextView) itemView.findViewById(R.id.tvTab);
        }

        void setName(String name) {
            ivIcon.setImageResource(FindIconHelper.getInstance().find(name));
            tvTab.setText(name);
        }

    }

    public List<Tab> getUsing() {
        return list;
    }
}
