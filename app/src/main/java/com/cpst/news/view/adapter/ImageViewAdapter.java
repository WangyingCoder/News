package com.cpst.news.view.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.cpst.news.R;
import com.cpst.news.model.ImageBean;

/**
 * Created by wy on 2018/1/5.
 * 图片的Adapter
 */

public class ImageViewAdapter extends BaseQuickAdapter<ImageBean,BaseViewHolder> {
    public ImageViewAdapter() {
        super(R.layout.item_image);
    }

    @Override
    protected void convert(BaseViewHolder holder, ImageBean item) {
        holder.setText(R.id.text, item.getTitle());
        Glide.with(holder.itemView.getContext()).load(item.getImage_url()).into((ImageView) holder.getView(R.id.image));
    }
}
