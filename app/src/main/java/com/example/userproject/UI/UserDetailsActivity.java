package com.example.userproject.UI;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.example.userproject.POJO.Address;
import com.example.userproject.POJO.Company;
import com.example.userproject.POJO.Geo;
import com.example.userproject.POJO.GeoAddress;
import com.example.userproject.POJO.User;
import com.example.userproject.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UserDetailsActivity extends AppCompatActivity {
    public static final String KEY_USER="User";
    public static final String MyPREFERENCES = "ListUserPrefs";
    public static final String FAV_BTN="FAVORITE_BTN";

    @BindView(R.id.userdetails_text_id)
    TextView tvId;
    @BindView(R.id.userdetails_text_name)
    TextView tvName;
    @BindView(R.id.userdetails_text_username)
    TextView tvUserName;
    @BindView(R.id.userdetails_text_website)
    TextView tvWebsite;
    @BindView(R.id.userdetails_text_email)
    TextView tvEmail;
    @BindView(R.id.userdetails_text_phone)
    TextView tvPhone;

    @BindView(R.id.userdetails_text_address_street)
    TextView tvAddressStreet;
    @BindView(R.id.userdetails_text_address_suite)
    TextView tvAddressSuite;
    @BindView(R.id.userdetails_text_address_city)
    TextView tvAddressCity;
    @BindView(R.id.userdetails_text_address_zipcode)
    TextView tvAddressZipcode;

    @BindView(R.id.userdetails_text_company_name)
    TextView tvCompanyName;
    @BindView(R.id.userdetails_text_company_catchphrase)
    TextView tvCompanyCatchphrase;
    @BindView(R.id.userdetails_text_company_bs)
    TextView tvCompanyBs;

    @BindView(R.id.userdetails_text_address_geo)
    TextView tvAddressGeo;
    @BindView(R.id.userdetails_text_address_geoView)
    TextView tvAddressGeoView;

    @BindView(R.id.userdetails_button_changestatus)
    ImageButton btChangeStatus;
    @BindView(R.id.loading_container)
    RelativeLayout rlLoading;

    private User userRetriave;
    boolean userIdle;
    SharedPreferences sharedpreferences;


    public static void start(Context context, User user) {
        Intent starter = new Intent(context, UserDetailsActivity.class);
        starter.putExtra(KEY_USER, user);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relative_user_details);
        bindViews();
        loadUser();
        loadPreference();
    }


    protected void bindViews() {
        ButterKnife.bind(this);
    }

    private void loadUser() {
        //here you can change the Id of user you want
        User user = (User) getIntent().getSerializableExtra(KEY_USER);
        hideLoading();
        getUserDetails(user);
    }

    private void hideLoading() {
        rlLoading.setVisibility(View.GONE);
    }

    protected void getUserDetails(User userRespose) {
        if (userRespose != null) {
            userRetriave = userRespose;
            setUserViews(userRespose);

            Company userCompany = userRespose.getCompany();
            setUserCompanyViews(userCompany);

            Address userAddress = userRespose.getAddress();
            setUserAddressViews(userAddress);

            setAddressGeoViews(userAddress);
        } else {
            String error_msg=getString(R.string.network_error_msg);
            Toast.makeText(this, error_msg, Toast.LENGTH_LONG).show();

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

    @OnClick(R.id.userdetails_text_email)
    public void onEmailClicked(View view) {
        String emailClickedMessage=getString(R.string.user_tvemail_clicked_msg);
        Toast.makeText(this,emailClickedMessage , Toast.LENGTH_LONG).show();
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.setType("message/rfc822");
        String emailClicked = userRetriave.getEmail() + "";
        intent.putExtra(Intent.EXTRA_EMAIL, emailClicked);
        startActivity(intent);
    }

    public void setUserStatus(View view) {
        if (userIdle){
            btChangeStatus.setImageResource(R.drawable.ic_busyuser_star_24);
            setPreferences();
            userIdle = false;
        } else {
            btChangeStatus.setImageResource(R.drawable.ic_idleuser_star_24);
            setPreferences();
            userIdle = true;
        }

    }

    private void setPreferences() {
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putBoolean(userRetriave.getId()+"", userIdle);
        editor.apply();
    }

    private void loadPreference() {
        sharedpreferences= getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        userIdle= sharedpreferences.getBoolean(userRetriave.getId()+"",false);
        setUserStatus(btChangeStatus);
    }

}




