package com.creative.iam.laundryin.views.login;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.creative.iam.laundryin.R;
import com.creative.iam.laundryin.network.ApiClient;
import com.creative.iam.laundryin.network.ApiInterface;
import com.creative.iam.laundryin.network.response.BaseDao;
import com.creative.iam.laundryin.network.response.LoginResponseDao;
import com.creative.iam.laundryin.tools.Constant;
import com.creative.iam.laundryin.tools.Tools;
import com.creative.iam.laundryin.views.main.UtamaActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    ProgressDialog pDialog;
    EditText etLoginUser, etLoginPass;
    Button btnLogin;
    TextView tvRegister;
    Intent intent;

    int success;
    ConnectivityManager conMgr;
    private static final String TAG = LoginActivity.class.getSimpleName();

    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";

    public final static String TAG_USERNAME = "username";

    String tag_json_obj = "json_obj_req";

    SharedPreferences sharedPreferences;
    Boolean session = false;
    String username;
    public static final String my_shared_preferences = "my_shared_preferences";
    public static final String session_status = "session_status";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        conMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        {
            if (conMgr.getActiveNetworkInfo() != null
                    && conMgr.getActiveNetworkInfo().isAvailable()
                    && conMgr.getActiveNetworkInfo().isConnected()) {
            } else {
                Toast.makeText(getApplicationContext(), "No Internet Connection",
                        Toast.LENGTH_LONG).show();
            }
        }

        btnLogin = (Button) findViewById(R.id.btnLogin);
        etLoginUser = (EditText) findViewById(R.id.etLoginUser);
        etLoginPass = (EditText) findViewById(R.id.etLoginPass);
        tvRegister = findViewById(R.id.tvRegister);

        Tools.loadTextFromHTML(tvRegister,
                "Belum punya akun ? <b><font color=\"#2398CD\"> Daftar </font></b>");

        sharedPreferences = getSharedPreferences(my_shared_preferences, Context.MODE_PRIVATE);
        session = sharedPreferences.getBoolean(session_status, false);
        username = sharedPreferences.getString(TAG_USERNAME, null);

        if (session) {
            Intent intent = new Intent(LoginActivity.this, UtamaActivity.class);
            intent.putExtra(TAG_USERNAME, username);
            finish();
            startActivity(intent);
        }

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = etLoginUser.getText().toString();
                String password = etLoginPass.getText().toString();

                if (username.trim().length() > 0 && password.trim().length() > 0) {
                    if (conMgr.getActiveNetworkInfo() != null
                            && conMgr.getActiveNetworkInfo().isAvailable()
                            && conMgr.getActiveNetworkInfo().isConnected()) {
                        checkLogin(username, password);
                    } else {
                        Toast.makeText(getApplicationContext(), "No Internet Connection", Toast.LENGTH_LONG).show();
                    }
                } else {
                    // Prompt user to enter credentials
                    Toast.makeText(getApplicationContext(), "Kolom tidak boleh kosong", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void checkLogin(final String username, final String password) {
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);
        pDialog.setMessage("Logging in ...");
        showDialog();

        ApiInterface apiClient = new ApiClient().getClient(Constant.BASE_URL).create(ApiInterface.class);
        apiClient.doLogin(username,password).enqueue(new Callback<BaseDao<LoginResponseDao>>() {
            @Override
            public void onResponse(Call<BaseDao<LoginResponseDao>> call, Response<BaseDao<LoginResponseDao>> response) {
                hideDialog();
                if(response.body().getCode() == 1){
                    sharedPreferences.edit().putString(TAG_USERNAME,response.body().getData().getUsername()).putBoolean(session_status,true).apply();
                    Intent intent = new Intent(LoginActivity.this, UtamaActivity.class);
                    startActivity(intent);
                    finish();
                }else {
                    Toast.makeText(getApplicationContext(), "Please Check your Credentials", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<BaseDao<LoginResponseDao>> call, Throwable t) {
                hideDialog();
                Log.e(TAG, "onFailure: "+t.getMessage() );
                Toast.makeText(getApplicationContext(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }
}
