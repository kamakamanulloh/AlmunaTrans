package com.softwarepintar.almuna_trans.midtransmodel;

import com.midtrans.sdk.corekit.core.TransactionRequest;
import com.midtrans.sdk.corekit.models.BankType;
import com.midtrans.sdk.corekit.models.CustomerDetails;
import com.midtrans.sdk.corekit.models.ItemDetails;
import com.midtrans.sdk.corekit.models.snap.Authentication;
import com.midtrans.sdk.corekit.models.snap.CreditCard;

import java.util.ArrayList;

public class CustomerModel {

    public static CustomerDetails customerDetails(){

        CustomerDetails customerDetails=new CustomerDetails();
        customerDetails.setFirstName("kamak");
        customerDetails.setPhone("089682428590");
        customerDetails.setEmail("kamak@gmail.com");

        return customerDetails;

    }

    public static TransactionRequest transactionRequest(String id,int price,int qty,String name){

        TransactionRequest transactionRequest=new TransactionRequest(System.currentTimeMillis()+"",price);
        transactionRequest.setCustomerDetails(customerDetails());
        ItemDetails itemDetails=new ItemDetails(id,price,qty,name);
        ArrayList<ItemDetails> itemDetailsArrayList=new ArrayList<>();
        itemDetailsArrayList.add(itemDetails);
        transactionRequest.setItemDetails(itemDetailsArrayList);
        CreditCard creditCard=new CreditCard();
        creditCard.setSaveCard(false);
        creditCard.setAuthentication(Authentication.AUTH_RBA);

// Set MIGS channel (ONLY for BCA, BRI and Maybank Acquiring bank)

        transactionRequest.setCreditCard(creditCard);
        return transactionRequest;

    }
}
