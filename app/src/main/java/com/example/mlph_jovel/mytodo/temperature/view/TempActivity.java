package com.example.mlph_jovel.mytodo.temperature.view;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.mlph_jovel.mytodo.R;
import com.example.mlph_jovel.mytodo.databinding.ActivityTempBinding;
import com.example.mlph_jovel.mytodo.temperature.viewmodel.TempViewModel;

public class TempActivity extends AppCompatActivity {

    private TempViewModel tempViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_temp);

        initDataBinding();
    }

    private void initDataBinding() {
        ActivityTempBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_temp);
        tempViewModel = ViewModelProviders.of(this).get(TempViewModel.class);
        tempViewModel.init(this);
        binding.setTemp(tempViewModel);
    }

}
