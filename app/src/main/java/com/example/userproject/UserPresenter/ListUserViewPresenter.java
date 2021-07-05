package com.example.userproject.UserPresenter;

import android.view.View;
import android.widget.Toast;

import com.example.userproject.Networking.UserClient;
import com.example.userproject.POJO.User;
import com.example.userproject.VM.UserViewModel;
import com.example.userproject.R;
import com.example.userproject.UI.UserListAdapter;

import java.util.ArrayList;
import java.util.List;


import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ListUserViewPresenter  {

    public interface UserListInterface{
        void onSuccusssRetreveUser(List<UserViewModel> userViewModels);
        // TODO : remove public , interface methods are always public
        public void hideLoading();
        public void showLoading();
        public void onErrorRetreveUser();
    }

    ListUserViewPresenter.UserListInterface userListInterface;

    public ListUserViewPresenter( ListUserViewPresenter.UserListInterface userListInterface) {
        this.userListInterface = userListInterface;
    }

    public void loadUsers() {
         UserClient.getInstance().getUsers()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<User>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        // TODO : always check if 'userListInterface' not null
                        userListInterface.showLoading();
                    }

                    @Override
                    public void onNext(List<User> value) {
                        List<UserViewModel> userViewModels =transform(value);
                        // TODO : always check if 'userListInterface' not null

                        userListInterface.onSuccusssRetreveUser(userViewModels);
                    }

                    @Override
                    public void onError(Throwable e) {
                        // TODO : always check if 'userListInterface' not null

                        userListInterface.onErrorRetreveUser();
                    }

                    @Override
                    public void onComplete()
                    {
                        // TODO : always check if 'userListInterface' not null

                        userListInterface.hideLoading();
                    }
                });
         }

    public List<UserViewModel> transform(List<User> users){
        List<UserViewModel> userViewModelViewModels =new ArrayList<>();
        for(User user:users) {
            //TODO : if one of the 'user' is null , you will be assigning null .so please take care of that
            UserViewModel userViewModel = new UserViewModel();
            userViewModel.setId(user.getId());
            userViewModel.setName(user.getName());
            userViewModel.setUsername(user.getUsername());
            userViewModel.setWebsite(user.getWebsite());
            userViewModelViewModels.add(userViewModel);
        }
        return userViewModelViewModels;
    }

}
