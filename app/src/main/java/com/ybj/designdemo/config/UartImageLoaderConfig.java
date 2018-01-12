package com.ybj.designdemo.config;

import com.ybj.designdemo.MemoryCache;
import com.ybj.designdemo.cache.UartBitmapCache;

/**
 * Created by 杨阳洋 on 2018/1/9.
 * 加载中的图片
 * 加载失败的图片
 * 缓存策略
 */

public class UartImageLoaderConfig {

    /**
     * 配置缓存方式
     */
    public UartBitmapCache bitmapCache = (UartBitmapCache) new MemoryCache();

    /**
     * 加载图片时的loading和加载失败的配置
     */
    public DisplayConfig displayConfig = new DisplayConfig();

}
