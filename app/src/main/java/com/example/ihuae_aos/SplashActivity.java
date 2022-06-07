package com.example.ihuae_aos;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.animation.AlphaAnimation;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ihuae_aos.Item.MonthVO;
import com.example.ihuae_aos.Util.SharedPreferencesManager;
import com.example.ihuae_aos.databinding.ActivitySplashBinding;

import java.util.Calendar;

public class SplashActivity extends AppCompatActivity {
    private ActivitySplashBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySplashBinding.inflate(LayoutInflater.from(this));
        setContentView(binding.getRoot());

        init();
    }

    private void init(){
        AlphaAnimation anim = new AlphaAnimation(0.2f, 1.0f);
        anim.setDuration(1500);
        anim.setFillAfter(true);
        binding.splashLogo.startAnimation(anim);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                sendNext();
            }
        }, 2000);

    }
    private void sendNext(){
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        getData();
        startActivity(intent);
        finish();
    }

    private void getData(){
        getCal();

        MainActivity.monthItems = SharedPreferencesManager.getMonthItem(this);
        MainActivity.msgItems = SharedPreferencesManager.getMsgItem(this);

        Log.d("##################sharemonth", SharedPreferencesManager.getMonthItem(this).toString());

    }

    private void getCal(){
        if(SharedPreferencesManager.getCal(this, "startCal")!=null){
            MainActivity.startCal = SharedPreferencesManager.getCal(this, "startCal");
        }
        if(SharedPreferencesManager.getCal(this, "endCal")!=null){
            MainActivity.endCal = SharedPreferencesManager.getCal(this, "endCal");
        }
    }


}
