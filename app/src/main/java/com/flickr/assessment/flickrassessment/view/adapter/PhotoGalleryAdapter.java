package com.flickr.assessment.flickrassessment.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.flickr.assessment.flickrassessment.R;
import com.flickr.assessment.flickrassessment.model.Items;
import com.flickr.assessment.flickrassessment.model.Media;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * PhotoGalleryAdapter contains the view layer binding
 * for a Flickr Photo Gallery.
 * <p>
 * NOTE: Adapters are only used for displaying data to
 * the view layer
 */
public class PhotoGalleryAdapter extends RecyclerView.Adapter<PhotoGalleryAdapter.PhotoHolder> {

    private RecyclerClickListener listener;
    private Items[] photos;

    public PhotoGalleryAdapter(RecyclerClickListener listener) {
        this.listener = listener;
    }

    public void setPhotos(Items[] photos) {
        this.photos = photos;
    }

    public Items getPhoto(int position) {
        return photos[position];
    }

    @Override
    public PhotoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_photo, parent, false);
        PhotoHolder viewHolder = new PhotoHolder(itemView, listener);
        return viewHolder;
    }

    @Override
    public int getItemCount() {
        return photos.length;
    }

    @Override
    public void onBindViewHolder(PhotoHolder holder, int position) {
        Items photoData = photos[position];
        holder.title.setText(photoData.getTitle());

        holder.dateTaken.setText(photoData.getDate_taken());

        Media media = photoData.getMedia();
        Picasso.with(holder.photo.getContext()).load(media.getM()).into(holder.photo);
    }

    public static class PhotoHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.photo_image)
        ImageView photo;
        @BindView(R.id.photo_title)
        TextView title;
        @BindView(R.id.photo_date_taken)
        TextView dateTaken;

        @BindView(R.id.photo_share)
        TextView shareBtn;
        @BindView(R.id.photo_container)
        LinearLayout exploreBtn;

        public PhotoHolder(View view, final RecyclerClickListener listener) {
            super(view);
            ButterKnife.bind(this, view);
            initialBtnListeners(listener);
        }

        public void initialBtnListeners(final RecyclerClickListener listener) {
            shareBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        listener.onShareClicked(getAdapterPosition());
                    }
                }
            });
            exploreBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        listener.onExploreClicked(getAdapterPosition());
                    }
                }
            });
        }
    }

    public interface RecyclerClickListener {
        void onShareClicked(int position);

        void onExploreClicked(int position);
    }
}
