package com.example.demo.utils;

public final class OsHelper {

    public enum OSType {
        WINDOWS, MACOS, LINUX, OTHER
    }

    public static OSType getOSType() {
        String os = System.getProperty("os.name", "generic").toLowerCase();
        if ((os.contains("mac")) || (os.contains("darwin"))) {
            return OSType.MACOS;
        } else if (os.contains("win")) {
            return OSType.WINDOWS;
        } else if (os.contains("nux")) {
            return OSType.LINUX;
        } else {
            return OSType.OTHER;
        }
    }
}