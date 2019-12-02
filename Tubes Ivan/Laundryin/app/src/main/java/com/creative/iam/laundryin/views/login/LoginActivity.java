package com.creative.iam.laundryin.views.login;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
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
import com.creative.iam.laundryin.tools.PreferencesUtils;
import com.creative.iam.laundryin.tools.Tools;
import com.creative.iam.laundryin.views.main.UtamaActivity;
import com.creative.iam.laundryin.views.registrasi.SignUpActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    ProgressDialog pDialog;
    EditText etLoginUser, etLoginPass;
    Button btnLogin;
    TextView tvRegister;

    ConnectivityManager conMgr;
    private static final String TAG = LoginActivity.class.getSimpleName();


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

        PreferencesUtils sp = new PreferencesUtils(getApplicationContext());
        String username = sp.get("uname",null);

        if (username != null) {
            Intent intent = new Intent(LoginActivity.this, UtamaActivity.class);
            intent.putExtra("username", username);
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

        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, SignUpActivity.class));
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
                    PreferencesUtils sp = new PreferencesUtils(getApplicationContext());
                    sp.set("uname",username);
                    sp.set("address",response.body().getData().getAlamat());

                    Constant.SAVED_USERNAME = response.body().getData().getUsername();
                    Constant.SAVED_ADDRESS = response.body().getData().getAlamat();

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
