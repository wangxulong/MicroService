package com.shu.microservice.util;

import android.graphics.Bitmap;
import android.util.LruCache;

import com.android.volley.toolbox.*;
import com.android.volley.toolbox.ImageLoader;

/**
 * Created by wxl on 2016/2/25.
 */
public class BitmapCache implements ImageLoader.ImageCache {
    private LruCache<String,Bitmap> mCache;

    public BitmapCache() {
        int maxMemory = (int) Runtime.getRuntime().maxMemory();
        int mCacheSize = maxMemory / 8;
        //给LruCache分配1/8 4M
        mCache = new LruCache<String, Bitmap>(mCacheSize){
            //必须重写此方法，来测量Bitmap的大小
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getRowBytes() * value.getHeight();
            }

        };
    }

    @Override
    public Bitmap getBitmap(String s) {
        LogUtil.v("BitmapCache","from cache");
        return mCache.get(s);
    }

    @Override
    public void putBitmap(String s, Bitmap bitmap) {
        LogUtil.v("BitmapCache","add to cache");
        mCache.put(s,bitmap);
    }
}
