package com.example.userproject.UI;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.userproject.POJO.User;
import com.example.userproject.R;

import java.util.ArrayList;
import java.util.List;

public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.UserViewHolder> {
    private List<User> userList = new ArrayList<>();

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new UserViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.user_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
            holder.userViewId.setText(userList.get(position).getId() + "");
            holder.userViewName.setText(userList.get(position).getName());
            holder.userViewUserName.setText(userList.get(position).getUsername());
            holder.userViewWebsite.setText(userList.get(position).getWebsite());

            final Context context = holder.itemView.getContext();
            holder.userViewShowUser.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, UserDetailsActivity.class);
                    intent.putExtra("id", holder.userViewId.getText());
                    context.startActivity(intent);
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
        TextView userViewId,userViewUserName,userViewName,userViewWebsite;
        ImageButton userViewShowUser;
        LinearLayout linearLayoutShowUser;
        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            linearLayoutShowUser=itemView.findViewById(R.id.linearlayout_displayUser);
            userViewId=linearLayoutShowUser.findViewById(R.id.userItem_id);
            userViewName=linearLayoutShowUser.findViewById(R.id.userItem_name);
            userViewUserName=linearLayoutShowUser.findViewById(R.id.userItem_username);
            userViewWebsite=linearLayoutShowUser.findViewById(R.id.userItem_website);
            userViewShowUser=linearLayoutShowUser.findViewById(R.id.userItem_showUser);

        }
    }
}
