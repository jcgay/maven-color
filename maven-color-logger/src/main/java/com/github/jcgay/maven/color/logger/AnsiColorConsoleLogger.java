package com.github.jcgay.maven.color.logger;

import com.google.common.annotations.VisibleForTesting;
import org.apache.maven.surefire.report.DefaultDirectConsoleReporter;
import org.fusesource.jansi.Ansi;
import org.fusesource.jansi.AnsiConsole;

import java.io.PrintStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.fusesource.jansi.Ansi.Color;
import static org.fusesource.jansi.Ansi.ansi;

/**
 * Modify Test summary display by colorizing test count (Error/Failure : RED, Skipped : YELLOW, Total : BOLD). <br />
 */
public class AnsiColorConsoleLogger extends DefaultDirectConsoleReporter {

    static interface Message {
        String RESULT = "Results :";
        String FAILED_TEST = "Failed tests: ";
        String ERROR_TEST = "Tests in error: ";
    }

    private static final Pattern TEST_SUMMARY = Pattern.compile(
            "(Tests run: )(\\d*)" +
            "(, Failures: )(\\d*)" +
            "(, Errors: )(\\d*)" +
            "(, Skipped: )(\\d*)"
    );

    public AnsiColorConsoleLogger() {
        super(AnsiConsole.out);
    }

    @VisibleForTesting AnsiColorConsoleLogger(PrintStream systemOut) {
        super(systemOut);
    }

    @Override
    public void info(String message) {
        super.info(colorizeMessage(message));
    }

    private String colorizeMessage(String message) {
        if (message.contains(Message.RESULT)) {
            return message.replace(Message.RESULT, ansi().bold().a(Message.RESULT).reset().toString());
        }
        if (message.contains(Message.FAILED_TEST)) {
            return message.replace(Message.FAILED_TEST, ansi().fgBright(Color.RED).bold().a(Message.FAILED_TEST).reset().toString());
        }
        if (message.contains(Message.ERROR_TEST)) {
            return message.replace(Message.ERROR_TEST, ansi().fgBright(Color.RED).bold().a(Message.ERROR_TEST).reset().toString());
        }
        Matcher matcher = TEST_SUMMARY.matcher(message);
        if (matcher.matches()) {
            StringBuilder builder = new StringBuilder()
                    .append(matcher.group(1))
                    .append(totalCount(matcher))
                    .append(matcher.group(3))
                    .append(failureCount(matcher))
                    .append(matcher.group(5))
                    .append(errorCount(matcher))
                    .append(matcher.group(7))
                    .append(skippedCount(matcher));

            return builder.toString();
        }
        return message;
    }

    private Ansi skippedCount(Matcher matcher) {
        String count = matcher.group(8);

        Ansi result = ansi().bold();
        if (Integer.valueOf(count) > 0) {
            result.fgBright(Color.YELLOW);
        }
        return result.a(count).reset();
    }

    private Ansi errorCount(Matcher matcher) {
        String count = matcher.group(6);

        Ansi result = ansi().bold();
        if (Integer.valueOf(count) > 0) {
            result.fgBright(Color.RED);
        }
        return result.a(count).reset();
    }

    private Ansi totalCount(Matcher matcher) {
        return ansi().bold().a(matcher.group(2)).reset();
    }

    private Ansi failureCount(Matcher matcher) {
        String count = matcher.group(4);

        Ansi result = ansi().bold();
        if (Integer.valueOf(count) > 0) {
            result.fgBright(Color.RED);
        }
        return result.a(count).reset();
    }
}
