package com.github.jcgay.maven.color.core;

import com.github.jcgay.maven.color.core.version.CurrentMavenVersion;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.io.File;
import java.net.URISyntaxException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.failBecauseExceptionWasNotThrown;
import static org.fusesource.jansi.Ansi.ansi;
import static org.mockito.Mockito.when;

public class ColorConfigurationTest {

    @Rule
    public TemporaryFolder temp = new TemporaryFolder();

    @Rule
    public MockitoRule mockito = MockitoJUnit.rule();

    @Mock
    private CurrentMavenVersion currentMavenVersion;

    @Test
    public void return_default_message_color_when_no_configuration_is_provided() {
        when(currentMavenVersion.hasBuiltInColor()).thenReturn(false);

        Colorizer result = ColorConfiguration.read(temp.getRoot(), currentMavenVersion);

        assertThat(result).isExactlyInstanceOf(DefaultColorization.class);
    }

    @Test
    public void return_maven_built_in_color_when_current_version_supports_it() {
        when(currentMavenVersion.hasBuiltInColor()).thenReturn(true);

        Colorizer result = ColorConfiguration.read(temp.getRoot(), currentMavenVersion);

        assertThat(result).isExactlyInstanceOf(KeepMavenDefaultColor.class);
    }

    @Test
    public void return_a_custom_configuration_when_configuration_is_provided() throws URISyntaxException {
        Colorizer result = ColorConfiguration.read(new File(getClass().getClassLoader().getResource("").toURI()), CurrentMavenVersion.read());

        assertThat(result.getClass().getSimpleName()).isEqualTo("MyColor");
        assertThat(result.colorize("SUCCESS")).isEqualTo(ansi().bold().a("SUCCESS").reset().toString());
    }

    @Test
    public void fail_when_custom_configuration_does_not_extend_CustomColorization() throws URISyntaxException {
        try {
            ColorConfiguration.read(new File(getClass().getClassLoader().getResource("fail-does-not-extend-CustomColorization").toURI()), CurrentMavenVersion.read());
            failBecauseExceptionWasNotThrown(RuntimeException.class);
        } catch (RuntimeException e) {
            assertThat(e.getMessage()).isEqualTo("maven.color must extends com.github.jcgay.maven.color.core.CustomColorization");
        }
    }
}
