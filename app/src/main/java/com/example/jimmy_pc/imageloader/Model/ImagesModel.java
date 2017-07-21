package com.example.jimmy_pc.imageloader.Model;

import com.example.jimmy_pc.imageloader.Utils.Helper.OnRequestListener;

/**
 * Created by Jimmy-PC on 20/7/2017.
 */

/**
 *
 * Define method and param required for
 * @see ImagesModelImpl
 */
public interface ImagesModel {

    /**
     *
     * This method is to retrieve images' url from remote server.
     * It is also consists of network response callback - OnSuccess & OnFailure
     *
     * @see OnRequestListener
     * @param onRequestListener
     */
    void getImages(OnRequestListener onRequestListener);
}
