package com.example.looking.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.looking.eventbus.EventBusController;
import com.example.looking.eventbus.IEventBusObserver;
import com.example.looking.eventbus.events.ActionEvent;
import com.example.looking.eventbus.events.BaseEvent;
import com.example.looking.eventbus.events.FailEvent;
import com.example.looking.eventbus.events.SuccesEvent;
import com.example.looking.managers.DataManager;
import com.example.looking.reqest.RequestController;

public abstract class BasePresenter<T extends IBaseMvpView> implements IBaseMvpPresenter<T>, IEventBusObserver {
    protected String TAG = getClass().getSimpleName();
    private T mMvpView;
    private DataManager mDatamanger;
    private EventBusController mEventBusController;

    protected T getMvpView() {
        return mMvpView;
    }

    @Override
    public void setArgs(Object... params) {

    }

    @Override
    public void attachView(T view) {
        mMvpView = view;
    }

    @Override
    public void detachView() {
        mMvpView = null;
    }

    @Override
    public boolean isViewAttached() {
        return mMvpView != null;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {

    }

    protected DataManager getDatamanager() {
        if (mDatamanger == null) {
            mDatamanger = App.getInstance().getApplicationComponent().datamanager();
        }
        return mDatamanger;
    }

    protected EventBusController getEventBusController() {
        if (mEventBusController == null) {
            mEventBusController = App.getInstance().getApplicationComponent().eventBusController();
        }
        return mEventBusController;
    }

    @Override
    public void onActionEvent(ActionEvent event) {

    }

    @Override
    public void onSuccessEvent(SuccesEvent succesEvent) {

    }

    @Override
    public void onFailError(FailEvent failEvent) {

    }

    @Override
    public void onCustomEvent(BaseEvent event) {

    }

    protected RequestController getRequestController(int action) {
        return new RequestController(getEventBusController(), action, getMvpView().getClassUniqueDeviceId());
    }
}
