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
        void onSuccusssRetreveUser(List<UserViewModel> userViewModels,List<User> userList);
        void hideLoading();
        void showLoading();
        void onErrorRetreveUser();
    }

    ListUserViewPresenter.UserListInterface userListInterface;

    public ListUserViewPresenter( ListUserViewPresenter.UserListInterface userListInterface) {
        this.userListInterface = userListInterface;
    }

    public void loadUsers() {
        if (userListInterface != null) {
            UserClient.getInstance().getUsers()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<List<User>>() {
                        @Override
                        public void onSubscribe(Disposable d) {
                            userListInterface.showLoading();
                        }

                        @Override
                        public void onNext(List<User> value) {
                            List<UserViewModel> userViewModels = transform(value);
                            //TODO :is it true to pass the list I transform it to have the certain class form User to UserViewModel
                            userListInterface.onSuccusssRetreveUser(userViewModels,value);
                        }

                        @Override
                        public void onError(Throwable e) {
                            userListInterface.onErrorRetreveUser();
                        }

                        @Override
                        public void onComplete() {
                            userListInterface.hideLoading();
                        }
                    });
        }
    }

    public List<UserViewModel> transform(List<User> users){
        List<UserViewModel> userViewModelViewModels =new ArrayList<>();
        for(User user:users) {
            if(user!=null){
            UserViewModel userViewModel = new UserViewModel();
            userViewModel.setId(user.getId());
            userViewModel.setName(user.getName());
            userViewModel.setUsername(user.getUsername());
            userViewModel.setWebsite(user.getWebsite());
            userViewModelViewModels.add(userViewModel);
            }
        }
        return userViewModelViewModels;
    }

}
