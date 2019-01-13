package com.github.jcgay.maven.color.core.version;

import org.slf4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.slf4j.LoggerFactory.getLogger;

public class Version {

    private static final String UNKNOWN_VERSION = "unknown-version";
    private static final Logger LOGGER = getLogger(Version.class);

    private final String source;

    Version(String classpathResource) {
        this.source = classpathResource;
    }

    public static Version current() {
        return new Version("/version");
    }

    public String get() {
        InputStream resource = Version.class.getResourceAsStream(source);
        if (resource == null) {
            return UNKNOWN_VERSION;
        }
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(resource, UTF_8))) {
            String version = reader.readLine();
            return version != null ? version : UNKNOWN_VERSION;
        } catch (IOException e) {
            LOGGER.warn("Error while trying to read current maven-color version.", e);
            return UNKNOWN_VERSION;
        }
    }
}
