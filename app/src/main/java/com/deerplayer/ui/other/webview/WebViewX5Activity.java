package com.deerplayer.ui.other.webview;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ProgressBar;

import com.deerplayer.R;
import com.deerplayer.app.base.BaseSwipeBackActivity;
import com.deerplayer.view.X5WebView;
import com.library.utils.CommonUtils;
import com.library.utils.TLog;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

import butterknife.BindView;


public class WebViewX5Activity extends BaseSwipeBackActivity {

	private static final String TAG = "WebViewX5Activity";
	public static final String BUNDLE_KEY_URL = "BUNDLE_KEY_URL";
	public static final String BUNDLE_KEY_TITLE = "BUNDLE_KEY_TITLE";

	private String mWebUrl = null;
	private String mWebTitle = "未知";

	@BindView(R.id.webView)
    X5WebView webView;
	@BindView(R.id.pb)
	ProgressBar pb;

	@Override
	protected int getContentViewLayoutID() {
		return R.layout.activity_webview_x5;
	}

	@Override
	protected void getBundleExtras(Bundle extras) {
		mWebTitle = extras.getString(BUNDLE_KEY_TITLE);
		mWebUrl = extras.getString(BUNDLE_KEY_URL);
	}

	@Override
	protected void init() {
		setTitle(mWebTitle);
		initWebView();
	}

	private void initWebView() {
		webView.setWebChromeClient(new MyWebChromeClient());
		webView.setWebViewClient(new MyWebViewClient());
		TLog.d(TAG, "---start");
		if (!CommonUtils.isEmpty(mWebUrl)) {
			webView.loadUrl(mWebUrl);
		}
	}

	private class MyWebChromeClient extends WebChromeClient {
		@Override
		public void onProgressChanged(WebView view, int newProgress) {
			super.onProgressChanged(view, newProgress);
            if (pb == null) {
                return;
            }
			if (newProgress == 100) {
                pb.setVisibility(View.GONE);
			}
            else {
                pb.setVisibility(View.VISIBLE);
                pb.setProgress(newProgress);
            }
		}
	}

	private class MyWebViewClient extends WebViewClient {
		@Override
		public boolean shouldOverrideUrlLoading(WebView view, String url) {
			view.loadUrl(url);
			return true;
		}
	}

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            //全屏播放退出全屏
            if (webView.canGoBack()) {
                //返回上一页面
                webView.goBack();
                return true;
            } else {
                finish();
            }
        }
        return false;
    }
}
