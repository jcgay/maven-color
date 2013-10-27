package com.github.jcgay.maven.color.logger;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static com.github.jcgay.maven.color.logger.SurefireColorizer.Message;
import static org.assertj.core.api.Assertions.assertThat;
import static org.fusesource.jansi.Ansi.Color;
import static com.github.jcgay.maven.color.core.CustomAnsi.ansi;

public class AnsiColorConsoleLoggerTest {

    private AnsiColorConsoleLogger logger;
    private ByteArrayOutputStream result;

    @Before
    public void init() {
        result = new ByteArrayOutputStream();
        logger = new AnsiColorConsoleLogger(new PrintStream(result));
    }

    @Test
    public void should_log_surefire_summary_test_result_title_in_bold() throws Exception {

        logger.info(Message.RESULT);

        assertThat(result.toString()).contains(ansi().bold().a(Message.RESULT).reset().toString());
    }

    @Test
    public void should_log_surefire_failed_test_in_red() throws Exception {

        logger.info(Message.FAILED_TEST);

        assertThat(result.toString()).contains(ansi().fgBright(Color.RED).bold().a(Message.FAILED_TEST).reset().toString());
    }

    @Test
    public void should_log_surefire_error_test_in_red() throws Exception {

        logger.info(Message.ERROR_TEST);

        assertThat(result.toString()).contains(ansi().fgBright(Color.RED).bold().a(Message.ERROR_TEST).reset().toString());
    }

    @Test
    public void should_colorize_surefire_test_summary() throws Exception {

        logger.info("Tests run: " + 10 + ", Failures: " + 0 + ", Errors: " + 1 + ", Skipped: " + 2);

        assertThat(result.toString()).contains(
                "Tests run: " + ansi().bold().a(10).reset() +
                ", Failures: " + ansi().bold().a(0).reset() +
                ", Errors: " + ansi().bold().fgBright(Color.RED).a(1).reset() +
                ", Skipped: " + ansi().bold().fgBright(Color.YELLOW).a(2).reset()
        );
    }
}
