package com.softwarepintar.almuna_trans.ui.profil;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;
import com.softwarepintar.almuna_trans.API.APIService;
import com.softwarepintar.almuna_trans.API.Utils;
import com.softwarepintar.almuna_trans.Login.Login_Response;
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

public class EditProfilActivity extends AppCompatActivity {

    @BindView(R.id.editTextName)
    EditText editTextName;
    @BindView(R.id.textInputName)
    TextInputLayout textInputName;
    @BindView(R.id.tvjk)
    TextView tvjk;
    @BindView(R.id.laki)
    RadioButton laki;
    @BindView(R.id.pr)
    RadioButton pr;
    @BindView(R.id.radiogroup)
    RadioGroup radiogroup;
    @BindView(R.id.editTextAlamat)
    EditText editTextAlamat;
    @BindView(R.id.textInputAlamat)
    TextInputLayout textInputAlamat;
    @BindView(R.id.btn_ubah)
    Button btnUbah;
    @BindView(R.id.btnpswd)
    Button btnpswd;
    int selectedId;
    RadioButton radioButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profil);
        ButterKnife.bind(this);
        editTextAlamat.setText(getIntent().getStringExtra("alamat"));
        editTextName.setText(getIntent().getStringExtra("nama"));

        if (getIntent().getStringExtra("jk").equalsIgnoreCase("l")){
            laki.setChecked(true);


        }
        else {
            pr.setChecked(true);
        }
    }

    @OnClick({R.id.btn_ubah, R.id.btnpswd})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_ubah:
                edit();
                break;
            case R.id.btnpswd:
                break;
        }
    }

    private void edit() {
        selectedId = radiogroup.getCheckedRadioButtonId();

        // mencari radio button
        radioButton = (RadioButton) findViewById(selectedId);

        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(true);
        progressDialog.setMessage("Proses Ubah Profil");
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Utils.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        APIService apiService = retrofit.create(APIService.class);
        Call<Register_Response> call = apiService.editprofil(Utils.user_id,editTextName.getText().toString(),
                editTextAlamat.getText().toString(),  radioButton.getText().toString());
        call.enqueue(new Callback<Register_Response>() {
            @Override
            public void onResponse(Call<Register_Response> call, Response<Register_Response> response) {
                int value = response.body().getValue();
                String message = response.body().getMessage();
                progressDialog.dismiss();
                if (value == 1) {
                    Toast.makeText(EditProfilActivity.this, "Perubahan Data Berhasil", Toast.LENGTH_SHORT).show();
//                   profil();
                } else {
                    Toast.makeText(EditProfilActivity.this, message, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Register_Response> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(EditProfilActivity.this, "Jaringan Error", Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void profil() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Utils.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        APIService apiService = retrofit.create(APIService.class);
        Call<Login_Response> profilResponseCall = apiService.profil(Utils.user_id);
        profilResponseCall.enqueue(new Callback<Login_Response>() {
            @Override
            public void onResponse(Call<Login_Response> call, Response<Login_Response> response) {
                int value = response.body().getValue();

                if (value == 1) {
                    editTextAlamat.setText(response.body().getAlamat());
                    editTextName.setText(response.body().getNama());
                    if (response.body().getJenis_kelamin().equalsIgnoreCase("l")){
                        laki.setChecked(true);
                    }
                    else {
                        pr.setChecked(true);
                    }


                } else {
                    Toast.makeText(EditProfilActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Login_Response> call, Throwable t) {
                Toast.makeText(EditProfilActivity.this, "Gagal", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
