package com.cpst.news.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.cpst.news.R;
import com.cpst.news.model.GankItemEntity;

/**
 * Created by wy on 2017/9/29.
 * 采用第三方adapter库，节省代码
 */

public class GankAdapter extends BaseQuickAdapter<GankItemEntity,BaseViewHolder> {

    public GankAdapter() {
        super(R.layout.item_gank);
    }

    @Override
    protected void convert(BaseViewHolder viewHolder, GankItemEntity bean) {
        viewHolder.setText(R.id.tv_data_title, bean.getDesc());
    }
}
