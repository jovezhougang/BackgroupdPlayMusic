package com.example.backgroupplaymusic;

import java.io.IOException;

import android.app.Service;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import android.os.Handler;
import android.os.IBinder;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
/**
 * 
 * @author gang QQ635901979
 * 
 */
public class PlayMusicService extends Service {

    RemoteCallbackList<IPlayMusicListenery> callback = new RemoteCallbackList<IPlayMusicListenery>();
    MediaPlayer mp;
    @Override
    public IBinder onBind(Intent intent) {
        return new Play();
    }

    @Override
    public void onCreate() {
        if (null == mp) {
            mp = new MediaPlayer();
        }
        super.onCreate();
    }

    class Play extends IPlay.Stub {
        @Override
        public void play() throws RemoteException {
            mp.reset();
            try {
                mp.setDataSource(getAssets().openFd("background.mp3")
                        .getFileDescriptor());
                mp.setAudioStreamType(AudioManager.STREAM_MUSIC);
                mp.setOnPreparedListener(new OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mp) {
                        mp.start();
                        handler.post(mUpdateProgressRunnable);
                        int N = callback.beginBroadcast();
                        for (int i = 0; i < N; ++i) {
                            IPlayMusicListenery listenery = callback
                                    .getBroadcastItem(i);
                            try {
                                listenery.total(mp.getDuration());
                                listenery.progress(0);
                            } catch (RemoteException e) {
                                e.printStackTrace();
                            }
                            callback.finishBroadcast();
                        }
                    }
                });
                mp.prepareAsync();
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalStateException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void pasue() throws RemoteException {
            if (null != mp && mp.isPlaying()) {
                mp.pause();
            }
        }

        @Override
        public void registerLisenery(IPlayMusicListenery listenery)
                throws RemoteException {
            callback.register(listenery);
            handler.postDelayed(mUpdateProgressRunnable, 0);
        }

        @Override
        public void unRegisterLisenery(IPlayMusicListenery listenery)
                throws RemoteException {
            callback.unregister(listenery);
            handler.removeCallbacks(mUpdateProgressRunnable);
        }
    }

    Handler handler = new Handler();
    UpdateProgressRunnable mUpdateProgressRunnable = new UpdateProgressRunnable();
    class UpdateProgressRunnable implements Runnable {
        @Override
        public void run() {
            if (null != mp && mp.isPlaying()) {
                int N = callback.beginBroadcast();
                for (int i = 0; i < N; ++i) {
                    IPlayMusicListenery listenery = callback
                            .getBroadcastItem(i);
                    try {
                        listenery.total(mp.getDuration());
                        listenery.progress(mp.getCurrentPosition());
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                    callback.finishBroadcast();
                    handler.postDelayed(mUpdateProgressRunnable, 100);
                }
            }
        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (null != mp) {
            mp.release();
            mp = null;
        }
    }
}
