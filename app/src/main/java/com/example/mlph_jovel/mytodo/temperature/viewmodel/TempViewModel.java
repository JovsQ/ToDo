package com.example.mlph_jovel.mytodo.temperature.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;
import android.widget.Toast;

public class TempViewModel extends AndroidViewModel{

    public ObservableField<String> input;
    public ObservableField<String> result;
    public ObservableBoolean buttonEnabled;

    public Application application;

    public TempViewModel(@NonNull Application application) {
        super(application);
        this.application = application;
        input = new ObservableField<>();
        result = new ObservableField<>();
        buttonEnabled = new ObservableBoolean();
        buttonEnabled.set(false);
    }

    public void inputChanged() {
        buttonEnabled.set(input.get().length() > 4);
    }

    public void buttonClicked() {
        Toast.makeText(application, "Clicked: " + input.get(), Toast.LENGTH_LONG).show();
        result.set(input.get());
    }
}
