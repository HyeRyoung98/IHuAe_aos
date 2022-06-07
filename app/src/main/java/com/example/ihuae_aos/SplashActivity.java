package com.example.ihuae_aos;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.animation.AlphaAnimation;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ihuae_aos.databinding.ActivitySplashBinding;

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
        //anim.setStartOffset(5000);
        anim.setFillAfter(true);
        binding.splashLogo.startAnimation(anim);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                sendNext();
            }
        }, 3000);

    }

    private void getData(){

    }

    private void sendNext(){
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
        finish();
    }
}
