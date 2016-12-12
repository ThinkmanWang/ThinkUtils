package com.thinkman.thinkutilssample;

import android.app.Activity;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

import com.gzsll.jsbridge.WVJBWebView;
import com.thinkman.thinkutils.activity.BaseActivityWithTranslucentBar;
import com.thinkman.thinkutils.view.ProgressWebView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WebViewActivity extends BaseActivityWithTranslucentBar {

    @BindView(R.id.pwv_content)
    ProgressWebView m_pwvContent = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        ButterKnife.bind(this);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimary));
        }

        m_pwvContent.setInitialScale(1);
        m_pwvContent.getSettings().setJavaScriptEnabled(true);
        m_pwvContent.getSettings().setLoadWithOverviewMode(true);
        m_pwvContent.getSettings().setUseWideViewPort(true);
        m_pwvContent.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
        m_pwvContent.setScrollbarFadingEnabled(false);

        m_pwvContent.loadUrl("http://dvpt.51lick.cn/index.php/Mobileweb/Events/statisticAddDealer?userId=125031542&facId=1542");

        m_pwvContent.registerHandler("back", new WVJBWebView.WVJBHandler() {

            @Override
            public void request(Object data, WVJBWebView.WVJBResponseCallback callback) {
                WebViewActivity.this.finish();
            }
        });

        m_pwvContent.registerHandler("change", new WVJBWebView.WVJBHandler() {

            @Override
            public void request(Object data, WVJBWebView.WVJBResponseCallback callback) {
                callback.callback("导航高度加20");
            }
        });
    }
}
