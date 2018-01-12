package com.ybj.designdemo.core;

import android.graphics.Bitmap;
import android.widget.ImageView;

/**
 * Created by 杨阳洋 on 2018/1/12.
 */

public class UartUsingImageLoader {

    /**
     * 图片加载Listener
     *
     * @author mrsimple
     */
    public static interface ImageListener {
        public void onComplete(ImageView imageView, Bitmap bitmap, String uri);
    }

}
