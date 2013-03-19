package com.github.jcgay.maven.color.logger;

import com.google.common.annotations.VisibleForTesting;
import org.apache.maven.surefire.booter.output.StandardOutputConsumer;
import org.fusesource.jansi.AnsiConsole;

import java.io.OutputStream;
import java.io.PrintWriter;

/**
 * Colorize and ouput surefire messages (Version pre-2.9).
 */
public class AnsiOutputConsumer extends StandardOutputConsumer {

    public AnsiOutputConsumer() {
        setPrintWriter(new PrintWriter(AnsiConsole.out));
    }

    @VisibleForTesting
    AnsiOutputConsumer(OutputStream output) {
        setPrintWriter(new PrintWriter(output));
    }

    @Override
    public void consumeFooterLine(String line) {
        super.consumeFooterLine(SurefireColorizer.message(line));
    }
}
