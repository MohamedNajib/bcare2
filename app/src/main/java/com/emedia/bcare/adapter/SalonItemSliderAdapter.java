package com.emedia.bcare.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.List;

public class SalonItemSliderAdapter extends PagerAdapter {

    private Context mContext;
    private List<String> mImageIUrl ;

    public SalonItemSliderAdapter(Context mContext, List<String> mImageIUrl) {
        this.mContext = mContext;
        this.mImageIUrl = mImageIUrl;
    }

    @Override
    public int getCount() {
        return mImageIUrl.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        ImageView imageView = new ImageView(mContext);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);

        Glide.with(mContext)
                .load(mImageIUrl.get(position))
                .into(imageView);

        container.addView(imageView, 0);
        return imageView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((ImageView) object);
    }
}
