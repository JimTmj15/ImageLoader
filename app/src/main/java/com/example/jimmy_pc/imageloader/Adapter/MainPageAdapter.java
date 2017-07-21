package com.example.jimmy_pc.imageloader.Adapter;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

import com.example.jimmy_pc.imageloader.Utils.Helper.ImageItemClickListener;
import com.example.jimmy_pc.imageloader.R;
import com.example.jimmy_pc.imageloader.Utils.ImageLoader.ImgLoader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Jimmy-PC on 19/7/2017.
 */

public class MainPageAdapter extends RecyclerView.Adapter<MainPageAdapter.ViewHolder> {

    /**
     *
     * Define necessary params
     */
    private List<String> imgList = new ArrayList<>();
    private Context context;
    private int lastPosition = -1; //for recyclerview animation
    private ImageItemClickListener imageItemClickListener; //for recyclerview to activity transition

    /**
     *
     * @param context
     * @param imgList
     */
    public MainPageAdapter(Context context, List<String> imgList, ImageItemClickListener imageItemClickListener){
        this.imgList = imgList;
        this.context = context;
        this.imageItemClickListener = imageItemClickListener;
    }

    /**
     *
     * Inflate a layout and insert into a view holder
     *
     * @param parent
     * @param viewType
     * @return view holder
     */
    @Override
    public MainPageAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.main_page_recycleview_cell,parent,false);
        return new ViewHolder(view);
    }

    /**
     *
     * Set content to the view holder based on their
     * respective position.
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(final MainPageAdapter.ViewHolder holder, final int position) {
        setAnimation(holder.imageView,position);
        ViewCompat.setTransitionName(holder.imageView, context.getResources().getString(R.string.transition_name)); //set transition name & target
        ImgLoader.getInstance().loadImage(context, holder.imageView, imgList.get(position));
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageItemClickListener.onImageItemClick(position, imgList.get(position), holder.imageView);
            }
        });
    }

    /**
     *
     * Configure animation params
     *
     * @param viewToAnimate
     * @param position
     */
    private void setAnimation(View viewToAnimate, int position) {
        if (position > lastPosition) {
            ScaleAnimation anim = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f, Animation.RELATIVE_TO_SELF, 0.1f, Animation.RELATIVE_TO_SELF, 0.1f);
            anim.setDuration(new Random().nextInt(501));
            anim.setFillAfter(true);
            viewToAnimate.startAnimation(anim);
            lastPosition = position;
        }
    }

    @Override
    public int getItemCount() {
        return imgList.size();
    } //get amount to be shown from the list

    /**
     *
     * Define/Initialize UI widget inside a view holder
     */
    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        public ViewHolder(View view){
            super(view);
            imageView = view.findViewById(R.id.cellImgView);
        }
    }
}
