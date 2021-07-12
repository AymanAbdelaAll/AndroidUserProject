package com.example.userproject.Networking.Photo;



import com.example.userproject.VM.PhotoViewModel;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface PhotoInterface {
    @GET("photos")
    public Observable<List<PhotoViewModel>> getPhotos();
    @GET("photos/{id}")
    public Call<PhotoViewModel> getPhoto(@Path("id") int id);
}
