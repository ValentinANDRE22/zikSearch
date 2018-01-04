package fr.mds.ziksearch.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by kingdom on 04/01/18.
 */

public class Album {

    private  int id;

    private  String title;

    @SerializedName("cover_medium")
    private String cover;

    private Artist artist;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }
}
