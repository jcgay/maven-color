package com.github.jcgay.maven.color.core;

import org.fusesource.jansi.Ansi;

import static java.lang.Character.isDigit;
import static org.fusesource.jansi.Ansi.Color.*;
import static org.fusesource.jansi.Ansi.ansi;

public final class MessageColor {

    private MessageColor() {
        // prevent instantiation.
    }

    public static interface Message {
        String SUCCESS = "SUCCESS";
        String FAILURE = "FAILURE";
        String SKIPPED = "SKIPPED";
        String BUILD_SUCCESS = "BUILD " + SUCCESS;
        String BUILD_FAILURE = "BUILD " + FAILURE;
        String BUILDING = "Building ";
        String SNAPSHOT = "-SNAPSHOT";
    }

    public static String colorize(String message) {
        if (message == null || "".equals(message)) {
            return message;
        }
        if (message.contains(Message.BUILD_SUCCESS)) {
            return statusMessage(message, Message.BUILD_SUCCESS, GREEN);
        }
        if (message.contains(Message.BUILD_FAILURE)) {
            return statusMessage(message, Message.BUILD_FAILURE, RED);
        }
        if (message.contains(Message.SUCCESS)) {
            return statusMessage(message, Message.SUCCESS, GREEN);
        }
        if (message.contains(Message.FAILURE)) {
            return statusMessage(message, Message.FAILURE, RED);
        }
        if (message.contains(Message.SKIPPED)) {
            return statusMessage(message, Message.SKIPPED, YELLOW);
        }
        if (isPluginExecution(message)) {
            return ansi().bold().a(message).reset().toString();
        }
        if (isModuleHeader(message)) {
            return ansi().fgBright(CYAN).a(message).reset().toString();
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

    private static boolean isPluginExecution(String message) {
        return message.startsWith("--- ") && message.endsWith(" ---");
    }

    private static String statusMessage(String message, String toReplace, Ansi.Color color) {
        return message.replace(toReplace, ansi().fgBright(color).bold().a(toReplace).reset().toString());
    }
}
