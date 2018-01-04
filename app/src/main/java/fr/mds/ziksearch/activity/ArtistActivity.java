package fr.mds.ziksearch.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.mds.valentinandre.ziksearch.R;

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

/**
 * Created by kingdom on 04/01/18.
 */

public class ArtistActivity extends AppCompatActivity {


    private Artist artist;
    private List<Album> albums;
    private List<Track> tracks;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.out.println("oncreate");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artist);




/*
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        artist = (Artist) bundle.get("artist");
*/

        artist = new Artist();
        artist.setId(13);

        this.getAlbums();
        this.getTracks();


    }

    private  void getAlbums()
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiService.ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService service = retrofit.create(ApiService.class);

        Call<AlbumContainer> callback = service.getAlbumsByArtist(artist.getId(), 4);

        callback.enqueue(new Callback<AlbumContainer>() {
            @Override
            public void onResponse(Call<AlbumContainer> call, Response<AlbumContainer> response) {
                System.out.println("retrofit");

                if (response.isSuccessful()) {

                    AlbumContainer datas =  response.body();

                    albums = datas.getAlbums();

                    for (Album album : albums)
                    {
                        System.out.println("albums : " + album.getTitle());

                    }
                }
            }

            @Override
            public void onFailure(Call<AlbumContainer> call, Throwable t) {
                System.out.println(t.getMessage());

            }
        });
    }

    private  void getTracks()
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiService.ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService service = retrofit.create(ApiService.class);

        Call<TrackContainer> callback = service.getTracksByArtist(artist.getId(), 10);

        callback.enqueue(new Callback<TrackContainer>() {
            @Override
            public void onResponse(Call<TrackContainer> call, Response<TrackContainer> response) {
                System.out.println("retrofit");

                if (response.isSuccessful()) {

                    TrackContainer datas =  response.body();

                    tracks = datas.getTracks();

                    for (Track track : tracks)
                    {
                        System.out.println("Tracks : " + track.getTitle());

                    }
                }
            }

            @Override
            public void onFailure(Call<TrackContainer> call, Throwable t) {
                System.out.println(t.getMessage());

            }
        });
    }



}
