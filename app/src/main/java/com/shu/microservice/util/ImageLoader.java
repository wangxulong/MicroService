package com.shu.microservice.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.util.LruCache;
import android.widget.ImageView;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by wxl on 2016/2/16.
 */
public class ImageLoader {
    private ImageView imageView;
    private String url;
    private LruCache<String, Bitmap> cache;
    public ImageLoader() {
        int maxMemory = (int) Runtime.getRuntime().maxMemory();
        int mCacheSize = maxMemory / 8;
        //给LruCache分配1/8 4M
        cache = new LruCache<String, Bitmap>(mCacheSize){
            //必须重写此方法，来测量Bitmap的大小
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getRowBytes() * value.getHeight();
            }

        };
    }

    public void addToCache(String key,Bitmap pic){
        if (getFromCache(key) == null && pic != null) {
            cache.put(key, pic);
        }
    }
    public Bitmap getFromCache(String key){
       return cache.get(key);
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(imageView.getTag().equals(url))
                imageView.setImageBitmap((Bitmap) msg.obj);
        }
    };
    public void showImageByThread(final ImageView imageView, final String url){
        this.imageView = imageView;
        this.url = url;
        new Thread(new Runnable() {
            @Override
            public void run() {
                Bitmap bitmap = getBitmapByUrl(url);
                Message message = Message.obtain();
                message.obj = bitmap;
                handler.sendMessage(message);
            }
        }).start();
    }
    public Bitmap getBitmapByUrl(String imageUrl){
        Bitmap bitmap =  getFromCache(imageUrl);
        if(null!=bitmap) return bitmap;
        InputStream inputStream = null;
        try {
            URLConnection connection =  new URL(imageUrl).openConnection();
            inputStream = new BufferedInputStream(connection.getInputStream());
            bitmap = BitmapFactory.decodeStream(inputStream);
            addToCache(imageUrl,bitmap);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if(null!=inputStream)inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return bitmap;
    }

    public void showImageByTask(ImageView imageView,String url){
        new ImageTask(imageView,url).execute(url);
    }
    class ImageTask extends AsyncTask<String,Void,Bitmap>{
        private ImageView imageView;
        private String url;
        public ImageTask(ImageView imageView,String url) {
            this.imageView = imageView;
            this.url = url;
        }

        @Override
        protected Bitmap doInBackground(String... params) {

            return getBitmapByUrl(params[0]);
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            if(imageView.getTag().equals(url))
                 imageView.setImageBitmap(bitmap);
        }
    }
}
