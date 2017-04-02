package com.zxzq.treasure;

import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.graphics.SurfaceTexture;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;

import java.io.FileDescriptor;
import java.io.IOException;

/**
 * Created by Administrator on 2017/3/28 0028.
 */

public class MainMP4Fragment extends Fragment implements TextureView.SurfaceTextureListener  {

    private TextureView mTextureView;
    private MediaPlayer mMediaPlayer;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mTextureView = new TextureView(getContext());
        return mTextureView;

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        mTextureView.setSurfaceTextureListener(this);
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onSurfaceTextureAvailable(final SurfaceTexture surfaceTexture, int i, int i1) {
        AssetManager assets = getActivity().getAssets();
        try {
            AssetFileDescriptor assetFileDescriptor = assets.openFd("welcome.mp4");
            FileDescriptor fileDescriptor = assetFileDescriptor.getFileDescriptor();
            mMediaPlayer = new MediaPlayer();
            mMediaPlayer.setDataSource(fileDescriptor,assetFileDescriptor.getStartOffset(),assetFileDescriptor.getLength());
            mMediaPlayer.prepareAsync();
            mMediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mediaPlayer) {
                    Surface surface = new Surface(surfaceTexture);
                    mediaPlayer.setSurface(surface);
                    mediaPlayer.setLooping(true);
                    mediaPlayer.start();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i1) {

    }

    @Override
    public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
        return false;
    }

    @Override
    public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(mMediaPlayer!=null) {
            mMediaPlayer.stop();
            mMediaPlayer.release();
            mMediaPlayer = null;
        }
    }
}
