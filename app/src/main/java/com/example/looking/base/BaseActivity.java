package com.example.looking.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.looking.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseActivity extends AppCompatActivity implements IBaseLoadingActivity {

    public static final String TITLE = "BaseActivity.TITLE";

    @BindView(R.id.tv_activity_base_title)
    TextView tvActivityBaseTitle;
    @BindView(R.id.tb_activity_base)
    Toolbar tbActivityBase;
    @BindView(R.id.app_bar_layout)
    AppBarLayout appBarLayout;
    @BindView(R.id.rl_activity_base_container)
    RelativeLayout rlActivityBaseContainer;
    Unbinder unbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        unbinder = ButterKnife.bind(this);
        setSupportActionBar(tbActivityBase);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        if (getIntent().getStringExtra(TITLE) != null) {
            setTitle(getIntent().getStringExtra(TITLE));
        } else {
            setTitle(R.string.app_name);
        }
        openFragment();
    }

    @Override
    public void setTitle(CharSequence title) {
        tvActivityBaseTitle.setText(title);
    }

    @Override
    public void setTitle(int titleId) {
        tvActivityBaseTitle.setText(titleId);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void openFragment() {
        BaseFragment baseFragment = getFragment();
        if (getIntent().getExtras() != null) {
            baseFragment.setArguments(getIntent().getExtras());
        }
        getSupportFragmentManager().beginTransaction().replace(R.id.rl_activity_base_container, baseFragment).commitAllowingStateLoss();
    }

    protected abstract BaseFragment getFragment();


    @Override
    public void onBackPressed() {
        BaseFragment baseFragment = (BaseFragment) getSupportFragmentManager().findFragmentById(R.id.container);
        if (baseFragment != null) {
            if (!baseFragment.onBackPressed()) {
                super.onBackPressed();
            }
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @Override
    public void startLoading() {

    }

    @Override
    public void completeLoading() {

    }

    @Override
    public void startProgressDialog() {

    }

    @Override
    public void completePorgressDialog() {

    }
}
