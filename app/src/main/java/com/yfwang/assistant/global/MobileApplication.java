package com.yfwang.assistant.global;

import android.app.Application;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.yfwang.assistant.utils.ImageloaderUtils;


/**
 * Description: 全局Application
 * Copyright  : Copyright (c) 2016
 * Author     : yfwang
 * Date       : 2017/4/8 10:59
 */
public class MobileApplication extends Application {

    private static MobileApplication mobileApplication;

    public static MobileApplication getInstance() {
        return mobileApplication;
    }

    DisplayImageOptions mOptions;
    DisplayImageOptions mRoundOptions;
    ImageLoader mImageLoader = null;
    ImageLoader mRoundImageLoader = null;
    ImageLoadingListener mAnimateFirstListener = null;


    @Override
    public void onCreate() {
        super.onCreate();
        mobileApplication = this;
        // 初始化ImageLoader
        initImageLoader();
    }

    private void initImageLoader() {
        mImageLoader = ImageloaderUtils.getImageLoader();

        mRoundImageLoader = ImageloaderUtils.getImageLoader();

        mRoundOptions = ImageloaderUtils.initImageLoaderOption(new RoundedBitmapDisplayer(8));

        mOptions = ImageloaderUtils.initImageLoaderOption();

        mAnimateFirstListener = new ImageloaderUtils.AnimateFirstDisplayListener();

    }


    // 获取版本号
    public String getVersionCode() {
        PackageManager packageManager = getPackageManager();
        PackageInfo packageInfo;
        String versionCode = "";
        try {
            packageInfo = packageManager.getPackageInfo(getPackageName(), 0);
            versionCode = packageInfo.versionCode + "";
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionCode;
    }

    // 获取版本名
    public String getVersionName() {
        PackageManager packageManager = getPackageManager();
        PackageInfo packageInfo;
        String versionName = "";
        try {
            packageInfo = packageManager.getPackageInfo(getPackageName(), 0);
            versionName = packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionName;
    }

}
