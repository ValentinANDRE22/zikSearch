package fr.mds.ziksearch.services;

import fr.mds.ziksearch.container.AlbumContainer;
import fr.mds.ziksearch.container.ArtistContainer;
import fr.mds.ziksearch.container.TrackContainer;
import fr.mds.ziksearch.model.Artist;
import fr.mds.ziksearch.model.Track;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by kingdom on 04/01/18.
 */

public interface ApiService {

    public static final String ENDPOINT = "https://api.deezer.com/";


    @GET("search/album")
    Call<AlbumContainer>getAlbums(@Query("q") String album, @Query("limit") int limit );


    @GET("search/artist")
    Call<ArtistContainer>getArtists(@Query("q") String artist, @Query("limit") int limit );

   @GET("search/track")
    Call<TrackContainer>getTracks(@Query("q") String artist, @Query("limit") int limit );

}
