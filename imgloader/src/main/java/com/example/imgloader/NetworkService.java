package com.example.imgloader;

/**
 * Created by Jimmy-PC on 20/8/2017.
 */

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.widget.ImageView;

import okhttp3.Request;
import okhttp3.Response;
import okhttp3.OkHttpClient;

/**
 *
 * This class is to load the image from the url given into
 * the image view. The process is carried out in the
 * background.
 */
public class NetworkService extends AsyncTask<String, Void, Bitmap> {

    /**
     *
     * Define variables
     */
    private ImageView imageView;
    private OkHttpClient okHttpClient = new OkHttpClient();
    Context context;

    public NetworkService(Context context, ImageView imageView){
        this.context = context;
        this.imageView = imageView;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Bitmap doInBackground(String... urls) {
        try {
            Request request = new Request.Builder()
                    .url(urls[0])
                    .build();

            Response response = okHttpClient
                    .newCall(request)
                    .execute();

            Bitmap bitmap = BitmapHelper.decodeBitmapFromByteArray(response.body().bytes(),200,200); //decode the bitmap with given dimensions
            LruCache.getInstance().addBitmapToCache(urls[0],bitmap); //save the decoded bitmap into cache
            return bitmap;
        } catch (Throwable ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);
        imageView.setImageBitmap(bitmap); //load the bitmap into image view
    }
}

