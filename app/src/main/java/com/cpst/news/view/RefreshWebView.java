package com.cpst.news.view;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;

import com.tencent.smtt.sdk.WebView;


/**
 * Created by wy on 2017/12/8.
 * 重新滑动事件，解决RefreshLayout和webview滑动冲突
 */

public class RefreshWebView extends WebView {

    private SwipeRefreshLayout mRefreshLayout;

    public RefreshWebView(Context context) {
        super(context);
    }

    public RefreshWebView(Context context, AttributeSet set) {
        super(context, set);
    }

    /**
     *
     * 将WebView的下拉刷新与RefreshLayout的下拉刷新绑定、同步。
     */
    public void setRefreshLayout(SwipeRefreshLayout swipeRefreshLayout) {
        mRefreshLayout = swipeRefreshLayout;
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (mRefreshLayout != null) {
            if (this.getScrollY() == 0) {
                mRefreshLayout.setEnabled(true);
            } else {
                mRefreshLayout.setEnabled(false);
            }
        }
    }
}

