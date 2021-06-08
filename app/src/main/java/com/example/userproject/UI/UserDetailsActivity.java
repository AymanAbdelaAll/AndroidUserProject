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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserDetailsActivity extends AppCompatActivity {
    TextView tvId, tvName, tvUserName, tvWebsite, tvEmail, tvPhone,
            tvAddressStreet, tvAddressSuite, tvAddressCity, tvAddressZipcode,
            tvCompanyName, tvCompanyCatchphrase, tvCompanyBs, tvAddressGeo,
            tvAddressGeoView;
    ProgressBar pbLoading;


    //    @BindView(R.id.userdetails_text_id)
//    TextView tvId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_constraint_user_details);
        pbLoading = (ProgressBar) findViewById(R.id.userdetails_progress_loadingbar);//TODO : remove , duplicate
        bindViews();
        loadUser();

    }

    protected void bindViews() {
        tvId = (TextView) this.findViewById(R.id.userdetails_text_id);
        tvName = (TextView) this.findViewById(R.id.userdetails_text_name);
        tvPhone = (TextView) this.findViewById(R.id.userdetails_text_phone);
        tvUserName = (TextView) this.findViewById(R.id.userdetails_text_username);
        tvEmail = (TextView) this.findViewById(R.id.userdetails_text_email);
        tvWebsite = (TextView) this.findViewById(R.id.userdetails_text_website);

        tvAddressStreet = (TextView) this.findViewById(R.id.userdetails_text_address_street);
        tvAddressSuite = (TextView) this.findViewById(R.id.userdetails_text_address_suite);
        tvAddressCity = (TextView) this.findViewById(R.id.userdetails_text_address_city);
        tvAddressZipcode = (TextView) this.findViewById(R.id.userdetails_text_address_zipcode);
        tvAddressGeo = (TextView) this.findViewById(R.id.userdetails_text_address_geo);

        tvCompanyName = (TextView) this.findViewById(R.id.userdetails_text_company_name);
        tvCompanyCatchphrase = (TextView) this.findViewById(R.id.userdetails_text_company_catchphrase);
        tvCompanyBs = (TextView) this.findViewById(R.id.userdetails_text_company_bs);
        tvAddressGeoView = (TextView) this.findViewById(R.id.userdetails_text_address_geoView);
        pbLoading = (ProgressBar) this.findViewById(R.id.userdetails_progress_loadingbar);
    }

    private void loadUser() {
        //here you can change the Id of user you want
        UserClient.getINSTANCE().getUser(1).enqueue(new Callback<User>() {
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
            String text = userGeo.getLat() + " - " + userGeo.getLng();
            tvAddressGeo.setText(text); //TODO : refactor
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
//        Context context = this.findViewById(R.id.userdetails_text_id).getContext(); // TODO : no need u r already in context . activity extends context
        Toast.makeText(this, "OOPS There Is An Problem ,Try Again .", Toast.LENGTH_LONG).show();
    }

    public void onEmailClicked(View view) {
        // email clicked toast
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);


        startActivity(intent);
        // send an email for whats given ( open mail app ) .
    }
}




