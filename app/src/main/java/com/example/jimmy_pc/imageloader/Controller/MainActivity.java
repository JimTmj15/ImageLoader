package com.example.jimmy_pc.imageloader.Controller;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.example.jimmy_pc.imageloader.Adapter.MainPageAdapter;
import com.example.jimmy_pc.imageloader.Utils.Helper.ImageItemClickListener;
import com.example.jimmy_pc.imageloader.Utils.Helper.LoadMoreListener;
import com.example.jimmy_pc.imageloader.Model.ImagesInfo;
import com.example.jimmy_pc.imageloader.Model.ImagesModelImpl;
import com.example.jimmy_pc.imageloader.Utils.Helper.OnRequestListener;
import com.example.jimmy_pc.imageloader.R;

import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements OnRequestListener, SwipeRefreshLayout.OnRefreshListener,
        ImageItemClickListener {

    /**
     * Declare variables
     **/
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;

    List<String> imgUrlList;
    MainPageAdapter mainPageAdapter;
    LinearLayoutManager mLayoutManager;
    ImagesModelImpl imagesModelImp;
    AlertDialog.Builder alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(MainActivity.this);
        initParam();
        imagesModelImp.getImages(MainActivity.this);

        /**
         *
         * Register on scroll listener and load more items when
         * it reaches the end of page.
         */
        recyclerView.addOnScrollListener(new LoadMoreListener(mLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                getMoreItems(page);
            }
        });
    }

    /**
     *
     * Get more items and add into an array list.
     * Refresh the recyclerview after finish
     * adding in the data into array list.
     *
     * @param page
     */
    public void getMoreItems(int page){
        if(page <= 50){
            swipeRefreshLayout.setRefreshing(true);
            for(int i = 0; i <= 8; i++){
                imgUrlList.add(imgUrlList.get(i));
            }
            swipeRefreshLayout.setRefreshing(false);
            mainPageAdapter.notifyDataSetChanged();
        }
    }

    /**
     * Initialize necessary parameters
     */
    public void initParam(){
        swipeRefreshLayout.setOnRefreshListener(this);
        alertDialog = new AlertDialog.Builder(MainActivity.this);
        imagesModelImp = new ImagesModelImpl(MainActivity.this);
        imgUrlList  = new ArrayList<>();
        mLayoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(mLayoutManager);
    }


    /**
     * This is a callback method from network request.
     * It passes back a list of json from network request & added image urls into
     * an array list. Dismiss refreshing if any.
     * Set the adapter with required params & set the adapter to recyclerview
     *
     * @see OnRequestListener
     * @param imagesList
     */
    @Override
    public void onSuccess(List<ImagesInfo> imagesList) {
        imgUrlList.clear();
        for (int i = 0; i < imagesList.size() -1; i++){
            imgUrlList.add(imagesList.get(i).getImgJsonObjList().get("raw").getAsString());
        }

        if (swipeRefreshLayout.isRefreshing()) {
            swipeRefreshLayout.setRefreshing(false);
        }
        mainPageAdapter = new MainPageAdapter(MainActivity.this, imgUrlList, this);
        recyclerView.setAdapter(mainPageAdapter);
    }

    /**
     * Callback function from network request for failure.
     * It passes back error back & show in alert dialog.
     *
     * @see OnRequestListener
     */
    @Override
    public void onFailure(String errorMsg) {
        if (swipeRefreshLayout.isRefreshing()) {
            swipeRefreshLayout.setRefreshing(false);
        }
        showErrorMsg(errorMsg);
    }

    /**
     * Swipe refresh layout listener method.
     * This method calls network service to retrieve data
     * whenever user pull to refresh.
     */
    @Override
    public void onRefresh() {
        imagesModelImp.getImages(MainActivity.this);
    }


    /**
     *
     * This method is to show error message obtained from the OnFailure callback method.
     *
     * @param errMsg
     */
    public void showErrorMsg(String errMsg){
        alertDialog.setMessage(errMsg)
                .setTitle(getResources().getString(R.string.alert_dialog_err_title))
                .setPositiveButton(getResources().getString(R.string.pos_btn),null)
                .create()
                .show();
    }

    /**
     *
     * This method is used for activity-activity transition animation
     *
     * @param position
     * @param url
     * @param sharedImageView
     */
    @Override
    public void onImageItemClick(int position, String url, ImageView sharedImageView) {
        Intent intent = new Intent(this, ImageDetailsActivity.class);
        intent.putExtra("transition name", ViewCompat.getTransitionName(sharedImageView));
        intent.putExtra("image url", url);

        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                this,
                sharedImageView,
                ViewCompat.getTransitionName(sharedImageView));

        startActivity(intent, options.toBundle());
    }
}
