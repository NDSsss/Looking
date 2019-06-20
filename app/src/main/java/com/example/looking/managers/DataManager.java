package com.example.looking.managers;

import com.example.looking.R;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class DataManager {

    @Inject
    public DataManager() {

    }

    public int getResIs() {
        return R.string.some_long_text;
    }
}
