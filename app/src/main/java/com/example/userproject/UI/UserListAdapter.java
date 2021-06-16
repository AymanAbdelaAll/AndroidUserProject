package com.example.userproject.UI;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.userproject.Networking.UserClient;
import com.example.userproject.POJO.User;
import com.example.userproject.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.UserViewHolder> {
    private List<User> userList = new ArrayList<>();

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new UserViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.user_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
            holder.tvId.setText(userList.get(position).getId() + "");
            holder.tvName.setText(userList.get(position).getName());
            holder.tvUserName.setText(userList.get(position).getUsername());
            holder.tvWebsite.setText(userList.get(position).getWebsite());
            final Context context = holder.itemView.getContext();

            holder.llShowUser.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    User user = userList.get(position);
                    UserDetailsActivity.start(context,user);
                }
            });
    }


    @Override
    public int getItemCount() {
        return userList.size();
    }

    public void setList(List<User> userList) {
        this.userList = userList;
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
