package fr.mds.ziksearch.activity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import com.mds.valentinandre.ziksearch.R;

import fr.mds.ziksearch.model.Track;


/**
 * Created by kingdom on 04/01/18.
 */

public class PreviewActivity extends AppCompatActivity {

    private  Track track ;
    private MediaPlayer mediaPlayer;
    private int playbackPosition=0;

    boolean  start = true;
    boolean paused = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview);

        /*
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        track = (Track) bundle.get("track");
*/

        Track track = new Track();
        track.setPreview("https://e-cdns-preview-7.dzcdn.net/stream/72dcaf71ea645b6740b22f6182a932e9-3.mp3");
        ImageButton play = (ImageButton) findViewById(R.id.start);

        try {
            this.playAudio(track.getPreview());
        } catch (Exception e) {
            e.printStackTrace();
        }

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try
                {

                    if(start) {

                        if(paused)
                        {
                            mediaPlayer.seekTo(playbackPosition);
                            mediaPlayer.start();
                            paused = false;

                        }
                        else{

                            playbackPosition = mediaPlayer.getCurrentPosition();
                            mediaPlayer.pause();
                            paused = true;
                        }
                    }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
            }
        });
    }

    public void doClick(View view) {

        switch(view.getId()) {
            case R.id.start:

                break;

        }
/*
        case R.id.pausePlayerBtn:
        if(mediaPlayer != null && mediaPlayer.isPlaying()) {
            playbackPosition = mediaPlayer.getCurrentPosition();
            mediaPlayer.pause();
        }
        break;
        case R.id.restartPlayerBtn:
        if(mediaPlayer != null && !mediaPlayer.isPlaying()) {
            mediaPlayer.seekTo(playbackPosition);
            mediaPlayer.start();
        }
        break;
        case R.id.stopPlayerBtn:
        if(mediaPlayer != null) {
            mediaPlayer.stop();
            playbackPosition = 0;
        }
        break;
        */
    }

    private void playAudio(String url) throws Exception
    {
        killMediaPlayer();

        mediaPlayer = new MediaPlayer();
        mediaPlayer.setDataSource(url);
        mediaPlayer.prepare();
        mediaPlayer.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        killMediaPlayer();
    }

    private void killMediaPlayer() {
        if(mediaPlayer!=null) {
            try {
                mediaPlayer.release();
            }
            catch(Exception e) {
                e.printStackTrace();
            }
        }
    }
}
