package fr.mds.ziksearch.container;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import fr.mds.ziksearch.model.Album;

/**
 * Created by kingdom on 04/01/18.
 */

public class AlbumContainer {

        @SerializedName("data")
        private List<Album> albums ;

        public List<Album> getAlbums() {
            return albums;
        }

        public void setAlbums(List<Album> albums) {
            this.albums = albums;
        }

}
