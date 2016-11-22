package cn.cyan.dragtab.tab;

import java.util.HashMap;
import java.util.Map;

import cn.cyan.dragtab.R;

/**
 * Desc : 获取图标的辅助
 * User : Cyan(baocq@maritech.cn)
 * New  : 2016/11/4 14:56
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
        icons.put("今日热点",R.mipmap.hot);
        icons.put("国际新闻",R.mipmap.news_international);
        icons.put("及时快讯",R.mipmap.news);
        icons.put("美女图片",R.mipmap.woman);
        icons.put("政经要闻",R.mipmap.politics);
        icons.put("体育赛事",R.mipmap.sports);
        icons.put("茶余饭后",R.mipmap.tea);
        icons.put("添加更多",R.mipmap.add);
    }


    /**
     * 根据按钮名称获取按钮的icon
     *
     * @param name 按钮名
     * @return 按钮的resId
     */
    public int find(String name) {
        if (icons.keySet().contains(name)) {
            return icons.get(name);
        }
        return R.mipmap.ic_launcher;
    }
}
