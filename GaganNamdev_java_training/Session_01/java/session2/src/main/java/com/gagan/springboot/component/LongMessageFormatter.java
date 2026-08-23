package com.gagan.springboot.component;

import org.springframework.stereotype.Component;

@Component
public class LongMessageFormatter {

    public String format() {
        return "This is a long message format";
    }
}