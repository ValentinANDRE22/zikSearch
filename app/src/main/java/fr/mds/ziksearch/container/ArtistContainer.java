package fr.mds.ziksearch.container;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import fr.mds.ziksearch.model.Album;
import fr.mds.ziksearch.model.Artist;

/**
 * Created by kingdom on 04/01/18.
 */

public class ArtistContainer {

        @SerializedName("data")
        private List<Artist> artists ;

        public List<Artist> getArtists() {
            return artists;
        }

        public void setArtists(List<Artist> artists) {
            this.artists = artists;
        }

}
