package com.cpst.news.view.activity;

import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.cpst.news.R;
import com.cpst.news.databinding.ActivitySelectTestBinding;
import com.cpst.news.utils.SelectorFactory;


/**
 * Created by wy on 2017/12/11.
 * 工具类drawable的selector效果
 */

public class SelectActivity extends AppCompatActivity{

    private ActivitySelectTestBinding mBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_select_test);
        initEditText();
        initButton();
        initTextView();
    }

    private void initEditText() {
        /**
         * 设置边框颜色、宽度、圆角半径
         */
        mBinding.userName.setBackground(SelectorFactory.newShapeSelector()
                //默认边框颜色
                .setDefaultStrokeColor(Color.GRAY)
                //获取焦点边框颜色
                .setFocusedStrokeColor(Color.YELLOW)
                //边框宽度
                .setStrokeWidth(2)
                //圆角半径
                .setCornerRadius(20)
                .create());
        /**
         * 设置回显字体的默认颜色、获取焦点颜色
         */
        mBinding.userName.setHintTextColor(SelectorFactory.newColorSelector()
                //默认颜色
                .setDefaultColor(Color.GRAY)
                //获取焦点颜色
                .setFocusedColor(Color.BLACK)
                .create());

        /**
         * 设置输入内容字体的颜色
         */
        mBinding.userName.setTextColor(SelectorFactory.newColorSelector()
                //内容字体默认颜色
                .setDefaultColor(Color.BLACK)
                //内容字体获取焦点颜色
                .setFocusedColor(Color.RED)
                .create());
    }

    private void initButton() {
        /**
         * 点击显示图片背景
         */
        mBinding.picture.setBackground(SelectorFactory.newGeneralSelector()
                //设置默认背景图片
                .setDefaultDrawable(ContextCompat.getDrawable(this, R.mipmap.blue_primary))
                //设置按压时背景图片
                .setPressedDrawable(this, R.mipmap.blue_primary_dark)
                .create());
        /**
         * 点击选择边框颜色
         */
        mBinding.Shape.setBackground(SelectorFactory.newShapeSelector()
                //设置默认背景颜色
                .setDefaultBgColor(ContextCompat.getColor(this, android.R.color.holo_orange_dark))
                //设置按压时背景颜色
                .setPressedBgColor(ContextCompat.getColor(this, android.R.color.holo_blue_dark))
                .create());
        /**
         * 点击选中背景颜色
         */
        mBinding.selected.setBackground(SelectorFactory.newShapeSelector()
                //设置默认背景颜色
                .setDefaultBgColor(ContextCompat.getColor(this, android.R.color.holo_blue_light))
                //设置按压时背景颜色
                .setPressedBgColor(ContextCompat.getColor(this, android.R.color.holo_blue_dark))
                //设置选中时背景颜色
                .setSelectedBgColor(ContextCompat.getColor(this, android.R.color.holo_blue_dark))
                .setCornerRadius(20)
                .create());

        mBinding.selected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //是否可选中
                mBinding.selected.setSelected(!mBinding.selected.isSelected());
            }
        });
        /**
         * 不可选
         */
        mBinding.not.setBackground(SelectorFactory.newShapeSelector()
                .setDefaultBgColor(ContextCompat.getColor(this, android.R.color.holo_blue_light))
                .setPressedBgColor(ContextCompat.getColor(this, android.R.color.holo_blue_dark))
                .setDisabledBgColor(Color.GRAY)
                .create());
        mBinding.not.setEnabled(false);
    }

    private void initTextView() {
        /**
         * 圆形边框文字
         */
        mBinding.textview1.setBackground(SelectorFactory.newShapeSelector()
                //设置默认边框颜色
                .setDefaultStrokeColor(Color.GRAY)
                //设置边框宽度
                .setStrokeWidth(1)
                //设置圆角半径
                .setCornerRadius(20)
                .create());
        /**
         * 点击改变字体颜色
         */
        mBinding.textview2.setTextColor(SelectorFactory.newColorSelector()
                //设置默认颜色
                .setDefaultColor(Color.BLACK)
                //设置按下时颜色
                .setPressedColor(Color.YELLOW)
                .create());

        /**
         * 选中改变字体颜色
         */
        mBinding.textview3.setTextColor(SelectorFactory.newColorSelector()
                //设置默认字体颜色
                .setDefaultColor(Color.BLACK)
                //设置选中后字体颜色
                .setSelectedColor(Color.YELLOW)
                .create());
        mBinding.textview3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //选中改变字体颜色
                mBinding.textview3.setSelected(!mBinding.textview3.isSelected());
            }
        });

        /**
         * TextView不可用状态
         */
        mBinding.textview4.setTextColor(SelectorFactory.newColorSelector()
                .setDefaultColor(Color.BLACK)
                .setSelectedColor(Color.YELLOW)
                //设置不可用字体颜色
                .setDisabledColor(Color.GRAY)
                .create());
        mBinding.textview4.setEnabled(false);
    }
}
