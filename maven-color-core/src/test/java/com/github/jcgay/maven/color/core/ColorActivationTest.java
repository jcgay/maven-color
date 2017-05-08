package com.github.jcgay.maven.color.core;

import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.ClearSystemProperties;
import org.junit.contrib.java.lang.system.EnvironmentVariables;

import static org.assertj.core.api.Assertions.assertThat;

public class ColorActivationTest {

    @Rule
    public ClearSystemProperties mavenColor = new ClearSystemProperties("maven.color");

    @Rule
    public final EnvironmentVariables env = new EnvironmentVariables();

    @Test
    public void should_be_activated_when_sys_property_maven_color_is_true() {
        System.setProperty("maven.color", "true");

        assertThat(ColorActivation.isActivated()).isTrue();
    }

    @Test
    public void should_not_be_activated_when_sys_property_maven_color_is_false() {
        System.setProperty("maven.color", "false");

        assertThat(ColorActivation.isActivated()).isFalse();
    }

    @Test
    public void should_not_be_activated_when_sys_property_maven_color_is_unparsable() {
        System.setProperty("maven.color", "WHATT!?");

        assertThat(ColorActivation.isActivated()).isFalse();
    }

    @Test
    public void should_be_activated_when_terminal_is_not_dumb() {
        env.set("TERM", "xterm");
        env.set("MAVEN_CMD_LINE_ARGS", "verify"); // Need to override MAVEN_CMD when build is launched from a console

        assertThat(ColorActivation.isActivated()).isTrue();
    }

    @Test
    public void should_not_be_activated_when_terminal_is_dumb() {
        env.set("TERM", "dumb");

        assertThat(ColorActivation.isActivated()).isFalse();
    }

    @Test
    public void should_not_be_activated_when_maven_is_launched_in_batch_mode() {
        env.set("MAVEN_CMD_LINE_ARGS", "-B verify");

        assertThat(ColorActivation.isActivated()).isFalse();
    }

    @Test
    public void should_not_be_activated_when_maven_is_launched_in_batch_mode_long_opt() {
        env.set("MAVEN_CMD_LINE_ARGS", "--batch-mode verify");

        assertThat(ColorActivation.isActivated()).isFalse();
    }

    @Test
    public void should_not_be_activated_when_maven_log_in_a_file() {
        env.set("MAVEN_CMD_LINE_ARGS", "-l test.log verify");

        assertThat(ColorActivation.isActivated()).isFalse();
    }

    @Test
    public void should_not_be_activated_when_maven_log_in_a_file_and_equal_delimiter() {
        env.set("MAVEN_CMD_LINE_ARGS", "test -l=test.log");

        assertThat(ColorActivation.isActivated()).isFalse();
    }

    @Test
    public void should_not_be_activated_when_maven_log_in_a_file_long_opt() {
        env.set("MAVEN_CMD_LINE_ARGS", "--log-file test.log verify");

        assertThat(ColorActivation.isActivated()).isFalse();
    }

    @Test
    public void should_not_be_activated_when_maven_log_in_a_file_long_opt_and_equal_delimiter() {
        env.set("MAVEN_CMD_LINE_ARGS", "test --log-file=test.log");

        assertThat(ColorActivation.isActivated()).isFalse();
    }

    @Test
    public void should_be_activated_when_a_maven_build_is_launched() {
        env.set("MAVEN_CMD_LINE_ARGS", "clean verify");

        assertThat(ColorActivation.isActivated()).isTrue();
    }

    @Test
    public void should_be_activated_when_arguments_could_have_been_misinterpreted_as_log_file_option() {
        env.set("MAVEN_CMD_LINE_ARGS", "dependency:tree -Dincludes=\"commons-logging\"");

        assertThat(ColorActivation.isActivated()).isTrue();
    }

    @Test
    public void return_maven_color_property_first() {
        System.setProperty("maven.color", "true");
        env.set("TERM", "dumb");
        env.set("MAVEN_CMD_LINE_ARGS", "test -B");

        assertThat(ColorActivation.isActivated()).isTrue();
    }
}
