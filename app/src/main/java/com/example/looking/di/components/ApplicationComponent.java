package com.example.looking.di.components;

import com.example.looking.eventbus.EventBusController;
import com.example.looking.managers.DataManager;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component
public interface ApplicationComponent {
    DataManager datamanager();

    EventBusController eventBusController();
}
