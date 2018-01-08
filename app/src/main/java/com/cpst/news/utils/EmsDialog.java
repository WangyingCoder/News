package com.cpst.news.utils;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.cpst.news.R;


/**
 * 统一样式的dialog
 * 需要优化
 */
public class EmsDialog extends Dialog {

    private boolean isCancelable = true;
    private String message, cancelText, confirmText, title, okText, messageClickText;
    private ClickListener cancelClickListener, confirmClickListener, messageClickListener;
    private TextView tvDialogCancel, tvDialogConfirm, tvDialogContent, tvDialogTitle, tvDialogOk;
    private View ll_dialog_bottom;
    private boolean is_first;
    private boolean is_true;
    private Context mContext;

    public EmsDialog(Context context) {
        super(context, R.style.EmsDialogStyle);
        mContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = getWindow();
        assert window != null;
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        window.setGravity(Gravity.CENTER);
        setContentView(R.layout.dialog_ems);
        window.setLayout((int)(ViewUtils.getScreenWidth(getContext()) * 0.8f),
                WindowManager.LayoutParams.WRAP_CONTENT);
        setCanceledOnTouchOutside(isCancelable);
        setCancelable(isCancelable);
        tvDialogTitle = (TextView) findViewById(R.id.tv_dialog_title);
        tvDialogCancel = (TextView) findViewById(R.id.tv_dialog_cancel);
        ll_dialog_bottom = findViewById(R.id.ll_dialog_bottom);
        tvDialogConfirm = (TextView) findViewById(R.id.tv_dialog_confirm);
        tvDialogContent = (TextView) findViewById(R.id.tv_dialog_content);
        tvDialogOk = (TextView) findViewById(R.id.tv_dialog_ok);
        tvDialogCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cancelClickListener != null) {
                    cancelClickListener.click(v);
                }
                dismiss();
            }
        });
        tvDialogConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (confirmClickListener != null) {
                    confirmClickListener.click(v);
                }
                dismiss();
            }
        });
        tvDialogOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (confirmClickListener != null) {
                    confirmClickListener.click(v);
                }
                dismiss();
            }
        });

        if (title != null) {
            tvDialogTitle.setText(title);
        }

        if (message != null) {
            if (messageClickText != null) {
                tvDialogContent.setHighlightColor(Color.parseColor("#00000000"));
                int index = message.indexOf(messageClickText);
                SpannableString info = new SpannableString(message);
                info.setSpan(new Clickable(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (messageClickListener != null) {
                            messageClickListener.click(v);
                        }
                    }
                }), index, message.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                tvDialogContent.setText(info);
                tvDialogContent.setMovementMethod(LinkMovementMethod.getInstance());
            } else {
                tvDialogContent.setText(message);
            }
        }

        if (cancelText != null) {
            tvDialogCancel.setText(cancelText);
        }
        if (confirmText != null) {
            tvDialogConfirm.setText(confirmText);
        }
        if (okText != null) {
            tvDialogOk.setText(okText);
        }

        if (is_true) {
            tvDialogTitle.setTextColor(Color.parseColor("#00C087"));
        } else {
            tvDialogTitle.setTextColor(Color.parseColor("#E0463C"));
        }

        if (is_first) {
            ll_dialog_bottom.setVisibility(View.GONE);
            tvDialogOk.setVisibility(View.VISIBLE);
        } else {
            ll_dialog_bottom.setVisibility(View.VISIBLE);
            tvDialogOk.setVisibility(View.GONE);
        }
    }


    //链式调用方法
    public EmsDialog isTrue(boolean is_true) {
        this.is_true = is_true;
        return this;
    }

    public EmsDialog isFirst(boolean is_first) {
        this.is_first = is_first;
        return this;
    }

    public EmsDialog setTitle(String title) {
        this.title = title;
        return this;
    }

    public EmsDialog setMessage(String message) {
        this.message = message;
        return this;
    }

    public EmsDialog setCancelText(String cancelText) {
        this.cancelText = cancelText;
        return this;
    }

    public EmsDialog setConfirmText(String confirmText) {
        this.confirmText = confirmText;
        return this;
    }

    public EmsDialog setOkText(String okText) {
        this.okText = okText;
        return this;
    }

    public EmsDialog setCancelClick(ClickListener listener) {
        cancelClickListener = listener;
        return this;
    }

    public EmsDialog setConfirmClick(ClickListener listener) {
        confirmClickListener = listener;
        return this;
    }

    public EmsDialog setMessageTextClick(ClickListener listener, String message, String clickText) {
        this.message = message;
        messageClickText = clickText;
        messageClickListener = listener;
        return this;
    }

    public EmsDialog setDialogCancelable(boolean isCancelable) {
        this.isCancelable = isCancelable;
        return this;
    }

    public interface ClickListener {
        void click(View v);
    }

    class Clickable extends ClickableSpan {
        private final View.OnClickListener mListener;

        public Clickable(View.OnClickListener l) {
            mListener = l;
        }

        /**
         * 重写父类点击事件
         */
        @Override
        public void onClick(View v) {
            mListener.onClick(v);
        }

        /**
         * 重写父类updateDrawState方法  我们可以给TextView设置字体颜色,背景颜色等等...
         */
        @Override
        public void updateDrawState(TextPaint ds) {
            ds.setColor(ContextCompat.getColor(mContext, R.color.selector_blue));
        }
    }
}
