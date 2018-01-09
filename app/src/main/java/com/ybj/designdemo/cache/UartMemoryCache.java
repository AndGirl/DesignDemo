package com.ybj.designdemo.cache;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;

/**
 * Created by 杨阳洋 on 2018/1/9.
 * 内存缓存
 */

public class UartMemoryCache implements UartBitmapCache{

    private LruCache<String ,Bitmap> mMemoryCache;
    private final int mMaxMemory;
    private int mSize;

    public UartMemoryCache() {
        // 计算可使用的最大内存
        mMaxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);

        // 取4分之一的可用内存作为缓存
        final int cacheSize = mMaxMemory / 4;
        mMemoryCache = new LruCache<String, Bitmap>(cacheSize) {

            @Override
            protected int sizeOf(String key, Bitmap bitmap) {
                mSize = bitmap.getRowBytes() * bitmap.getHeight() / 1024;
                return mSize;
            }
        };
    }

    @Override
    public Bitmap get(String key) {
        return mMemoryCache.get(key);
    }

    @Override
    public void set(String key, Bitmap bitmap) {
        mMemoryCache.put(key,bitmap);
    }

    @Override
    public int size() {
        return mSize;
    }

    @Override
    public int maxSize() {
        return mMaxMemory;
    }

    @Override
    public void clear() {
        mMemoryCache.evictAll();
    }

    @Override
    public void clearKeyUri(String keyPrefix) {
        mMemoryCache.remove(keyPrefix);
    }

}
