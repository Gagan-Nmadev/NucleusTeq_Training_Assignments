package com.gagan.springboot.component;

import org.springframework.stereotype.Component;

@Component
public class NotificationComponent {

    public String sendNotification() {
        return "Notification sent";
    }
}