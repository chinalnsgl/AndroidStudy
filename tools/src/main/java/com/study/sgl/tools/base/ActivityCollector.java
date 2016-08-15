package com.study.sgl.tools.base;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Activity集合类,用于保存所有Activity,在 BaseActivity 中onCreate调用 addActivity
 * onDestroy 调用 removeActivity
 * 然后任何时候都可以调用 finishAllActivity 退出程序
 *
 * @author Song.gl
 * @version 2016 08 13 16:45
 */
public class ActivityCollector {

    public static List<Activity> activities = new ArrayList<>();

    public static void addActivity(Activity activity) {
        activities.add(activity);
    }

    public static void removeActivity(Activity activity) {
        activities.remove(activity);
    }

    public static void finishAllActivity() {
        for (Activity activity : activities) {
            if (!activity.isFinishing()) {
                activity.finish();
            }
        }
    }
}
