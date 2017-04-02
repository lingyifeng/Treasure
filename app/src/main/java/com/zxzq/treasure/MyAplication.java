package com.zxzq.treasure;

import android.app.Application;

import com.zxzq.treasure.user.UserProf;

/**
 * Created by Administrator on 2017/4/1 0001.
 */

public class MyAplication extends Application {
    @Override
    public void onCreate() {
        UserProf.init(this);
        super.onCreate();

    }
}
