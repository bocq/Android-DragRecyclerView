package cn.cyan.sample.list;

import android.support.v7.widget.LinearLayoutManager;
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
public class ListFragment extends BaseFragment {
    @Override
    protected SampleAdapter adapter(List<SampleEntity> data) {
        return new ListAdapter(getActivity(),data);
    }

    @Override
    protected RecyclerView.LayoutManager layoutManager() {
        return new LinearLayoutManager(getActivity());
    }

    @Override
    protected List<SampleEntity> initData() {
        List<SampleEntity> result = new ArrayList<>();
        String[] strings = getResources().getStringArray(R.array.list_array);
        for (String s : strings) {
            SampleEntity item = new SampleEntity();
            item.setText(s);
            item.setDragEnable(true);
            item.setDropEnable(true);
            result.add(item);
        }
        return result;
    }
}
