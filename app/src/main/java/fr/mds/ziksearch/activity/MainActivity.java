package fr.mds.ziksearch.activity;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import com.mds.valentinandre.ziksearch.R;

import java.util.List;

import fr.mds.ziksearch.model.Album;
import fr.mds.ziksearch.model.Artist;
import fr.mds.ziksearch.model.Track;

public class MainActivity extends AppCompatActivity {

    public List<Album> albums;
    public List<Artist> artists;
    public List<Track> tracks;
    public  int limit = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ImageButton ib_search = (ImageButton) findViewById(R.id.ib_search);
        final EditText et_keywords = (EditText) findViewById(R.id.et_keywords);


        ib_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(et_keywords.getText().length() > 0 )
                {
                    Intent intent = new Intent(MainActivity.this, SearchActivity.class);
                    intent.putExtra("keywords", et_keywords.getText().toString());
                    startActivity(intent);
                }
            }
        });

    }


}
