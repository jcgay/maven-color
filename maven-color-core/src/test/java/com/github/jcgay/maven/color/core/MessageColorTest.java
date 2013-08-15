package com.github.jcgay.maven.color.core;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.fusesource.jansi.Ansi.ansi;

public class MessageColorTest {

    @Test
    public void should_display_bold_plugin_execution() throws Exception {
        assertThat(MessageColor.colorize("--- maven-install-plugin ---"))
                .isEqualTo(ansi().bold().a("--- maven-install-plugin ---").reset().toString());
    }
}
