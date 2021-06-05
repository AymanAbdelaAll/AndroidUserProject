package com.example.userproject.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.userproject.Networking.UserClient;
import com.example.userproject.POJO.Address;
import com.example.userproject.POJO.Company;
import com.example.userproject.POJO.Geo;
import com.example.userproject.POJO.GeoAddress;
import com.example.userproject.POJO.User;
import com.example.userproject.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShowUser extends AppCompatActivity {
    UserViewModel userViewModel;
    TextView showUserId,showUserName,showUserUserName,showUserWebsite,showUserEmail,showUserPhone,
            showUserAddressStreet,showUserAddressSuite,showUserAddressCity,showUserAddressZipcode,
            showUserCompanyName,showUserCompanyCatchphrase,showUserCompanyBs,showUserAddressGeo,
            showUserAddressGeoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_user);
        userViewModel= ViewModelProviders.of(this).get(UserViewModel.class);
        Intent intent=getIntent();
        int userId=Integer.parseInt(intent.getStringExtra("id"));
        TextView showUserId=(TextView)findViewById(R.id.showUser_id);
        userViewModel.getUser(userId,showUserId.getRootView());
        inFlateUser();

    }
    protected void inFlateUser(){
        showUserId = (TextView) findViewById(R.id.showUser_id);
        showUserName = (TextView) findViewById(R.id.showUser_name);
        showUserPhone = (TextView) findViewById(R.id.showUser_phone);
        showUserUserName = (TextView) findViewById(R.id.showUser_username);
        showUserEmail = (TextView) findViewById(R.id.showUser_email);
        showUserWebsite = (TextView) findViewById(R.id.showUser_website);
        showUserAddressStreet = (TextView) findViewById(R.id.showUser_street);
        showUserAddressSuite = (TextView) findViewById(R.id.showUser_suite);
        showUserAddressCity = (TextView) findViewById(R.id.showUser_city);
        showUserAddressZipcode = (TextView) findViewById(R.id.showUser_zipcode);
        showUserAddressGeo = (TextView) findViewById(R.id.showUser_geo);
        showUserCompanyName = (TextView) findViewById(R.id.showUser_companyName);
        showUserCompanyCatchphrase = (TextView) findViewById(R.id.showUser_catchphrase);
        showUserCompanyBs = (TextView) findViewById(R.id.showUser_bs);
        showUserAddressGeoView = (TextView) findViewById(R.id.showUser_geoView);
        userViewModel.userMutableLiveData.observe(this, new Observer<User>() {
            @Override
            public void onChanged(User user) {
                showUserId.setText(user.getId() + "");
                showUserName.setText(user.getName());
                showUserUserName.setText(user.getUsername());
                showUserPhone.setText(user.getPhone());
                showUserEmail.setText(user.getEmail());
                showUserWebsite.setText(user.getWebsite());
                Company userCompany = user.getCompany();
                showUserCompanyBs.setText(userCompany.getBs());
                showUserCompanyName.setText(userCompany.getName());
                showUserCompanyCatchphrase.setText(userCompany.getCatchphrase());
                Address userAddress = user.getAddress();
                showUserAddressStreet.setText(userAddress.getStreet());
                showUserAddressSuite.setText(userAddress.getSuite());
                showUserAddressCity.setText(userAddress.getCity());
                showUserAddressZipcode.setText(userAddress.getZipcode());
                if (userAddress instanceof GeoAddress) {
                    Geo userGeo = ((GeoAddress) userAddress).getGeo();
                    showUserAddressGeo.setText(userGeo.getLat() + " - " + userGeo.getLng());
                } else {
                    showUserAddressGeo.setText("");
                    showUserAddressGeoView.setText("");
                }
            }
        });

    }


}