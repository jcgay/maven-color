package com.github.jcgay.maven.color.logger;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;

import static org.assertj.core.api.Assertions.assertThat;
import static com.github.jcgay.maven.color.core.CustomAnsi.ansi;

public class AnsiOutputConsumerTest {

    private AnsiOutputConsumer logger;
    private ByteArrayOutputStream result;

    @Before
    public void init() {
        result = new ByteArrayOutputStream();
        logger = new AnsiOutputConsumer(result);
    }

    @Test
    public void should_log_surefire_summary_test_result_title_in_bold() throws Exception {

        logger.consumeFooterLine(SurefireColorizer.Message.RESULT);

        assertThat(result.toString()).contains(ansi().bold().a(SurefireColorizer.Message.RESULT).reset().toString());
    }
}
