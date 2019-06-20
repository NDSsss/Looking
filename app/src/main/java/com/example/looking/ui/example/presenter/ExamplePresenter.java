package com.example.looking.ui.example.presenter;

import com.example.looking.base.BasePresenter;
import com.example.looking.ui.example.IExampleContract;

public class ExamplePresenter extends BasePresenter<IExampleContract.View> implements IExampleContract.Presenter {
    @Override
    public void onCreate() {

    }

    @Override
    public void buttonClicked() {
        getMvpView().initView(getDatamanager().getResIs());
    }
}
