package com.github.jcgay.maven.color.core;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.github.jcgay.maven.color.core.DefaultColorization.Message.BUILD_FAILURE;
import static com.github.jcgay.maven.color.core.DefaultColorization.Message.BUILD_SUCCESS;
import static com.github.jcgay.maven.color.core.DefaultColorization.Message.FAILURE;
import static com.github.jcgay.maven.color.core.DefaultColorization.Message.SKIPPED;
import static com.github.jcgay.maven.color.core.DefaultColorization.Message.SUCCESS;
import static java.lang.Character.isDigit;

public class DefaultColorization implements Colorizer {

    private static final Pattern PLUGIN_EXECUTION = Pattern.compile("(--- .+ @ )(.+)( ---)");

    private final ConfigurableColor configuration;

    public interface Message {
        String SUCCESS = "SUCCESS";
        String FAILURE = "FAILURE";
        String SKIPPED = "SKIPPED";
        String BUILD_SUCCESS = "BUILD " + SUCCESS;
        String BUILD_FAILURE = "BUILD " + FAILURE;
        String BUILDING = "Building ";
        String SNAPSHOT = "-SNAPSHOT";
    }

    public DefaultColorization() {
        this(new DefaultColorConfiguration());
    }

    public DefaultColorization(ConfigurableColor configuration) {
        this.configuration = configuration;
    }

    @Override
    public String colorize(String message) {
        if (message == null || "".equals(message)) {
            return message;
        }
        if (message.contains(BUILD_SUCCESS)) {
            return message.replace(BUILD_SUCCESS, configuration.onBuildSuccess().a(BUILD_SUCCESS).reset().toString());
        }
        if (message.contains(BUILD_FAILURE)) {
            return message.replace(BUILD_FAILURE, configuration.onBuildFailure().a(BUILD_FAILURE).reset().toString());
        }
        if (message.contains(SUCCESS)) {
            return message.replace(SUCCESS, configuration.onSuccess().a(SUCCESS).reset().toString());
        }
        if (message.contains(FAILURE)) {
            return message.replace(FAILURE, configuration.onFailure().a(FAILURE).reset().toString());
        }
        if (message.contains(SKIPPED)) {
            return message.replace(SKIPPED, configuration.onSkipped().a(SKIPPED).reset().toString());
        }
        Matcher matcher = PLUGIN_EXECUTION.matcher(message);
        if (matcher.matches()) {
            return configuration.onPluginExecution().a(matcher.group(1)).reset().toString()
                + configuration.onPluginExecutionModuleName().a(matcher.group(2)).reset().toString()
                + configuration.onPluginExecution().a(matcher.group(3)).reset().toString();
        }
        if (isModuleHeader(message)) {
            return configuration.onModuleHeader().a(message).reset().toString();
        }
        return message;
    }

    private static boolean isModuleHeader(String message) {
        return message.startsWith(Message.BUILDING)
                && (message.endsWith(Message.SNAPSHOT) || isDigit(lastChar(message)));
    }

    private static char lastChar(String message) {
        return message.charAt(message.length() - 1);
    }

}
