package com.github.jcgay.maven.color.logback;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.pattern.color.ForegroundCompositeConverterBase;

import static ch.qos.logback.core.pattern.color.ANSIConstants.*;

public class MavenHighlightConverter extends ForegroundCompositeConverterBase<ILoggingEvent> {

    @Override
    protected String getForegroundColorCode(ILoggingEvent event) {
        int level = event.getLevel().toInt();
        switch (level) {
            case Level.ERROR_INT: return BOLD+RED_FG;
            case Level.WARN_INT: return YELLOW_FG;
            default: return DEFAULT_FG;
        }
    }
}
