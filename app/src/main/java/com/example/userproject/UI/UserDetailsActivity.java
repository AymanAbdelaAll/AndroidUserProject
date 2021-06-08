package com.example.userproject.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
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

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserDetailsActivity extends AppCompatActivity {
    @BindView(R.id.userdetails_text_id) TextView tvId;
    @BindView(R.id.userdetails_text_name) TextView tvName;
    @BindView(R.id.userdetails_text_username) TextView tvUserName;
    @BindView(R.id.userdetails_text_website) TextView tvWebsite;
    @BindView(R.id.userdetails_text_email) TextView tvEmail;
    @BindView(R.id.userdetails_text_phone) TextView tvPhone;

    @BindView(R.id.userdetails_text_address_street) TextView tvAddressStreet;
    @BindView(R.id.userdetails_text_address_suite) TextView tvAddressSuite;
    @BindView(R.id.userdetails_text_address_city) TextView tvAddressCity;
    @BindView(R.id.userdetails_text_address_zipcode) TextView tvAddressZipcode;

    @BindView(R.id.userdetails_text_company_name) TextView tvCompanyName;
    @BindView(R.id.userdetails_text_company_catchphrase) TextView tvCompanyCatchphrase;
    @BindView(R.id.userdetails_text_company_bs) TextView tvCompanyBs;

    @BindView(R.id.userdetails_text_address_geo) TextView tvAddressGeo;
    @BindView(R.id.userdetails_text_address_geoView) TextView tvAddressGeoView;
    @BindView(R.id.userdetails_progress_loadingbar) ProgressBar pbLoading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_constraint_user_details);
        bindViews();
        loadUser();
    }

    protected void bindViews() {
        ButterKnife.bind(this);
    }

    private void loadUser() {
        //here you can change the Id of user you want
        UserClient.getInstance().getUser(1).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                hideLoading();
                getRetriaveUserRequest(response.body());
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                hideLoading();
                getFaliarUserRequest();
            }
        });
    }

    private void hideLoading() {
        pbLoading.setVisibility(View.GONE);
    }

    protected void getRetriaveUserRequest(User userRespose) {
        if (userRespose != null) {
            setUserViews(userRespose);

            Company userCompany = userRespose.getCompany();
            setUserCompanyViews(userCompany);

            Address userAddress = userRespose.getAddress();
            setUserAddressViews(userAddress);

            setAddressGeoViews(userAddress);
        } else {
            getFaliarUserRequest();
        }

    }

    private void setAddressGeoViews(Address userAddress) {
        if (userAddress instanceof GeoAddress) {
            Geo userGeo = ((GeoAddress) userAddress).getGeo();
            String geoLatAndLng = userGeo.getLat() + " - " + userGeo.getLng();
            tvAddressGeo.setText(geoLatAndLng);
        } else {
            tvAddressGeo.setVisibility(View.GONE);
            tvAddressGeoView.setVisibility(View.GONE);
        }
    }

    private void setUserAddressViews(Address userAddress) {
        tvAddressStreet.setText(userAddress.getStreet());
        tvAddressSuite.setText(userAddress.getSuite());
        tvAddressCity.setText(userAddress.getCity());
        tvAddressZipcode.setText(userAddress.getZipcode());
    }

    private void setUserCompanyViews(Company userCompany) {
        tvCompanyBs.setText(userCompany.getBs());
        tvCompanyName.setText(userCompany.getName());
        tvCompanyCatchphrase.setText((userCompany.getCatchphrase()));
    }

    private void setUserViews(User userRespose) {
        tvId.setText(userRespose.getId() + "");
        tvName.setText(userRespose.getName());
        tvUserName.setText(userRespose.getUsername());
        tvPhone.setText(userRespose.getPhone());
        tvEmail.setText(userRespose.getEmail());
        tvWebsite.setText(userRespose.getWebsite());
    }

    protected void getFaliarUserRequest() {
        Toast.makeText(this, "OOPS There Is An Problem ,Try Again .", Toast.LENGTH_LONG).show();
    }

    @OnClick(R.id.userdetails_text_email)
    public void onEmailClicked(View view) {
        Toast.makeText(this,"Email view Clicked .",Toast.LENGTH_LONG).show();
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.setType("message/rfc822");
        String emailClicked=tvEmail.getText()+"";
        intent.putExtra(Intent.EXTRA_EMAIL,emailClicked);
        startActivity(intent);
    }
}




