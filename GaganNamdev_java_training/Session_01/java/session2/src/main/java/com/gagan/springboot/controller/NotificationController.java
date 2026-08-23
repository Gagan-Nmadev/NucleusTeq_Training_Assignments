package com.gagan.springboot.controller;

import org.springframework.web.bind.annotation.*;
import com.gagan.springboot.service.NotificationService;

@RestController
public class NotificationController {

    private final NotificationService service;

    public NotificationController(NotificationService service) {
        this.service = service;
    }

    @GetMapping("/notify")
    public String notifyUser() {
        return service.triggerNotification();
    }
}