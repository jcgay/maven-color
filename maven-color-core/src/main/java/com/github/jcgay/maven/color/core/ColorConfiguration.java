package com.github.jcgay.maven.color.core;

import com.github.jcgay.maven.color.core.version.CurrentMavenVersion;
import groovy.lang.GroovyClassLoader;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;

public class ColorConfiguration {

    private static final File MVN_USER_HOME = new File(System.getProperty("user.home") + "/.m2");

    private ColorConfiguration() {
        throw new IllegalStateException("I'm hidden!");
    }

    public static Colorizer read() {
        return read(MVN_USER_HOME, CurrentMavenVersion.read());
    }

    public static Colorizer read(File root, CurrentMavenVersion currentMavenVersion) {
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
                throw new MavenColorException("maven.color must extends com.github.jcgay.maven.color.core.CustomColorization");
            } catch (IOException e) {
                throw new MavenColorException("Error while reading: " + configurations[0], e);
            } catch (InstantiationException e) {
                throw new MavenColorException("Cannot instantiate custom colorization from: " + configurations[0], e);
            } catch (IllegalAccessException e) {
                throw new MavenColorException("Cannot access custom colorization from: " + configurations[0], e);
            }
        }

        return currentMavenVersion.hasBuiltInColor() ? new KeepMavenDefaultColor() : new DefaultColorization();
    }
}
