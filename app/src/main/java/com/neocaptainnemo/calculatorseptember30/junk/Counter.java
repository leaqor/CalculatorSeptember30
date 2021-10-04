package com.neocaptainnemo.calculatorseptember30.junk;

import java.io.Serializable;

public class Counter implements Serializable {

    private int value;

    public int getValue() {
        return value;
    }

    public void increase() {
        value++;
    }
}
