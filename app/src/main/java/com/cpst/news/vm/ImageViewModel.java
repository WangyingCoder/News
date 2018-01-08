package com.cpst.news.vm;

import android.util.Log;

import com.cpst.news.base.BaseFragment;
import com.cpst.news.model.ImageList;
import com.cpst.news.network.ErrorHanding;
import com.cpst.news.network.ImageRequest;
import com.cpst.news.view.adapter.ImageViewAdapter;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

/**
 * Created by wy on 2018/1/5.
 *
 */

public class ImageViewModel {
    private ImageViewAdapter adapter = new ImageViewAdapter();
    private BaseFragment mFragment;

    public ImageViewModel(BaseFragment baseFragment, ImageViewAdapter adapter) {
        this.mFragment = baseFragment;
        this.adapter = adapter;
    }

    public void getImageView(boolean isRefresh){
        if(isRefresh) {
            //初始化，需要加载Shimmer动画、
            mFragment.showLoading();
        }
        ImageRequest.getImageData()
                .subscribe(new Consumer<ImageList>() {
                    @Override
                    public void accept(@NonNull ImageList list) throws Exception {
                        mFragment.showContent();
                        if (isRefresh) {
                            adapter.setNewData(list.getData());
                        } else {
                            adapter.addData(list.getData());
                        }
                        adapter.loadMoreComplete();
                        Log.i("TAG","VM获取数据："+list);
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
