package com.github.jcgay.maven.color.core;

public class LogLevelActivation {

    private LogLevelActivation() {
        throw new IllegalStateException("I'm hidden!");
    }

    public static boolean isShown() {
        String property = System.getProperty("maven.color.hide.level");
        return "false".equalsIgnoreCase(property);
    }
}
