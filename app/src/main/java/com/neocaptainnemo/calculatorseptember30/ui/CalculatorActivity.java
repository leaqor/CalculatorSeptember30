package com.neocaptainnemo.calculatorseptember30.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.neocaptainnemo.calculatorseptember30.R;
import com.neocaptainnemo.calculatorseptember30.domain.CalculatorImp;
import com.neocaptainnemo.calculatorseptember30.domain.Operation;
import com.neocaptainnemo.calculatorseptember30.domain.Theme;
import com.neocaptainnemo.calculatorseptember30.storage.ThemeStorage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CalculatorActivity extends AppCompatActivity implements CalculatorView {

    private static final String ARG_THEME = "ARG_THEME";

    private TextView txtResult;

    private LinearLayout container;

    private CalculatorPresenter presenter;

    private ThemeStorage storage;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        storage = new ThemeStorage(this);

        setTheme(storage.getTheme().getTheme());

//        if (savedInstanceState != null && savedInstanceState.containsKey(ARG_THEME)) {
//            Theme selectedTheme = (Theme) savedInstanceState.getSerializable(ARG_THEME);
//            setTheme(selectedTheme.getTheme());
//        }

        setContentView(R.layout.activity_calculator);

        presenter = new CalculatorPresenter(this, new CalculatorImp());

        txtResult = findViewById(R.id.txt_result);

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

        container = findViewById(R.id.theme_container);

        if (container == null) {
            return;
        }

        float pxCalcBtnSize = getResources().getDimension(R.dimen.calc_btn_size);
        String key0 = getString(R.string.key_0);

        for (Theme theme: Theme.values()) {
            View itemView = getLayoutInflater().inflate(R.layout.item_theme, container, false);

            ImageView img = itemView.findViewById(R.id.img);
            TextView txt = itemView.findViewById(R.id.txt);

            img.setImageResource(theme.getImg());

            String txtValue = getString(theme.getTitle());
            txt.setText(txtValue);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    storage.setTheme(theme);

                    recreate();
                }
            });

            container.addView(itemView);
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
