package com.example.userproject.UI.ListUser;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.userproject.POJO.FavoriteEvent;
import com.example.userproject.R;
import com.example.userproject.UI.UserDetailsActivity;
import com.example.userproject.VM.UserViewModel;

import org.greenrobot.eventbus.EventBus;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.UserViewHolder> {
    private static final String TAG ="USERlISTACTIVITY" ;
    private List<UserViewModel> userViewModelList = new ArrayList<>();
    SharedPreferences sharedPreferences;


    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new UserViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.user_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        UserViewModel userViewModel = userViewModelList.get(position);
            holder.tvName.setText(userViewModel.getName());
            holder.tvEmail.setText(userViewModel.getEmail());
            ImageButton ibFavorite = holder.ibFavorite;
            Context userListContext=holder.llShowUser.getContext();

        loadUserPreferance(userViewModel, ibFavorite, userListContext);
        setUserStatus(ibFavorite,userListContext,userViewModel);


        ibFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setUserStatus(ibFavorite, userListContext, userViewModel);
                FavoriteEvent userstatus = new FavoriteEvent(ibFavorite.getTag() + "");
                EventBus.getDefault().post(userstatus);
            }
        });
        holder.llShowUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserDetailsActivity.start(v.getContext(), userViewModel);
            }
        });
    }

    private void loadUserPreferance(UserViewModel userViewModel, ImageButton ibFavorite, Context userListContext) {
        sharedPreferences= userListContext.getSharedPreferences(UserDetailsActivity.MyPREFERENCES, Context.MODE_PRIVATE);
        String userIdle= sharedPreferences.getString(userViewModel.getId()+"","idleuser");
        ibFavorite.setTag(userIdle);

        setBtFavoriteTag(ibFavorite, userIdle);
    }

    private void setBtFavoriteTag(ImageButton ibFavorite, String userIdle) {
        if (userIdle.equals("idleuser")){
            ibFavorite.setTag("busyuser");
        }else {
            ibFavorite.setTag("idleuser");
        }
    }

    private void setPreferences(int userId,ImageButton ibFavorite) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(userId+"", ibFavorite.getTag()+"");
        editor.apply();
    }

    @NotNull
    private void setUserStatus(ImageButton ibFavorite, Context context, UserViewModel userViewModel) {
        if (ibFavorite.getTag()==null){
            ibFavorite.setTag("idleuser");
            ibFavorite.setImageResource(R.drawable.ic_idleuser_star_24);
            setPreferences(userViewModel.getId(),ibFavorite);
        }else{
            if(ibFavorite.getTag().equals("idleuser")){
                ibFavorite.setTag("busyuser");
                ibFavorite.setImageResource(R.drawable.ic_busyuser_star_24);
                setPreferences(userViewModel.getId(),ibFavorite);
            }else{
                ibFavorite.setTag("idleuser");
                ibFavorite.setImageResource(R.drawable.ic_idleuser_star_24);
                setPreferences(userViewModel.getId(),ibFavorite);
            }
        }
    }



    @Override
    public int getItemCount() {
        return userViewModelList.size();
    }

    public void setList(List<UserViewModel> userViewList) {
        this.userViewModelList = userViewList;

        notifyDataSetChanged();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {
        TextView tvName,tvEmail;
        ImageButton ibFavorite;
        LinearLayout llShowUser;
        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName=itemView.findViewById(R.id.userItem_name);
            tvEmail=itemView.findViewById(R.id.userItem_email);
            ibFavorite=itemView.findViewById(R.id.useritem_button_changestatus);
            llShowUser=itemView.findViewById(R.id.linearlayout_displayUser);
        }
    }
}
