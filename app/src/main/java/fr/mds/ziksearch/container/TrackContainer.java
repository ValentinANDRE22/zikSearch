package fr.mds.ziksearch.container;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import fr.mds.ziksearch.model.Album;
import fr.mds.ziksearch.model.Track;

/**
 * Created by kingdom on 04/01/18.
 */

public class TrackContainer {

        @SerializedName("data")
        private List<Track> tracks ;

        public List<Track> getTracks() {
            return tracks;
        }

        public void setTracks(List<Track> tracks) {
            this.tracks = tracks;
        }

}
