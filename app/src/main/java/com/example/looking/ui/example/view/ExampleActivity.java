package com.example.looking.ui.example.view;

import com.example.looking.base.BaseActivity;
import com.example.looking.base.BaseFragment;

public class ExampleActivity extends BaseActivity {
    @Override
    protected BaseFragment getFragment() {
        return new ExampleFragment();
    }
}
