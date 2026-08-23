package com.gagan.springboot.service;

import org.springframework.stereotype.Service;
import com.gagan.springboot.component.ShortMessageFormatter;
import com.gagan.springboot.component.LongMessageFormatter;

@Service
public class MessageService {

    private final ShortMessageFormatter shortMsg;
    private final LongMessageFormatter longMsg;

    public MessageService(ShortMessageFormatter shortMsg, LongMessageFormatter longMsg) {
        this.shortMsg = shortMsg;
        this.longMsg = longMsg;
    }

    public String getMessage(String type) {
        if(type.equals("SHORT")) {
            return shortMsg.format();
        }
        return longMsg.format();
    }
}