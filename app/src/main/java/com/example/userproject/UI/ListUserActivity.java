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


import org.jetbrains.annotations.NotNull;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

import io.reactivex.schedulers.Schedulers;


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
        UserListAdapter adapter = getUserListAdapter();
        createRecycleObserver(adapter);
    }

    @NotNull
    private UserListAdapter getUserListAdapter() {
        UserListAdapter adapter=new UserListAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        return adapter;
    }

    private void createRecycleObserver(UserListAdapter adapter) {
        Observable listObservavle= UserClient.getInstance().getUsers()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        Observer observer=new Observer() {
            @Override
            public void onSubscribe(Disposable d) {
                hideLoading();
            }

            @Override
            public void onNext(Object value) {
                adapter.setList((List<User>)value);
            }

            @Override
            public void onError(Throwable e) {
                Toast.makeText(recyclerView.getContext(),"OOPS There Is An problem ,Try Again .",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onComplete() {
                hideLoading();
            }
        };
        listObservavle.subscribe(observer);
    }

    protected void bindViews() {
        ButterKnife.bind(this);
    }

    private void hideLoading() {
        rlLoading.setVisibility(View.GONE);
    }

}
