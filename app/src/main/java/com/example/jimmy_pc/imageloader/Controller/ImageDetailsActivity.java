package com.example.jimmy_pc.imageloader.Controller;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.jimmy_pc.imageloader.R;
import com.example.jimmy_pc.imageloader.Utils.ImageLoader.LruCache;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ImageDetailsActivity extends AppCompatActivity {

    @BindView(R.id.contentImg)
    ImageView contentImgView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_details);
        ButterKnife.bind(this);

        Bundle extras = getIntent().getExtras();
        String imgUrl = extras.getString("image url");
        String transitionName = extras.getString("transition name");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            contentImgView.setTransitionName(transitionName);
        }

        contentImgView.setImageBitmap(LruCache.getInstance().getBitmapFromCache(imgUrl));
    }
}
