package com.ybj.designdemo.cache;

import android.graphics.Bitmap;

/**
 * Created by 杨阳洋 on 2018/1/9.
 * 没有缓存
 */

public class UartNoneCache implements UartBitmapCache{
    @Override
    public Bitmap get(String key) {
        return null;
    }

    @Override
    public void set(String key, Bitmap bitmap) {

    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public int maxSize() {
        return 0;
    }

    @Override
    public void clear() {

    }

    @Override
    public void clearKeyUri(String keyPrefix) {

    }
}
