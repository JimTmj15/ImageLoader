package com.example.jimmy_pc.imageloader.Utils.Network;

import com.example.jimmy_pc.imageloader.Model.ImagesInfo;
import java.util.List;
import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by Jimmy-PC on 19/7/2017.
 */

/**
 *
 * List down required api service details & HTTP method needed
 */
public interface ApiService {

    /**
     *
     * This is a HTTP get method to retrieve
     * @return Return observable with a list of json obj from remote server.
     */
    @GET("/raw/wgkJgazE")
    Observable<List<ImagesInfo>> getImages();
}
