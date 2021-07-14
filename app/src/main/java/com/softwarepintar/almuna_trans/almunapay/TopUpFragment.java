package com.softwarepintar.almuna_trans.almunapay;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.softwarepintar.almuna_trans.R;

import java.text.NumberFormat;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static com.softwarepintar.almuna_trans.API.Utils.saldo;

/**
 * A simple {@link Fragment} subclass.
 */
public class TopUpFragment extends Fragment {

    @BindView(R.id.tvsaldo)
    TextView tvsaldo;
    @BindView(R.id.line)
    LinearLayout line;
    @BindView(R.id.tvnominal)
    TextView tvnominal;
    @BindView(R.id.txtnominal)
    EditText txtnominal;
    @BindView(R.id.btn50ribu)
    ImageView btn50ribu;
    @BindView(R.id.btn100ribu)
    ImageView btn100ribu;
    @BindView(R.id.btn150ribu)
    ImageView btn150ribu;
    @BindView(R.id.btn200ribu)
    ImageView btn200ribu;
    @BindView(R.id.linee)
    LinearLayout linee;
    @BindView(R.id.tvmetode)
    TextView tvmetode;

    @BindView(R.id.btnpetugas)
    Button btnpetugas;
    @BindView(R.id.btntf)
    Button btntf;
    Locale localeID = new Locale("in", "ID");
    NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
    Intent intent;
    public TopUpFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_top_up, container, false);
        Unbinder unbinder = ButterKnife.bind(this, view);
        tvsaldo.setText("Saldo Anda "+formatRupiah.format(Double.parseDouble(saldo)));



//        try {
//            topuop=new Integer(total).intValue();
//            int kd= Integer.parseInt(getRandomString());
//
//            totharga= topuop+kd;
//            total= String.valueOf(totharga);
//
//        }
//        catch (NumberFormatException ignored){
//            ignored.printStackTrace();
//        }
//        return total;


//        Toast.makeText(this, batas_bayar+" "+kdbayar+" "+total, Toast.LENGTH_SHORT).show();


        return view;
    }

    @OnClick({R.id.btn50ribu, R.id.btn100ribu,R.id.btntf, R.id.btn150ribu, R.id.btn200ribu, R.id.btnpetugas})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn50ribu:
                txtnominal.setText("50000");


                break;
            case R.id.btn100ribu:
                txtnominal.setText("100000");

                break;
            case R.id.btn150ribu:
                txtnominal.setText("150000");

                break;
            case R.id.btn200ribu:
                txtnominal.setText("200000");

                break;

            case R.id.btnpetugas:
                intent=new Intent(getContext(),TopupPetugasActivity.class);
                startActivity(intent);

                break;
            case R.id.btntf:

                intent=new Intent(getContext(),TransferActivity.class);
                intent.putExtra("nominal",txtnominal.getText().toString().trim());
                startActivity(intent);
                break;
        }
    }
}
