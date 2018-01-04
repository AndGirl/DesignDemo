package com.ybj.designdemo;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;

/**
 * Created by 杨阳洋 on 2018/1/4.
 */

public class ImageCaches {

    //内存缓存
    LruCache<String , Bitmap> mImageCache;

    public ImageCaches() {
        initCacheSize();
    }

    private void initCacheSize() {
        int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
        int cacheSize = maxMemory / 4;
        mImageCache = new LruCache<String,Bitmap>(cacheSize){
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getRowBytes() * value.getHeight() / 1024;
            }
        };
    }

    public void put(String url,Bitmap bitmap){
        mImageCache.put(url,bitmap);
    }

    public Bitmap get(String url){
        return mImageCache.get(url);
    }

}
