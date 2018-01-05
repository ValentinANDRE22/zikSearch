package fr.mds.ziksearch.interfaces;

import android.view.View;

import fr.mds.ziksearch.model.Artist;
import fr.mds.ziksearch.model.Track;

/**
 * Created by kingdom on 20/09/17.
 */

public interface OnTrackClickListener {
    void onTrackClick(Track track, int position, View itemViewHolder);
}
