package com.cpst.news.vm;

import com.cpst.news.adapter.GankAdapter;
import com.cpst.news.base.BaseFragment;
import com.cpst.news.model.GankList;
import com.cpst.news.network.ErrorHanding;
import com.cpst.news.network.GankRequest;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;


/**
 * Created by wy on 2017/11/25.
 * 干货的ViewModel
 */

public class GankViewModel {
    private GankAdapter adapter = new GankAdapter();
    private int page = 1;
    private BaseFragment mFragment;

    public GankViewModel(BaseFragment baseFragment, GankAdapter adapter) {
        this.mFragment = baseFragment;
        this.adapter = adapter;
    }

    public void getDate(boolean isRefresh, String category ){
        if(isRefresh) {
            //初始化，需要加载Shimmer动画、
            mFragment.showLoading();
            page = 1;
        }
        GankRequest.getDate(category,10,page)
                .subscribe(new Consumer<GankList>() {
                    @Override
                    public void accept(@NonNull GankList list) throws Exception {
                        mFragment.showContent();
                        if (isRefresh) {
                            adapter.setNewData(list.getResults());
                        } else {
                            adapter.addData(list.getResults());
                        }
                        adapter.loadMoreComplete();
                        page++;
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        if (isRefresh) {
                            mFragment.showError(ErrorHanding.handError(throwable));
                        } else {
                            adapter.loadMoreFail();
                        }
                    }
                });
    }
}
