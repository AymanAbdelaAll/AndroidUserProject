package com.example.userproject.UserPresenter;

import com.example.userproject.Networking.Photo.PhotoClient;
import com.example.userproject.VM.PhotoViewModel;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ListPhotoPresenter {

    public interface PhotoListInterface{
        void onSuccusssRetrevePhoto(List<PhotoViewModel> photoViewModels);
        void hideLoading();
        void showLoading();
        void onErrorRetrevePhoto();
    }

    PhotoListInterface photoListInterface;

    public ListPhotoPresenter(PhotoListInterface photoListInterface) {
        this.photoListInterface = photoListInterface;
    }

    public void loadPhotos(){
        PhotoClient.getInstance().getPhotos()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<PhotoViewModel>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        photoListInterface.showLoading();
                    }

                    @Override
                    public void onNext(List<PhotoViewModel> value) {
                        photoListInterface.onSuccusssRetrevePhoto(value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        photoListInterface.onErrorRetrevePhoto();
                    }

                    @Override
                    public void onComplete() {
                        photoListInterface.hideLoading();
                    }
                });

}

}