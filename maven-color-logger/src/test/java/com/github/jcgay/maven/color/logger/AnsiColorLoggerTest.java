package com.github.jcgay.maven.color.logger;

import org.codehaus.plexus.logging.Logger;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static com.github.jcgay.maven.color.logger.AnsiColorLogger.Message;
import static org.fest.assertions.api.Assertions.assertThat;
import static org.fusesource.jansi.Ansi.Color;
import static org.fusesource.jansi.Ansi.ansi;

/**
 * User: jcgay
 */
public class AnsiColorLoggerTest {

    private AnsiColorLogger logger;
    private ByteArrayOutputStream result;

    @Before
    public void init() {
        result = new ByteArrayOutputStream();
        logger = new AnsiColorLogger(new PrintStream(result));
    }

    @Test
    public void should_log_a_warning_message_in_yellow() throws Exception {

        logger.warn("text in yellow");

        assertThat(result.toString()).isEqualTo("\u001B[93m[WARNING] text in yellow\u001B[m\n");
    }

    @Test
    public void should_log_an_exception_with_level_warning() throws Exception {

        logger.warn("message", new NullPointerException("exception"));

        assertThat(result.toString()).contains("exception");
    }

    @Test
    public void should_not_log_if_level_warn_is_not_enabled() throws Exception {

        logger.setThreshold(Logger.LEVEL_DISABLED);
        logger.warn("message");

        assertThat(result.toString()).doesNotContain("message");
    }

    @Test
    public void should_log_an_error_message_in_red() throws Exception {

        logger.error("text in red");

        assertThat(result.toString()).isEqualTo("\u001B[91m[ERROR] text in red\u001B[m\n");
    }

    @Test
    public void should_log_an_exception_with_level_error() throws Exception {

        logger.error("message", new NullPointerException("exception"));

        assertThat(result.toString()).contains("exception");
    }

    @Test
    public void should_not_log_if_level_error_is_not_enabled() throws Exception {

        logger.setThreshold(Logger.LEVEL_DISABLED);
        logger.error("message");

        assertThat(result.toString()).doesNotContain("message");
    }

    @Test
    public void should_not_modify_message_when_logging_in_debug() throws Exception {

        logger.debug("message");

        assertThat(result.toString()).isEqualTo("[DEBUG] message\n");
    }

    @Test
    public void should_log_an_exception_with_level_debug() throws Exception {

        logger.debug("message", new NullPointerException("exception"));

        assertThat(result.toString()).contains("exception");
    }

    @Test
    public void should_not_log_if_level_debug_is_not_enabled() throws Exception {

        logger.setThreshold(Logger.LEVEL_DISABLED);
        logger.debug("message");

        assertThat(result.toString()).doesNotContain("message");
    }

    @Test
    public void should_not_modify_message_when_logging_in_info() throws Exception {

        logger.info("message");

        assertThat(result.toString()).isEqualTo("[INFO] message\n");
    }

    @Test
    public void should_log_an_exception_with_level_info() throws Exception {

        logger.info("message", new NullPointerException("exception"));

        assertThat(result.toString()).contains("exception");
    }

    @Test
    public void should_not_log_if_level_info_is_not_enabled() throws Exception {

        logger.setThreshold(Logger.LEVEL_DISABLED);
        logger.info("message");

        assertThat(result.toString()).doesNotContain("message");
    }

    @Test
    public void should_log_a_fatal_message_in_red() throws Exception {

        logger.fatalError("text in red");

        assertThat(result.toString()).isEqualTo("\u001B[91;1m[FATAL] text in red\u001B[m\n");
    }

    @Test
    public void should_log_an_exception_with_level_fatal() throws Exception {

        logger.fatalError("message", new NullPointerException("exception"));

        assertThat(result.toString()).contains("exception");
    }

    @Test
    public void should_not_log_if_level_fatal_is_not_enabled() throws Exception {

        logger.setThreshold(Logger.LEVEL_DISABLED);
        logger.fatalError("message");

        assertThat(result.toString()).doesNotContain("message");
    }

    @Test
    public void should_log_build_status_success_in_green() throws Exception {

        logger.info(Message.BUILD_SUCCESS);

        assertThat(result.toString()).contains(ansi().fgBright(Color.GREEN).bold().a(Message.BUILD_SUCCESS).reset().toString());
    }

    @Test
    public void should_log_build_status_failure_in_red() throws Exception {

        logger.info(Message.BUILD_FAILURE);

        assertThat(result.toString()).contains(ansi().fgBright(Color.RED).bold().a(Message.BUILD_FAILURE).reset().toString());
    }

    @Test
    public void should_log_reactor_summary_success_in_green() throws Exception {

        logger.info(Message.SUCCESS);

        assertThat(result.toString()).contains(ansi().fgBright(Color.GREEN).bold().a(Message.SUCCESS).reset().toString());
    }

    @Test
    public void should_log_reactor_summary_failure_in_green() throws Exception {

        logger.info(Message.FAILURE);

        assertThat(result.toString()).contains(ansi().fgBright(Color.RED).bold().a(Message.FAILURE).reset().toString());
    }

    @Test
    public void should_throw_npe_when_message_to_log_is_null() throws Exception {

        logger.info(null);

        assertThat(result.toString()).isNotEmpty();
    }
}