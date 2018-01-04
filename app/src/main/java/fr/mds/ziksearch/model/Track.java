package fr.mds.ziksearch.model;

import com.google.gson.annotations.SerializedName;
import com.google.gson.internal.Primitives;

/**
 * Created by kingdom on 04/01/18.
 */

public class Track {

    private  int id;
    private String title;
    private String link;

    @SerializedName("picture_medium")
    private String image;
    private  String preview;
    private  Artist artist;
    private  Album album;

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

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPreview() {
        return preview;
    }

    public void setPreview(String preview) {
        this.preview = preview;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }
}
