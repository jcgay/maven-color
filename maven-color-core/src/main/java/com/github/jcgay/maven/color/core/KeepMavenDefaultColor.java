package com.github.jcgay.maven.color.core;

import com.github.jcgay.maven.color.core.Colorizer;

public class KeepMavenDefaultColor implements Colorizer {

    @Override
    public String colorize(String message) {
        return message;
    }
}
