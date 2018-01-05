package fr.mds.ziksearch.holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mds.valentinandre.ziksearch.R;

/**
 * Created by kingdom on 05/01/18.
 */

public class TrackViewHolder extends RecyclerView.ViewHolder {

    public View itemViews;

    public TrackViewHolder(View itemView) {
        super(itemView);
        this.itemViews = itemView;
    }

    public TextView getTextViewttribut() {
        return this.itemViews.findViewById(R.id.tv_name);
    }

    public ImageView getImageViewttribut() {
        return this.itemViews.findViewById(R.id.iv_image);
    }
}


