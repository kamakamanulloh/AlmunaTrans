package com.softwarepintar.almuna_trans.lupa_sandi;

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
import com.softwarepintar.almuna_trans.R;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OtpActivity extends AppCompatActivity {

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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);
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
                Log.e("onVerificationCompleted", phoneAuthCredential.getSmsCode());

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


                        Toast.makeText(OtpActivity.this, "Verifikasi Kode Berhasil", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(OtpActivity.this, LupaSandiActivity.class);


                        intent.putExtra("phone", getIntent().getStringExtra("login_cust_phone_number"));
                        intent.putExtra("userid", getIntent().getStringExtra("userid"));

                        startActivity(intent);
                        finish();


                    } else {
                        Log.w("status", "signInWithCredential:failure", task.getException());
                        if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                            Log.e("err", task.getException().toString());
                            Toast.makeText(OtpActivity.this, "Kode Verifikasi Salah", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
    public void resendCodeVerify(String phone) {
        if (mResendToken != null) {
            PhoneAuthProvider.getInstance().verifyPhoneNumber(
                    phone, 120, TimeUnit.SECONDS, this, callbacks(), mResendToken
            );
            Toast.makeText(OtpActivity.this, "Kode dikirim ulang", Toast.LENGTH_SHORT).show();
        }
    }

}
