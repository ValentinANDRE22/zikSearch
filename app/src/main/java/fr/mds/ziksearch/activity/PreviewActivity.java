package fr.mds.ziksearch.activity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.mds.valentinandre.ziksearch.R;
import com.squareup.picasso.Picasso;

import fr.mds.ziksearch.holders.TrackViewHolder;
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
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        getSupportActionBar().setTitle((String) bundle.get("title"));

        Track track = new Track();
        track.setPreview((String) bundle.get("track"));
        track.setImage((String) bundle.get("cover"));
        final ImageButton play = (ImageButton) findViewById(R.id.start);
        System.out.println(track.getImage());

        Picasso.with(this)
                .load(track.getImage())
                .placeholder(R.drawable.user)
                .into((ImageView) findViewById(R.id.iv_image));

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
                            play.setImageResource(R.drawable.pause);


                        }
                        else{

                            playbackPosition = mediaPlayer.getCurrentPosition();
                            mediaPlayer.pause();
                            paused = true;
                            play.setImageResource(R.drawable.play);

                        }
                    }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
            }
        });
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
