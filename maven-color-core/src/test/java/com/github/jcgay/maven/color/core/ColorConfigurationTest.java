package com.github.jcgay.maven.color.core;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.net.URISyntaxException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.failBecauseExceptionWasNotThrown;

public class ColorConfigurationTest {

    @Rule
    public TemporaryFolder temp = new TemporaryFolder();

    @Test
    public void return_default_message_color_when_no_configuration_is_provided() {
        Colorizer result = ColorConfiguration.read(temp.getRoot(), new DefaultColorization());

        assertThat(result).isExactlyInstanceOf(DefaultColorization.class);
    }

    @Test
    public void return_a_custom_configuration_when_configuration_is_provided() throws URISyntaxException {
        Colorizer result = ColorConfiguration.read(new File(getClass().getClassLoader().getResource("").toURI()), new DefaultColorization());

        assertThat(result.getClass().getSimpleName()).isEqualTo("MyColor");
        assertThat(result.colorize("SUCCESS")).isEqualTo(CustomAnsi.ansi().bold().a("SUCCESS").reset().toString());
    }

    @Test
    public void fail_when_custom_configuration_does_not_extend_CustomColorization() throws URISyntaxException {
        try {
            ColorConfiguration.read(new File(getClass().getClassLoader().getResource("fail-does-not-extend-CustomColorization").toURI()), new DefaultColorization());
            failBecauseExceptionWasNotThrown(RuntimeException.class);
        } catch (RuntimeException e) {
            assertThat(e.getMessage()).isEqualTo("maven.color must extends com.github.jcgay.maven.color.core.CustomColorization");
        }
    }
}
