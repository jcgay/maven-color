package com.github.jcgay.maven.color.log4j2;

import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.ClearSystemProperties;
import org.junit.contrib.java.lang.system.EnvironmentVariables;

import static org.assertj.core.api.Assertions.assertThat;

public class ColorActivationLookupTest {

    @Rule
    public ClearSystemProperties myPropertyIsCleared = new ClearSystemProperties("maven.color");

    @Rule
    public EnvironmentVariables env = new EnvironmentVariables();

    private ColorActivationLookup mc = new ColorActivationLookup();

    @Test
    public void return_true_when_property_maven_color_is_set() {
        System.setProperty("maven.color", "true");

        assertThat(mc.lookup("maven.color")).isEqualTo("true");
    }

    @Test
    public void return_false_when_property_maven_color_is_set() {
        System.setProperty("maven.color", "false");

        assertThat(mc.lookup("maven.color")).isEqualTo("false");
    }

    @Test
    public void return_false_when_env_term_is_dumb() {
        env.set("TERM", "dumb");

        assertThat(mc.lookup("maven.color")).isEqualTo("false");
    }

    @Test
    public void return_maven_color_property_first() {
        System.setProperty("maven.color", "true");
        env.set("TERM", "dumb");

        assertThat(mc.lookup("maven.color")).isEqualTo("true");
    }

    @Test
    public void return_true_by_default() {
        assertThat(mc.lookup("maven.color")).isEqualTo("true");
    }

    @Test(expected = IllegalArgumentException.class)
    public void fail_when_property_is_not_maven_color() {
        mc.lookup("key");
    }
}
