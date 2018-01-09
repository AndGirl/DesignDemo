package com.ybj.designdemo.cache;

import android.graphics.Bitmap;

/**
 * Created by 杨阳洋 on 2018/1/9.
 * 读取，大小，最大缓存值，清空，清空特定的Url
 */

public interface UartBitmapCache {
    /**
     * 读取图片
     * @param key
     * @return
     */
    Bitmap get(String key);
    /**
     * 存取图片
     * @param key
     * @param bitmap
     */
    void set(String key, Bitmap bitmap);
    /**
     * 返回当前缓存大小
     * @return
     */
    int size();

    /**
     * 返回最大缓存
     * @return
     */
    int maxSize();

    /**
     * 清空缓存
     */
    void clear();

    /**
     * 移出特定的缓存
     * @param keyPrefix
     */
    void clearKeyUri(String keyPrefix);

}
