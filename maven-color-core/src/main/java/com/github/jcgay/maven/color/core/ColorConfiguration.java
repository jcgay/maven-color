package com.github.jcgay.maven.color.core;

import groovy.lang.GroovyClassLoader;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;

public class ColorConfiguration {

    private static final File MVN_USER_HOME = new File(System.getProperty("user.home") + "/.m2");

    private ColorConfiguration() {
    }

    public static Colorizer read() {
        return read(MVN_USER_HOME, new DefaultColorization());
    }

    public static Colorizer read(Colorizer defaultColorizer) {
        return read(MVN_USER_HOME, defaultColorizer);
    }

    public static Colorizer read(File root, Colorizer defaultColorizer) {
        File[] configurations = root.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File directory, String name) {
                return "maven.color".equals(name);
            }
        });

        if (configurations != null && configurations.length >= 1) {
            GroovyClassLoader gcl = new GroovyClassLoader();
            try {
                Object customColorization = gcl.parseClass(configurations[0]).newInstance();
                if (customColorization instanceof Colorizer) {
                    return (Colorizer) customColorization;
                }
                throw new RuntimeException("maven.color must extends com.github.jcgay.maven.color.core.CustomColorization");
            } catch (IOException e) {
                throw new RuntimeException("Error while reading: " + configurations[0], e);
            } catch (InstantiationException e) {
                throw new RuntimeException("Cannot instantiate custom colorization from: " + configurations[0], e);
            } catch (IllegalAccessException e) {
                throw new RuntimeException("Cannot access custom colorization from: " + configurations[0], e);
            }
        }

        return defaultColorizer;
    }
}
