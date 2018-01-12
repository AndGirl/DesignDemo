package com.ybj.designdemo.policy;

import com.ybj.designdemo.request.BitmapRequest;

/**
 * Created by 杨阳洋 on 2018/1/12.
 */

public class LoadPolicy {

    public LoadPolicy() {
    }

    public int compare(BitmapRequest bitmapRequest, BitmapRequest o) {
        return 0;
    }
}
