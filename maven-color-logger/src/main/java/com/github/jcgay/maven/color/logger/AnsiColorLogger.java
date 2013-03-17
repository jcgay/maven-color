package com.github.jcgay.maven.color.logger;

import com.google.common.annotations.VisibleForTesting;
import org.apache.maven.Maven;
import org.codehaus.plexus.logging.AbstractLogger;
import org.codehaus.plexus.logging.Logger;
import org.fusesource.jansi.Ansi;
import org.fusesource.jansi.AnsiConsole;

import java.io.PrintStream;

import static org.fusesource.jansi.Ansi.Color.*;
import static org.fusesource.jansi.Ansi.ansi;

/**
 * Add color based on message level:
 * <ul>
 *     <li>Fatal : RED</li>
 *     <li>Error : RED</li>
 *     <li>Warning : YELLOW</li>
 *     <li>Info : untouched</li>
 *     <li>Debug : untouched</li>
 * </ul>
 * Colorize some specific messages:
 * <ul>
 *     <li>(BUILD) SUCCESS : GREEN</li>
 *     <li>(BUILD) FAILURE : RED</li>
 * </ul>
 */
public class AnsiColorLogger extends AbstractLogger {

    static interface Message {
        String SUCCESS = "SUCCESS";
        String FAILURE = "FAILURE";
        String BUILD_SUCCESS = "BUILD " + SUCCESS;
        String BUILD_FAILURE = "BUILD " + FAILURE;
    }

    private PrintStream out;

    private static final String WARNING = "[WARNING] ";
    private static final String ERROR = "[ERROR] ";
    private static final String INFO = "[INFO] ";
    private static final String DEBUG = "[DEBUG] ";
    private static final String FATAL = "[FATAL] ";

    public AnsiColorLogger() {
        super(LEVEL_INFO, Maven.class.getName());
        out = AnsiConsole.out;
    }

    @VisibleForTesting AnsiColorLogger(PrintStream out) {
        super(LEVEL_DEBUG, Maven.class.getName());
        if (out == null) {
            throw new IllegalArgumentException("A PrintStream is mandatory.");
        }
        this.out = out;
    }

    public void debug(String message, Throwable throwable) {
        if (isDebugEnabled()) {
            out.print(DEBUG);
            out.println(message);
            printStackTrace(throwable);
        }
    }

    public void info(String message, Throwable throwable) {
        if (isInfoEnabled()) {
            out.print(INFO);
            out.println(colorizeMessage(message));
            printStackTrace(throwable);
        }
    }

    public void warn(String message, Throwable throwable) {
        if (isWarnEnabled()) {
            out.println(ansi().fgBright(YELLOW).a(WARNING).a(message).reset());
            printStackTrace(throwable);
        }
    }

    public void error(String message, Throwable throwable) {
        if (isErrorEnabled()) {
            out.println(ansi().fgBright(RED).a(ERROR).a(message).reset());
            printStackTrace(throwable);
        }
    }

    public void fatalError(String message, Throwable throwable) {
        if (isFatalErrorEnabled()) {
            out.println(ansi().fgBright(RED).bold().a(FATAL).a(message).reset());
            printStackTrace(throwable);
        }
    }

    public Logger getChildLogger(String name) {
        return this;
    }

    private void printStackTrace(Throwable throwable) {
        if (throwable != null) {
            throwable.printStackTrace(out);
        }
    }

    private String colorizeMessage(String message) {
        if (message == null || message.isEmpty()) {
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
        return message;
    }

    private String statusMessage(String message, String toReplace, Ansi.Color color) {
        return message.replace(toReplace, ansi().fgBright(color).bold().a(toReplace).reset().toString());
    }
}
