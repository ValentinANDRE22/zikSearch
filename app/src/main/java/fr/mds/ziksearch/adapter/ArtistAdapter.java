package fr.mds.ziksearch.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.mds.valentinandre.ziksearch.R;

import com.squareup.picasso.Picasso;

import java.util.List;

import fr.mds.ziksearch.holders.ArtistViewHolder;
import fr.mds.ziksearch.interfaces.OnArtistClickListener;
import fr.mds.ziksearch.model.Artist;

/**
 * Created by kingdom on 19/09/17.
 */

public class ArtistAdapter extends RecyclerView.Adapter<ArtistViewHolder> {
    private List<Artist> artists;
    private  Context context;
    private OnArtistClickListener onClickListener;



    public ArtistAdapter(List<Artist> artists, Context context, OnArtistClickListener onClickListener){
        this.artists = artists;
        this.context = context;
        this.onClickListener = onClickListener;
    }


    @Override
    public ArtistViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ArtistViewHolder artistViewHolder = new ArtistViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.artist_view_holder, parent, false));


        return artistViewHolder;
    }

    @Override
    public void onBindViewHolder(ArtistViewHolder holder, final int position) {
        Artist artist = artists.get(position);
        holder.getTextViewttribut().setText(artist.getName());

        Picasso.with(this.context)
                .load(artist.getImage())
                .placeholder(R.drawable.user)
                .into( holder.getImageViewttribut());

        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void  onClick(View view){
                onClickListener.onArtistClick(artists.get(position), position, view);
            }
        });
    }

    @Override
    public int getItemCount() {
        return artists.size();
    }
}

