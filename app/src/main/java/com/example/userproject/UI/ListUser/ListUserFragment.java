package com.example.userproject.UI.ListUser;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;


import com.example.userproject.R;
import com.example.userproject.UserPresenter.ListUserViewPresenter;
import com.example.userproject.VM.UserViewModel;

import java.util.List;

import butterknife.ButterKnife;

import androidx.recyclerview.widget.LinearLayoutManager;

import android.widget.Toast;




import org.jetbrains.annotations.NotNull;

public class ListUserFragment extends Fragment implements ListUserViewPresenter.UserListInterface {
    RecyclerView recyclerView;
    RelativeLayout rlLodaingContainer;
    UserListAdapter adapter;
    ListUserViewPresenter presenter;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_list_user , container,false);
        loadViews(view);
        adapter = getUserListAdapter();
        presenter = new ListUserViewPresenter(this);
        presenter.loadUsers();
        return view;
    }

    private void loadViews(View view) {
        rlLodaingContainer=view.findViewById(R.id.listuser_loading_container);
        recyclerView=view.findViewById(R.id.recyclerView);
    }

    @Override
    public void onSuccusssRetreveUser(List<UserViewModel> userViewModels) {
        adapter.setList(userViewModels);
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
    public void onErrorRetreveUser() {
        String error_msg=getString(R.string.network_error_msg);
        Toast.makeText(getContext(),error_msg,Toast.LENGTH_LONG).show();
    }
    protected void bindViews(View view ) {
        ButterKnife.bind(view);
    }


    @NotNull
    private UserListAdapter getUserListAdapter() {
        UserListAdapter adapter=new UserListAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
        return adapter;
    }






}
