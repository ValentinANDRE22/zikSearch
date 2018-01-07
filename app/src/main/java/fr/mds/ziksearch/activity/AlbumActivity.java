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

import fr.mds.ziksearch.adapter.TrackAdapter;
import fr.mds.ziksearch.container.TrackContainer;
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

public class AlbumActivity  extends AppCompatActivity implements OnTrackClickListener {
    private Album album;
    private List<Track> tracks;
    public TrackAdapter trackAdapter ;

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setContentView(R.layout.activity_album);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        album = new Album();
        album.setId((Integer) bundle.get("id"));
        album.setCover((String) bundle.get("cover"));
        getSupportActionBar().setTitle((String) bundle.get("title"));

        Picasso.with(this)
                .load(album.getCover()).fit().centerCrop()
                .placeholder(R.drawable.user)
                .into((ImageView) findViewById(R.id.iv_artist));

        this.getTracks();


        tracks = new ArrayList<>();
        trackAdapter = new TrackAdapter(tracks, this, this);

        final GridLayoutManager gridLayoutTrackManager = new GridLayoutManager(this, 1);
        RecyclerView trackRecyclerView = (RecyclerView) findViewById(R.id.rv_track);

        trackRecyclerView.setAdapter(trackAdapter);
        trackRecyclerView.setLayoutManager(gridLayoutTrackManager);


    }
    private  void getTracks()
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiService.ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService service = retrofit.create(ApiService.class);

        Call<TrackContainer> callback = service.getTracksByAlbum(album.getId());

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
    public void onTrackClick(Track track, int position, View itemViewHolder) {
        System.out.println(track.getTitle());


        Intent intent = new Intent(AlbumActivity.this, PreviewActivity.class);
        intent.putExtra("track", track.getPreview());
        intent.putExtra("title", track.getTitle());
        intent.putExtra("cover", album.getCover());
        startActivity(intent);

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
