package com.example.looking.eventbus;

import com.example.looking.eventbus.events.ActionEvent;
import com.example.looking.eventbus.events.BaseEvent;
import com.example.looking.eventbus.events.EventType;
import com.example.looking.eventbus.events.FailEvent;
import com.example.looking.eventbus.events.SuccesEvent;

import java.util.ArrayList;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class EventBusController implements IEventBusSubject {
    private ArrayList<IEventBusObserver> mObservers;

    @Inject
    public EventBusController() {
        mObservers = new ArrayList<>();
    }

    @Override
    public void addObserver(IEventBusObserver observer) {
        mObservers.add(observer);
    }

    @Override
    public void removeObserver(IEventBusObserver observer) {
        mObservers.remove(observer);
    }

    @Override
    public void removeAllObservers() {
        mObservers.clear();
    }

    @Override
    public void notifyStartedWithAction(int action, int classUniqueId) {
        ActionEvent actionEvent = new ActionEvent(EventType.START_REQUEST, action, classUniqueId);
        for (IEventBusObserver observer : mObservers) {
            observer.onActionEvent(actionEvent);
        }
    }

    @Override
    public void notifyFinishWithAction(int action, int classUniqueId) {
        ActionEvent actionEvent = new ActionEvent(EventType.FINISH_REQUEST, action, classUniqueId);
        for (IEventBusObserver observer : mObservers) {
            observer.onActionEvent(actionEvent);
        }
    }

    @Override
    public void notifySuccess(int actionCode, EventBusContainer data, int classUniqueId) {
        SuccesEvent succesEvent = new SuccesEvent(actionCode, classUniqueId, data);
        for (IEventBusObserver observer : mObservers) {
            observer.onSuccessEvent(succesEvent);
        }
    }

    @Override
    public void notifyFailed(String errorMessage, int actionCode, int classUniqueId) {
        FailEvent failEvent = new FailEvent(actionCode, classUniqueId, errorMessage);
        for (IEventBusObserver observer : mObservers) {
            observer.onFailError(failEvent);
        }
    }

    @Override
    public void notifyCustomEvent(BaseEvent event) {
        for (IEventBusObserver observer : mObservers) {
            observer.onCustomEvent(event);
        }
    }
}
