package com.cpst.news.base;

import com.trello.rxlifecycle2.components.RxActivity;

/**
 * Created by wy on 2017/11/25.
 * Activity基类
 */

public abstract class BaseActivity extends RxActivity {
    public abstract void showLoading();

    public abstract void showContent();

    public abstract void showError(String msg);

    public abstract void showEmpty();
}
