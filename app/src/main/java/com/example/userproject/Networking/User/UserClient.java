package com.example.userproject.Networking.User;

import com.example.userproject.POJO.User;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


public class UserClient {
    private final String URL="https://jsonplaceholder.typicode.com/";
    private UserInterface userInterface;
    private static UserClient instance ;

    public UserClient() {
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        userInterface=retrofit.create(UserInterface.class);
    }
    public static UserClient getInstance(){
        if (instance ==null)
            instance =new UserClient();
        return instance ;
    }
    public Call<User> getUser(int id){
        return getInstance ().userInterface.getUser(id);
    }

    public Observable<List<User>> getUsers(){
        return getInstance ().userInterface.getUsers();
    }

}
