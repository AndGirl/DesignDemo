package com.ybj.designdemo;

import android.graphics.Bitmap;

/**
 * Created by 杨阳洋 on 2018/1/4.
 */

public class DoubleCache implements ImageCache{

    //内存缓存
    ImageCaches mImageCache = new ImageCaches();
    //SD卡缓存
    DiskCache mDiskCache = new DiskCache();

    @Override
    public Bitmap get(String url){
        Bitmap bitmap = mImageCache.get(url);
        if(bitmap == null) {
            bitmap = mDiskCache.get(url);
        }
        return bitmap;
    }

    @Override
    public void put(String url,Bitmap bitmap){
        mImageCache.put(url,bitmap);
        mDiskCache.put(url,bitmap);
    }

}
