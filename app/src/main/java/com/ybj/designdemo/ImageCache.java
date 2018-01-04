package com.ybj.designdemo;

import android.graphics.Bitmap;

/**
 * Created by 杨阳洋 on 2018/1/4.
 */

public interface ImageCache {

    public Bitmap get(String url);
    public void put(String url,Bitmap bitmap);

}
