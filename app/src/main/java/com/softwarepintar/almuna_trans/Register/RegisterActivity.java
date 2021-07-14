package com.softwarepintar.almuna_trans.Register;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.FirebaseApp;
import com.softwarepintar.almuna_trans.API.APIService;
import com.softwarepintar.almuna_trans.API.Utils;
import com.softwarepintar.almuna_trans.Auth.CodeActivity;
import com.softwarepintar.almuna_trans.Login.LoginActivity;
import com.softwarepintar.almuna_trans.R;

import java.util.ArrayList;
import java.util.List;

import br.com.simplepass.loadingbutton.customViews.CircularProgressButton;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegisterActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    @BindView(R.id.editTextName)
    EditText editTextName;
    @BindView(R.id.textInputName)
    TextInputLayout textInputName;
    @BindView(R.id.editTextEmail)
    EditText editTextEmail;
    @BindView(R.id.textInputEmail)
    TextInputLayout textInputEmail;
    @BindView(R.id.editTextMobile)
    EditText editTextMobile;
    @BindView(R.id.textInputMobile)
    TextInputLayout textInputMobile;
    @BindView(R.id.editTextPassword)
    EditText editTextPassword;
    @BindView(R.id.textInputPassword)
    TextInputLayout textInputPassword;

    @BindView(R.id.cirRegisterButton)
    CircularProgressButton cirRegisterButton;
    @BindView(R.id.tvlogin)
    TextView tvlogin;
    @BindView(R.id.cardview)
    CardView cardview;

    @BindView(R.id.editTextAlamat)
    EditText editTextAlamat;
    @BindView(R.id.textInputAlamat)
    TextInputLayout textInputAlamat;
    @BindView(R.id.laki)
    RadioButton laki;
    @BindView(R.id.pr)
    RadioButton pr;
    @BindView(R.id.radiogroup)
    RadioGroup radiogroup;
    int selectedId;
    RadioButton radioButton;
    @BindView(R.id.spn_id)
    Spinner spnId;
    @BindView(R.id.txt_noid)
    TextInputEditText txtNoid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        FirebaseApp.initializeApp(this);

        spnId.setOnItemSelectedListener(this);
        List<String> categories = new ArrayList<String>();

        categories.add("KTP");
        categories.add("SIM");
        categories.add("Passpor");
        categories.add("Laiinnya");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(RegisterActivity.this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spnId.setAdapter(dataAdapter);

    }

    @OnClick({R.id.cirRegisterButton, R.id.tvlogin})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.cirRegisterButton:

                validate();
                break;
            case R.id.tvlogin:
                startActivity(new Intent(this, LoginActivity.class));
                overridePendingTransition(R.anim.slide_in_left, android.R.anim.slide_out_right);
                break;

        }
    }

    private void validate() {
        if (editTextEmail.getText().toString().length() == 0) {
            //jika form Email belum di isi / masih kosong
            editTextEmail.setError("Email diperlukan!");
        } else if (editTextName.getText().toString().length() == 0) {
            //jika form Username belum di isi / masih kosong
            editTextName.setError("Username diperlukan!");
        } else if (editTextPassword.getText().toString().length() == 0) {
            //jika form Passwrod belum di isi / masih kosong
            editTextPassword.setError("Password diperlukan!");
        } else if (editTextMobile.getText().toString().length() == 0) {
            //jika form Passwrod belum di isi / masih kosong
            editTextMobile.setError("Nomor HP diperlukan!");
        } else {
            if (editTextMobile.getText().toString().charAt(0) == '0') {

//                String temp = "+62" + editTextMobile.getText().toString().substring(1);
//
//                Bundle bundle = new Bundle();
//                Intent intent = new Intent(RegisterActivity.this, CodeActivity.class);
//                bundle.putString("phone", temp);
//                bundle.putString("nama", editTextName.getText().toString());
//                bundle.putString("alamat", editTextAlamat.getText().toString());
//
//                bundle.putString("jenis_id",spnId.getSelectedItem().toString());
//                bundle.putString("no_id",txtNoid.getText().toString());
//
//                bundle.putString("email", editTextEmail.getText().toString());
//                bundle.putString("user_password", editTextPassword.getText().toString());
//                selectedId = radiogroup.getCheckedRadioButtonId();
//
//                // mencari radio button
//                radioButton = (RadioButton) findViewById(selectedId);
//                bundle.putString("jk", radioButton.getText().toString());
//                intent.putExtras(bundle);
//                startActivity(intent);
                register();

            }
        }


    }

    private void register() {
        selectedId = radiogroup.getCheckedRadioButtonId();

        // mencari radio button
        radioButton = (RadioButton) findViewById(selectedId);

        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(true);
        progressDialog.setMessage("Proses Registrasi");
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Utils.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        APIService apiService = retrofit.create(APIService.class);
        Call<Register_Response> call = apiService.register(editTextName.getText().toString(),
                editTextAlamat.getText().toString(), editTextMobile.getText().toString(),
                editTextEmail.getText().toString(), editTextPassword.getText().toString(),radioButton.getText().toString()
                ,spnId.getSelectedItem().toString(),txtNoid.getText().toString());
        call.enqueue(new Callback<Register_Response>() {
            @Override
            public void onResponse(Call<Register_Response> call, Response<Register_Response> response) {
                int value = response.body().getValue();
                String message = response.body().getMessage();
                progressDialog.dismiss();
                if (value == 1) {
                    Toast.makeText(RegisterActivity.this, "Registrasi Berhasil Silahkan Login", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                } else {
                    Toast.makeText(RegisterActivity.this, message, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Register_Response> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(RegisterActivity.this, "Jaringan Error", Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String item = parent.getItemAtPosition(position).toString();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
