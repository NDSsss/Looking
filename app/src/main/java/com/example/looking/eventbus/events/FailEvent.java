package com.example.looking.eventbus.events;

public class FailEvent extends BaseEvent{

    private String mMessage;

    public FailEvent(int actionCode, int classUniqueId, String message) {
        super(actionCode, classUniqueId);
        this.mMessage = message;
    }

    @Override
    public EventType getEventType() {
        return EventType.FAIL_REQUEST;
    }
}
