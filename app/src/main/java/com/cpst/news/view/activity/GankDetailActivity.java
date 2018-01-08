package com.cpst.news.view.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.KeyEvent;

import com.cpst.news.R;
import com.cpst.news.base.BaseSwipeActivity;
import com.cpst.news.databinding.ActivityGankDetailBinding;
import com.cpst.news.model.GankItemEntity;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

import sunxl8.myutils.NetworkUtils;


/**
 * Created by wy on 2017/12/5.
 * 干货详情页面
 */

public class GankDetailActivity extends BaseSwipeActivity<ActivityGankDetailBinding> {
    private GankItemEntity entity;

    @Override
    protected int setContentViewId() {
        return R.layout.activity_gank_detail;
    }

    public void init() {
        entity = (GankItemEntity) getIntent().getSerializableExtra("entity");
        mBinding.title.setText(entity.getDesc());
        mBinding.layoutRefresh.setOnRefreshListener(() -> mBinding.web.reload());
        mBinding.ivBack.setOnClickListener(view -> finish());

        /**
         * webview的设置
         */
        WebSettings settings = mBinding.web.getSettings();
        settings.setJavaScriptEnabled(true);
        //设置WebView是否使用viewport
        settings.setUseWideViewPort(true);
        //设置WebView是否使用预览模式加载界面。
        settings.setLoadWithOverviewMode(true);
        //设置WebView是否支持使用屏幕控件或手势进行缩放
        settings.setSupportZoom(true);
        //设置WebView是否使用其内置的变焦机制，该机制集合屏幕缩放控件使用
        settings.setBuiltInZoomControls(true);
        /**
         * 缓存策略
         */
        if (NetworkUtils.isConnected()) {
            settings.setCacheMode(WebSettings.LOAD_DEFAULT);
        } else {
            settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        }
        String cacheDirPath = getFilesDir().getAbsolutePath() + "/netcache";
        settings.setAppCachePath(cacheDirPath);

        mBinding.web.canGoBack();
        mBinding.web.canGoForward();

        mBinding.web.loadUrl(entity.getUrl());
        mBinding.web.setWebViewClient(new WebViewClient() {

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                showLoading();
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                showContent();
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        mBinding.web.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onReceivedTitle(WebView view, String title) {

            }
        });
        //绑定WebView的下拉刷新事件与SwipeRefreshLayout的下拉事件
        mBinding.web.setRefreshLayout(mBinding.layoutRefresh);
    }

    @Override
    public void showLoading() {
        mBinding.layoutStatus.showLoading();
    }

    @Override
    public void showContent() {
        mBinding.layoutStatus.showContent();
    }

    @Override
    public void showError(String msg) {
        mBinding.layoutStatus.showError(msg, v -> mBinding.web.loadUrl(entity.getUrl()));
    }

    @Override
    public void showEmpty() {

    }

    /**
     * 点击返回键，WebView网页回退，否则会finish退出。
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && mBinding.web.canGoBack()) {
            mBinding.web.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * 页面跳转
     */
    public static void startActivity(Context context, GankItemEntity entity){
        Intent intent = new Intent(context,GankDetailActivity.class);
        intent.putExtra("entity",entity);
        context.startActivity(intent);
    }
}
