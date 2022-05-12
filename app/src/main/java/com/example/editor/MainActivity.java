package com.example.editor;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import hotchemi.android.rate.AppRate;

import com.example.editor.databinding.ActivityMainBinding;
import com.github.dhaval2404.imagepicker.ImagePicker;

public class MainActivity extends AppCompatActivity {
ActivityMainBinding binding;
public static Uri imgUri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Here 0 means
        // the installation date.
        AppRate.with(this)

                // default 10
                .setInstallDays(1)

                // default 10
                .setLaunchTimes(3)

                // default 1
                .setRemindInterval(1)
                .monitor();

        // Show a dialogue
        // if meets conditions
        AppRate
                .showRateDialogIfMeetsConditions(
                        this);

        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImagePicker.Companion.with(MainActivity.this)
                        .crop()
                        .start();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            imgUri=data.getData();
            if(!imgUri.equals(""))
            startActivity(new Intent(MainActivity.this, FinalActivity.class));
        }
        catch (Exception e){

        }
    }
}