package com.example.looking.ui.example;

import com.example.looking.base.IBaseMvpPresenter;
import com.example.looking.base.IBaseMvpView;

public interface IExampleContract {
    interface View extends IBaseMvpView {
        void initView(int stringId);
    }

    interface Presenter extends IBaseMvpPresenter<View> {
        void buttonClicked();
    }
}
