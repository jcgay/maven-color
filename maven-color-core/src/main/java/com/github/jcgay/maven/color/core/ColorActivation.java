package com.github.jcgay.maven.color.core;

public class ColorActivation {

    public static boolean isActivated() {
        String color = System.getProperty("maven.color");
        if (color != null) {
            return Boolean.valueOf(color);
        }

        return !isBatchMode() && !isLogFile() && !isDumbTerminal();
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
    private static boolean isBatchMode() {
        String args = System.getenv("MAVEN_CMD_LINE_ARGS");
        return args != null && (args.contains("-B") || args.contains("--batch-mode"));
    }

    private static boolean isLogFile() {
        String args = System.getenv("MAVEN_CMD_LINE_ARGS");
        return args != null && (args.contains("-l") || args.contains("--log-file"));
    }
}
