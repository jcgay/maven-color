package com.github.jcgay.maven.color.logback;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.classic.spi.LoggingEvent;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.ClearSystemProperties;

import static ch.qos.logback.classic.Level.INFO;
import static org.assertj.core.api.Assertions.assertThat;

public class MavenLevelConverterTest {

    @Rule
    public ClearSystemProperties myPropertyIsCleared = new ClearSystemProperties("maven.color.hide.level");

    private MavenLevelConverter level = new MavenLevelConverter();

    @Test
    public void hide_level_by_default() {
        String result = level.convert(event(INFO));

        assertThat(result).isEmpty();
    }

    @Test
    public void show_level_when_property_equals_false() {
        System.setProperty("maven.color.hide.level", "false");

        String result = level.convert(event(INFO));

        assertThat(result).isEqualTo("[INFO] ");
    }

    @Test
    public void hide_level_when_property_equals_true() {
        System.setProperty("maven.color.hide.level", "true");

        String result = level.convert(event(INFO));

        assertThat(result).isEmpty();
    }

    private ILoggingEvent event(Level level) {
        LoggingEvent event = new LoggingEvent();
        event.setLevel(level);
        return event;
    }
}
