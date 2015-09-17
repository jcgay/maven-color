package com.github.jcgay.maven.color.logback;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.pattern.MessageConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;
import com.github.jcgay.maven.color.core.ColorConfiguration;
import com.github.jcgay.maven.color.core.Colorizer;

public class MavenMessageConverter extends MessageConverter {

    private final Colorizer colorization;

    public MavenMessageConverter() {
        colorization = ColorConfiguration.read();
    }

    @Override
    public String convert(ILoggingEvent event) {
        if (event.getLevel().toInt() == Level.INFO_INT) {
            return colorization.colorize(super.convert(event));
        }
        return super.convert(event);
    }
}
