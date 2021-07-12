package com.example.userproject.UI.ListPhoto;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.userproject.R;
import com.example.userproject.UserPresenter.ListPhotoPresenter;
import com.example.userproject.VM.PhotoViewModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ListPhotoFragment extends Fragment  implements ListPhotoPresenter.PhotoListInterface{
    @BindView(R.id.listphoto_recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.listuser_loading_container)
    RelativeLayout rlLodaingContainer;
    PhotoListAdapter adapter;
    ListPhotoPresenter presenter;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_list_photo , container,false);
        ButterKnife.bind(this,view);
        adapter=getPhotoAdapter();
        presenter = new ListPhotoPresenter(this);
        presenter.loadPhotos();
        return view;
    }

    public PhotoListAdapter getPhotoAdapter(){
        PhotoListAdapter adapter=new PhotoListAdapter();
        GridLayoutManager layoutManager=new GridLayoutManager(getContext(),2,GridLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        return adapter;
    }

    @Override
    public void onSuccusssRetrevePhoto(List<PhotoViewModel> photoViewModels) {
        adapter.setList(photoViewModels);
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
    public void onErrorRetrevePhoto() {
        String error_msg=getString(R.string.network_error_msg);
        Toast.makeText(getContext(),error_msg,Toast.LENGTH_LONG).show();
    }
}