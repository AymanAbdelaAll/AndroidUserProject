package com.example.userproject.UserPresenter;

import com.example.userproject.Networking.User.UserClient;
import com.example.userproject.POJO.User;
import com.example.userproject.VM.UserViewModel;

import java.util.ArrayList;
import java.util.List;


import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ListUserViewPresenter  {

    public interface UserListInterface{
        void onSuccusssRetreveUser(List<UserViewModel> userViewModels);
        void hideLoading();
        void showLoading();
        void onErrorRetreveUser();
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
                            userListInterface.showLoading();
                        }

                        @Override
                        public void onNext(List<User> value) {
                            List<UserViewModel> userViewModels = transform(value);
                            userListInterface.onSuccusssRetreveUser(userViewModels);
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


    public List<UserViewModel> transform(List<User> users){
        List<UserViewModel> userViewModelViewModels =new ArrayList<>();
        for(User user:users) {
            if(user!=null){
            UserViewModel userViewModel = new UserViewModel();
            userViewModel.setId(user.getId());
            userViewModel.setName(user.getName());
            userViewModel.setUsername(user.getUsername());
            userViewModel.setEmail(user.getEmail());
            userViewModelViewModels.add(userViewModel);
            }
        }
        return userViewModelViewModels;
    }

}
