package com.example.jimmy_pc.imageloader.Utils.ImageLoader;

import android.content.Context;
import android.widget.ImageView;

/**
 * Created by Jimmy-PC on 20/7/2017.
 */

/**
 *
 * Define method to load the image content
 */
public interface iImgContentLoader {
    void loadImage(Context context, ImageView imageView, String url);
}
