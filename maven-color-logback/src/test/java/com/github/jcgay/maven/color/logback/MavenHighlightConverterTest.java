package com.github.jcgay.maven.color.logback;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.classic.spi.LoggingEvent;
import org.junit.Test;

import static ch.qos.logback.core.pattern.color.ANSIConstants.*;
import static org.assertj.core.api.Assertions.assertThat;

public class MavenHighlightConverterTest {

    private MavenHighlightConverter converter = new MavenHighlightConverter();

    @Test
    public void should_return_bold_red_for_error_level() throws Exception {
        assertThat(converter.getForegroundColorCode(event(Level.ERROR))).isEqualTo(BOLD + RED_FG);
    }

    @Test
    public void should_return_yellow_for_warn_level() throws Exception {
        assertThat(converter.getForegroundColorCode(event(Level.WARN))).isEqualTo(YELLOW_FG);
    }

    @Test
    public void should_return_default_for_other_level() throws Exception {
        assertThat(converter.getForegroundColorCode(event(Level.TRACE))).isEqualTo(DEFAULT_FG);
        assertThat(converter.getForegroundColorCode(event(Level.DEBUG))).isEqualTo(DEFAULT_FG);
        assertThat(converter.getForegroundColorCode(event(Level.INFO))).isEqualTo(DEFAULT_FG);
    }

    private static ILoggingEvent event(Level level) {
        LoggingEvent loggingEvent = new LoggingEvent();
        loggingEvent.setLevel(level);
        return loggingEvent;
    }
}
