package com.github.jcgay.maven.color.log4j2;

import com.github.jcgay.maven.color.core.DefaultColorization;
import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.impl.Log4jLogEvent;
import org.apache.logging.log4j.message.SimpleMessage;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(DataProviderRunner.class)
public class MavenMessagePatternConverterTest {

    private MavenMessagePatternConverter converter = MavenMessagePatternConverter.newInstance(null);

    @DataProvider
    public static Object[][] level_no_color() {
        return new Object[][] {
                {Level.DEBUG},
                {Level.TRACE},
                {Level.WARN},
                {Level.FATAL},
                {Level.ERROR}
        };
    }

    @Test
    @UseDataProvider("level_no_color")
    public void should_not_colorize_message_when_level_is_not_debug(Level level) throws Exception {

        StringBuilder result = new StringBuilder();

        converter.format(event(level), result);

        assertThat(result).doesNotContain(colorize(DefaultColorization.Message.BUILD_SUCCESS));
    }

    @Test
    public void should_colorize_message_when_level_is_info() throws Exception {

        StringBuilder result = new StringBuilder();

        converter.format(event(Level.INFO), result);

        assertThat(result.toString()).isEqualTo(colorize(DefaultColorization.Message.BUILD_SUCCESS));
    }

    private static LogEvent event(Level level) {
        SimpleMessage message = new SimpleMessage(DefaultColorization.Message.BUILD_SUCCESS);
        return new Log4jLogEvent("logger.name", null, "", level, message, null);
    }

    private static CharSequence colorize(String message) {
        return new DefaultColorization().colorize(message);
    }
}
