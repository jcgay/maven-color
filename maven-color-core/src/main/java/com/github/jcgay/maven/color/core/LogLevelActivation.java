package com.github.jcgay.maven.color.core;

public class LogLevelActivation {

    public static boolean isShown() {
        String property = System.getProperty("maven.color.hide.level");
        return "false".equalsIgnoreCase(property);
    }
}
