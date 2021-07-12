package com.example.userproject.UI.ListPhoto;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.LazyHeaders;
import com.example.userproject.R;
import com.example.userproject.UI.PhotoDetailsActivity;
import com.example.userproject.VM.PhotoViewModel;

import java.util.ArrayList;
import java.util.List;

public class PhotoListAdapter extends RecyclerView.Adapter<PhotoListAdapter.PhotoViewHolder>{
    private List<PhotoViewModel> photoViewModelList = new ArrayList<>();

    @NonNull
    @Override
    public PhotoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PhotoViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.photo_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PhotoViewHolder holder, int position) {
        PhotoViewModel photoViewModel=photoViewModelList.get(position);
        holder.tvTitle.setText(photoViewModel.getTitle());
        String photoThumbnailUrl=photoViewModel.getThumbnailUrl();
        View itemView=holder.itemView;

        fetchPhoto(holder, photoThumbnailUrl);


        holder.clPhotoItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PhotoDetailsActivity.start(v.getContext(),photoViewModel);
            }
        });

    }

    private void fetchPhoto(@NonNull PhotoViewHolder holder, String photoThumbnailUrl) {
        GlideUrl glideUrl = new GlideUrl(photoThumbnailUrl, new LazyHeaders.Builder()
                .addHeader("User-Agent",
                        "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_2) AppleWebKit / 537.36(KHTML, like Gecko) Chrome  47.0.2526.106 Safari / 537.36")
                .build());

        Glide.with(holder.itemView)
                .load(glideUrl)
                .into(holder.ivPhoto);
    }

    @Override
    public int getItemCount() {
        return photoViewModelList.size();
    }

    public void setList(List<PhotoViewModel> photoViewList) {
        this.photoViewModelList = photoViewList;

        notifyDataSetChanged();
    }

    public class PhotoViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle;
        ImageView ivPhoto;
        ConstraintLayout clPhotoItem;
        public PhotoViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle=itemView.findViewById(R.id.listphoto_text_title);
            ivPhoto=itemView.findViewById(R.id.listphoto_imageview_thumbnail);
            clPhotoItem=itemView.findViewById(R.id.photoitem_constraintlayout);
        }
    }
}
