package com.example.demo.utils.impl;

import com.example.demo.utils.EventLogger;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OtherLogger implements EventLogger {

    private static final String PREFIX = "[OTHER]";

    @Override
    public void log(String str) {
        log.info("{} {}", PREFIX, str);
    }
}
