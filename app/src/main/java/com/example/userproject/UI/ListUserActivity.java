package com.example.userproject.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.userproject.Networking.UserClient;
import com.example.userproject.POJO.User;
import com.example.userproject.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListUserActivity extends AppCompatActivity {
    @BindView(R.id.recyclerView) RecyclerView recyclerView;
    @BindView(R.id.loading_container) RelativeLayout rlLoading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_user);
        loadUsers();
    }

    protected void loadUsers() {
        bindViews();

        UserListAdapter adapter=new UserListAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        UserClient.getInstance().getUsers().enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                hideLoading();
                adapter.setList(response.body());
            }



            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                hideLoading();
                Toast.makeText(recyclerView.getContext(),"OOPS There Is An problem ,Try Again .",Toast.LENGTH_LONG).show();;
            }
        });

    }

    protected void bindViews() {
        ButterKnife.bind(this);
    }

    private void hideLoading() {
        rlLoading.setVisibility(View.GONE);
    }

}
