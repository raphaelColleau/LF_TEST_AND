package com.superappli.lafourchette.ui.restoDetails;

import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.superappli.lafourchette.data.model.Pics_diaporama;

/**
 * PagerAdapter pour la galerie
 * Created by raphael on 04/12/2016.
 */

public class GalleryViewPagerAdapter extends PagerAdapter {

    private Pics_diaporama[] mImages;

    public GalleryViewPagerAdapter(Pics_diaporama[] images) {
        mImages = images;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        ImageView imageView = new ImageView(container.getContext());
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        container.addView(imageView);

        Picasso.with(container.getContext())
                .load(mImages[position].getUrl())
                .into(imageView);

        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View)object);
    }

    @Override
    public int getCount() {
        return mImages.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}
