package com.github.jcgay.maven.color.plugin;

import org.junit.Test;

import java.net.URL;

import static org.fest.assertions.Assertions.assertThat;

public class ClassRealmModifierBuildPluginManagerTest {

    private ClassRealmModifierBuildPluginManager manager = new ClassRealmModifierBuildPluginManager();

    @Test
    public void should_extract_jar_url_from_class() throws Exception {

        URL result = manager.getJarUrl(String.class);

        assertThat(result).isNotNull();
        assertThat(result.getProtocol()).isEqualTo("file");
        assertThat(result.getPath()).doesNotContain("/java/lang/String.class");
        assertThat(result.getPath()).endsWith(".jar");
    }
}
