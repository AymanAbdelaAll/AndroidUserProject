package com.example.userproject.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
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

public class UserDetailsActivity extends AppCompatActivity {
    TextView showUserId, showUserName, showUserUserName, showUserWebsite, showUserEmail, showUserPhone,
            showUserAddressStreet, showUserAddressSuite, showUserAddressCity, showUserAddressZipcode,
            showUserCompanyName, showUserCompanyCatchphrase, showUserCompanyBs, showUserAddressGeo,
            showUserAddressGeoView;
    ProgressBar userProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_details_activity);
        userProgressBar = (ProgressBar) findViewById(R.id.userDetails_loadingbar);
        bindViews();
        loadUser();

    }

    protected void bindViews(){
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
        userProgressBar=(ProgressBar)findViewById(R.id.userDetails_loadingbar);
    }
    private void loadUser() {

        UserClient.getINSTANCE().getUser(1).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                userProgressBar.setVisibility(View.GONE);
                getRetriaveUserRequest(response.body());
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                userProgressBar.setVisibility(View.GONE);
            getFaliarUserRequest();

            }
        });
    }

    protected void getRetriaveUserRequest(User userRespose){
        if (userRespose!=null) {
            setUserViews(userRespose);

            Company userCompany = userRespose.getCompany();
            setUserCompanyViews(userCompany);

            Address userAddress = userRespose.getAddress();
            setUserAddressViews(userAddress);

            setAddressGeoViews(userAddress);
        }else{
            getFaliarUserRequest();
        }

    }

    private void setAddressGeoViews(Address userAddress) {
        if (userAddress instanceof GeoAddress) {
            Geo userGeo = ((GeoAddress) userAddress).getGeo();
            showUserAddressGeo.setText(userGeo.getLat() + " - " + userGeo.getLng());
        } else {
            showUserAddressGeo.setVisibility(View.GONE);
            showUserAddressGeoView.setVisibility(View.GONE);
        }
    }

    private void setUserAddressViews(Address userAddress) {
        showUserAddressStreet.setText(userAddress.getStreet());
        showUserAddressSuite.setText(userAddress.getSuite());
        showUserAddressCity.setText(userAddress.getCity());
        showUserAddressZipcode.setText(userAddress.getZipcode());
    }

    private void setUserCompanyViews(Company userCompany) {
        showUserCompanyBs.setText(userCompany.getBs());
        showUserCompanyName.setText(userCompany.getName());
        showUserCompanyCatchphrase.setText(userCompany.getCatchphrase());
    }

    private void setUserViews(User userRespose) {
        showUserId.setText(userRespose.getId() + "");
        showUserName.setText(userRespose.getName());
        showUserUserName.setText(userRespose.getUsername());
        showUserPhone.setText(userRespose.getPhone());
        showUserEmail.setText(userRespose.getEmail());
       // showUserWebsite.setText(userRespose.getWebsite());
    }

    protected void getFaliarUserRequest(){
        Context context=this.findViewById(R.id.showUser_id).getContext();
        Toast.makeText(context,"OOPS There Is An Problem ,Try Again .",Toast.LENGTH_LONG).show();
    }
}




