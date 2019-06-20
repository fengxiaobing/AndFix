package com.esite.myvideo.andfix;

import android.content.Context;

import com.alipay.euler.andfix.patch.PatchManager;
import com.esite.myvideo.Utils;

import java.io.IOException;

/**
 * Created by fxb on 2019/6/20.
 * 管理AndFix所有的App
 */

public class AndFixPathManager {
    private static AndFixPathManager mInstance = null;

    private static PatchManager mPatchManager = null;

    private AndFixPathManager() {
    }

    public static AndFixPathManager getInstance() {
        if (mInstance == null) {
            synchronized (AndFixPathManager.class) {
                if (mInstance == null) {
                    mInstance = new AndFixPathManager();
                }
            }
        }
        return mInstance;
    }
//初始化AndFix方法
    public void initPatch(Context context) {
        mPatchManager = new PatchManager(context);
        mPatchManager.init(Utils.getVersonName(context));
        mPatchManager.loadPatch();
    }
    //加载我们的patch文件
    public void addPatch(String path){
        try {
            if(mPatchManager!=null){
                mPatchManager.addPatch(path);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
