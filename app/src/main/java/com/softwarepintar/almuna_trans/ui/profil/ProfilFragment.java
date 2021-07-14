package com.softwarepintar.almuna_trans.ui.profil;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.softwarepintar.almuna_trans.API.APIService;
import com.softwarepintar.almuna_trans.API.Utils;
import com.softwarepintar.almuna_trans.Login.LoginActivity;
import com.softwarepintar.almuna_trans.Login.Login_Response;
import com.softwarepintar.almuna_trans.MainActivity;
import com.softwarepintar.almuna_trans.R;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProfilFragment extends Fragment {

    @BindView(R.id.info)
    TextView info;
    @BindView(R.id.txtubah)
    TextView txtubah;

    @BindView(R.id.tvnama)
    TextView tvnama;
    @BindView(R.id.garis2)
    View garis2;

    @BindView(R.id.tvjk)
    TextView tvjk;
    @BindView(R.id.garis3)
    View garis3;


    @BindView(R.id.garis4)
    View garis4;

    @BindView(R.id.tvalamat)
    TextView tvalamat;
    @BindView(R.id.card1)
    CardView card1;
    @BindView(R.id.akun)
    TextView akun;
    @BindView(R.id.noid)
    TextView noid;
    @BindView(R.id.tvnoid)
    TextView tvnoid;
    @BindView(R.id.garis5)
    View garis5;
    @BindView(R.id.email)
    TextView email;
    @BindView(R.id.tv_email)
    TextView tvEmail;
    @BindView(R.id.garis6)
    View garis6;
    @BindView(R.id.notelp)
    TextView notelp;
    @BindView(R.id.tv_telp)
    TextView tvTelp;
    @BindView(R.id.card2)
    CardView card2;
    @BindView(R.id.bnthelp)
    Button bnthelp;
    @BindView(R.id.btnpassword)
    Button btnpassword;
    @BindView(R.id.btn_logout)
    Button btnLogout;
    @BindView(R.id.tvusername)
    TextView tvusername;
    private ProfilViewModel notificationsViewModel;
    private static String nama, jk, tl, alamat, user_id;
    Intent intent;
    SharedPreferences sharedpreferences;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel =
                ViewModelProviders.of(this).get(ProfilViewModel.class);
        View root = inflater.inflate(R.layout.fragment_profil, container, false);
        Unbinder unbinder = ButterKnife.bind(this, root);
        ((AppCompatActivity) getActivity()).getSupportActionBar().show();
        ((MainActivity) Objects.requireNonNull(getActivity())).getSupportActionBar().setTitle("Profil");
        ((MainActivity) Objects.requireNonNull(getActivity())).getSupportActionBar().setSubtitle("");

        sharedpreferences = getContext().getSharedPreferences(LoginActivity.shared_preferenced, Context.MODE_PRIVATE);

        notificationsViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {

            }
        });
//        Toast.makeText(getContext(), MainActivity.user_id, Toast.LENGTH_SHORT).show();
        user_id = Utils.user_id;
        Profil();

        return root;

    }

    private void Profil() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Utils.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        APIService apiService = retrofit.create(APIService.class);
        Call<Login_Response> profilResponseCall = apiService.profil(user_id);
        profilResponseCall.enqueue(new Callback<Login_Response>() {
            @Override
            public void onResponse(Call<Login_Response> call, Response<Login_Response> response) {
                int value = response.body().getValue();


                if (value == 1) {
                    nama = response.body().getNama();
                    alamat = response.body().getAlamat();

                    jk = response.body().getJenis_kelamin();
                    String jekel;
                    if (jk.equalsIgnoreCase("l")) {
                        jekel = "Laki-laki";


                    } else {
                        jekel = "Perempuan";
                    }
                    alamat = response.body().getAlamat();
                    tvnama.setText(response.body().getNama());
                    tvTelp.setText(response.body().getHp());
                    tvEmail.setText(response.body().getEmail());
                    tvnoid.setText(response.body().getNo_id());
                    tvalamat.setText(response.body().getAlamat());
                    tvjk.setText(jekel);
                    tvusername.setText(response.body().getUser_name());


                } else {
                    Toast.makeText(getContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<Login_Response> call, Throwable t) {
                Toast.makeText(getContext(), "Gagal", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @OnClick({R.id.txtubah, R.id.bnthelp, R.id.btnpassword, R.id.btn_logout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.txtubah:
                intent = new Intent(getContext(), EditProfilActivity.class);
                intent.putExtra("nama", nama);
                intent.putExtra("jk", jk);
                intent.putExtra("alamat", alamat);
                startActivity(intent);


                break;
            case R.id.bnthelp:
                String url = "https://almunatrans.com/kontak-kami";
                intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
                break;
            case R.id.btnpassword:
                intent = new Intent(getContext(), ResetPasswordActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_logout:
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putBoolean(LoginActivity.session, false);
                editor.putString(Utils.TAG_user_id, null);
                editor.putString(Utils.TAG_user_nama, null);

                editor.clear();
                editor.commit();


                Intent intent = new Intent(getContext(), LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);




                startActivity(intent);
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}