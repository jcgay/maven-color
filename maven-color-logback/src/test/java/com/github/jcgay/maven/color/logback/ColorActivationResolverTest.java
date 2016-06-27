package com.github.jcgay.maven.color.logback;

import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.ClearSystemProperties;
import org.junit.contrib.java.lang.system.EnvironmentVariables;

import static org.assertj.core.api.Assertions.assertThat;

public class ColorActivationResolverTest {

    @Rule
    public ClearSystemProperties myPropertyIsCleared = new ClearSystemProperties("maven.color");

    @Rule
    public EnvironmentVariables env = new EnvironmentVariables();

    private ColorActivationResolver mc = new ColorActivationResolver();

    @Test
    public void return_true_when_property_maven_color_is_set() {
        System.setProperty("maven.color", "true");

        assertThat(mc.getPropertyValue()).isEqualTo("true");
    }

    @Test
    public void return_false_when_property_maven_color_is_set() {
        System.setProperty("maven.color", "false");

        assertThat(mc.getPropertyValue()).isEqualTo("false");
    }

    @Test
    public void return_false_when_env_term_is_dumb() {
        env.set("TERM", "dumb");
        env.set("MAVEN_CMD_LINE_ARGS", "");

        assertThat(mc.getPropertyValue()).isEqualTo("false");
    }

    @Test
    public void return_maven_color_property_first() {
        System.setProperty("maven.color", "true");
        env.set("TERM", "dumb");
        env.set("MAVEN_CMD_LINE_ARGS", "test -B -l test.log");

        assertThat(mc.getPropertyValue()).isEqualTo("true");
    }

    @Test
    public void return_true_by_default() {
        env.set("MAVEN_CMD_LINE_ARGS", "");
        env.set("TERM", "xterm");

        assertThat(mc.getPropertyValue()).isEqualTo("true");
    }

    @Test
    public void return_false_when_batch_mode_is_activated_with_short_opt() {
        env.set("MAVEN_CMD_LINE_ARGS", "test -B");
        env.set("TERM", "xterm");

        assertThat(mc.getPropertyValue()).isEqualTo("false");
    }

    @Test
    public void return_false_when_batch_mode_is_activated_with_long_opt() {
        env.set("MAVEN_CMD_LINE_ARGS", "test --batch-mode");
        env.set("TERM", "xterm");

        assertThat(mc.getPropertyValue()).isEqualTo("false");
    }

    @Test
    public void return_false_when_log_file_is_activated_with_short_opt() {
        env.set("MAVEN_CMD_LINE_ARGS", "test -l test.log");
        env.set("TERM", "xterm");

        assertThat(mc.getPropertyValue()).isEqualTo("false");
    }

    @Test
    public void return_false_when_log_file_is_activated_with_long_opt() {
        env.set("MAVEN_CMD_LINE_ARGS", "test --log-file test.log");
        env.set("TERM", "xterm");

        assertThat(mc.getPropertyValue()).isEqualTo("false");
    }
}
