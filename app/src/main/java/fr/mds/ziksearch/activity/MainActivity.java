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

        System.out.println("start");

        albums = new ArrayList<>() ;


        this.getArtist("eminem");
        this.getAlbums("eminem");
        this.getTracks("eminem");
    }

    private  void getAlbums(String search)
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiService.ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService service = retrofit.create(ApiService.class);

        Call<AlbumContainer> callback = service.getAlbums(search, limit);

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

    private  void getArtist(String search)
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiService.ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService service = retrofit.create(ApiService.class);

        Call<ArtistContainer> callback = service.getArtists(search, limit);

        callback.enqueue(new Callback<ArtistContainer>() {
            @Override
            public void onResponse(Call<ArtistContainer> call, Response<ArtistContainer> response) {
                System.out.println("retrofit");

                if (response.isSuccessful()) {

                    ArtistContainer datas =  response.body();

                    artists = datas.getArtists();

                    for (Artist artist : artists)
                    {
                        System.out.println("Artists : " + artist.getName());

                    }
                }
            }

            @Override
            public void onFailure(Call<ArtistContainer> call, Throwable t) {
                System.out.println(t.getMessage());

            }
        });
    }

    private  void getTracks(String search)
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiService.ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService service = retrofit.create(ApiService.class);

        Call<TrackContainer> callback = service.getTracks(search, limit);

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
