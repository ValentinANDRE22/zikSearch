package fr.mds.ziksearch.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.mds.valentinandre.ziksearch.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import fr.mds.ziksearch.adapter.AlbumAdapter;
import fr.mds.ziksearch.adapter.TrackAdapter;
import fr.mds.ziksearch.container.AlbumContainer;
import fr.mds.ziksearch.container.ArtistContainer;
import fr.mds.ziksearch.container.TrackContainer;
import fr.mds.ziksearch.interfaces.OnAlbumClickListener;
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

public class ArtistActivity extends AppCompatActivity implements OnAlbumClickListener, OnTrackClickListener {


    private Artist artist;
    private List<Album> albums;
    private List<Track> tracks;
    public AlbumAdapter albumAdapter ;
    public TrackAdapter trackAdapter ;

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artist);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        artist = new Artist();
        artist.setId((Integer) bundle.get("id"));
        artist.setImage((String) bundle.get("image"));

        getSupportActionBar().setTitle((String) bundle.get("name"));

        Picasso.with(this)
                .load(artist.getImage()).fit().centerCrop()
                .placeholder(R.drawable.user)
                .into((ImageView) findViewById(R.id.iv_artist));

        albums = new ArrayList<>();
        tracks = new ArrayList<>();
        albumAdapter = new AlbumAdapter(albums, this,  this);
        trackAdapter = new TrackAdapter(tracks, this, this);
        final GridLayoutManager gridLayoutAlbumManager = new GridLayoutManager(this, 2);

        //gridLayoutAlbumManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        RecyclerView albumRecyclerView = (RecyclerView) findViewById(R.id.rv_album);

        albumRecyclerView.setAdapter(albumAdapter);
        albumRecyclerView.setLayoutManager(gridLayoutAlbumManager);

        final GridLayoutManager gridLayoutTrackManager = new GridLayoutManager(this, 1);
        RecyclerView trackRecyclerView = (RecyclerView) findViewById(R.id.rv_track);

        trackRecyclerView.setAdapter(trackAdapter);
        trackRecyclerView.setLayoutManager(gridLayoutTrackManager);

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
    public void onAlbumClick(Album album, int position, View itemViewHolder) {
        System.out.println(album.getTitle());


        Intent intent = new Intent(ArtistActivity.this, AlbumActivity.class);
        intent.putExtra("id", album.getId());
        intent.putExtra("title", album.getTitle());
        intent.putExtra("cover", album.getCover());
        startActivity(intent);
    }

    @Override
    public void onTrackClick(Track track, int position, View itemViewHolder) {
        System.out.println(track.getTitle());


        Intent intent = new Intent(ArtistActivity.this, PreviewActivity.class);
        intent.putExtra("track", track.getPreview());
        intent.putExtra("title", track.getTitle());

        intent.putExtra("cover", track.getAlbum().getCover());
        startActivity(intent);

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
