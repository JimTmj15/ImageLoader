package com.example.jimmy_pc.imageloader.Model;


import android.content.Context;

import com.example.jimmy_pc.imageloader.Controller.MainActivity;
import com.example.jimmy_pc.imageloader.Utils.Helper.OnRequestListener;
import com.example.jimmy_pc.imageloader.Utils.Network.RetrofitClient;
import java.util.List;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Jimmy-PC on 20/7/2017.
 */

/**
 *
 * This purpose of this class is to retrieve data from remote server.
 * The result returned will be passed to
 * @see MainActivity via
 * @see OnRequestListener callback methods.
 *
 */
public class ImagesModelImpl implements ImagesModel {

    /**
     * Define necesseary paramas
     */
    private OnRequestListener mOnRequestListener;
    RetrofitClient retrofitClient;


    public ImagesModelImpl(Context context){
        retrofitClient = new RetrofitClient(context);
    }

    /**
     *
     * This method attempts to prompt data from remote server and
     * return the results obtained via OnRequesListener to
     * caller class.
     *
     * @see OnRequestListener
     * @param onRequestListener
     */
    @Override
    public void getImages(OnRequestListener onRequestListener) {
        mOnRequestListener = onRequestListener;

        Observable<List<ImagesInfo>> networkReq = retrofitClient.apiServiceCall().getImages()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()); //Return the result to main thread

        networkReq.subscribe(new Observer<List<ImagesInfo>>() {
            @Override
            public void onSubscribe(@io.reactivex.annotations.NonNull Disposable d) {
            }

            @Override
            public void onNext(@io.reactivex.annotations.NonNull List<ImagesInfo> images) {
                mOnRequestListener.onSuccess(images);
            }

            @Override
            public void onError(@io.reactivex.annotations.NonNull Throwable e) {
                mOnRequestListener.onFailure(e.toString());
            }

            @Override
            public void onComplete() {
            }
        });
    }
}
