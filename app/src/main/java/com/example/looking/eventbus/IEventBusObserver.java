package com.example.looking.eventbus;

import com.example.looking.eventbus.events.ActionEvent;
import com.example.looking.eventbus.events.BaseEvent;
import com.example.looking.eventbus.events.FailEvent;
import com.example.looking.eventbus.events.SuccesEvent;

public interface IEventBusObserver {
    void onActionEvent(ActionEvent event);

    void onSuccessEvent(SuccesEvent succesEvent);

    void onFailError(FailEvent failEvent);

    void onCustomEvent(BaseEvent event);
}
