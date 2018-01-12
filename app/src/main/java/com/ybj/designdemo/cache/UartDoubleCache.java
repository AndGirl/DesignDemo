package com.ybj.designdemo.cache;

import android.content.Context;
import android.graphics.Bitmap;

/**
 * Created by 杨阳洋 on 2018/1/9.
 * 磁盘缓存和内存缓存
 */

public class UartDoubleCache implements UartBitmapCache {

    /**
     * 创建两种缓存的类
     */
    UartDiskCache mUartDiskCache;
    UartMemoryCache mUartMemoryCache = new UartMemoryCache();

    public UartDoubleCache(Context context) {
        mUartDiskCache = UartDiskCache.getDiskCache(context);
    }

    /**
     * 读取的话先从内存中读取，在读磁盘
     * @param key
     * @return
     */
    @Override
    public Bitmap get(String key) {
        Bitmap bitmap = mUartMemoryCache.get(key);
        if(bitmap == null) {
            bitmap = mUartDiskCache.get(key);
            saveBitmapIntoMemory(key,bitmap);
        }
        return bitmap;
    }

    private void saveBitmapIntoMemory(String key,Bitmap bitmap){
        if(bitmap != null) {
            mUartMemoryCache.set(key,bitmap);
        }
    }

    @Override
    public void set(String key, Bitmap bitmap) {
        mUartDiskCache.set(key,bitmap);
        mUartMemoryCache.set(key,bitmap);
    }

    @Override
    public int size() {
        return mUartDiskCache.size() + mUartMemoryCache.size();
    }

    @Override
    public int maxSize() {
        return mUartDiskCache.maxSize() + mUartMemoryCache.maxSize();
    }

    @Override
    public void clear() {
        mUartDiskCache.clear();
        mUartMemoryCache.clear();
    }

    @Override
    public void clearKeyUri(String keyPrefix) {
        mUartDiskCache.clearKeyUri(keyPrefix);
        mUartMemoryCache.clearKeyUri(keyPrefix);
    }
}
