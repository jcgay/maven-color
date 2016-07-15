package com.github.jcgay.maven.color.log4j2;

import com.github.jcgay.maven.color.core.LogLevelActivation;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.pattern.ConverterKeys;
import org.apache.logging.log4j.core.pattern.LogEventPatternConverter;

import java.util.EnumMap;

@Plugin(name = "MavenLevelPatternConverter", category = "Converter")
@ConverterKeys({"mavenLevel" })
public class MavenLevelPatternConverter extends LogEventPatternConverter {

    private static final MavenLevelPatternConverter INSTANCE = new MavenLevelPatternConverter(null);

    private final EnumMap<Level, String> levelMap;

    private MavenLevelPatternConverter(final EnumMap<Level, String> map) {
        super("Level", "level");
        this.levelMap = map;
    }

    /**
     * Obtains an instance of pattern converter.
     *
     * @param options options, may be null. May contain a list of level names and
     * The value that should be displayed for the Level.
     * @return instance of pattern converter.
     */
    public static MavenLevelPatternConverter newInstance(final String[] options) {
        if (options == null || options.length == 0) {
            return INSTANCE;
        }
        final EnumMap<Level, String> levelMap = new EnumMap<Level, String>(Level.class);
        final String[] definitions = options[0].split(",");
        for (final String def : definitions) {
            final String[] pair = def.split("=");
            if (pair.length != 2) {
                LOGGER.error("Invalid option {}", def);
                continue;
            }
            final Level level = Level.toLevel(pair[0].trim(), null);
            if (level == null) {
                LOGGER.error("Invalid Level {}", pair[0].trim());
            } else {
                levelMap.put(level, pair[1].trim());
            }
        }
        if (levelMap.size() == 0) {
            return INSTANCE;
        }
        for (final Level level : Level.values()) {
            if (!levelMap.containsKey(level)) {
                levelMap.put(level, level.toString());
            }
        }
        return new MavenLevelPatternConverter(levelMap);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void format(final LogEvent event, final StringBuilder output) {
        if (LogLevelActivation.isShown()) {
            output.append('[')
                .append(levelMap == null ? event.getLevel().toString() : levelMap.get(event.getLevel()))
                .append("] ");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getStyleClass(final Object e) {
        if (e instanceof LogEvent) {
            final Level level = ((LogEvent) e).getLevel();

            switch (level) {
                case TRACE:
                    return "level trace";

                case DEBUG:
                    return "level debug";

                case INFO:
                    return "level info";

                case WARN:
                    return "level warn";

                case ERROR:
                    return "level error";

                case FATAL:
                    return "level fatal";

                default:
                    return "level " + ((LogEvent) e).getLevel().toString();
            }
        }

        return "level";
    }
}
