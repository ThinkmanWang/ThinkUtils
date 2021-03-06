package com.thinkman.thinkutils.imagepreview.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.thinkman.thinkutils.imagepreview.adapter.ImagePreviewAdapter;

import java.util.List;

import cn.finalteam.galleryfinal.GalleryFinal;
import cn.finalteam.galleryfinal.PhotoBaseActivity;
import cn.finalteam.galleryfinal.ThemeConfig;
import cn.finalteam.galleryfinal.adapter.PhotoPreviewAdapter;
import cn.finalteam.galleryfinal.model.PhotoInfo;
import cn.finalteam.galleryfinal.widget.GFViewPager;

public class ImagePreviewActivity extends PhotoBaseActivity implements ViewPager.OnPageChangeListener{

    public static final String PHOTO_LIST = "photo_list";
    public static final String START_POS = "start_pos";

    private RelativeLayout mTitleBar;
    private ImageView mIvBack;
    private TextView mTvTitle;
    private TextView mTvIndicator;

    private GFViewPager mVpPager;
    private List<PhotoInfo> mPhotoList;
    private ImagePreviewAdapter mPhotoPreviewAdapter;

    private ThemeConfig mThemeConfig;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mThemeConfig = GalleryFinal.getGalleryTheme();

        if ( mThemeConfig == null) {
            resultFailureDelayed(getString(cn.finalteam.galleryfinal.R.string.please_reopen_gf), true);
        } else {
            setContentView(cn.finalteam.galleryfinal.R.layout.gf_activity_photo_preview);
            findViews();
            setListener();
            setTheme();

            mPhotoList = (List<PhotoInfo>) getIntent().getSerializableExtra(PHOTO_LIST);
            mPhotoPreviewAdapter = new ImagePreviewAdapter(this, mPhotoList);
            mVpPager.setAdapter(mPhotoPreviewAdapter);

            if (getIntent().hasExtra(START_POS)) {
                mVpPager.setCurrentItem(getIntent().getIntExtra(START_POS, 0));
            }
        }
    }

    private void findViews() {
        mTitleBar = (RelativeLayout) findViewById(cn.finalteam.galleryfinal.R.id.titlebar);
        mIvBack = (ImageView) findViewById(cn.finalteam.galleryfinal.R.id.iv_back);
        mTvTitle = (TextView) findViewById(cn.finalteam.galleryfinal.R.id.tv_title);
        mTvIndicator = (TextView) findViewById(cn.finalteam.galleryfinal.R.id.tv_indicator);

        mVpPager = (GFViewPager) findViewById(cn.finalteam.galleryfinal.R.id.vp_pager);
    }

    private void setListener() {
        mVpPager.addOnPageChangeListener(this);
        mIvBack.setOnClickListener(mBackListener);
    }

    private void setTheme() {
        mIvBack.setImageResource(mThemeConfig.getIconBack());
        if (mThemeConfig.getIconBack() == cn.finalteam.galleryfinal.R.drawable.ic_gf_back) {
            mIvBack.setColorFilter(mThemeConfig.getTitleBarIconColor());
        }

        mTitleBar.setBackgroundColor(mThemeConfig.getTitleBarBgColor());
        mTvTitle.setTextColor(mThemeConfig.getTitleBarTextColor());
        if(mThemeConfig.getPreviewBg() != null) {
            mVpPager.setBackgroundDrawable(mThemeConfig.getPreviewBg());
        }
    }

    @Override
    protected void takeResult(PhotoInfo info) {
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        mTvIndicator.setText((position + 1) + "/" + mPhotoList.size());
    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    private View.OnClickListener mBackListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    };

}
