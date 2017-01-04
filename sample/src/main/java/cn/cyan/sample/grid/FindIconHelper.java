package cn.cyan.sample.grid;

import android.support.annotation.StringRes;

import java.util.HashMap;
import java.util.Map;

import cn.cyan.sample.R;
import cn.cyan.sample.base.SampleApplication;

/**
 * User : Cyan(newbeeeeeeeee@gmail.com)
 * Date : 2017/1/4
 */

public class FindIconHelper {

    private static FindIconHelper instance;

    private Map<String, Integer> icons;

    public static FindIconHelper getInstance() {
        if (instance == null) {
            instance = new FindIconHelper();
        }
        return instance;
    }

    private FindIconHelper() {
        icons = new HashMap<>();
        icons.put(getStr(R.string.hot),R.mipmap.hot);
        icons.put(getStr(R.string.news_international),R.mipmap.news_international);
        icons.put(getStr(R.string.news),R.mipmap.news);
        icons.put(getStr(R.string.beauty),R.mipmap.beauty);
        icons.put(getStr(R.string.politics),R.mipmap.politics);
        icons.put(getStr(R.string.sports),R.mipmap.sports);
        icons.put(getStr(R.string.gossip),R.mipmap.gossip);
        icons.put(getStr(R.string.more),R.mipmap.more);
    }

    private String getStr(@StringRes int resId){
        return SampleApplication.getContext().getString(resId);
    }


    public int find(String name) {
        if (icons.keySet().contains(name)) {
            return icons.get(name);
        }
        return R.mipmap.ic_launcher;
    }
}
