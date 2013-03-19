package com.github.jcgay.maven.color.logger;

import com.google.common.annotations.VisibleForTesting;
import org.apache.maven.surefire.report.DefaultDirectConsoleReporter;
import org.fusesource.jansi.AnsiConsole;

import java.io.PrintStream;

/**
 * Colorize and ouput surefire messages (Version post-2.9).
 */
public class AnsiColorConsoleLogger extends DefaultDirectConsoleReporter {

    public AnsiColorConsoleLogger() {
        super(AnsiConsole.out);
    }

    @VisibleForTesting AnsiColorConsoleLogger(PrintStream systemOut) {
        super(systemOut);
    }

    @Override
    public void info(String message) {
        super.info(SurefireColorizer.message(message));
    }
}
