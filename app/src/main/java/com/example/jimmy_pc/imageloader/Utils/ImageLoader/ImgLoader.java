package com.example.jimmy_pc.imageloader.Utils.ImageLoader;

import android.content.ContentProvider;
import android.content.Context;
import android.graphics.Matrix;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Jimmy-PC on 19/7/2017.
 */


public class ImgLoader implements iImgContentLoader {
    private static ImgLoader imgLoader;

    public static ImgLoader getInstance(){
        if (imgLoader == null){
            imgLoader = new ImgLoader();
        }
        return imgLoader;
    }

    /**
     *
     * This method is to load image from url given into an image view.
     * If the content is saved in the cache, it will load directly from the
     * cache into the image view rather than get from remote server.
     *
     * @param context
     * @param imageView
     * @param url
     */
    @Override
    public void loadImage(Context context, ImageView imageView, String url) {
        if(!LruCache.getInstance().isContentSaved(url)){
            NetworkService imageLoader = new NetworkService(context, imageView);
            imageLoader.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, url);
        }else{
            imageView.setImageBitmap(LruCache.getInstance().getBitmapFromCache(url));
        }
    }
}
