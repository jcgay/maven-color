package com.github.jcgay.maven.color.logback;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.classic.spi.LoggingEvent;
import org.junit.Test;

import static com.github.jcgay.maven.color.core.MessageColor.Message;
import static com.github.jcgay.maven.color.core.MessageColor.colorize;
import static org.fest.assertions.Assertions.assertThat;

public class MavenMessageConverterTest {

    private MavenMessageConverter converter = new MavenMessageConverter();

    @Test
    public void should_not_colorize_message_when_level_is_not_debug() throws Exception {
        assertThat(converter.convert(event(Level.ERROR))).doesNotContain(colorize(Message.BUILD_SUCCESS));
        assertThat(converter.convert(event(Level.DEBUG))).doesNotContain(colorize(Message.BUILD_SUCCESS));
        assertThat(converter.convert(event(Level.WARN))).doesNotContain(colorize(Message.BUILD_SUCCESS));
        assertThat(converter.convert(event(Level.TRACE))).doesNotContain(colorize(Message.BUILD_SUCCESS));
    }

    @Test
    public void should_colorize_message_when_level_is_info() throws Exception {
        assertThat(converter.convert(event(Level.INFO))).isEqualTo(colorize(Message.BUILD_SUCCESS));
    }

    private ILoggingEvent event(Level level) {
        LoggingEvent event = new LoggingEvent();
        event.setLevel(level);
        event.setMessage(Message.BUILD_SUCCESS);
        return event;
    }
}
