package com.library.utils;

import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.library.R;


public class ToolbarUtil {
    public static void setToolbar(Toolbar toolbar, String title, final View.OnClickListener listener) {
        ImageView leftIm = toolbar.findViewById(R.id.tv_image_left);
        TextView titleTv = toolbar.findViewById(R.id.toolbar_title);
        titleTv.setText(title);
        if(listener != null){
            leftIm.setVisibility(View.VISIBLE);
            leftIm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null) {
                        listener.onClick(view);
                    }
                }
            });
        }else{
            leftIm.setVisibility(View.GONE);
        }
    }

    public static void setToolbar(Toolbar toolbar, String title, String leftStr, final View.OnClickListener listener) {
        TextView titleTv = toolbar.findViewById(R.id.toolbar_title);
        ImageView leftIm = toolbar.findViewById(R.id.tv_image_left);
        TextView leftTv = toolbar.findViewById(R.id.tv_title_left);
        if (!TextUtils.isEmpty(title)) {
            titleTv.setText(title);
        }else{
            titleTv.setVisibility(View.GONE);
        }
        if (TextUtils.isEmpty(leftStr)) {
            leftTv.setVisibility(View.GONE);
            leftIm.setVisibility(View.VISIBLE);
            leftIm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null) {
                        listener.onClick(view);
                    }
                }
            });
        } else {
            leftIm.setVisibility(View.GONE);
            leftTv.setText(leftStr);
            leftTv.setVisibility(View.VISIBLE);
            leftTv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null) {
                        listener.onClick(view);
                    }
                }
            });
        }
    }
}
