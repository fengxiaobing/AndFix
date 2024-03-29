package com.esite.myvideo;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.esite.myvideo.andfix.AndFixPathManager;
import com.esite.myvideo.andfix.AndFixService;

import java.io.File;

public class MainActivity extends AppCompatActivity {

  private static final String FILD_END = ".apatch";
  private String mPatchDir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        11);

                startPatchService();

            }else {
                startPatchService();
            }
        }


    }

    private void startPatchService() {
        Intent intent = new Intent(MainActivity.this, AndFixService.class);
        startService(intent);

    }


    public void makeBug(View view) {
//        Log.e("",null);
        Toast.makeText(this, "修复成功", Toast.LENGTH_SHORT).show();
    }

    public void fixBug(View view) {
    }

}
