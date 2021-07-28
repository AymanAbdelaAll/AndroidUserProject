package com.example.userproject.UI.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.os.Handler;


import com.example.userproject.R;
import com.example.userproject.UI.ListPhoto.ListPhotoFragment;
import com.example.userproject.UI.ListUser.ListUserFragment;
import com.google.android.material.tabs.TabLayout;

import io.branch.referral.Branch;

public class ListViewActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_user);

        tabLayout =findViewById(R.id.tablayout);
        viewPager=findViewById(R.id.listuser_viewpager);

        new Handler().post(new Runnable() {
            @Override
            public void run() {
                ListViewFragmentAdapter listViewFragmentAdapter =new ListViewFragmentAdapter(getSupportFragmentManager());
                ListUserFragment listUserFragment=new ListUserFragment();
                ListPhotoFragment listPhotoFragment=new ListPhotoFragment();

                listViewFragmentAdapter.addFragment(listPhotoFragment,"Photos");
                listViewFragmentAdapter.addFragment(listUserFragment,"User");


                viewPager.setAdapter(listViewFragmentAdapter);
                tabLayout.setupWithViewPager(viewPager);
            }
        });


    }




}
