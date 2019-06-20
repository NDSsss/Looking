package com.example.looking.reqest;

import com.example.looking.eventbus.EventBusContainer;

public interface IRequestCallBack {
    void onStartRequest();

    void onFinishRequest();

    void onErrorRequest(String message);

    void onSuccess(EventBusContainer data);
}
