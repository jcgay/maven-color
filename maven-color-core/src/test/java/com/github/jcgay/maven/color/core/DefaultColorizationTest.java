package com.github.jcgay.maven.color.core;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.github.jcgay.maven.color.core.CustomAnsi.ansi;
import static com.tngtech.java.junit.dataprovider.DataProviders.$;
import static com.tngtech.java.junit.dataprovider.DataProviders.$$;
import static org.assertj.core.api.Assertions.assertThat;
import static org.fusesource.jansi.Ansi.Color.CYAN;
import static org.fusesource.jansi.Ansi.Color.GREEN;
import static org.fusesource.jansi.Ansi.Color.RED;
import static org.fusesource.jansi.Ansi.Color.YELLOW;

@RunWith(DataProviderRunner.class)
public class DefaultColorizationTest {

    private DefaultColorization messageColor = new DefaultColorization();

    @Test
    public void should_display_bold_plugin_execution_with_cyan_module_name() throws Exception {
        String message = "--- maven-surefire-plugin:2.19.1:test (default-test) @ maven-color-log4j2 ---";
        assertThat(messageColor.colorize(message)).isEqualTo(
            ansi()
                .bold()
                .a("--- maven-surefire-plugin:2.19.1:test (default-test) @ ")
                .reset().toString()
            + ansi()
                .fgBright(CYAN)
                .bold()
                .a("maven-color-log4j2")
                .reset().toString()
            + ansi()
                .bold()
                .a(" ---")
                .reset()
                .toString()
        );
    }

    @DataProvider
    public static Object[][] module_header_messages() {
        return new Object[][] {
            {"Building maven-color-log4j2 0.2-SNAPSHOT"},
            {"Building maven-color-log4j2 0.2"}
        };
    }

    @Test
    @UseDataProvider("module_header_messages")
    public void should_display_module_header_in_cyan(String message) throws Exception {
        assertThat(messageColor.colorize(message))
                .isEqualTo(ansi().fgBright(CYAN).a(message).reset().toString());
    }

    @Test
    public void should_not_colorize_message_containing_building_without_being_a_module_header() throws Exception {
        String message = "Building jar: /Users/jcgay/IdeaProjects/maven-color/maven-color-log4j2/target/maven-color-log4j2-0.2-SNAPSHOT.jar";
        assertThat(messageColor.colorize(message)).isEqualTo(message);
    }

    @DataProvider
    public static Object[][] success() {
        return $$($("SUCCESS"), $("BUILD SUCCESS"));
    }

    @Test
    @UseDataProvider("success")
    public void should_display_success_in_green(String message) {
        assertThat(messageColor.colorize(message)).isEqualTo(ansi().fgBright(GREEN).bold().a(message).reset().toString());
    }

    @DataProvider
    public static Object[][] failure() {
        return $$($("FAILURE"), $("BUILD FAILURE"));
    }
    
    @Test
    @UseDataProvider("failure")
    public void should_display_failure_in_red(String message) {
        assertThat(messageColor.colorize(message)).isEqualTo(ansi().fgBright(RED).bold().a(message).reset().toString());
    }

    @Test
    public void should_display_skipped_in_yellow() {
        String message = "SKIPPED";
        assertThat(messageColor.colorize(message)).isEqualTo(ansi().fgBright(YELLOW).bold().a(message).reset().toString());
    }
}
