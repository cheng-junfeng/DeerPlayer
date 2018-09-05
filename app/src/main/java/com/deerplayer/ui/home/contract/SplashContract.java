package com.deerplayer.ui.home.contract;


import android.content.Context;
import android.view.animation.Animation;


public interface SplashContract {
    interface View {
        void animateBackgroundImage(Animation animation);

        void initViews(String versionName, int backgroundResId);

        void toHomePage();
    }

    interface Presenter {
        void initialized();

        String getVersionName(Context context);

        Animation getBackgroundImageAnimation(Context context);
    }
}
