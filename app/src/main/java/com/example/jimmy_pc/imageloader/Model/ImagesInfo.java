package com.example.jimmy_pc.imageloader.Model;

import com.google.gson.JsonObject;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Jimmy-PC on 20/7/2017.
 */

/**
 * Bean class for network response to be serialized using Gson.
 * Getter & setter to alter & retrieve the variable.
 */
public class ImagesInfo {

    /**
     * Define response tag to be serialized.
     * In this demo, only urls are required.
     *
     */
    @SerializedName("urls")
    @Expose
    private JsonObject imgJsonObjList;

    /**
     *
     * @return The json object retrieved from server
     */
    public JsonObject getImgJsonObjList() {
        return imgJsonObjList;
    }

    /**
     * To alter the json object in this bean class.
     *
     * @param imgJsonObjList
     */
    public void setImgJsonObjList(JsonObject imgJsonObjList) {
        this.imgJsonObjList = imgJsonObjList;
    }
}
