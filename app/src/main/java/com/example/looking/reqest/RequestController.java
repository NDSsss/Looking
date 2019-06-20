package com.example.looking.reqest;

import com.example.looking.eventbus.EventBusContainer;
import com.example.looking.eventbus.IEventBusSubject;

public class RequestController implements IRequestCallBack {

    protected IEventBusSubject mEventBus;
    private int mActionCode;
    protected int mClassUniqueId;

    public RequestController(IEventBusSubject mEventBus, int mActionCode, int mClassUniqueId) {
        this.mEventBus = mEventBus;
        this.mActionCode = mActionCode;
        this.mClassUniqueId = mClassUniqueId;
    }

    @Override
    public void onStartRequest() {
        mEventBus.notifyStartedWithAction(mActionCode, mClassUniqueId);
    }

    @Override
    public void onFinishRequest() {
        mEventBus.notifyFinishWithAction(mActionCode, mClassUniqueId);
    }

    @Override
    public void onErrorRequest(String message) {
        mEventBus.notifyFailed(message, mActionCode, mClassUniqueId);
    }

    @Override
    public void onSuccess(EventBusContainer data) {
        mEventBus.notifySuccess(mActionCode, data, mClassUniqueId);
    }
}
