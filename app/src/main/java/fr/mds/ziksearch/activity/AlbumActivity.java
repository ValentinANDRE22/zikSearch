package fr.mds.ziksearch.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.mds.valentinandre.ziksearch.R;

import java.util.List;

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

public class AlbumActivity  extends AppCompatActivity {
    private Album album;
    private List<Track> tracks;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.out.println("oncreate");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album);
/*
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        album = (Album) bundle.get("artist");
*/

        album = new Album();
        album.setId(302127);

        this.getTracks();
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
