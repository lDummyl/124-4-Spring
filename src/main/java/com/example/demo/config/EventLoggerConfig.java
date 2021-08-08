package com.example.demo.config;

import com.example.demo.utils.EventLogger;
import com.example.demo.utils.OsHelper;
import com.example.demo.utils.impl.MacOSLogger;
import com.example.demo.utils.impl.OtherLogger;
import com.example.demo.utils.impl.LinuxLogger;
import com.example.demo.utils.impl.WindowsLogger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EventLoggerConfig {
    @Bean
    public EventLogger createEventLogger() {
        OsHelper.OSType osType = OsHelper.getOSType();
        switch (osType) {
            case LINUX:
                return new LinuxLogger();
            case WINDOWS:
                return new WindowsLogger();
            case MACOS:
                return new MacOSLogger();
            default:
                return new OtherLogger();
        }
    }
}
