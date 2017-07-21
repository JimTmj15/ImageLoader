package com.example.jimmy_pc.imageloader.Utils.ImageLoader;

import android.graphics.Bitmap;

/**
 * Created by Jimmy-PC on 20/7/2017.
 */

/**
 *
 * Define necessary methods for cache
 */
public interface iBaseCache {
    Bitmap getBitmapFromCache(String key);
    void addBitmapToCache(String key, Bitmap bitmap);
    boolean isContentSaved(String key);
    void clearMemory();
}
