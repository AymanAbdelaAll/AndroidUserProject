package com.example.userproject.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.userproject.R;
import com.example.userproject.UserPresenter.ListUserViewPresenter;


import org.jetbrains.annotations.NotNull;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ListUserActivity extends AppCompatActivity implements ListUserViewPresenter.userListInterface{
    @BindView(R.id.recyclerView) RecyclerView recyclerView;
    @BindView(R.id.loading_container) RelativeLayout rlLoading;
    ListUserViewPresenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_user);
        bindViews();
        UserListAdapter adapter = getUserListAdapter();
        presenter=new ListUserViewPresenter();
        onRetrieveUser(adapter);

    }


    @NotNull
    private UserListAdapter getUserListAdapter() {
        UserListAdapter adapter=new UserListAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        return adapter;
    }


    protected void bindViews() {
        ButterKnife.bind(this);
    }


    @Override
    public void onRetrieveUser(UserListAdapter userListAdapter) {
        presenter.setUserListView(rlLoading.getRootView());
        presenter.loadUsers(userListAdapter);
    }
}
