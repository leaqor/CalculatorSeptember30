package com.neocaptainnemo.calculatorseptember30.junk;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;
import com.neocaptainnemo.calculatorseptember30.R;

public class SettingsActivity extends AppCompatActivity {

    private static final String ARG_COUNT = "ARG_COUNT";
    private static final String ARG_COUNT_2 = "ARG_COUNT_2";
    private static final String ARG_COUNT_3 = "ARG_COUNT_3";

    private int count = 0;

    private Counter counter;
    private CounterAnother counterAnother;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_settings);

        TextInputLayout textInputLayout = findViewById(R.id.text_input_layout);

        textInputLayout.setError("Error");

        if (savedInstanceState == null) {
            logLifecycle("onCreate for the first time");
            counter = new Counter();
            counterAnother = new CounterAnother();
        } else {

            count = savedInstanceState.getInt(ARG_COUNT);
            counter = (Counter) savedInstanceState.getSerializable(ARG_COUNT_2);
            counterAnother = savedInstanceState.getParcelable(ARG_COUNT_3);

            logLifecycle("onCreate for the next times");
        }

        TextView textCounter = findViewById(R.id.txt_counter);
        textCounter.setText(String.valueOf(count));

        TextView textCounter2 = findViewById(R.id.txt_counter_2);
        textCounter2.setText(String.valueOf(counter.getValue()));

        TextView textCounter3 = findViewById(R.id.txt_counter_3);
        textCounter3.setText(String.valueOf(counterAnother.getValue()));

        View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;
                textCounter.setText(String.valueOf(count));
            }
        };

        ClickHandler clickHandler = new ClickHandler();
        findViewById(R.id.btn_increase_count).setOnClickListener(clickListener);

        findViewById(R.id.btn_increase_count_2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counter.increase();
                textCounter2.setText(String.valueOf(counter.getValue()));
            }
        });

        findViewById(R.id.btn_increase_count_3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counterAnother.increase();
                textCounter3.setText(String.valueOf(counterAnother.getValue()));
            }
        });

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

        outState.putInt(ARG_COUNT, count);
        outState.putSerializable(ARG_COUNT_2, counter);
        outState.putParcelable(ARG_COUNT_3, counterAnother);

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

    static class ClickHandler implements View.OnClickListener {

        @Override
        public void onClick(View v) {



        }
    }

}
