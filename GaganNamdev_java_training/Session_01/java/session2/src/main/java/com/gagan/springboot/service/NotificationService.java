package com.gagan.springboot.service;

import org.springframework.stereotype.Service;
import com.gagan.springboot.component.NotificationComponent;

@Service
public class NotificationService {

    private final NotificationComponent component;

    public NotificationService(NotificationComponent component) {
        this.component = component;
    }

    public String triggerNotification() {
        return component.sendNotification();
    }
}