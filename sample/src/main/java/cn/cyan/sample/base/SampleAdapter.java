package cn.cyan.sample.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import java.util.Collections;
import java.util.List;

import cn.cyan.dragrecyclerview.OnItemChangeListener;

/**
 * User : Cyan(newbeeeeeeeee@gmail.com)
 * Date : 2017/1/4
 */
public abstract class SampleAdapter<T extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<T> implements OnItemChangeListener {
    protected Context context;
    protected volatile List<SampleEntity> data;

    public SampleAdapter(Context context, List<SampleEntity> list) {
        this.context = context;
        this.data = list;
    }

    @Override
    public void onItemMoved(int form, int target) {
        if (form < target) {
            // after
            for (int i = form; i < target; i++) {
                Collections.swap(data, i, i + 1);
            }
        } else {
            // before
            for (int i = form; i > target; i--) {
                Collections.swap(data, i, i - 1);
            }
        }
        notifyItemMoved(form, target);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public boolean onItemDrag(int position) {
        return data.get(position).isDragEnable();
    }

    @Override
    public boolean onItemDrop(int position) {
        return data.get(position).isDropEnable();
    }
}
