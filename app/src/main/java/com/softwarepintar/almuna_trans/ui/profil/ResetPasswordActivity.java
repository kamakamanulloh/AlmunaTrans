package com.softwarepintar.almuna_trans.ui.profil;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatCheckBox;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.softwarepintar.almuna_trans.API.APIService;
import com.softwarepintar.almuna_trans.API.Utils;
import com.softwarepintar.almuna_trans.R;
import com.softwarepintar.almuna_trans.Register.Register_Response;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ResetPasswordActivity extends AppCompatActivity {

    @BindView(R.id.txtsandi)
    TextInputEditText txtsandi;
    @BindView(R.id.sandi)
    TextInputLayout sandi;
    @BindView(R.id.checkbox1)
    AppCompatCheckBox checkbox1;
    @BindView(R.id.txtsandii)
    TextInputEditText txtsandii;
    @BindView(R.id.sandii)
    TextInputLayout sandii;
    @BindView(R.id.checkbox2)
    AppCompatCheckBox checkbox2;
    @BindView(R.id.btnsave)
    Button btnsave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.checkbox1, R.id.checkbox2, R.id.btnsave})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.checkbox1:
                txtsandi.setTransformationMethod(HideReturnsTransformationMethod.getInstance());

                break;
            case R.id.checkbox2:
                txtsandii.setTransformationMethod(HideReturnsTransformationMethod.getInstance());

                break;
            case R.id.btnsave:
                if (txtsandi.getText().toString().length()<=8 ||txtsandii.getText().toString().length()<=8 ) {
                    txtsandi.setError("Password Harus Lebih dari 8 Karakter");
                }
                else{
                    if (txtsandi.getText().toString().equals(txtsandii.getText().toString())){
                        simpan();
                    }
                    else{
                        txtsandii.setError("Kata Sandi Tidak Sesuai");
                    }
                }
                break;
        }
    }

    private void simpan() {

        ProgressDialog progressDialog =new ProgressDialog(this);
        progressDialog.setCancelable(true);
        progressDialog.setMessage("Updating");
        progressDialog.show();
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(Utils.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        APIService apiService=retrofit.create(APIService.class);
        Call<Register_Response>call=apiService.edit_password(Utils.user_id,txtsandi.getText().toString());
        call.enqueue(new Callback<Register_Response>() {
            @Override
            public void onResponse(Call<Register_Response> call, Response<Register_Response> response) {
                int value=response.body().getValue();
                String messages=response.body().getMessage();
                progressDialog.dismiss();
                if (value==1){
                    Toast.makeText(ResetPasswordActivity.this, messages, Toast.LENGTH_SHORT).show();

                }
                else{
                    Toast.makeText(ResetPasswordActivity.this, messages, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Register_Response> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(ResetPasswordActivity.this, "Failure", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
