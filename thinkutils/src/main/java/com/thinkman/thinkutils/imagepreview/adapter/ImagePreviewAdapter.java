package com.thinkman.thinkutils.imagepreview.adapter;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

import cn.finalteam.galleryfinal.GalleryFinal;
import cn.finalteam.galleryfinal.R;
import cn.finalteam.galleryfinal.adapter.PhotoPreviewAdapter;
import cn.finalteam.galleryfinal.adapter.ViewHolderRecyclingPagerAdapter;
import cn.finalteam.galleryfinal.model.PhotoInfo;
import cn.finalteam.galleryfinal.widget.zoonview.PhotoView;
import cn.finalteam.toolsfinal.DeviceUtils;

/**
 * Created by wangx on 2016-12-24.
 */

public class ImagePreviewAdapter extends ViewHolderRecyclingPagerAdapter<ImagePreviewAdapter.PreviewViewHolder, PhotoInfo> {

    private Activity mActivity;
    private DisplayMetrics mDisplayMetrics;

    public ImagePreviewAdapter(Activity activity, List<PhotoInfo> list) {
        super(activity, list);
        this.mActivity = activity;
        this.mDisplayMetrics = DeviceUtils.getScreenPix(mActivity);
    }

    @Override
    public ImagePreviewAdapter.PreviewViewHolder onCreateViewHolder(ViewGroup parent, int position) {
        View view = getLayoutInflater().inflate(R.layout.gf_adapter_preview_viewpgaer_item, null);
        return new ImagePreviewAdapter.PreviewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ImagePreviewAdapter.PreviewViewHolder holder, int position) {
        PhotoInfo photoInfo = getDatas().get(position);
        String path = "";
        if (photoInfo != null) {
            path = photoInfo.getPhotoPath();
        }
        holder.mImageView.setImageResource(R.drawable.ic_gf_default_photo);
        Drawable defaultDrawable = mActivity.getResources().getDrawable(R.drawable.ic_gf_default_photo);
        
        GalleryFinal.getCoreConfig().getImageLoader().displayImage(mActivity, path, holder.mImageView, defaultDrawable, mDisplayMetrics.widthPixels/2, mDisplayMetrics.heightPixels/2);
    }

    static class PreviewViewHolder extends ViewHolderRecyclingPagerAdapter.ViewHolder{
        PhotoView mImageView;
        public PreviewViewHolder(View view) {
            super(view);
            mImageView = (PhotoView) view;
        }
    }
}
