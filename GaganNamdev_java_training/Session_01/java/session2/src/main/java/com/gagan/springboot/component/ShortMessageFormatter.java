package com.gagan.springboot.component;

import org.springframework.stereotype.Component;

@Component
public class ShortMessageFormatter {

    public String format() {
        return "Short message";
    }
}