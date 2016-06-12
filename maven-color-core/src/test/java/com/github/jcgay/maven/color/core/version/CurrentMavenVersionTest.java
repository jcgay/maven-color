package com.github.jcgay.maven.color.core.version;

import org.apache.maven.artifact.versioning.ComparableVersion;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CurrentMavenVersionTest {

    @Test
    public void read_version_from_configuration_file() {
        ComparableVersion result = new CurrentMavenVersion("/version/1.0.properties").get();

        assertThat(result).isEqualTo(new ComparableVersion("1.0"));
    }

    @Test
    public void return_version_0_when_version_is_missing() {
        ComparableVersion result = new CurrentMavenVersion("/version/no-version.properties").get();

        assertThat(result).isEqualTo(new ComparableVersion("0"));
    }

    @Test
    public void return_version_0_when_build_config_cannot_be_read() {
        ComparableVersion result = new CurrentMavenVersion("/version/does-not-exist.properties").get();

        assertThat(result).isEqualTo(new ComparableVersion("0"));
    }

    @Test
    public void return_true_when_current_version_is_greater() {
        CurrentMavenVersion version = new CurrentMavenVersion("/version/1.0.properties");

        assertThat(version.isGreaterThanOrEqualTo("0.1")).isTrue();
    }

    @Test
    public void return_true_when_current_version_is_equal() {
        CurrentMavenVersion version = new CurrentMavenVersion("/version/1.0.properties");

        assertThat(version.isGreaterThanOrEqualTo("1.0")).isTrue();
    }

    @Test
    public void return_false_when_current_version_is_lower() {
        CurrentMavenVersion version = new CurrentMavenVersion("/version/1.0.properties");

        assertThat(version.isGreaterThanOrEqualTo("2.0")).isFalse();
    }

    @Test
    public void return_true_when_current_version_has_color() {
        CurrentMavenVersion version = new CurrentMavenVersion("/version/3.5.0.properties");

        assertThat(version.hasBuiltInColor()).isTrue();
    }

    @Test
    public void return_true_when_current_version_has_no_color() {
        CurrentMavenVersion version = new CurrentMavenVersion("/version/3.3.9.properties");

        assertThat(version.hasBuiltInColor()).isFalse();
    }
}
