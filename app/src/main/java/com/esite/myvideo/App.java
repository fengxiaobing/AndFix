package com.esite.myvideo;

import android.app.Application;

import com.esite.myvideo.andfix.AndFixPathManager;

/**
 * Created by fxb on 2019/6/20.
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //完成AndFix的初始化
      initAndFix();
    }

    private void initAndFix() {
        AndFixPathManager.getInstance().initPatch(this);
    }

}
