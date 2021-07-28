package com.example.userproject.UI;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.example.userproject.POJO.FavoriteEvent;
import com.example.userproject.R;
import com.example.userproject.VM.UserViewModel;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONObject;

import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import io.branch.referral.Branch;
import io.branch.referral.BranchError;

public class UserDetailsActivity extends AppCompatActivity {
    public static final String KEY_USER="User";
    public static final String KEY_USER_ID="id";
    public static final String KEY_USER_NAME="name";
    public static final String KEY_USER_USERNAME="username";
    public static final String KEY_USER_EMAIL="email";
    public static final String MyPREFERENCES = "ListUserPrefs";
    public static final String FAV_BTN="FAVORITE_BTN";
    Context context=this;

    @BindView(R.id.userdetails_text_id)
    TextView tvId;
    @BindView(R.id.userdetails_text_name)
    TextView tvName;
    @BindView(R.id.userdetails_text_username)
    TextView tvUserName;

    @BindView(R.id.userdetails_text_email)
    TextView tvEmail;

    @BindView(R.id.userdetails_button_changestatus)
    ImageButton btChangeStatus;
    @BindView(R.id.listuser_loading_container)
    RelativeLayout rlLoading;

    private UserViewModel userRetriave;
    SharedPreferences sharedpreferences;



    public static void start(Context context, UserViewModel userViewModel) {
        Intent starter = new Intent(context, UserDetailsActivity.class);
        starter.putExtra(KEY_USER, userViewModel);
        context.startActivity(starter);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linear_user_details);
        loadBranch();

        bindViews();
        getIntentFromLink();
        loadUser();
        loadPreference();



    }

    private void loadBranch() {
        // Branch logging for debugging
        Branch.enableLogging();

        // Branch object initialization
        Branch.getAutoInstance(this);


    }
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        // if activity is in foreground (or in backstack but partially visible) launching the same
        // activity will skip onStart, handle this case with reInitSession
        Branch.sessionBuilder(this).withCallback(branchReferralInitListener).reInit();
    }
    private Branch.BranchReferralInitListener branchReferralInitListener = new Branch.BranchReferralInitListener() {
        @Override
        public void onInitFinished(JSONObject linkProperties, BranchError error) {

        }
    };


    /*

    private Branch.BranchReferralInitListener branchReferralInitListener = new Branch.BranchReferralInitListener() {
        @Override
        public void onInitFinished(JSONObject linkProperties, BranchError error) {
            if (error == null) {
                Log.i("BranchDeepLink", "" + linkProperties.toString());
                try {
                    Gson gson = new Gson();
                    JsonParser parser = new JsonParser();
                    JsonElement json = parser.parse(linkProperties.toString());
                    userRetriave = gson.fromJson(json, UserViewModel.class);
                    //ShareScreenDataModel shareScreenDataModel = gson.fromJson(json, ShareScreenDataModel.class);
                } catch (Exception e) {

                }
            }

        }
    };*/


    private void getIntentFromLink() {
        Intent appLinkIntent = getIntent();
        String appLinkAction = appLinkIntent.getAction();
        Uri appLinkData = appLinkIntent.getData();
        System.out.println(appLinkData);
        if (appLinkData!=null) {

            Set<String> queryParameterNames = appLinkData.getQueryParameterNames();
            System.out.println(appLinkData.toString());

            String userId = appLinkData.getQueryParameter(UserDetailsActivity.KEY_USER_ID);
            String userName = appLinkData.getQueryParameter(UserDetailsActivity.KEY_USER_NAME);
            String userUserName = appLinkData.getQueryParameter(UserDetailsActivity.KEY_USER_USERNAME);
            String userEmail = appLinkData.getQueryParameter(UserDetailsActivity.KEY_USER_EMAIL);

            System.out.println("ID : " + userId);
            System.out.println("Name : " + userName);
            System.out.println("UserName : " + userUserName);
            System.out.println("Email : " + userEmail);
            userRetriave = new UserViewModel();
            //userRetriave.setId(Integer.parseInt(userId));
            userRetriave.setName(userName);
            userRetriave.setUsername(userUserName);
            userRetriave.setEmail(userEmail);

        }
    }


    protected void bindViews() {
        ButterKnife.bind(this);
    }

    private void loadUser() {
        UserViewModel user;
        if (userRetriave==null)
         user = (UserViewModel) getIntent().getSerializableExtra(KEY_USER);
        else
            user=userRetriave;
        hideLoading();
        getUserDetails(user);
    }

    private void hideLoading() {
        rlLoading.setVisibility(View.GONE);
    }

    protected void getUserDetails(UserViewModel userRespose) {
        if (userRespose != null) {
            userRetriave = userRespose;
            setUserViews(userRespose);
        }

    }

    /*private void setAddressGeoViews(Address userAddress) {
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
    */
    
    private void setUserViews(UserViewModel userRespose) {
        tvId.setText(userRespose.getId() + "");
        tvName.setText(userRespose.getName());
        tvUserName.setText(userRespose.getUsername());
        tvEmail.setText(userRespose.getEmail());
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
        if(btChangeStatus.getTag().equals("idleuser")){
            btChangeStatus.setImageResource(R.drawable.ic_busyuser_star_24);
            btChangeStatus.setTag("busyuser");
            setPreferences();

        } else {
            btChangeStatus.setImageResource(R.drawable.ic_idleuser_star_24);
            btChangeStatus.setTag("idleuser");
            setPreferences();
        }

    }

    private void setPreferences() {
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString(userRetriave.getId()+"", btChangeStatus.getTag()+"");
        editor.apply();
    }

    private void loadPreference() {
        sharedpreferences= getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        String userIdle= sharedpreferences.getString(userRetriave.getId()+"","idleuser");
        setBtFavoriteTag(userIdle);
        setUserStatus(btChangeStatus);
    }

    private void setBtFavoriteTag(String userIdle) {
        if (userIdle.equals("idleuser")){
            btChangeStatus.setTag("busyuser");
        }else {
            btChangeStatus.setTag("idleuser");
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    Branch.sessionBuilder(this).withCallback(branchReferralInitListener).withData(getIntent() != null ? getIntent().getData() : null).init();

        EventBus.getDefault().register(this);
    }




    @Override
    protected void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onChangedUserStatus(FavoriteEvent favoriteEvent){
        String userIdle=favoriteEvent.userStatus;
        setBtFavoriteTag(userIdle);
        setUserStatus(btChangeStatus);
    }

    public void getUserLink(View view) {

        String host="http://userdetails.app.link";
        String userId=userRetriave.getId()+"";
        String userName=userRetriave.getName();
        String userUserName=userRetriave.getUsername();
        String userEmail=userRetriave.getEmail();
        String url=host;

        Uri uri=Uri.parse(host).buildUpon().
                appendQueryParameter(UserDetailsActivity.KEY_USER_ID,userId).
                appendQueryParameter(UserDetailsActivity.KEY_USER_NAME,userName).
                appendQueryParameter(UserDetailsActivity.KEY_USER_USERNAME,userUserName).
                appendQueryParameter(UserDetailsActivity.KEY_USER_EMAIL,userEmail).
                build();
        TextView tvgetLink=findViewById(R.id.userdetails_palintext_userlink_url);
        tvgetLink.setText(uri.toString()+"");
    }
}




