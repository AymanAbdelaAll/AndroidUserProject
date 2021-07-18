package com.example.userproject.Networking.User;

import com.example.userproject.POJO.User;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface UserInterface {

    @GET("users")
    public Observable<List<User>> getUsers();
    @GET("users/{id}")
    public Observable<User> getUser(@Path("id") int id);
}
