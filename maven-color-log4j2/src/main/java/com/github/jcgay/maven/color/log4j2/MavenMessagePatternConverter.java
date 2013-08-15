package com.github.jcgay.maven.color.log4j2;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.pattern.ConverterKeys;
import org.apache.logging.log4j.core.pattern.LogEventPatternConverter;

import static com.github.jcgay.maven.color.core.MessageColor.colorize;

@Plugin(name = "maven message", category = "Converter")
@ConverterKeys({"mavenMsg"})
public class MavenMessagePatternConverter extends LogEventPatternConverter {

    private MavenMessagePatternConverter() {
        super("MavenMessage", "maven-message");
    }

    public static MavenMessagePatternConverter newInstance(final String[] options) {
        return new MavenMessagePatternConverter();
    }

    @Override
    public void format(LogEvent event, StringBuilder toAppendTo) {
        String message = event.getMessage().getFormattedMessage();
        if (event.getLevel() == Level.INFO) {
            message = colorize(message);
        }
        toAppendTo.append(message);
    }
}
