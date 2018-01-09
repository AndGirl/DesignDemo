package com.ybj.designdemo;

import java.io.Closeable;

/**
 * Created by 杨阳洋 on 2018/1/9.
 */

public class CloseUtils {

    private CloseUtils(){

    }

    /**
     * 关闭closeable对象
     * @param closeable
     */
    public static void closeQuietly(Closeable closeable){
        if(closeable != null) {
            try {
                closeable.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

}
