package com.ybj.designdemo.cache;

import android.content.Context;
import android.graphics.Bitmap;

/**
 * Created by 杨阳洋 on 2018/1/9.
 * 磁盘缓存
 */

public class UartDiskCache implements UartBitmapCache{
    /**
     * 1MB
     */
    private static final int MB = 1024 * 1024;

    /**
     * cache dir
     */
    private static final String IMAGE_DISK_CACHE = "bitmap";
    /**
     * Disk LRU Cache
     */
    private DiskLruCache mDiskLruCache;
    /**
     * Disk Cache Instance
     */
    private static UartDiskCache mUartDiskCache;

    public UartDiskCache(Context context){
        initDiskCache(context);
    }

    /**
     * 初始化磁盘缓存
     * @param context
     */
    private void initDiskCache(Context context) {

    }

    /**
     * 获取磁盘缓存
     * @param context
     */
    public static UartDiskCache getDiskCache(Context context) {
        if(mUartDiskCache == null) {
            synchronized (UartDiskCache.class){
                if(mUartDiskCache == null) {
                    mUartDiskCache = new UartDiskCache(context);
                }
            }
        }
        return mUartDiskCache;
    }

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
