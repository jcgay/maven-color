package com.github.jcgay.maven.color.core;

public class KeepMavenDefaultColor implements Colorizer {

    @Override
    public String colorize(String message) {
        return message;
    }
}
