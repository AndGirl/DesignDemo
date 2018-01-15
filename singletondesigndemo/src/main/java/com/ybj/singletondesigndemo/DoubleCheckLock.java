package com.ybj.singletondesigndemo;

/**
 * Created by 杨阳洋 on 2018/1/12.
 * 第一层判空避免了不必要的同步
 * 第二层判空
 *
 * 注意：在高并发的情况下会出现失效的问题
 */

public class DoubleCheckLock {

    //保证mInstance每次都是从主内存中读取
    private static volatile DoubleCheckLock mInstance;
    private DoubleCheckLock(){}

    public static DoubleCheckLock getmInstance(){
        if(mInstance == null) {
            synchronized (DoubleCheckLock.class){
                if(mInstance == null) {
                    mInstance = new DoubleCheckLock();
                }
            }
        }
        return mInstance;
    }

}
