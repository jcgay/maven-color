package com.github.jcgay.maven.color.logback;

import ch.qos.logback.core.PropertyDefinerBase;
import com.github.jcgay.maven.color.env.Environment;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

public class WithJansiResolver extends PropertyDefinerBase {

    private final Environment env;

    public WithJansiResolver() {
        env = new Environment();
    }

    public WithJansiResolver(Environment env) {
        this.env = env;
    }

    @Override
    public String getPropertyValue() {
        if (isWindows()) {
            return hasTerminalWithColors() ? FALSE.toString() : TRUE.toString();
        }
        return FALSE.toString();
    }

    private static boolean isWindows() {
        return System.getProperty("os.name").toLowerCase().contains("win");
    }

    private boolean hasTerminalWithColors() {
        String term = env.getValue("TERM");
        return !"msys".equalsIgnoreCase(term) && term != null;
    }
}
