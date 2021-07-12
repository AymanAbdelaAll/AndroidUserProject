package com.example.userproject.UI;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.ActionMode;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.LazyHeaders;
import com.example.userproject.R;
import com.example.userproject.VM.PhotoViewModel;

public class PhotoDetailsActivity extends AppCompatActivity {

    private static String KEY_PHOTO="Photo";
    private TextView tvTitle;
    private ImageView ivPhotoUrl;

    public static void start(Context context, PhotoViewModel photoViewModel) {
       Intent starter = new Intent(context, PhotoDetailsActivity.class);
       starter.putExtra(KEY_PHOTO,photoViewModel);
       context.startActivity(starter);
   }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_details);
        tvTitle=findViewById(R.id.photodetails_text_title);
        ivPhotoUrl=findViewById(R.id.photodetails_image_url);

        Intent intent=getIntent();
        PhotoViewModel photoViewModel=(PhotoViewModel) intent.getSerializableExtra(PhotoDetailsActivity.KEY_PHOTO);
        String url=photoViewModel.getUrl();

        fetchPhoto(url);

        tvTitle.setText(photoViewModel.getTitle());
    }

    private void fetchPhoto(String url) {
        GlideUrl glideUrl = new GlideUrl(url, new LazyHeaders.Builder()
                .addHeader("User-Agent",
                        "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_2) AppleWebKit / 537.36(KHTML, like Gecko) Chrome  47.0.2526.106 Safari / 537.36")
                .build());

        Glide.with(this)
                .load(glideUrl)
                .into(ivPhotoUrl);
    }
}