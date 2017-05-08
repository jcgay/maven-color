package com.github.jcgay.maven.color.core;

import java.util.LinkedHashSet;
import java.util.Set;

import static java.util.Arrays.asList;
import static java.util.Collections.emptySet;

public class ColorActivation {

    private ColorActivation() {
        throw new IllegalStateException("I'm hidden!");
    }

    public static boolean isActivated() {
        String color = System.getProperty("maven.color");
        if (color != null) {
            return Boolean.valueOf(color);
        }

        Set<String> args = split(System.getenv("MAVEN_CMD_LINE_ARGS"));
        return !isBatchMode(args) && !isLogFile(args) && !isDumbTerminal();
    }

    private static Set<String> split(String arguments) {
        if (arguments == null || arguments.isEmpty()) {
            return emptySet();
        }

        return new LinkedHashSet<String>(asList(arguments.split(" |=")));
    }

    private static boolean isDumbTerminal() {
        return "dumb".equalsIgnoreCase(System.getenv("TERM"));
    }

    /* This is possible because Maven launcher exposes arguments in an environment variable
    * See mvn.sh or mvn.cmd section:
    *     # Provide a "standardized" way to retrieve the CLI args that will
    *     # work with both Windows and non-Windows executions.
    *     MAVEN_CMD_LINE_ARGS="$MAVEN_CONFIG $@"
    *     export MAVEN_CMD_LINE_ARGS
    */
    private static boolean isBatchMode(Set<String> args) {
        return args.contains("-B") || args.contains("--batch-mode");
    }

    private static boolean isLogFile(Set<String> args) {
        return args.contains("-l") || args.contains("--log-file");
    }
}
