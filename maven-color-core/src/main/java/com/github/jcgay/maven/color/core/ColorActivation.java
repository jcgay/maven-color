package com.github.jcgay.maven.color.core;

public class ColorActivation {

    public static boolean isActivated() {
        String color = System.getProperty("maven.color");
        if (color != null) {
            return Boolean.valueOf(color);
        }

        return !"dumb".equalsIgnoreCase(System.getenv("TERM"));
    }
}
