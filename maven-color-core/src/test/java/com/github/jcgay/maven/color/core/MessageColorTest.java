package com.github.jcgay.maven.color.core;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.fusesource.jansi.Ansi;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static com.github.jcgay.maven.color.core.CustomAnsi.ansi;

public class MessageColorTest {

    @Test
    public void should_display_bold_plugin_execution() throws Exception {
        String message = "--- maven-install-plugin ---";
        assertThat(MessageColor.colorize(message))
                .isEqualTo(ansi().bold().a(message).reset().toString());
    }

    @Test
    @UseDataProvider("module_header_messages")
    public void should_display_module_header_in_cyan() throws Exception {
        String message = "Building maven-color-log4j2 0.2-SNAPSHOT";
        assertThat(MessageColor.colorize(message))
                .isEqualTo(ansi().fgBright(Ansi.Color.CYAN).a(message).reset().toString());
    }

    @Test
    public void should_not_colorize_message_containing_building_without_being_a_module_header() throws Exception {
        String message = "Building jar: /Users/jcgay/IdeaProjects/maven-color/maven-color-log4j2/target/maven-color-log4j2-0.2-SNAPSHOT.jar";
        assertThat(MessageColor.colorize(message)).isEqualTo(message);
    }

    @DataProvider
    public static Object[][] module_header_messages() {
        return new Object[][] {
                {"Building maven-color-log4j2 0.2-SNAPSHOT"},
                {"Building maven-color-log4j2 0.2"}
        };
    }
}
