package cn.cyan.sample.grid;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import cn.cyan.sample.R;
import cn.cyan.sample.base.SampleAdapter;
import cn.cyan.sample.base.SampleEntity;

/**
 * User : Cyan(newbeeeeeeeee@gmail.com)
 * Date : 2017/1/4
 */
public class GridAdapter extends SampleAdapter<GridAdapter.GridViewHolder> {

    public GridAdapter(Context context, List<SampleEntity> list) {
        super(context, list);
    }

    @Override
    public GridViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_grid, parent, false);
        return new GridViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(GridViewHolder holder, int position) {
        holder.setText(data.get(position).getText());
    }

    class GridViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;

        GridViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
            textView = (TextView) itemView.findViewById(R.id.textView);
        }

        void setText(String name) {
            imageView.setImageResource(FindIconHelper.getInstance().find(name));
            textView.setText(name);
        }

    }

}
