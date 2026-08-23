package com.gagan.springboot.controller;

import org.springframework.web.bind.annotation.*;
import com.gagan.springboot.service.MessageService;

@RestController
public class MessageController {

    private final MessageService service;

    public MessageController(MessageService service) {
        this.service = service;
    }

    @GetMapping("/message")
    public String message(@RequestParam String type) {
        return service.getMessage(type);
    }
}
