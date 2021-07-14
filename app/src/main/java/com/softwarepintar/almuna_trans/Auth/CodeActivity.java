package com.softwarepintar.almuna_trans.Auth;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.alimuzaffar.lib.pin.PinEntryEditText;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.softwarepintar.almuna_trans.API.APIService;
import com.softwarepintar.almuna_trans.API.Utils;
import com.softwarepintar.almuna_trans.Login.LoginActivity;
import com.softwarepintar.almuna_trans.R;
import com.softwarepintar.almuna_trans.Register.Register_Response;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CodeActivity extends AppCompatActivity {
    @BindView(R.id.txt)
    TextView txt;
    @BindView(R.id.otp_code)
    PinEntryEditText otpCode;
    @BindView(R.id.resend_code)
    TextView resendCode;
    @BindView(R.id.success_text)
    TextView successText;
    private FirebaseAuth mAuth;
    private PhoneAuthProvider.ForceResendingToken mResendToken;
    private String mVerificationId;
    private String phoneNumber;
    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_code);
        ButterKnife.bind(this);
        Bundle bundle = getIntent().getExtras();
        FirebaseApp.initializeApp(this);
        assert bundle != null;
        phoneNumber = bundle.getString("phone");
        mAuth = FirebaseAuth.getInstance();
        requestOtp(phoneNumber);
        widgetListener();
    }

    private void widgetListener() {
        otpCode.setOnPinEnteredListener(str -> sendOtpCode(str.toString()));

        resendCode.setOnClickListener(v -> resendCodeVerify(phoneNumber));
    }

    private void requestOtp(String phone) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phone, 120, TimeUnit.SECONDS, this, callbacks()
        );

    }

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks callbacks() {

        return new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
//                Log.e("onVerificationCompleted", phoneAuthCredential.getSmsCode());
                register();
            }

            @Override
            public void onVerificationFailed(FirebaseException e) {

                if (e instanceof FirebaseAuthInvalidCredentialsException) {
                    Log.e("invalidCredential", e.toString());
                } else if (e instanceof FirebaseTooManyRequestsException) {
                    Log.e("out of quota", e.toString());
                }
            }

            @Override
            public void onCodeAutoRetrievalTimeOut(String s) {
                super.onCodeAutoRetrievalTimeOut(s);
                Log.e("", s);
            }

            @Override
            public void onCodeSent(String verificationId, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                super.onCodeSent(verificationId, forceResendingToken);

                Log.e("", "onCodeSent:" + verificationId);
                mVerificationId = verificationId;
                mResendToken = forceResendingToken;
            }
        };
    }

    private void sendOtpCode(String code) {
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(mVerificationId, code);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {

                        Log.e("status", "signInWithCredential:success");

                        // do login auth !!
                        FirebaseAuth.getInstance().signOut();
                        otpCode.setVisibility(View.GONE);
                        resendCode.setVisibility(View.GONE);


                        Toast.makeText(CodeActivity.this, "Verifikasi Kode Berhasil", Toast.LENGTH_SHORT).show();
//                        Intent intent = new Intent(CodeActivity.this, MainActivity.class);
//
//
//                        intent.putExtra("login_cust_phone_number", getIntent().getStringExtra("login_cust_phone_number"));
//
//                        startActivity(intent);
                        register();

                    } else {
                        Log.w("status", "signInWithCredential:failure", task.getException());
                        if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                            Log.e("err", task.getException().toString());
                            Toast.makeText(CodeActivity.this, "Kode Verifikasi Salah", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void register() {
        progressDialog =new ProgressDialog(this);
        progressDialog.setCancelable(true);
        progressDialog.setMessage("Proses Registrasi");
        progressDialog.show();
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(Utils.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        APIService apiService=retrofit.create(APIService.class);
        Call<Register_Response>call=apiService.register(getIntent().getStringExtra("nama"),
                getIntent().getStringExtra("alamat"),getIntent().getStringExtra("phone"),
                getIntent().getStringExtra("email"),getIntent().getStringExtra("user_password"),getIntent().getStringExtra("jk"),
                getIntent().getStringExtra("jenis_id"),getIntent().getStringExtra("no_id"));
        call.enqueue(new Callback<Register_Response>() {
            @Override
            public void onResponse(Call<Register_Response> call, Response<Register_Response> response) {
                int value=response.body().getValue();
                String message=response.body().getMessage();
                progressDialog.dismiss();
                if (value==1){
                    Toast.makeText(CodeActivity.this, "Registrasi Berhasil Silahkan Login", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(CodeActivity.this, LoginActivity.class));
                    finish();
                }
                else {
                    Toast.makeText(CodeActivity.this, message, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Register_Response> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(CodeActivity.this, "Jaringan Error", Toast.LENGTH_SHORT).show();

            }
        });
    }

    // TODO 4: Buat fungsi resend kode OTP
    public void resendCodeVerify(String phone) {
        if (mResendToken != null) {
            PhoneAuthProvider.getInstance().verifyPhoneNumber(
                    phone, 120, TimeUnit.SECONDS, this, callbacks(), mResendToken
            );
            Toast.makeText(CodeActivity.this, "Kode dikirim ulang", Toast.LENGTH_SHORT).show();
        }
    }
}
