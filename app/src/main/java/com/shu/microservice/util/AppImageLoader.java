package com.shu.microservice.util;

import android.widget.ImageView;

import com.android.volley.toolbox.ImageLoader;
import com.shu.microservice.R;

/**
 * Created by wxl on 2016/2/25.
 */
public class AppImageLoader {
    private ImageView pic;
    private String picUrl;
    private static int defaultImage = R.mipmap.ic_launcher;
    private static int errorImage = R.mipmap.ic_launcher;

    public AppImageLoader(ImageView pic, String picUrl) {
        this.pic = pic;
        this.picUrl = picUrl;
    }
    public void loadImage(){
        ImageLoader imageLoader = new ImageLoader(AppContext.getAppQueue(),new BitmapCache());
        ImageLoader.ImageListener listener = ImageLoader.getImageListener(pic,defaultImage,errorImage);
        imageLoader.get(picUrl,listener);

    }
}
