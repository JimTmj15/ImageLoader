package com.example.imgloader;

/**
 * Created by Jimmy-PC on 20/8/2017.
 */

import android.content.Context;
import android.widget.ImageView;

/**
 *
 * Define method to load the image content
 */
public interface iImgContentLoader {
    void loadImage(Context context, ImageView imageView, String url);
}
