package com.example.userproject.UserPresenter;

import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.userproject.Networking.UserClient;
import com.example.userproject.POJO.User;
import com.example.userproject.R;
import com.example.userproject.UI.UserListAdapter;

import java.util.ArrayList;
import java.util.List;


import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ListUserViewPresenter implements ListUserViewInterface {

    public interface userListInterface{
        void onRetrieveUser(UserListAdapter userListAdapter);
    }

    private View userListView;

    public void setUserListView(View userListView) {
        this.userListView = userListView;
    }

    public void loadUsers(UserListAdapter adapter) {
         UserClient.getInstance().getUsers()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<User>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        showLodaing();
                    }

                    @Override
                    public void onNext(List<User> value) {
                        List<UserViewModel> userViewModelViewModels =transform(value);
                        adapter.setList((List<UserViewModel>) userViewModelViewModels);
                    }

                    @Override
                    public void onError(Throwable e) {
                        String error_msg=userListView.getResources().getString(R.string.network_error_msg);
                        Toast.makeText(userListView.getContext(),error_msg,Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onComplete()
                    {
                        hideLoading();
                    }
                });
         }


    public List<UserViewModel> transform(List<User> users){
        List<UserViewModel> userViewModelViewModels =new ArrayList<>();
        for(User user:users) {
            UserViewModel userViewModel = new UserViewModel();
            userViewModel.setId(user.getId());
            userViewModel.setName(user.getName());
            userViewModel.setUsername(user.getUsername());
            userViewModel.setWebsite(user.getWebsite());
            userViewModelViewModels.add(userViewModel);
        }
        return userViewModelViewModels;
    }

    @Override
    public void hideLoading() {
        RelativeLayout rlLodaingContainer=userListView.findViewById(R.id.loading_container);
        rlLodaingContainer.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showLodaing() {
        RelativeLayout rlLodaingContainer=userListView.findViewById(R.id.loading_container);
        rlLodaingContainer.setVisibility(View.VISIBLE);
    }

}
