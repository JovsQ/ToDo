package com.example.mlph_jovel.mytodo.temperature.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.widget.Toast;

public class TempViewModel extends ViewModel {

    public ObservableField<String> input;
    public ObservableBoolean buttonEnabled;
    private Context context;
    private int counter;


    public void init(Context context) {
        counter = 0;
        input = new ObservableField<>();
        buttonEnabled = new ObservableBoolean();
        this.context = context;
    }

    public void buttonClicked() {
        counter++;
        input.set("clicked: " + counter);
    }

}
