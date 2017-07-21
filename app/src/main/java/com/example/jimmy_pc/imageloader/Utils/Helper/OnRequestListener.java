package com.example.jimmy_pc.imageloader.Utils.Helper;

import com.example.jimmy_pc.imageloader.Model.ImagesInfo;

import java.util.List;

/**
 * Created by Jimmy-PC on 20/7/2017.
 */

/**
 *
 * Include the required callback methods for network response.
 */
public interface OnRequestListener {

    /**
     *
     * This method is to pass a list of json object back to the caller
     * function when the network return the data from server successfully.
     *
     * @see ImagesInfo
     * @param imagesInfoList
     */
    void onSuccess(List<ImagesInfo> imagesInfoList);

    /**
     * This method is to pass error back to the caller
     * function when the network fails to return data from server.
     *
     * @param errorMsg
     */
    void onFailure(String errorMsg);
}
