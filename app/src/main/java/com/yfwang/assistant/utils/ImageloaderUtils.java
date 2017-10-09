package com.yfwang.assistant.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.DrawableRes;
import android.support.annotation.IdRes;
import android.view.View;
import android.widget.ImageView;

import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.display.BitmapDisplayer;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import com.yfwang.assistant.R;
import com.yfwang.assistant.global.MobileApplication;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * author ：minifly
 * date: 2017/9/29
 * time: 16:34
 * desc: imageloader的封装
 */
public class ImageloaderUtils {

    public static final String DRAWABLE_PREFIX = "drawable://";
    public static final String FILE_PREFIX = "file://";


    public static ImageLoaderConfiguration initBaseOption(){
        return initBaseOption(MobileApplication.getInstance().getApplicationContext());
    }

    public static ImageLoaderConfiguration initBaseOption(Context mContext){
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(mContext)
                // 设置内存缓存大小
                .threadPriority(Thread.NORM_PRIORITY)
                // 设置线程优先级
                .diskCacheSize(200 * 1024 * 1024).memoryCacheSize(500 * 1024 * 1024)
                .denyCacheImageMultipleSizesInMemory().threadPoolSize(4).tasksProcessingOrder(QueueProcessingType.LIFO)
                .memoryCache(new WeakMemoryCache()).memoryCacheSizePercentage(60)
                .build();
        return config;
    }



    //显示配置
    public static DisplayImageOptions initImageLoaderOption(){
        DisplayImageOptions mOptions = new DisplayImageOptions.Builder().cacheOnDisk(true) // 设置下载的图片是否缓存在SD卡中
                .cacheInMemory(true)
                .imageScaleType(ImageScaleType.EXACTLY)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .showImageForEmptyUri(R.drawable.picture_bg)
                .showImageOnFail(R.drawable.picture_bg)
                .build();
        return mOptions;
    }

    //显示配置
    public static DisplayImageOptions initImageLoaderOption(BitmapDisplayer bitmapDisplayer){
        DisplayImageOptions mRoundOptions = new DisplayImageOptions.Builder().displayer(bitmapDisplayer) // 设置成圆角图片
                .cacheOnDisk(true) // 设置下载的图片是否缓存在SD卡中
                .cacheInMemory(true)
                .imageScaleType(ImageScaleType.EXACTLY)
                .showImageForEmptyUri(R.drawable.picture_bg)
                .showImageOnFail(R.drawable.picture_bg)
                .build();

        return mRoundOptions;
    }

    //显示配置
    public static DisplayImageOptions initImageLoaderOption(@IdRes int emptyRes , @IdRes int errorRes){
        DisplayImageOptions mOptions = new DisplayImageOptions.Builder().cacheOnDisk(true) // 设置下载的图片是否缓存在SD卡中
                .cacheInMemory(true)
                .imageScaleType(ImageScaleType.EXACTLY)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .showImageForEmptyUri(emptyRes)
                .showImageOnFail(errorRes)
                .build();
        return mOptions;
    }

    //显示配置
    public static DisplayImageOptions initImageLoaderOptionNoEmptyError(){
        DisplayImageOptions mOptions = new DisplayImageOptions.Builder().cacheOnDisk(true) // 设置下载的图片是否缓存在SD卡中
                .cacheInMemory(true)
                .imageScaleType(ImageScaleType.EXACTLY)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .build();
        return mOptions;
    }

    //显示配置
    public static DisplayImageOptions initImageLoaderOption(int emptyRes , int errorRes , BitmapDisplayer bitmapDisplayer){
        DisplayImageOptions mRoundOptions = new DisplayImageOptions.Builder().displayer(bitmapDisplayer) // 设置成圆角图片
                .cacheOnDisk(true) // 设置下载的图片是否缓存在SD卡中
                .cacheInMemory(true)
                .imageScaleType(ImageScaleType.EXACTLY)
                .showImageForEmptyUri(emptyRes)
                .showImageOnFail(errorRes)
                .build();

        return mRoundOptions;
    }




