package com.deerplayer.ui.player;

import android.content.res.AssetFileDescriptor;
import android.graphics.SurfaceTexture;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnBufferingUpdateListener;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.media.MediaPlayer.OnVideoSizeChangedListener;
import android.os.Bundle;
import android.view.Surface;
import android.view.TextureView;
import android.view.TextureView.SurfaceTextureListener;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.deerplayer.R;
import com.deerplayer.app.base.BaseSwipeBackActivity;
import com.library.utils.TLog;

import java.io.IOException;

import butterknife.BindView;
import butterknife.OnClick;

public class PlayerLocalActivity extends BaseSwipeBackActivity implements SurfaceTextureListener,
        OnBufferingUpdateListener, OnCompletionListener, OnPreparedListener,
        OnVideoSizeChangedListener {
    private static final String TAG = "PlayerLocalActivity";

    public static final String BUNDLE_KEY_TITLE = "BUNDLE_KEY_TITLE";
    public static final String BUNDLE_KEY_PATH = "BUNDLE_KEY_PATH";

    private String mPath = null;
    private String mTitle = "未知";

    @BindView(R.id.textureview)
    TextureView textureview;
    @BindView(R.id.pause)
    ImageView pause;

    private MediaPlayer mediaPlayer;
    private boolean isPause = false;
    private int currentPosition = 0;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_player_texture_view;
    }

    @Override
    protected void getBundleExtras(Bundle extras) {
        mTitle = extras.getString(BUNDLE_KEY_TITLE);
        mPath = extras.getString(BUNDLE_KEY_PATH);
    }

    @Override
    protected void init() {
        setTitle(mTitle);

        textureview.setSurfaceTextureListener(this);
    }

    @Override
    public void onVideoSizeChanged(MediaPlayer arg0, int arg1, int arg2) {
    }

    @Override
    public void onPrepared(MediaPlayer arg0) {

    }

    @Override
    public void onCompletion(MediaPlayer arg0) {

    }

    @Override
    public void onBufferingUpdate(MediaPlayer arg0, int arg1) {

    }

    @Override
    public void onSurfaceTextureAvailable(SurfaceTexture surface, int arg1,
                                          int arg2) {
        Surface s = new Surface(surface);
        try {
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setDataSource(mPath);
            mediaPlayer.setSurface(s);
            mediaPlayer.prepare();
            mediaPlayer.setOnBufferingUpdateListener(this);
            mediaPlayer.setOnCompletionListener(this);
            mediaPlayer.setOnPreparedListener(this);
            mediaPlayer.setOnVideoSizeChangedListener(this);
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mediaPlayer.setLooping(true);
            mediaPlayer.start();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onSurfaceTextureDestroyed(SurfaceTexture arg0) {
        mediaPlayer.stop();
        return true;
    }

    @Override
    public void onSurfaceTextureSizeChanged(SurfaceTexture arg0, int arg1,
                                            int arg2) {
    }

    @Override
    public void onSurfaceTextureUpdated(SurfaceTexture arg0) {
    }

    @OnClick({R.id.pause})
    public void onViewClicked(View view) {
        if(isPause){
            if (mediaPlayer != null) {
                mediaPlayer.start();
                mediaPlayer.seekTo(currentPosition);
                TLog.d(TAG, "replay replay :" + currentPosition);
            }
            pause.setImageResource(R.mipmap.ic_pause);
        }else{
            if (mediaPlayer != null && mediaPlayer.isPlaying()) {
                currentPosition = mediaPlayer.getCurrentPosition();
                mediaPlayer.pause();
            }
            pause.setImageResource(R.mipmap.ic_play);
        }
        isPause = !isPause;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        TLog.d(TAG, "onDestroy");
        //停止
        mediaPlayer.stop();
        mediaPlayer.release();
        mediaPlayer = null;
    }
}