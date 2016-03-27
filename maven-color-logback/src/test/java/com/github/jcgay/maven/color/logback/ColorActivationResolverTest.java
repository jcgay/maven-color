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

        assertThat(mc.getPropertyValue()).isEqualTo("false");
    }

    @Test
    public void return_maven_color_property_first() {
        System.setProperty("maven.color", "true");
        env.set("TERM", "dumb");

        assertThat(mc.getPropertyValue()).isEqualTo("true");
    }

    @Test
    public void return_true_by_default() {
        assertThat(mc.getPropertyValue()).isEqualTo("true");
    }
}
