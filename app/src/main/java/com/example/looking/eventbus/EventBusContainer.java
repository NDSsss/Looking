package com.example.looking.eventbus;

public class EventBusContainer<T> {
    private T mValue;

    public EventBusContainer(T value) {
        this.mValue = value;
    }

    public T getValue() {
        return mValue;
    }

    public void setmValue(T newValue) {
        this.mValue = newValue;
    }
}
