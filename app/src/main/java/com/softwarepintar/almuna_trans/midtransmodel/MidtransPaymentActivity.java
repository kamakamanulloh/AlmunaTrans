package com.softwarepintar.almuna_trans.midtransmodel;

import androidx.appcompat.app.AppCompatActivity;

import com.midtrans.sdk.corekit.core.themes.CustomColorTheme;
import com.midtrans.sdk.uikit.SdkUIFlowBuilder;
import com.softwarepintar.almuna_trans.BuildConfig;
import com.softwarepintar.almuna_trans.R;
import android.os.Bundle;
import android.widget.Toast;

import com.midtrans.sdk.corekit.callback.TransactionFinishedCallback;
import com.midtrans.sdk.corekit.core.MidtransSDK;
import com.midtrans.sdk.corekit.core.PaymentMethod;
import com.midtrans.sdk.corekit.core.UIKitCustomSetting;
import com.midtrans.sdk.corekit.core.themes.CustomColorTheme;
import com.midtrans.sdk.corekit.models.snap.TransactionResult;
import com.midtrans.sdk.uikit.SdkUIFlowBuilder;
public class MidtransPaymentActivity extends AppCompatActivity implements TransactionFinishedCallback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_midtrans_payment);
        paynow();
        initMidtranskSDK();
    }

    private void paynow(){
        String id = getIntent().getStringExtra("id");
        String price = getIntent().getStringExtra("nominal");
        Toast.makeText(this,price,Toast.LENGTH_LONG).show();
        String qty =  getIntent().getStringExtra("qty");
        Toast.makeText(this,qty,Toast.LENGTH_LONG).show();
        String title = getIntent().getStringExtra("name");

        MidtransSDK.getInstance().setTransactionRequest(CustomerModel.transactionRequest(
                id,
                10000,
                1,
                title
        ));

        MidtransSDK.getInstance()
                .startPaymentUiFlow(this);
    }

    @Override
    public void onTransactionFinished(TransactionResult result) {
        if (result.getResponse() != null){
            switch (result.getStatus()){
                case TransactionResult.STATUS_SUCCESS :
                    Toast.makeText(this, "Transaksi Sukses dengan ID : "+result.getResponse().getOrderId(), Toast.LENGTH_SHORT).show();
                    break;
                case TransactionResult.STATUS_PENDING :
                    Toast.makeText(this, "Transaksi Tertunda dengan ID : "+result.getResponse().getTransactionId(), Toast.LENGTH_SHORT).show();
                    break;
                case TransactionResult.STATUS_FAILED :
                    Toast.makeText(this, "Transaksi Gagal dengan ID : "+result.getResponse().getTransactionId(), Toast.LENGTH_SHORT).show();
                    break;

            }
            result.getResponse().getValidationMessages();
        } else if (result.isTransactionCanceled()) {
            Toast.makeText(this, "Transaksi di Batalkan", Toast.LENGTH_SHORT).show();

        } else {
            if (result.getStatus().equalsIgnoreCase(TransactionResult.STATUS_INVALID)) {
                Toast.makeText(this, "Transaksi invalid", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Transaksi finish with fail", Toast.LENGTH_SHORT).show();
            }
        }
    }


    private void initMidtranskSDK(){
        SdkUIFlowBuilder.init()
                .setContext(this)
                .setMerchantBaseUrl(BuildConfig.BASE_URL)
                .setClientKey(BuildConfig.CLIENT_KEY)
                .setTransactionFinishedCallback(this)
                .enableLog(true)
                .setColorTheme(new CustomColorTheme("#174EE6", "#1A45BC", "#2C60EF"))
//                .setUIkitCustomSetting(uisetting)
                .buildSDK();

    }
}