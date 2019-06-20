package com.example.looking.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public interface IBaseMvpPresenter<V extends IBaseMvpView> {
    void setArgs(Object... params);
    void attachView(V view);
    void detachView();
    boolean isViewAttached();
    void onSaveInstanceState(@NonNull Bundle outState);
    void onActivityCreated(@Nullable Bundle savedInstanceState);
    void onCreate();
}
