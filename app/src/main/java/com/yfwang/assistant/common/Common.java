package com.yfwang.assistant.common;

import android.app.Activity;

import java.util.ArrayList;

/**
 * Description:
 * Copyright  : Copyright (c) 2017
 * Author     : yfwang
 * Date       : 2017/10/9 16:31
 */
public class Common {
    private static Common instance;
    // Activity栈
    private static ArrayList<Activity> activityList;
    // 获取单例
    public static Common getInstance() {
        synchronized (Common.class) {
            if (instance == null) {
                instance = new Common();
                activityList = new ArrayList<>();
            }
        }

        return instance;
    }

    // 把Activity添加进栈
    public void addActivity(Activity activity) {
        activityList.add(activity);
    }

    // 把Activity从栈中移除
    public void removeActivity(Activity activity) {
        try {
            activityList.remove(activity);
        } catch (Exception e) {

        }
    }

    // 结束栈中的所有Activity
    public void finishAllActivity() {
        if (activityList != null && activityList.size() != 0) {
            for (int i = activityList.size() - 1; i >= 0; i--) {
                try {
                    activityList.get(i).finish();
                } catch (Exception e) {

                }
            }
            activityList.clear();
        }
    }


}
