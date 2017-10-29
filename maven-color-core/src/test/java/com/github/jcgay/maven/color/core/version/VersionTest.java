package com.github.jcgay.maven.color.core.version;


import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class VersionTest {

    @Test
    public void should_read_version_from_existing_resource() {
        Version version = new Version("/version-test");

        assertThat(version.get()).isEqualTo("1.0.0");
    }

    @Test
    public void should_return_unknown_version_when_resource_does_not_exist() {
        Version version = new Version("/does-not-exist");

        assertThat(version.get()).isEqualTo("unknown-version");
    }

    @Test
    public void should_return_unknown_version_when_resource_is_empty() {
        Version version = new Version("/version-test-empty");

        assertThat(version.get()).isEqualTo("unknown-version");
    }
}
