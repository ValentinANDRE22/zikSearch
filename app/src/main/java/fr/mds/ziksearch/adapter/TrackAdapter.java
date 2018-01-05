package fr.mds.ziksearch.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mds.valentinandre.ziksearch.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import fr.mds.ziksearch.holders.TrackViewHolder;
import fr.mds.ziksearch.interfaces.OnTrackClickListener;
import fr.mds.ziksearch.model.Track;

/**
 * Created by kingdom on 19/09/17.
 */

public class TrackAdapter extends RecyclerView.Adapter<TrackViewHolder> {
    private List<Track> tracks;
    private  Context context;
    private OnTrackClickListener onClickListener;



    public TrackAdapter(List<Track> tracks, Context context, OnTrackClickListener onClickListener){
        this.tracks = tracks;
        this.context = context;
        this.onClickListener = onClickListener;
    }


    @Override
    public TrackViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        TrackViewHolder trackViewHolder = new TrackViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.track_view_holder, parent, false));


        return trackViewHolder;
    }

    @Override
    public void onBindViewHolder(TrackViewHolder holder, final int position) {
        Track track = tracks.get(position);
        holder.getTextViewttribut().setText(track.getTitle()+"\n \n"+ "Ecouter 30 sec");

        if(track.getAlbum() != null)
        {
            Picasso.with(this.context)
                    .load(track.getAlbum().getCover())
                    .placeholder(R.drawable.user)
                    .into( holder.getImageViewttribut());
        }else
        {
            Picasso.with(this.context)
                    .load(R.drawable.sound)
                    .placeholder(R.drawable.user)
                    .into( holder.getImageViewttribut());

        }
        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void  onClick(View view){
                onClickListener.onTrackClick(tracks.get(position), position, view);
            }
        });
    }

    @Override
    public int getItemCount() {
        return tracks.size();
    }
}

