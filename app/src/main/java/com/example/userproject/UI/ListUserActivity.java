package com.example.userproject.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.userproject.Networking.UserClient;
import com.example.userproject.POJO.User;
import com.example.userproject.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListUserActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_user);
        loadUsers();
    }

    protected void loadUsers() {
        recyclerView=findViewById(R.id.recyclerView);

        UserListAdapter adapter=new UserListAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        UserClient.getINSTANCE().getUsers().enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                adapter.setList(response.body());
            }



            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Toast.makeText(recyclerView.getContext(),"OOPS There Is An problem ,Try Again .",Toast.LENGTH_LONG).show();;
            }
        });

    }

    }
