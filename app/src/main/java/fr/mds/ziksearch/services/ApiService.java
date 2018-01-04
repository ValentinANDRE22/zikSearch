package fr.mds.ziksearch.services;

import fr.mds.ziksearch.container.AlbumContainer;
import fr.mds.ziksearch.container.ArtistContainer;
import fr.mds.ziksearch.container.TrackContainer;
import fr.mds.ziksearch.model.Artist;
import fr.mds.ziksearch.model.Track;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
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

    @GET("artist/{id}/albums")
    Call<AlbumContainer> getAlbumsByArtist(@Path("id") int id, @Query("limit") int limit );


    @GET("artist/{id}/top")
    Call<TrackContainer> getTracksByArtist(@Path("id") int id, @Query("limit") int limit );

    @GET("album/{id}/tracks")
    Call<TrackContainer> getTracksByAlbum(@Path("id") int id);



    //album id
   // https://api.deezer.com/album/302127


}
