package com.example.looking.eventbus.events;

public abstract class BaseEvent {
    protected int mActionCode;
    protected int mClassUniqueId;

    public BaseEvent(int actionCode, int classUniqueId){
        this.mActionCode = actionCode;
        this.mClassUniqueId = classUniqueId;
    }

    public int getmActionCode() {
        return mActionCode;
    }

    public int getmClassUniqueId() {
        return mClassUniqueId;
    }

    public abstract EventType getEventType();
}
