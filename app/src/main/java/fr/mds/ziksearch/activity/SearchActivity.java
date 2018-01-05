package fr.mds.ziksearch.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.mds.valentinandre.ziksearch.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import fr.mds.ziksearch.adapter.AlbumAdapter;
import fr.mds.ziksearch.adapter.ArtistAdapter;
import fr.mds.ziksearch.adapter.TrackAdapter;
import fr.mds.ziksearch.container.AlbumContainer;
import fr.mds.ziksearch.container.ArtistContainer;
import fr.mds.ziksearch.container.TrackContainer;
import fr.mds.ziksearch.interfaces.OnAlbumClickListener;
import fr.mds.ziksearch.interfaces.OnArtistClickListener;
import fr.mds.ziksearch.interfaces.OnTrackClickListener;
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

public class SearchActivity extends AppCompatActivity implements  OnArtistClickListener, OnAlbumClickListener, OnTrackClickListener{


    public List<Album> albums;
    public List<Artist> artists;
    public List<Track> tracks;
    public int limit = 15;
    public ArtistAdapter artistAdapter ;
    public AlbumAdapter albumAdapter ;
    public TrackAdapter trackAdapter ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.out.println("oncreate");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        System.out.println("start");

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String keywords = (String) bundle.get("keywords");

        artists = new ArrayList<>();
        albums = new ArrayList<>();
        tracks = new ArrayList<>();

        artistAdapter = new ArtistAdapter(artists, this,  this);
        albumAdapter = new AlbumAdapter(albums, this,  this);
        trackAdapter = new TrackAdapter(tracks, this, this);

        getAlbums(keywords);
        getTracks(keywords);
        getArtist(keywords);



        final GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        recyclerView.setAdapter(artistAdapter);
        recyclerView.setLayoutManager(gridLayoutManager);

        final GridLayoutManager gridLayoutAlbumManager = new GridLayoutManager(this, 2);

        //gridLayoutAlbumManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        RecyclerView albumRecyclerView = (RecyclerView) findViewById(R.id.rv_album);

        albumRecyclerView.setAdapter(albumAdapter);
        albumRecyclerView.setLayoutManager(gridLayoutAlbumManager);

        final GridLayoutManager gridLayoutTrackManager = new GridLayoutManager(this, 1);
        RecyclerView trackRecyclerView = (RecyclerView) findViewById(R.id.rv_track);

        trackRecyclerView.setAdapter(trackAdapter);
        trackRecyclerView.setLayoutManager(gridLayoutTrackManager);


        FloatingActionButton fab_search = findViewById(R.id.fab_search);

        fab_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SearchActivity.this, MainActivity.class);

                startActivity(intent);
            }
        });

    }

    private  void getAlbums(String search)
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiService.ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService service = retrofit.create(ApiService.class);

        Call<AlbumContainer> callback = service.getAlbums(search, 4);

        callback.enqueue(new Callback<AlbumContainer>() {
            @Override
            public void onResponse(Call<AlbumContainer> call, Response<AlbumContainer> response) {
                System.out.println("retrofit");

                if (response.isSuccessful()) {

                    AlbumContainer datas =  response.body();


                    albums.addAll(datas.getAlbums());
                    albumAdapter.notifyDataSetChanged();
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

        Call<ArtistContainer> callback = service.getArtists(search, 4);

        callback.enqueue(new Callback<ArtistContainer>() {
            @Override
            public void onResponse(Call<ArtistContainer> call, Response<ArtistContainer> response) {
                System.out.println("retrofit");

                if (response.isSuccessful()) {

                    ArtistContainer datas =  response.body();

                    artists.addAll(datas.getArtists());
                    artistAdapter.notifyDataSetChanged();
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

        Call<TrackContainer> callback = service.getTracks(search, 15);

        callback.enqueue(new Callback<TrackContainer>() {
            @Override
            public void onResponse(Call<TrackContainer> call, Response<TrackContainer> response) {
                System.out.println("retrofit");

                if (response.isSuccessful()) {

                    TrackContainer datas =  response.body();

                    tracks.addAll(datas.getTracks());
                    trackAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<TrackContainer> call, Throwable t) {
                System.out.println(t.getMessage());

            }
        });
    }

    @Override
    public void onArtistClick(Artist artist, int position, View itemViewHolder) {
        System.out.println(artist.getName());


        Intent intent = new Intent(SearchActivity.this, ArtistActivity.class);
        intent.putExtra("id", artist.getId());
        intent.putExtra("image", artist.getImage());
        intent.putExtra("name", artist.getName());
        startActivity(intent);

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    public void onAlbumClick(Album album, int position, View itemViewHolder) {
        System.out.println(album.getTitle());


        Intent intent = new Intent(SearchActivity.this, AlbumActivity.class);
        intent.putExtra("id", album.getId());
        intent.putExtra("cover", album.getCover());
        intent.putExtra("title", album.getTitle());

        startActivity(intent);
    }

    @Override
    public void onTrackClick(Track track, int position, View itemViewHolder) {
        System.out.println(track.getTitle());


        Intent intent = new Intent(SearchActivity.this, PreviewActivity.class);
        intent.putExtra("track", track.getPreview());
        intent.putExtra("cover", track.getAlbum().getCover());
        intent.putExtra("title", track.getTitle());

        startActivity(intent);

    }
}
