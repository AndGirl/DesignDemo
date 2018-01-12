package com.ybj.designdemo.request;

import android.support.annotation.NonNull;
import android.widget.ImageView;

import com.ybj.designdemo.config.DisplayConfig;
import com.ybj.designdemo.core.UartUsingImageLoader;
import com.ybj.designdemo.policy.LoadPolicy;
import com.ybj.designdemo.utils.ImageViewHelper;
import com.ybj.designdemo.utils.Md5Helper;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

/**
 * Created by 杨阳洋 on 2018/1/12.
 * 图片加载请求类
 * 存储了一个ImageView,图片Uri，DisplayConfig和ImageListener
 * 请求前参数的配置，并没有实际上对其进行请求处理
 */

public class BitmapRequest implements Comparable<BitmapRequest>{

    Reference<ImageView> mImageViewRef;
    public DisplayConfig mDisplayConfig;
    public UartUsingImageLoader.ImageListener mImageListener;
    public String imageUri = "";
    public String imageUriMd5 = "";

    /**
     * 请求序列号
     */
    public int serialNum = 0;
    /**
     * 是否取消该请求
     */
    public boolean isCancel = false;

    /**
     * 仅仅使用内存缓存
     */
    public boolean justCacheInMem = false;

    /**
     * 加载策略
     */
    private LoadPolicy mLoadPolicy = new LoadPolicy();
    //LoadPolicy mLoadPolicy = SimpleImageLoader.getInstance().getConfig().loadPolicy;

    public BitmapRequest(ImageView imageView, String uri, DisplayConfig config,
                         UartUsingImageLoader.ImageListener listener){
        mImageViewRef = new WeakReference<ImageView>(imageView);
        mDisplayConfig = config;
        mImageListener = listener;
        imageUri = uri;
        imageUriMd5 = Md5Helper.toMD5(imageUri);
    }

    public void setLoadPolicy(LoadPolicy loadPolicy) {
        if(loadPolicy != null) {
            mLoadPolicy = loadPolicy;
        }
    }

    /**
     * 判断imageview的tag与uri是否相等
     *
     * @return
     */
    public boolean isImageViewTagValid() {
        return mImageViewRef.get() != null ? mImageViewRef.get().getTag().equals(imageUri) : false;
    }

    public ImageView getImageView() {
        return mImageViewRef.get();
    }

    public int getImageViewWidth() {
        return ImageViewHelper.getImageViewWidth(mImageViewRef.get());
    }

    public int getImageViewHeight() {
        return ImageViewHelper.getImageViewHeight(mImageViewRef.get());
    }


    @Override
    public int compareTo(@NonNull BitmapRequest o) {
        return mLoadPolicy.compare(this,o);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((imageUri == null) ? 0 : imageUri.hashCode());
        result = prime * result + ((mImageViewRef == null) ? 0 : mImageViewRef.get().hashCode());
        result = prime * result + serialNum;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        BitmapRequest other = (BitmapRequest) obj;
        if (imageUri == null) {
            if (other.imageUri != null)
                return false;
        } else if (!imageUri.equals(other.imageUri))
            return false;
        if (mImageViewRef == null) {
            if (other.mImageViewRef != null)
                return false;
        } else if (!mImageViewRef.get().equals(other.mImageViewRef.get()))
            return false;
        if (serialNum != other.serialNum)
            return false;
        return true;
    }

}
