package com.example.backgroupplaymusic;

import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.app.Activity;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.view.Menu;
import android.view.View;
import android.widget.ProgressBar;
/**
 * 
 * @author gang QQ635901979
 *
 */
public class MainActivity extends Activity {

    IPlay play;
    ProgressBar pb;
    private ServiceConnection conn = new ServiceConnection() {

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            play = IPlay.Stub.asInterface(service);
            try {
                play.registerLisenery(listenery);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    };

    private IPlayMusicListenery.Stub listenery = new IPlayMusicListenery.Stub() {

        @Override
        public void total(int time) throws RemoteException {
            pb.setMax(time);
        }

        @Override
        public void progress(int time) throws RemoteException {
            pb.setProgress(time);
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /**
         * 启动一次服务 保证Service 能在后台一直运行（最好是在Application里面启动）
         */
        startService(new Intent(getApplicationContext(), PlayMusicService.class));
        pb = (ProgressBar) findViewById(R.id.progress);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        bindService(
                new Intent(getApplicationContext(), PlayMusicService.class),
                conn, Service.BIND_AUTO_CREATE);
    }
    @Override
    protected void onPause() {
        super.onPause();

        try {
            play.unRegisterLisenery(listenery);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        unbindService(conn);

    }
    public void onPlay(View v) {
        try {
            play.play();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void onPause(View v) {
        try {
            play.pasue();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

}
