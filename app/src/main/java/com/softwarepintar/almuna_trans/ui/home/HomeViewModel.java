package com.softwarepintar.almuna_trans.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.Calendar;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    String salam;

    public HomeViewModel() {
        Calendar calendar=Calendar.getInstance();
        int time=calendar.get(Calendar.HOUR_OF_DAY);
        if (time >= 0 && time < 12) {

            salam = "Selamat Pagi \n Siap Melayani Anda Setulus Hati";

        } else if (time >= 12 && time < 15) {

            salam = "Selamat Siang";

        } else if (time >= 15 && time < 19) {

            salam = "Selamat Sore \n Siap Melayani Anda Setulus Hati";


        } else if (time >= 19 && time <= 23) {

            salam = "Selamat Malam \n Siap Melayani Anda Setulus Hati";

        }
        mText = new MutableLiveData<>();
        mText.setValue(salam);

    }

    public LiveData<String> getText() {
        return mText;
    }
}