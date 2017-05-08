package com.github.jcgay.maven.color.core;

import com.github.jcgay.maven.color.core.version.CurrentMavenVersion;
import org.fusesource.jansi.AnsiOutputStream;
import org.slf4j.Logger;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.github.jcgay.maven.color.core.DefaultColorization.Message.BUILD_FAILURE;
import static com.github.jcgay.maven.color.core.DefaultColorization.Message.BUILD_SUCCESS;
import static com.github.jcgay.maven.color.core.DefaultColorization.Message.FAILURE;
import static com.github.jcgay.maven.color.core.DefaultColorization.Message.SKIPPED;
import static com.github.jcgay.maven.color.core.DefaultColorization.Message.SUCCESS;
import static java.lang.Character.isDigit;
import static org.slf4j.LoggerFactory.getLogger;

public class DefaultColorization implements Colorizer {

    private static final Logger LOGGER = getLogger(DefaultColorization.class);

    private static final Pattern PLUGIN_EXECUTION = Pattern.compile("(--- .+ @ )(.+)( ---)");

    private final ConfigurableColor configuration;
    private final boolean stripAnsiCodes;

    static final class Message {
        static final String SUCCESS = "SUCCESS";
        static final String FAILURE = "FAILURE";
        static final String SKIPPED = "SKIPPED";
        static final String BUILD_SUCCESS = "BUILD " + SUCCESS;
        static final String BUILD_FAILURE = "BUILD " + FAILURE;
        static final String BUILDING = "Building ";
        static final String SNAPSHOT = "-SNAPSHOT";
    }

    public DefaultColorization() {
        this(new DefaultColorConfiguration());
    }

    public DefaultColorization(ConfigurableColor configuration) {
        this(configuration, CurrentMavenVersion.read());
    }

    public DefaultColorization(ConfigurableColor configuration, CurrentMavenVersion currentVersion) {
        this.configuration = configuration;
        this.stripAnsiCodes = currentVersion.hasBuiltInColor();
    }

    @Override
    public String colorize(String message) {
        if (message == null || "".equals(message)) {
            return message;
        }

        String cleanMessage = stripAnsiCodes(message);
        if (cleanMessage.contains(BUILD_SUCCESS)) {
            return cleanMessage.replace(BUILD_SUCCESS, configuration.onBuildSuccess().a(BUILD_SUCCESS).reset().toString());
        }
        if (cleanMessage.contains(BUILD_FAILURE)) {
            return cleanMessage.replace(BUILD_FAILURE, configuration.onBuildFailure().a(BUILD_FAILURE).reset().toString());
        }
        if (cleanMessage.contains(SUCCESS)) {
            return cleanMessage.replace(SUCCESS, configuration.onSuccess().a(SUCCESS).reset().toString());
        }
        if (cleanMessage.contains(FAILURE)) {
            return cleanMessage.replace(FAILURE, configuration.onFailure().a(FAILURE).reset().toString());
        }
        if (cleanMessage.contains(SKIPPED)) {
            return cleanMessage.replace(SKIPPED, configuration.onSkipped().a(SKIPPED).reset().toString());
        }
        Matcher matcher = PLUGIN_EXECUTION.matcher(cleanMessage);
        if (matcher.matches()) {
            return configuration.onPluginExecution().a(matcher.group(1)).reset().toString()
                + configuration.onPluginExecutionModuleName().a(matcher.group(2)).reset().toString()
                + configuration.onPluginExecution().a(matcher.group(3)).reset().toString();
        }
        if (isModuleHeader(cleanMessage)) {
            return configuration.onModuleHeader().a(cleanMessage).reset().toString();
        }

        return cleanMessage;
    }

    private String stripAnsiCodes(String message) {
        if (!stripAnsiCodes) {
            return message;
        }

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        AnsiOutputStream writer = new AnsiOutputStream(bos);
        try {
            writer.write(message.getBytes());
            return bos.toString();
        } catch (IOException e) {
            LOGGER.warn("Cannot strip ansi codes from: {}.", message, e);
            return message;
        } finally {
            try {
                bos.close();
            } catch (IOException ignored) {}

            try {
                writer.close();
            } catch (IOException ignored) {}
        }
    }

    private static boolean isModuleHeader(String message) {
        return message.startsWith(Message.BUILDING)
                && (message.endsWith(Message.SNAPSHOT) || isDigit(lastChar(message)));
    }

    private static char lastChar(String message) {
        return message.charAt(message.length() - 1);
    }

}
