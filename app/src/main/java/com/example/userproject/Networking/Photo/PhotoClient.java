package com.example.userproject.Networking.Photo;


import com.example.userproject.VM.PhotoViewModel;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class PhotoClient {
    private final String URL="https://jsonplaceholder.typicode.com/";
    private PhotoInterface photoInterface;
    private static PhotoClient instance ;

    public PhotoClient() {
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        photoInterface=retrofit.create(PhotoInterface.class);
    }

    public static PhotoClient getInstance(){
        if (instance ==null)
            instance =new PhotoClient();
        return instance ;
    }

    public Observable<List<PhotoViewModel>> getPhotos(){
        return getInstance ().photoInterface.getPhotos();
    }

    public Call<PhotoViewModel> getPhoto(int id){
        return getInstance ().photoInterface.getPhoto(id);
    }

}
