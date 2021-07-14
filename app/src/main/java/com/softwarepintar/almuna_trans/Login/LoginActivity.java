package com.softwarepintar.almuna_trans.Login;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;
import com.softwarepintar.almuna_trans.API.APIService;
import com.softwarepintar.almuna_trans.API.Utils;
import com.softwarepintar.almuna_trans.MainActivity;
import com.softwarepintar.almuna_trans.R;
import com.softwarepintar.almuna_trans.Register.RegisterActivity;
import com.softwarepintar.almuna_trans.lupa_sandi.OtpActivity;

import br.com.simplepass.loadingbutton.customViews.CircularProgressButton;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.editTextUsername)
    EditText editTextUsername;
    @BindView(R.id.textInputEmail)
    TextInputLayout textInputEmail;
    @BindView(R.id.editTextPassword)
    EditText editTextPassword;
    @BindView(R.id.textPassword)
    TextInputLayout textPassword;
    @BindView(R.id.cirLoginButton)
    CircularProgressButton cirLoginButton;
    @BindView(R.id.tvregister)
    TextView tvregister;

    @BindView(R.id.btnregister)
    ImageView btnregister;
    public static final String shared_preferenced = "shared_preferenced";
    public static final String session = "session";
    SharedPreferences sharedPreferences;
    Boolean session_status = false;
    String user_id, user_name;

    AlertDialog.Builder dialog;
    LayoutInflater inflater;
    View dialogView;
    @BindView(R.id.lupa)
    TextView lupa;
    private static String txthp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        sharedPreferences = getSharedPreferences(shared_preferenced, Context.MODE_PRIVATE);
        session_status = sharedPreferences.getBoolean(session, false);
        user_id = sharedPreferences.getString(Utils.TAG_user_id, null);
        user_name = sharedPreferences.getString(Utils.TAG_user_nama, null);
        if (session_status) {
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            intent.putExtra(Utils.TAG_user_id, user_id);
            intent.putExtra(Utils.TAG_user_nama, user_name);
            Utils.user_id=user_id;
            Utils.nm_user=user_name;

            finish();
            startActivity(intent);
        }
    }

    @OnClick({R.id.cirLoginButton, R.id.tvregister, R.id.btnregister,
    R.id.lupa})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.cirLoginButton:
                login();
                break;
            case R.id.tvregister:
            case R.id.btnregister:
                startActivity(new Intent(this, RegisterActivity.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.stay);
                break;

            case R.id.lupa:
                lupaa();
                break;
        }
    }

    private void lupaa() {
        dialog = new AlertDialog.Builder(LoginActivity.this);
        inflater = getLayoutInflater();
        dialogView = inflater.inflate(R.layout.kolom_no_hp, null);
        dialog.setView(dialogView);
        dialog.setCancelable(true);
        dialog.setIcon(R.mipmap.ic_launcher);


        EditText txtnohp    = (EditText) dialogView.findViewById(R.id.edt_comment);

        dialog.setPositiveButton("SUBMIT", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                txthp    = txtnohp.getText().toString();
              cekhp();
            }
        });

        dialog.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    private void cekhp() {
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(true);
        progressDialog.setMessage("Proses");
        progressDialog.show();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Utils.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        APIService apiService = retrofit.create(APIService.class);
        Call<Login_Response> call = apiService.cekhp(txthp);
        call.enqueue(new Callback<Login_Response>() {
            @Override
            public void onResponse(Call<Login_Response> call, Response<Login_Response> response) {
                int value = response.body().getValue();
                String message = response.body().getMessage();
                user_id = response.body().getUser_id();
                user_name = response.body().getUser_name();

                if (value == 1) {

                    Intent intent = new Intent(LoginActivity.this, OtpActivity.class);
                    intent.putExtra("phone", txthp);
                    intent.putExtra("userid",user_id);

                    startActivity(intent);
                    finish();
//                    Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();

                } else {

                    Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<Login_Response> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(LoginActivity.this, "Failure", Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void login() {
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(true);
        progressDialog.setMessage("Proses Login");
        progressDialog.show();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Utils.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        APIService apiService = retrofit.create(APIService.class);
        Call<Login_Response> call = apiService.login(editTextUsername.getText().toString(),
                editTextPassword.getText().toString());
        call.enqueue(new Callback<Login_Response>() {
            @Override
            public void onResponse(Call<Login_Response> call, Response<Login_Response> response) {
                int value = response.body().getValue();
                String message = response.body().getMessage();
                user_id = response.body().getUser_id();
                user_name = response.body().getUser_name();

                if (value == 1) {
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean(session, true);
                    editor.putString(Utils.TAG_user_id, user_id);
                    editor.putString(Utils.TAG_user_nama, user_name);
                    editor.commit();
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    intent.putExtra(Utils.TAG_user_id, user_id);
                    intent.putExtra(Utils.TAG_user_nama, user_name);
                    startActivity(intent);
                    Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();
                    finish();
                } else {

                    Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<Login_Response> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(LoginActivity.this, "Failure", Toast.LENGTH_SHORT).show();
            }
        });


    }
}
