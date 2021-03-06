package com.ybj.designdemo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Looper;
import android.widget.ImageView;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by 杨阳洋 on 2018/1/4.
 */

public class ImageLoader {

    //图片缓存
    ImageCache mImageCache = new MemoryCache();
    //线程池，数量为CPU的数量
    ExecutorService fixedThreadPool =
            Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    Handler mUIHandler = new Handler(Looper.getMainLooper());

    /**
     * 设置缓存策略
     * @param imageCache
     */
    public void setImageCache(ImageCache imageCache) {
        mImageCache = imageCache;
    }

    public void displayImage(final String url, final ImageView imageView){
        Bitmap bitmap = mImageCache.get(url);
        if(bitmap != null) {
            imageView.setImageBitmap(bitmap);
            return;
        }
        submitLoadRequest(url, imageView);
    }

    private void submitLoadRequest(final String url, final ImageView imageView) {
        imageView.setTag(url);
        fixedThreadPool.submit(new Runnable() {
            @Override
            public void run() {
                Bitmap bitmap = downloadImage(url);
                if(bitmap == null) {
                    return;
                }
                if(imageView.getTag().equals(url)) {
                    updateImageView(imageView,bitmap);
                }
                mImageCache.put(url,bitmap);
            }
        });
    }

    public void updateImageView(final ImageView imageView, final Bitmap bitmap){
        mUIHandler.post(new Runnable() {
            @Override
            public void run() {
                imageView.setImageBitmap(bitmap);
            }
        });
    }

    public Bitmap downloadImage(String imageUrl){
        Bitmap bitmap = null;
        HttpURLConnection urlConnection = null;
        try{
            URL url = new URL(imageUrl);
            urlConnection = (HttpURLConnection) url.openConnection();
            bitmap = BitmapFactory.decodeStream(urlConnection.getInputStream());
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            urlConnection.disconnect();
        }
        return bitmap;
    }

}
