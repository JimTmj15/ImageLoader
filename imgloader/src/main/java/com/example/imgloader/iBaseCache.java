package com.example.imgloader;

/**
 * Created by Jimmy-PC on 20/8/2017.
 */

import android.graphics.Bitmap;

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
