package com.example.userproject.UI;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.userproject.POJO.User;
import com.example.userproject.R;
import com.example.userproject.VM.UserViewModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.UserViewHolder> {
    private static final String TAG ="USERlISTACTIVITY" ;
    private List<UserViewModel> userViewModelList = new ArrayList<>();


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
            holder.tvEmail.setText(userViewModel.getEmail());

            holder.llShowUser.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    UserDetailsActivity.start(v.getContext(),userViewModel);
                }
            });
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
        TextView tvId,tvName, tvUserName,tvEmail;
        LinearLayout llShowUser;
        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(itemView);
            tvId=itemView.findViewById(R.id.userItem_id);
            tvName=itemView.findViewById(R.id.userItem_name);
            tvUserName=itemView.findViewById(R.id.userItem_username);
            tvEmail=itemView.findViewById(R.id.userItem_email);
            llShowUser=itemView.findViewById(R.id.linearlayout_displayUser);
        }
    }
}
