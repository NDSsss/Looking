package com.example.looking.eventbus.events;

public class ActionEvent extends BaseEvent {

    private EventType mEventType;

    public ActionEvent(EventType eventType, int actionCode, int classUniqueId) {
        super(actionCode, classUniqueId);
        mEventType = eventType;
    }

    @Override
    public EventType getEventType() {
        return mEventType;
    }
}
