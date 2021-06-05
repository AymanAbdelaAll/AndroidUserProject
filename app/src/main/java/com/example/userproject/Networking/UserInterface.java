package com.example.userproject.Networking;

import com.example.userproject.POJO.User;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface UserInterface {

    @GET("users")
    public Call<List<User>> getUsers();
    @GET("users/{id}")
    public  Call<User> getUser(@Path("id") int id);
}
