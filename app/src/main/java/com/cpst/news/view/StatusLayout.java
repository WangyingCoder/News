package com.cpst.news.view;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.cpst.news.R;
import com.romainpiel.shimmer.Shimmer;
import com.romainpiel.shimmer.ShimmerTextView;


/**
 * Created by sun on 2017/6/26.
 * 显示FrameLayout 网络失败、加载中等布局。
 */

public class StatusLayout extends FrameLayout {

    private View viewError;
    private TextView tvErrorMsg;
    private AppCompatButton btnErrorRetry;
    private ShimmerTextView stvLoading;

    private Shimmer shimmer;

    private boolean isContentShowing = false;

    public StatusLayout(@NonNull Context context) {
        super(context);
    }

    public StatusLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        viewError = LayoutInflater.from(context).inflate(R.layout.view_error, null);
        tvErrorMsg = (TextView) viewError.findViewById(R.id.tv_error_msg);
        btnErrorRetry = (AppCompatButton) viewError.findViewById(R.id.btn_error_retry);
        shimmer = new Shimmer();
        shimmer.setDuration(2000)
                .setDirection(Shimmer.ANIMATION_DIRECTION_RTL);
        stvLoading = new ShimmerTextView(context);
        stvLoading.setText("你关心的，才是头条");
        stvLoading.setReflectionColor(Color.WHITE);
        stvLoading.setTextColor(context.getResources().getColor(R.color.colorTextGray));
        stvLoading.setTextSize(20);
        stvLoading.setGravity(Gravity.CENTER);
        addView(stvLoading);
        addView(viewError);
    }

    public void hideViews() {
        shimmer.cancel();
        for (int i = 0; i < getChildCount(); i++) {
            if (getChildAt(i) instanceof SwipeRefreshLayout) {
                ((SwipeRefreshLayout) getChildAt(i)).setRefreshing(false);
            }
            getChildAt(i).setVisibility(View.GONE);
        }
        isContentShowing = false;
    }

    public void showError(String msg, OnClickListener listener) {
        hideViews();
        viewError.setVisibility(View.VISIBLE);
        tvErrorMsg.setText(msg);
        btnErrorRetry.setOnClickListener(listener);
    }

    public void showLoading() {
        boolean show = true;
        shimmer.cancel();
        for (int i = 0; i < getChildCount(); i++) {
            if (getChildAt(i) instanceof SwipeRefreshLayout) {
                if (((SwipeRefreshLayout) getChildAt(i)).isRefreshing()) {
                    show = false;
                }
            }
            getChildAt(i).setVisibility(View.GONE);
        }
        if (show) {
            stvLoading.setVisibility(View.VISIBLE);
            shimmer.start(stvLoading);
        }
    }

    public void showContent() {
        if (!isContentShowing) {
            hideViews();
        }
        for (int i = 0; i < getChildCount(); i++) {
            if (isContentView(getChildAt(i))) {
                if (getChildAt(i) instanceof SwipeRefreshLayout) {
                    ((SwipeRefreshLayout) getChildAt(i)).setRefreshing(false);
                }
                getChildAt(i).setVisibility(View.VISIBLE);
                isContentShowing = true;
                continue;
            }
        }
    }

    private boolean isContentView(View view) {
        if ((view != null && view != viewError
                && view != stvLoading)) {
            return true;
        }
        return false;
    }
}
