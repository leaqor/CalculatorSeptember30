package com.neocaptainnemo.calculatorseptember30.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.neocaptainnemo.calculatorseptember30.CalcApp;
import com.neocaptainnemo.calculatorseptember30.R;
import com.neocaptainnemo.calculatorseptember30.domain.CalculatorImp;
import com.neocaptainnemo.calculatorseptember30.domain.Operation;
import com.neocaptainnemo.calculatorseptember30.domain.Theme;
import com.neocaptainnemo.calculatorseptember30.storage.ThemeStorage;

import java.util.HashMap;
import java.util.Map;

public class CalculatorActivity extends AppCompatActivity implements CalculatorView {

    private static final String ARG_THEME = "ARG_THEME";

    private TextView txtResult;

    private CalculatorPresenter presenter;

    private ThemeStorage storage;

    private final ActivityResultLauncher<Intent> settingsLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {

            if (result.getResultCode() == Activity.RESULT_OK) {
                if (result.getData() != null) {
                    Theme theme = (Theme) result.getData().getSerializableExtra(ARG_THEME);

                    storage.setTheme(theme);

                    recreate();
                }
            }

        }
    });

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Context context = this;

        CalcApp calcApp = (CalcApp) getApplicationContext();

        Context applicationContext = getApplicationContext();


        storage = new ThemeStorage(this);

        setTheme(storage.getTheme().getTheme());

//        if (savedInstanceState != null && savedInstanceState.containsKey(ARG_THEME)) {
//            Theme selectedTheme = (Theme) savedInstanceState.getSerializable(ARG_THEME);
//            setTheme(selectedTheme.getTheme());
//        }

        setContentView(R.layout.activity_calculator);

        presenter = new CalculatorPresenter(this, new CalculatorImp());

        txtResult = findViewById(R.id.txt_result);

        Intent launchIntent = getIntent();

        txtResult.setText(launchIntent.getStringExtra("WELCOME"));

        Map<Integer, Integer> digits = new HashMap<>();
        digits.put(R.id.key_0, 0);
        digits.put(R.id.key_1, 1);
        digits.put(R.id.key_2, 2);
        digits.put(R.id.key_3, 3);
        digits.put(R.id.key_4, 4);
        digits.put(R.id.key_5, 5);
        digits.put(R.id.key_6, 6);
        digits.put(R.id.key_7, 7);
        digits.put(R.id.key_8, 8);
        digits.put(R.id.key_9, 9);

        View.OnClickListener digitClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onDigitPressed(digits.get(v.getId()));
            }
        };

        findViewById(R.id.key_1).setOnClickListener(digitClickListener);
        findViewById(R.id.key_2).setOnClickListener(digitClickListener);
        findViewById(R.id.key_3).setOnClickListener(digitClickListener);
        findViewById(R.id.key_4).setOnClickListener(digitClickListener);
        findViewById(R.id.key_5).setOnClickListener(digitClickListener);
        findViewById(R.id.key_6).setOnClickListener(digitClickListener);
        findViewById(R.id.key_7).setOnClickListener(digitClickListener);
        findViewById(R.id.key_8).setOnClickListener(digitClickListener);
        findViewById(R.id.key_9).setOnClickListener(digitClickListener);
        findViewById(R.id.key_0).setOnClickListener(digitClickListener);

        Map<Integer, Operation> operators = new HashMap<>();
        operators.put(R.id.key_plus, Operation.ADD);
        operators.put(R.id.key_minus, Operation.SUB);
        operators.put(R.id.key_mult, Operation.MULT);
        operators.put(R.id.key_div, Operation.DIV);

        View.OnClickListener operationClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onOperationPressed(operators.get(v.getId()));
            }
        };

        findViewById(R.id.key_plus).setOnClickListener(operationClickListener);
        findViewById(R.id.key_minus).setOnClickListener(operationClickListener);
        findViewById(R.id.key_mult).setOnClickListener(operationClickListener);
        findViewById(R.id.key_div).setOnClickListener(operationClickListener);

        findViewById(R.id.key_dot).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onDotPressed();
            }
        });

        Button logBtn = findViewById(R.id.key_log);

        if (logBtn != null) {
            logBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }

        float pxCalcBtnSize = getResources().getDimension(R.dimen.calc_btn_size);
        String key0 = getString(R.string.key_0);

        Button settingsButton = findViewById(R.id.btn_settings);

        if (settingsButton != null) {
            settingsButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(CalculatorActivity.this, SettingsActivity.class);

                    Theme theme = storage.getTheme();
                    intent.putExtra(SettingsActivity.ARG_THEME, theme);

//                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);

                    settingsLauncher.launch(intent);
//                    startActivity(intent);
                }
            });
        }
    }

    @Override
    public void showResult(String result) {
        txtResult.setText(result);
    }

//    @Override
//    protected void onSaveInstanceState(@NonNull Bundle outState) {
//        if (selectedTheme != null) {
//            outState.putSerializable(ARG_THEME, selectedTheme);
//        }
//        super.onSaveInstanceState(outState);
//    }
}
