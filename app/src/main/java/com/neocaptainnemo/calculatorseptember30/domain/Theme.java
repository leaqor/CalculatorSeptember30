package com.neocaptainnemo.calculatorseptember30.domain;

import androidx.annotation.DrawableRes;
import androidx.annotation.StringRes;
import androidx.annotation.StyleRes;

import com.neocaptainnemo.calculatorseptember30.R;

public enum Theme {

    THEME_ONE(R.string.theme_one, R.drawable.ic_baseline_add_alarm_24, R.style.Theme_CalculatorSeptember30_Version2, "one"),
    THEME_TWO(R.string.theme_two, R.drawable.ic_baseline_ac_unit_24, R.style.Theme_CalculatorSeptember30_Version3, "two"),
    THEME_THREE(R.string.theme_three, R.drawable.ic_baseline_add_24, R.style.Theme_CalculatorSeptember30_Version4, "three"),
    THEME_FOUR(R.string.theme_four, R.drawable.ic_5g, R.style.Theme_CalculatorSeptember30, "four");

    @StringRes
    private final int title;
    @DrawableRes
    private final int img;
    @StyleRes
    private final int theme;

    private final String key;

    Theme(int title, int img, int theme, String key) {
        this.title = title;
        this.img = img;
        this.theme = theme;
        this.key = key;
    }

    public int getTitle() {
        return title;
    }

    public int getImg() {
        return img;
    }

    public int getTheme() {
        return theme;
    }

    public String getKey() {
        return key;
    }
}
