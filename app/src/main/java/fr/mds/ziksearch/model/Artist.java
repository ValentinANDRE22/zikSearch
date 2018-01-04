package fr.mds.ziksearch.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by kingdom on 04/01/18.
 */

public class Artist {

    private int id;
    private  String name;

    @SerializedName("picture_medium")
    private  String image;

    @SerializedName("tracklist")
    private String trackList ;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTrackList() {
        return trackList;
    }

    public void setTrackList(String trackList) {
        this.trackList = trackList;
    }
}
