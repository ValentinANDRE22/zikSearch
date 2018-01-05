package fr.mds.ziksearch.interfaces;

import android.view.View;

import fr.mds.ziksearch.model.Album;
import fr.mds.ziksearch.model.Artist;

/**
 * Created by kingdom on 20/09/17.
 */

public interface OnAlbumClickListener {
    void onAlbumClick(Album album, int position, View itemViewHolder);
}
