package com.github.jcgay.maven.color.logback;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.pattern.MessageConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;

import static com.github.jcgay.maven.color.core.MessageColor.colorize;

public class MavenMessageConverter extends MessageConverter {

    @Override
    public String convert(ILoggingEvent event) {
        if (event.getLevel().toInt() == Level.INFO_INT) {
            return colorize(super.convert(event));
        }
        return super.convert(event);
    }
}
