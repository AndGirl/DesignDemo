package com.ybj.designdemo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.FileOutputStream;

/**
 * Created by 杨阳洋 on 2018/1/4.
 */

public class DiskCache implements ImageCache{
    static String cacheDir = "sdcard/cache";

    @Override
    public Bitmap get(String url){
        return BitmapFactory.decodeFile(cacheDir + url);
    }

    @Override
    public void put(String url ,Bitmap bitmap){
        FileOutputStream mFileOutputStream = null;
        try {
            mFileOutputStream = new FileOutputStream(cacheDir + url);
            bitmap.compress(Bitmap.CompressFormat.PNG,100,mFileOutputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            CloseUtils.closeQuietly(mFileOutputStream);
        }
    }

}
