package com.esite.myvideo.andfix;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.annotation.Nullable;

import java.io.File;

/**
 * Created by fxb on 2019/6/20.
 * 1、检查patch文件
 * 2、下载patch文件
 * 3、加载patch文件
 */

public class AndFixService extends Service {
    private static final String TAG = AndFixService.class.getSimpleName();
    private static final String FILE_END = ".apatch";
    private static final int UPDATE_PATCH = 0x02;
    private static final int DOWNLOAD_PATCH = 0x01;
    private String mPatchFileDir;
    private String mPatchFile;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case UPDATE_PATCH:
                    checkPatchUpdate();
                    break;
                case DOWNLOAD_PATCH:
                    downloadPatch();
                    break;
            }

        }
    };




    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        init();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        mHandler.sendEmptyMessage(UPDATE_PATCH);

        //START_NOT_STICKY 代表被系统回收后不会自动重启
        return START_NOT_STICKY;
    }

    //完成文件目录构造
    private void init() {
        mPatchFileDir = getExternalCacheDir().getAbsolutePath() + "/apatch/";
        File patchDir = new File(mPatchFileDir);
        try {
            if (patchDir == null && !patchDir.exists()) {
                patchDir.mkdir();
            }
        } catch (Exception e) {
            e.printStackTrace();
            stopSelf();
        }
    }
    //检查服务器是否有patch
    private void checkPatchUpdate() {
        //这里就是网络请求
        /**
         * 1、先进行网络请求，如果失败 就终止（stopSelf();），如果成功就判断版本号，开始下载
         */
        mHandler.sendEmptyMessage(DOWNLOAD_PATCH);
    }
    private void downloadPatch() {
        //这里是网络下载   先模拟 这个是下载的文件名称
        mPatchFile =  mPatchFileDir.concat("bing").concat(FILE_END);

        //下载成功之后  加载补丁
        AndFixPathManager.getInstance().addPatch(mPatchFile);
    }
}
