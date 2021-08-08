package com.example.demo.utils.impl;

import com.example.demo.utils.EventLogger;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LinuxLogger implements EventLogger {

    private static final String PREFIX = "[LINUX]";

    @Override
    public void log(String str) {
        log.info("{} {}", PREFIX, str);
    }
}
