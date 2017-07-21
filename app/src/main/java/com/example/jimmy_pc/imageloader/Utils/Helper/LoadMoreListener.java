package com.example.jimmy_pc.imageloader.Utils.Helper;

/**
 * Created by Jimmy-PC on 21/7/2017.
 */

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public abstract class LoadMoreListener extends RecyclerView.OnScrollListener {

    /**
     *
     * Define necessary variables
     */
    private int visibleThreshold = 8; //The amount of items below current scroll position before load more
    LinearLayoutManager mLayoutManager;
    int totalItemCount = 0;
    int lastVisiblesItems = 0;

    public LoadMoreListener(LinearLayoutManager layoutManager) {
        this.mLayoutManager = layoutManager;
    }

    /**
     *
     * This method is used to determine if to load
     * more items to the recyclerview
     *
     * @param view
     * @param dx
     * @param dy
     */
    @Override
    public void onScrolled(RecyclerView view, int dx, int dy) {
        totalItemCount = mLayoutManager.getItemCount(); //get current total items in the recyclerview
        lastVisiblesItems = mLayoutManager
                    .findFirstVisibleItemPosition();

        /**
         *
         * Check if it reaches the end of page in
         * order to update the recyclerview
         */
        if (totalItemCount <= (lastVisiblesItems + visibleThreshold)) {
            onLoadMore(lastVisiblesItems,totalItemCount,view);
        }
    }

    /**
     *
     * Define template method to load more item into recyclerview
     *
     * @param page
     * @param totalItemsCount
     * @param view
     */
    public abstract void onLoadMore(int page, int totalItemsCount, RecyclerView view);

}