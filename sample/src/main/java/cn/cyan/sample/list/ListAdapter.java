package cn.cyan.sample.list;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import cn.cyan.dragrecyclerview.OnItemChangeListener;
import cn.cyan.sample.R;
import cn.cyan.sample.base.SampleAdapter;
import cn.cyan.sample.base.SampleEntity;

/**
 * User : Cyan(newbeeeeeeeee@gmail.com)
 * Date : 2017/1/4
 */
public class ListAdapter extends SampleAdapter<ListAdapter.ListViewHolder> implements OnItemChangeListener {
    public ListAdapter(Context context, List<SampleEntity> list) {
        super(context, list);
    }

    @Override
    public ListAdapter.ListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_list, parent, false);
        return new ListAdapter.ListViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ListAdapter.ListViewHolder holder, int position) {
        holder.setText(data.get(position).getText());
    }

    class ListViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        ListViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.textView);
        }

        void setText(String name) {
            textView.setText(name);
        }

    }
}
