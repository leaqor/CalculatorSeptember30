package com.neocaptainnemo.calculatorseptember30.junk;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.neocaptainnemo.calculatorseptember30.R;

public class MainActivity extends AppCompatActivity {

    private int someVariable = 100500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_settings).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, JunkActivity.class);
                startActivity(intent);
            }
        });

        if (savedInstanceState == null) {
            logLifecycle("onCreate for the first time");

        } else {
            logLifecycle("onCreate for the next times");
        }
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        logLifecycle("onRestoreInstanceState");

        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    protected void onStart() {
        super.onStart();

        logLifecycle("onStart");

    }

    @Override
    protected void onResume() {
        super.onResume();

        logLifecycle("onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();

        logLifecycle("onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();

        logLifecycle("onStop");
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        logLifecycle("onSaveInstanceState");

        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        logLifecycle("onDestroy");
    }

    private void logLifecycle(String method) {
        Log.d("LifecycleMain", method + " " + this);
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(this, "Back presed", Toast.LENGTH_SHORT).show();
        finish();
    }
}