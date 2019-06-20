package com.example.looking.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment<P extends IBaseMvpPresenter> extends Fragment implements IBaseMvpView {
    protected String TAG = getClass().getSimpleName();

    private Unbinder mUnbinder;
    public P mPresenter;
    private BaseActivity mBaseActivity;
    private IBaseLoadingActivity mLoadingActivity;

    protected P getPresenter() {
        if (mPresenter == null) {
            mPresenter = getNewPresenter();
        }
        if (!mPresenter.isViewAttached()) {
            mPresenter.attachView(this);
        }
        return mPresenter;
    }

    protected abstract P getNewPresenter();

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mBaseActivity = (BaseActivity) context;
        mLoadingActivity = (IBaseLoadingActivity) context;
    }

    public BaseActivity getBaseActivity() {
        return mBaseActivity;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayout(), container, false);
        mUnbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        getPresenter().onSaveInstanceState(outState);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getPresenter().onActivityCreated(savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if(mPresenter != null){
            mPresenter.detachView();
        }
        mLoadingActivity = null;
    }

    protected abstract int getLayout();



    public boolean onBackPressed() {
        return false;
    }

    @Override
    public void startLoading() {

    }

    @Override
    public void completeLoading() {

    }

    @Override
    public void errorLoadin(String error) {

    }

    @Override
    public void startProgressDialog() {

    }

    @Override
    public void completePorgressDialog() {

    }

    @Override
    public void errorLoadingDialog(String error) {

    }

    @Override
    public int getClassUniqueDeviceId() {
        return System.identityHashCode(this);
    }
}
