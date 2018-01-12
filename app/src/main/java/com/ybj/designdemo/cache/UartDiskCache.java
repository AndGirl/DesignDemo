package com.ybj.designdemo.cache;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Environment;
import android.util.Log;

import com.ybj.designdemo.CloseUtils;
import com.ybj.designdemo.ImageResizer;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by 杨阳洋 on 2018/1/9.
 * 磁盘缓存
 */

public class UartDiskCache implements UartBitmapCache {
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
    private ImageResizer mImageResizer = new ImageResizer();

    public UartDiskCache(Context context) {
        initDiskCache(context);
    }

    /**
     * 初始化磁盘缓存
     *
     * @param context
     */
    private void initDiskCache(Context context) {
        try {
            File cacheDir = getDiskCacheDir(context, IMAGE_DISK_CACHE);
            if (!cacheDir.exists()) {
                cacheDir.mkdirs();
            }
            mDiskLruCache = DiskLruCache.open(cacheDir, getAppVersion(context), 1, 50 * MB);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * @param context
     * @return
     */
    private int getAppVersion(Context context) {
        try {
            PackageInfo info = context.getPackageManager().getPackageInfo(context.getPackageName(),
                    0);
            return info.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return 1;
    }

    /**
     * 获取磁盘缓存
     *
     * @param context
     */
    public static UartDiskCache getDiskCache(Context context) {
        if (mUartDiskCache == null) {
            synchronized (UartDiskCache.class) {
                if (mUartDiskCache == null) {
                    mUartDiskCache = new UartDiskCache(context);
                }
            }
        }
        return mUartDiskCache;
    }

    /**
     * 获取缓存路径
     *
     * @param context
     * @param uniqueName
     * @return
     */
    public File getDiskCacheDir(Context context, String uniqueName) {
        String cachePath;
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            Log.d("", "### context : " + context + ", dir = " + context.getExternalCacheDir());
            cachePath = context.getExternalCacheDir().getPath();
        } else {
            cachePath = context.getCacheDir().getPath();
        }
        return new File(cachePath + File.separator + uniqueName);
    }

    @Override
    public Bitmap get(String key) {
        InputStream fileInputStream = null;
        try {
            fileInputStream = getInputStream(key);
            FileInputStream inputStream = (FileInputStream) fileInputStream;
            FileDescriptor fd = inputStream.getFD();
            Bitmap bitmap = mImageResizer.decodeSampledBitmapFromFileDescriptor(fd, 500, 500);
            if (bitmap != null) {
                return bitmap;
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            CloseUtils.closeQuietly(fileInputStream);
        }
        return null;
    }

    /**
     * MD5加密处理
     *
     * @param md5
     * @return
     */
    private InputStream getInputStream(String md5) {
        DiskLruCache.Snapshot snapshot;
        try {
            snapshot = mDiskLruCache.get(md5);
            if (snapshot != null) {
                return snapshot.getInputStream(0);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 存储图片
     *
     * @param key
     * @param bitmap
     */
    @Override
    public void set(String key, Bitmap bitmap) {
        DiskLruCache.Editor edit = null;
        try {
            edit = mDiskLruCache.edit(key);
            if(edit != null) {
                OutputStream outputStream = edit.newOutputStream(0);
                if(writeBitmapToDisk(bitmap,outputStream)) {
                    //写入磁盘
                    edit.commit();
                }else{
                    edit.abort();
                }
                IOUtil.closeQuietly(outputStream);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 写入磁盘缓存
     *
     * @param bitmap
     * @param outputStream
     * @return
     */
    private boolean writeBitmapToDisk(Bitmap bitmap, OutputStream outputStream) {
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream, 8 * 1024);
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bufferedOutputStream);
        boolean result = true;
        try {
            bufferedOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
            result = false;
        }
        return result;
    }

    @Override
    public int size() {
        return (int) mDiskLruCache.size();
    }

    @Override
    public int maxSize() {
        return (int) mDiskLruCache.getMaxSize();
    }

    @Override
    public void clear() {
        try {
            mDiskLruCache.delete();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void clearKeyUri(String keyPrefix) {
        try {
            mDiskLruCache.remove(keyPrefix);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
