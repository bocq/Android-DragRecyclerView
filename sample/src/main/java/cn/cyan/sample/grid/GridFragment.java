package cn.cyan.sample.grid;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import cn.cyan.sample.R;
import cn.cyan.sample.base.BaseFragment;
import cn.cyan.sample.base.SampleAdapter;
import cn.cyan.sample.base.SampleEntity;

/**
 * User : Cyan(newbeeeeeeeee@gmail.com)
 * Date : 2017/1/4
 */
public class GridFragment extends BaseFragment {
    @Override
    protected SampleAdapter adapter(List<SampleEntity> data) {
        return new GridAdapter(getActivity(),data);
    }

    @Override
    protected RecyclerView.LayoutManager layoutManager() {
        return new GridLayoutManager(getActivity(),3);
    }

    @Override
    protected List<SampleEntity> initData() {
        List<SampleEntity> result = new ArrayList<>();
        String[] strings = getResources().getStringArray(R.array.grid_array);
        for (String s : strings) {
            SampleEntity item = new SampleEntity();
            item.setText(s);
            item.setDragEnable(true);
            item.setDropEnable(true);
            result.add(item);
        }
        // add more
        SampleEntity addMore = new SampleEntity();
        addMore.setText(getString(R.string.more));
        addMore.setDragEnable(false);
        addMore.setDragEnable(false);
        result.add(addMore);
        return result;
    }


}
