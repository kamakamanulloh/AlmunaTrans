package com.softwarepintar.almuna_trans.ui.TiketSaya;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class TiketSayaViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public TiketSayaViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is dashboard fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}