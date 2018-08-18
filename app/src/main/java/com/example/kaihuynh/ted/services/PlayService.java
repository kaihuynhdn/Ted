package com.example.kaihuynh.ted.services;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Binder;
import android.os.IBinder;
import android.os.PowerManager;
import android.support.annotation.Nullable;
import android.widget.Button;
import android.widget.VideoView;

import com.example.kaihuynh.ted.R;

public class PlayService extends Service  implements
        MediaPlayer.OnPreparedListener, MediaPlayer.OnErrorListener, MediaPlayer.OnCompletionListener{

    public static final int STOPPED = 0;
    public static final int PAUSED = 1;
    public static final int PLAYING = 2;
    public static boolean isStarted = false;
    public boolean isPlay = true;

    private int playerState = STOPPED;
    private final IBinder playBinder = new PlayBinder();

    private MediaPlayer mediaPlayer;
    private Button playButton;
    private VideoView videoView;

    private int position;

    @Override
    public void onCreate() {
        super.onCreate();

        mediaPlayer = new MediaPlayer();
    }

    public void initUI() {

//        this.videoView = videoView;
        videoView = new VideoView(this);
        String path = "android.resource://" + getPackageName() + "/" + R.raw.video;
        videoView.setVideoURI(Uri.parse(path));
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp1) {
                mp1.setVolume(0,0);
                if (!isStarted){
                    mediaPlayer.start();
                    isStarted = true;
                }else {
                    int positon = mediaPlayer.getCurrentPosition() - 1000;
                    videoView.seekTo(positon);
                    mediaPlayer.seekTo(positon);
                    if (isPlay){
                        mediaPlayer.start();
                        videoView.start();
                    }else{
                        videoView.pause();
                        mediaPlayer.pause();
                        playerState = PAUSED;
                    }
                }
            }
        });
    }

    private void initMediaPlayer() {
        mediaPlayer.setWakeMode(getApplicationContext(), PowerManager.PARTIAL_WAKE_LOCK);
        mediaPlayer.setOnPreparedListener(this);
        mediaPlayer.setOnCompletionListener(this);
        mediaPlayer.setOnErrorListener(this);
    }


    @Override
    public void onDestroy() {
        mediaPlayer.stop();
//        if (mediaPlayer.isPlaying()){
//            Intent intent = new Intent("service.transfers.data");
//            intent.putExtra("position", mediaPlayer.getCurrentPosition());
//            LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
//        }
//        if (mediaPlayer!=null){
//            mediaPlayer.release();
//        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {

        return playBinder;
    }

    public class PlayBinder extends Binder{
        public PlayService getService(){
            return PlayService.this;
        }
    }

    @Override
    public void onCompletion(MediaPlayer mediaPlayer) {

    }

    @Override
    public boolean onError(MediaPlayer mediaPlayer, int i, int i1) {
        return false;
    }

    @Override
    public void onPrepared(MediaPlayer mediaPlayer) {
        mediaPlayer.start();
        videoView.start();
    }

    public int getPosition(){
        return mediaPlayer.getCurrentPosition();
    }

    public void updateVideoView(boolean isPlay){
        this.isPlay = isPlay;
        String path = "android.resource://" + getPackageName() + "/" + R.raw.video;
        videoView.setVideoURI(Uri.parse(path));
    }

    public  VideoView getView(){
        return videoView;
    }

    public void togglePlay() {
        switch(playerState) {
            case STOPPED:
                playSong();
                break;
            case PAUSED:
                mediaPlayer.start();
                videoView.start();
                playerState = PLAYING;
                break;
            case PLAYING:
                videoView.pause();
                mediaPlayer.pause();
                playerState = PAUSED;
                break;
        }
    }

    private void playSong() {
        mediaPlayer = MediaPlayer.create(this, R.raw.video);
        initMediaPlayer();
        playerState = PLAYING;
    }
}
