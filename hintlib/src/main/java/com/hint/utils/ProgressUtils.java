package com.hint.utils;

import android.content.Context;

public class ProgressUtils {
    private static HintLoadingDialog loadingDialog = null;

    public static void showLoading(Context context) {
        loadingDialog = new HintLoadingDialog(context, 0, "");
        if (null != loadingDialog && !loadingDialog.isShowing()) {
            loadingDialog.show();
        }
    }

    public static void showLoading(Context context, String msg) {
        loadingDialog = new HintLoadingDialog(context, 1, msg);
        if (null != loadingDialog && !loadingDialog.isShowing()) {
            loadingDialog.show();
        }
    }

    public static void dismissLoading() {
        if (null != loadingDialog) {
            if (loadingDialog.isShowing()) {
                loadingDialog.cancel();
                loadingDialog.dismiss();
            }
            loadingDialog = null;
        }
    }
}