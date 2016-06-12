package com.github.jcgay.maven.color.core.version;

import com.github.jcgay.maven.color.core.DefaultColorization;
import org.apache.maven.artifact.versioning.ComparableVersion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class CurrentMavenVersion {

    private static final Logger LOGGER = LoggerFactory.getLogger(CurrentMavenVersion.class);

    private final ComparableVersion version;

    CurrentMavenVersion() {
        this("/org/apache/maven/messages/build.properties");
    }

    CurrentMavenVersion(String buildConfigLocation) {
        version = readVersion(buildConfigLocation);
    }

    private ComparableVersion readVersion(String buildConfig) {
        InputStream build = DefaultColorization.class.getResourceAsStream(buildConfig);
        if (build == null) {
            return new ComparableVersion("0");
        }

        Properties props = new Properties();
        try {
            props.load(build);
            return new ComparableVersion(props.getProperty("version", "0"));
        } catch (IOException e) {
            LOGGER.warn("Cannot read current Maven version from: {}.", buildConfig, e);
            return new ComparableVersion("0");
        }
    }

    public static CurrentMavenVersion read() {
        return new CurrentMavenVersion();
    }

    public boolean isGreaterThanOrEqualTo(String version) {
        return this.version.compareTo(new ComparableVersion(version)) >= 0;
    }

    public boolean hasBuiltInColor() {
        return isGreaterThanOrEqualTo("3.5.0-SNAPSHOT");
    }

    ComparableVersion get() {
        return version;
    }
}
