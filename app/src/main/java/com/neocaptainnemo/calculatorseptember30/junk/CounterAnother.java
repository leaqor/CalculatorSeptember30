package com.neocaptainnemo.calculatorseptember30.junk;

import android.os.Parcel;
import android.os.Parcelable;

public class CounterAnother implements Parcelable {

    private int value;

    public CounterAnother() {

    }

    protected CounterAnother(Parcel in) {
        value = in.readInt();
    }

    public static final Creator<CounterAnother> CREATOR = new Creator<CounterAnother>() {
        @Override
        public CounterAnother createFromParcel(Parcel in) {
            return new CounterAnother(in);
        }

        @Override
        public CounterAnother[] newArray(int size) {
            return new CounterAnother[size];
        }
    };

    public int getValue() {
        return value;
    }

    public void increase() {
        value++;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(value);
    }
}