    public static ImageLoader getImageLoader(ImageLoaderConfiguration configuration){
        ImageLoader mImageLoader = ImageLoader.getInstance();
        mImageLoader.init(configuration);
        return mImageLoader;
    }

    public static ImageLoader getImageLoader(Context mContext){
        return getImageLoader(initBaseOption(mContext));
    }

    public static ImageLoader getImageLoader(){
        return getImageLoader(MobileApplication.getInstance().getApplicationContext());
    }

    // 显示图片
    public static void displayImg(String url, ImageView imgV) {
        try {
            displayImg(ImageloaderUtils.getImageLoader(),url, imgV, ImageloaderUtils.initImageLoaderOption(), new AnimateFirstDisplayListener());
        } catch (Exception e) {
        }
    }

    // 显示图片
    public static void displayImg(ImageLoader mImageLoader, String url, ImageView imgV) {
        try {
            displayImg(mImageLoader,url, imgV, ImageloaderUtils.initImageLoaderOption(), new AnimateFirstDisplayListener());
        } catch (Exception e) {

        }
    }

    // 显示图片
    public static void displayImg(ImageLoader mImageLoader, String url, ImageView imgV, DisplayImageOptions mOptions, AnimateFirstDisplayListener mAnimateFirstListener) {
        try {
            mImageLoader.displayImage(url, imgV, mOptions, mAnimateFirstListener);
        } catch (Exception e) {

        }
    }

    // 显示圆角图片
    public void displayRoundImg(String url, ImageView imgV) {
        try {
            displayRoundImg(getImageLoader(),url,imgV);
        } catch (Exception e) {
        }
    }


    // 显示圆角图片
    public void displayRoundImg(ImageLoader mRoundImageLoader, String url, ImageView imgV) {
        try {
            displayRoundImg(mRoundImageLoader,url,imgV,initImageLoaderOption(new RoundedBitmapDisplayer(8)), new AnimateFirstDisplayListener());
        } catch (Exception e) {
        }
    }

    // 显示圆角图片
    public void displayRoundImg(ImageLoader mRoundImageLoader, String url, ImageView imgV, DisplayImageOptions mRoundOptions, AnimateFirstDisplayListener mAnimateFirstListener) {
        try {
            mRoundImageLoader.displayImage(url, imgV, mRoundOptions, mAnimateFirstListener);
        } catch (Exception e) {
        }
    }


    // 显示圆角图片
    public void displayRoundImgPixel(String url, ImageView imgV, int roundPixel) {
        try {
            displayImg(getImageLoader(),url,imgV,initImageLoaderOption(new RoundedBitmapDisplayer(roundPixel)),new AnimateFirstDisplayListener());
        } catch (Exception e) {

        }
    }



    //url 获取 bitmap
    public static Bitmap loadImage(ImageLoader mImageLoader , String url, DisplayImageOptions mOptions) {
        return mImageLoader.loadImageSync(url, mOptions);
    }

    //url 获取 bitmap
    public static Bitmap loadImage(String url,DisplayImageOptions mOptions) {
        return ImageloaderUtils.loadImage(ImageloaderUtils.getImageLoader(),url, mOptions);
    }


    public static void displayFileImage(String filePath, ImageView imageView) {
        displayImg(FILE_PREFIX + filePath, imageView);
    }

    /**
     * 加载drawable资源图片
     */
    public static void displayDrawableImage(@DrawableRes int drawableId, ImageView imageView) {
        displayImg(DRAWABLE_PREFIX + drawableId, imageView);
    }



    public static class AnimateFirstDisplayListener extends SimpleImageLoadingListener {

        static final List<String> displayedImages = Collections.synchronizedList(new LinkedList<String>());

        public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
            if (loadedImage != null) {
                ImageView imageView = (ImageView) view;
                boolean firstDisplay = !displayedImages.contains(imageUri);
                if (firstDisplay) {
                    FadeInBitmapDisplayer.animate(imageView, 500);
                    displayedImages.add(imageUri);
                }
            }
        }
    }


}
