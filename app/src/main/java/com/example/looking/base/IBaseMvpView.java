package com.example.looking.base;

public interface IBaseMvpView {
    void startLoading();
    void completeLoading();
    void errorLoadin(String error);
    void startProgressDialog();
    void completePorgressDialog();
    void errorLoadingDialog(String error);
    int getClassUniqueDeviceId();
}
