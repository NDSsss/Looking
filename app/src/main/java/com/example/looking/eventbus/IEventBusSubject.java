package com.example.looking.eventbus;

import com.example.looking.eventbus.events.BaseEvent;

public interface IEventBusSubject {
    void addObserver(IEventBusObserver observer);

    void removeObserver(IEventBusObserver observer);

    void removeAllObservers();

    void notifyStartedWithAction(final int action, final int classUniqueId);

    void notifyFinishWithAction(final int action, final int classUniqueId);

    void notifySuccess(final int actionCode, final EventBusContainer data, final int classUniqueId);

    void notifyFailed(final String errorMessage, final int actionCode, final int classUniqueId);

    void notifyCustomEvent(final BaseEvent event);
}
