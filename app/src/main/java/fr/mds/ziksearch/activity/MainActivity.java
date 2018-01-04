package fr.mds.ziksearch.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.mds.valentinandre.ziksearch.R;

import java.util.ArrayList;
import java.util.List;

import fr.mds.ziksearch.container.AlbumContainer;
import fr.mds.ziksearch.container.ArtistContainer;
import fr.mds.ziksearch.container.TrackContainer;
import fr.mds.ziksearch.model.Album;
import fr.mds.ziksearch.model.Artist;
import fr.mds.ziksearch.model.Track;
import fr.mds.ziksearch.services.ApiService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    public List<Album> albums;
    public List<Artist> artists;
    public List<Track> tracks;
    public  int limit = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.out.println("oncreate");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //vers search
        System.out.println("sta rt");

    }


}
