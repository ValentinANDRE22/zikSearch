package fr.mds.ziksearch.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mds.valentinandre.ziksearch.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import fr.mds.ziksearch.holders.AlbumViewHolder;
import fr.mds.ziksearch.interfaces.OnAlbumClickListener;
import fr.mds.ziksearch.model.Album;
import fr.mds.ziksearch.model.Album;

/**
 * Created by kingdom on 19/09/17.
 */

public class AlbumAdapter extends RecyclerView.Adapter<AlbumViewHolder> {
    private List<Album> albums;
    private  Context context;
    private OnAlbumClickListener onClickListener;



    public AlbumAdapter(List<Album> albums, Context context, OnAlbumClickListener onClickListener){
        this.albums = albums;
        this.context = context;
        this.onClickListener = onClickListener;
    }


    @Override
    public AlbumViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        AlbumViewHolder AlbumViewHolder = new AlbumViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.artist_view_holder, parent, false));


        return AlbumViewHolder;
    }

    @Override
    public void onBindViewHolder(AlbumViewHolder holder, final int position) {
        final Album album = albums.get(position);
        holder.getTextViewttribut().setText(album.getTitle());

        Picasso.with(this.context)
                .load(album.getCover())
                .placeholder(R.drawable.user)
                .into( holder.getImageViewttribut());

        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void  onClick(View view){
                onClickListener.onAlbumClick(albums.get(position), position, view);
            }
        });
    }

    @Override
    public int getItemCount() {
        return albums.size();
    }
}

