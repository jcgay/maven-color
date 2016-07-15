package com.github.jcgay.maven.color.logback;

import ch.qos.logback.classic.pattern.LevelConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;
import com.github.jcgay.maven.color.core.LogLevelActivation;

public class MavenLevelConverter extends LevelConverter {

    @Override
    public String convert(ILoggingEvent le) {
        if (LogLevelActivation.isShown()) {
            return "[" + super.convert(le) + "] ";
        }

        return "";
    }
}
