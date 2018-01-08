package com.cpst.news.base;

import android.content.pm.ActivityInfo;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import sunxl8.library.swipeback.SwipeBackActivityBase;
import sunxl8.library.swipeback.SwipeBackActivityHelper;
import sunxl8.library.swipeback.SwipeBackLayout;
import sunxl8.library.swipeback.Utils;


/**
 * Created by sunxl8 on 2017/6/23.
 *  主要实现：详情页向右侧滑动可以返回上一级
 *  需要实现侧滑返回上一级的activity可以继承这个类
 */

public abstract class BaseSwipeActivity<T extends ViewDataBinding> extends RxAppCompatActivity implements SwipeBackActivityBase {

    private SwipeBackActivityHelper mHelper;

    protected abstract int setContentViewId();

    protected abstract void init();

    protected T mBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mHelper = new SwipeBackActivityHelper(this);
        mHelper.onActivityCreate();
        mBinding = DataBindingUtil.setContentView(this, setContentViewId());
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        init();
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mHelper.onPostCreate();
    }

    @Override
    public View findViewById(int id) {
        View v = super.findViewById(id);
        if (v == null && mHelper != null)
            return mHelper.findViewById(id);
        return v;
    }

    @Override
    public SwipeBackLayout getSwipeBackLayout() {
        return mHelper.getSwipeBackLayout();
    }

    @Override
    public void setSwipeBackEnable(boolean enable) {
        getSwipeBackLayout().setEnableGesture(enable);
    }

    @Override
    public void scrollToFinishActivity() {
        Utils.convertActivityToTranslucent(this);
        getSwipeBackLayout().scrollToFinishActivity();
    }

    public abstract void showLoading();

    public abstract void showContent();

    public abstract void showError(String msg);

    public abstract void showEmpty();
}
