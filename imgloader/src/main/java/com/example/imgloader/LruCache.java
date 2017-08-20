package com.example.imgloader;

/**
 * Created by Jimmy-PC on 20/8/2017.
 */

import android.graphics.Bitmap;
import android.util.Log;

/**
 *
 * Lru cache class handle the content with android LRU cache.
 */
public class LruCache implements iBaseCache{

    final String MEMTAG = "Cache";
    final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024); //Get the maximum memory by divide the runtime max memory with 1MB
    final int defaultCacheSize = maxMemory / 8; //Allocate smaller size of cache memory
    private static LruCache lruCache;
    private android.util.LruCache<String, Bitmap> mMemoryCache;

    public static LruCache getInstance(int cacheSize){
        if(lruCache == null){
            lruCache = new LruCache(cacheSize);
        }
        return lruCache;
    }

    public static LruCache getInstance(){
        if(lruCache == null){
            lruCache = new LruCache();
        }
        return lruCache;
    }


    private LruCache(int cacheSize){
        initCache(cacheSize);
    } //set assigned cache size

    private LruCache(){
        mMemoryCache = new android.util.LruCache<>(defaultCacheSize); //set default cache size
    }

    /**
     *
     * Get bitmap from cache by passing the key.
     * Return value if the key is found in the cache.
     *
     * @param key
     * @return Bitmap/null
     */
    @Override
    public Bitmap getBitmapFromCache(String key) {
        if(key != null){
            return mMemoryCache.get(key);
        }
        return null;
    }

    /**
     *
     * Add bitmap & key as indicator into cache memory,
     * given that the bitmap is never been saved into the
     * cache memory.
     *
     * @param key
     * @param bitmap
     */
    @Override
    public void addBitmapToCache(String key, Bitmap bitmap) {
        if (getBitmapFromCache(key) == null && lruCache != null) {
            mMemoryCache.put(key, bitmap);
        }
    }

    /**
     *
     * Check if the content been saved into cache memory.
     * Return true if found, else return false.
     *
     * @param key
     * @return true/false
     */
    @Override
    public boolean isContentSaved(String key) {
        Bitmap bitmap = getBitmapFromCache(key);
        if(bitmap == null) {
            return false;
        }
        return true;
    }

    /**
     * Clear the cache memory
     */
    @Override
    public void clearMemory() {
        mMemoryCache.trimToSize(0);
    }

    /**
     *
     * Initiate the cache with appropriate size.
     *
     * @param cacheSize
     */
    private void initCache(int cacheSize) {
        if(cacheSize == 0 || cacheSize < 0){
            Log.e(MEMTAG,"Invalid cache size");
            mMemoryCache = new android.util.LruCache<>(defaultCacheSize);
        }else{
            mMemoryCache  = new android.util.LruCache<>(getCacheSize(cacheSize));
        }
    }

    /**
     *
     * Calculate the appropriate cache size to be allocated after
     * compare with the maximum runtime memory available.
     *
     * @param desiredCacheSize
     * @return
     */
    public int getCacheSize(int desiredCacheSize){
        int maxMemory = (int) Runtime.getRuntime().maxMemory();
        int cacheSizeInMb = desiredCacheSize * 1024;

        if(desiredCacheSize > maxMemory){
            return maxMemory;
        }
        return maxMemory/cacheSizeInMb;
    }
}
