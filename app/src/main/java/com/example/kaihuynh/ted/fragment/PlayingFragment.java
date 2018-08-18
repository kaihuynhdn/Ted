package com.example.kaihuynh.ted.fragment;


import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.VideoView;

import com.example.kaihuynh.ted.R;
import com.example.kaihuynh.ted.services.PlayService;
import com.ohoussein.playpause.PlayPauseView;

/**
 * A simple {@link Fragment} subclass.
 */
public class PlayingFragment extends Fragment {

    private LinearLayout viewGroup;
    private VideoView videoView;
    private PlayPauseView playPauseView;
    private SeekBar seekBar;
    private MediaPlayer mp;

    private Intent playIntent;

    private PlayService playService;

    private boolean playBound = false;

    private OnDataPass mCallback;

    public interface OnDataPass {
        public void onDataPass(boolean isPlay);
    }

    public PlayingFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        mCallback = (OnDataPass) context;
    }

    private ServiceConnection playConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            PlayService.PlayBinder binder = (PlayService.PlayBinder) iBinder;
            playService = binder.getService();
            playService.initUI();
            playBound = true;

            playService.togglePlay();
            viewGroup.removeAllViews();
            viewGroup.addView(playService.getView());
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            playBound = false;
        }
    };


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_playing, container, false);

        getWidgets(view);
        init();
        setWidgets();
        setWidgetsListener();

        return view;
    }

    private void getWidgets(View view) {
        viewGroup = view.findViewById(R.id.view_group);
        videoView = view.findViewById(R.id.video_view);
        playPauseView = view.findViewById(R.id.btn_play_pause);
        seekBar = view.findViewById(R.id.seek_bar_play);
    }

    private void init() {
        playPauseView.toggle();
    }

    private void setWidgets() {


    }

    private void setWidgetsListener() {

        playPauseView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playPauseView.toggle();

                playService.togglePlay();
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        if (playIntent == null) {
            playIntent = new Intent(getContext(), PlayService.class);
            getContext().bindService(playIntent, playConnection, Context.BIND_AUTO_CREATE);
            getContext().startService(playIntent);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        mCallback.onDataPass(true);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (playService!=null ){
            playService.updateVideoView(!playPauseView.isPlay());
        }
    }

    @Override
    public void onDestroy() {
//        getContext().stopService(playIntent);
//        if (playBound) {
//            getContext().unbindService(playConnection);
//        }
        super.onDestroy();

    }


}
