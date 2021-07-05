package com.example.userproject.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.userproject.POJO.User;
import com.example.userproject.R;
import com.example.userproject.UserPresenter.ListUserViewPresenter;
import com.example.userproject.VM.UserViewModel;


import org.jetbrains.annotations.NotNull;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ListUserActivity extends AppCompatActivity implements ListUserViewPresenter.UserListInterface{
    @BindView(R.id.recyclerView) RecyclerView recyclerView;
    @BindView(R.id.loading_container) RelativeLayout rlLodaingContainer;
    UserListAdapter adapter;
    ListUserViewPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_user);
        bindViews();
        adapter = getUserListAdapter();
        presenter=new ListUserViewPresenter(this);
        presenter.loadUsers();
    }

    protected void bindViews() {
        ButterKnife.bind(this);
    }

    @NotNull
    private UserListAdapter getUserListAdapter() {
        UserListAdapter adapter=new UserListAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        return adapter;
    }

    @Override
    public void hideLoading() {
        rlLodaingContainer.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showLoading() {
        rlLodaingContainer.setVisibility(View.VISIBLE);
    }

    @Override
    public void onSuccusssRetreveUser(List<UserViewModel> userViewModels, List<User>userList) {
        adapter.setList(userViewModels,userList);
    }

    @Override
    public void onErrorRetreveUser() {
        String error_msg=getString(R.string.network_error_msg);
        Toast.makeText(this,error_msg,Toast.LENGTH_LONG).show();
    }
}
