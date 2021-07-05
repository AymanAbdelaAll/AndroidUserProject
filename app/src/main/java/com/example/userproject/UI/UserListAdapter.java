package com.example.userproject.UI;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.userproject.Networking.UserClient;
import com.example.userproject.POJO.User;
import com.example.userproject.R;
import com.example.userproject.VM.UserViewModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.UserViewHolder> {
    private static final String TAG ="USERlISTACTIVITY" ;
    private List<UserViewModel> userViewModelList = new ArrayList<>();
    private List<User> userList=new ArrayList<>();

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new UserViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.user_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        UserViewModel userViewModel = userViewModelList.get(position);
        holder.tvId.setText(userViewModel.getId() + "");
            holder.tvName.setText(userViewModel.getName());
            holder.tvUserName.setText(userViewModel.getUsername());
            holder.tvWebsite.setText(userViewModel.getWebsite());

            holder.llShowUser.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    // TODO : what is this!?!?!?!??!?!?!
                    // we already talked about you already have the object !!!!!!!!!!
                    // no need to call api , just pass the user object
                    User user = userList.get(position);
                    UserDetailsActivity.start(v.getContext(),user);
                    /*
                    UserClient.getInstance().getUser(userViewModel.getId()).enqueue(new Callback<User>() {
                        @Override
                        public void onResponse(Call<User> call, Response<User> response) {
                            User user = response.body();
                            UserDetailsActivity.start(v.getContext(), user);
                        }

                        @Override
                        public void onFailure(Call<User> call, Throwable t) {
                            Log.d(TAG, "The User Doesnt Exist");
                        }
                    });*/
                }
            });
    }


    @Override
    public int getItemCount() {
        return userViewModelList.size();
    }

    public void setList(List<UserViewModel> userViewList,List<User> usersList) {
        this.userViewModelList = userViewList;
        this.userList=usersList;
        notifyDataSetChanged();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {
        TextView tvId,tvName, tvUserName,tvWebsite;
        LinearLayout llShowUser;
        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(itemView);
            tvId=itemView.findViewById(R.id.userItem_id);
            tvName=itemView.findViewById(R.id.userItem_name);
            tvUserName=itemView.findViewById(R.id.userItem_username);
            tvWebsite=itemView.findViewById(R.id.userItem_website);
            llShowUser=itemView.findViewById(R.id.linearlayout_displayUser);
        }
    }
}
