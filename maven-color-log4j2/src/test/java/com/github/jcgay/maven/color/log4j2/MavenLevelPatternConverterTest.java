package com.github.jcgay.maven.color.log4j2;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.impl.Log4jLogEvent;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.ClearSystemProperties;

import static org.apache.logging.log4j.Level.INFO;
import static org.assertj.core.api.Assertions.assertThat;

public class MavenLevelPatternConverterTest {

    @Rule
    public ClearSystemProperties myPropertyIsCleared = new ClearSystemProperties("maven.color.hide.level");

    private MavenLevelPatternConverter converter = MavenLevelPatternConverter.newInstance(null);

    @Test
    public void hide_level_by_default() {
        StringBuilder result = new StringBuilder();

        converter.format(event(INFO), result);

        assertThat(result).isEmpty();
    }

    @Test
    public void show_level_when_property_equals_false() {
        System.setProperty("maven.color.hide.level", "false");
        StringBuilder result = new StringBuilder();

        converter.format(event(INFO), result);

        assertThat(result.toString()).isEqualTo("[INFO] ");
    }

    @Test
    public void hide_level_when_property_equals_true() {
        System.setProperty("maven.color.hide.level", "true");
        StringBuilder result = new StringBuilder();

        converter.format(event(INFO), result);

        assertThat(result).isEmpty();
    }

    private static LogEvent event(Level level) {
        return new Log4jLogEvent("logger.name", null, "", level, null, null);
    }
}
