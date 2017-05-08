package com.github.jcgay.maven.color.gossip;

import com.planet57.gossip.Event;
import com.planet57.gossip.Level;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.ClearSystemProperties;
import org.slf4j.Logger;

import static com.planet57.gossip.Level.ERROR;
import static com.planet57.gossip.Level.INFO;
import static com.planet57.gossip.Level.WARN;
import static org.assertj.core.api.Assertions.assertThat;
import static org.fusesource.jansi.Ansi.Color.RED;
import static org.fusesource.jansi.Ansi.Color.YELLOW;
import static org.fusesource.jansi.Ansi.ansi;
import static org.slf4j.LoggerFactory.getLogger;

public class MavenColorRendererTest {

    @Rule
    public ClearSystemProperties myPropertyIsCleared = new ClearSystemProperties("maven.color.hide.level");

    private static final Logger LOGGER = getLogger(MavenColorRendererTest.class);

    @Test
    public void build_message_in_yellow_for_level_warning() {
        StringBuilder result = new StringBuilder();

        new MavenColorRenderer(true).renderMessage(event(WARN, "warning message"), result);

        assertThat(result.toString()).isEqualTo(ansi().fgBright(YELLOW).bold().a("warning message").reset().toString());
    }

    @Test
    public void do_not_colorize_when_colorization_is_deactivated() {
        StringBuilder result = new StringBuilder();

        new MavenColorRenderer(false).renderMessage(event(WARN, "warning message"), result);

        assertThat(result.toString()).isEqualTo("warning message");
    }

    @Test
    public void do_not_render_level_when_colorization_is_activated() {
        StringBuilder result = new StringBuilder();

        new MavenColorRenderer(true).renderLevel(anyEvent(), result);

        assertThat(result.toString()).isEmpty();
    }

    @Test
    public void render_level_when_colorization_is_activated_and_property_equals_false() {
        System.setProperty("maven.color.hide.level", "false");
        StringBuilder result = new StringBuilder();

        new MavenColorRenderer(true).renderLevel(event(INFO, "message"), result);

        assertThat(result.toString()).isEqualTo("[INFO] ");
    }

    @Test
    public void do_not_render_level_when_colorization_is_activated_and_property_equals_true() {
        System.setProperty("maven.color.hide.level", "true");
        StringBuilder result = new StringBuilder();

        new MavenColorRenderer(true).renderLevel(event(INFO, "message"), result);

        assertThat(result.toString()).isEmpty();
    }

    @Test
    public void render_error_level_in_red_when_colorization_is_activated() {
        StringBuilder result = new StringBuilder();

        MavenColorRenderer renderer = new MavenColorRenderer(true);
        Event event = event(ERROR, "message");
        renderer.renderLevel(event, result);
        renderer.renderMessage(event, result);

        assertThat(result.toString()).isEqualTo(ansi().fgBright(RED).bold().a("[ERROR]").reset().toString() + " message");
    }

    @Test
    public void render_warning_level_in_yellow_when_colorization_is_activated_and_property_equals_false() {
        System.setProperty("maven.color.hide.level", "false");
        StringBuilder result = new StringBuilder();

        MavenColorRenderer renderer = new MavenColorRenderer(true);
        Event event = event(WARN, "message");
        renderer.renderLevel(event, result);
        renderer.renderMessage(event, result);

        assertThat(result.toString()).isEqualTo(ansi().fgBright(YELLOW).bold().a("[WARNING]").reset().toString() + " " + ansi().fgBright(YELLOW).bold().a("message").reset().toString());
    }

    @Test
    public void render_error_level_when_colorization_is_deactivated() {
        StringBuilder result = new StringBuilder();

        MavenColorRenderer renderer = new MavenColorRenderer(false);
        Event event = event(ERROR, "message");
        renderer.renderLevel(event, result);
        renderer.renderMessage(event, result);

        assertThat(result.toString()).isEqualTo("[ERROR] message");
    }

    @Test
    public void render_level_when_colorization_is_deactivated() {
        StringBuilder result = new StringBuilder();

        new MavenColorRenderer(false).renderLevel(event(INFO, "message"), result);

        assertThat(result.toString()).isEqualTo("[INFO] ");
    }

    @Test
    public void strip_existing_colors_when_colorization_is_deactivated() {
        StringBuilder result = new StringBuilder();

        new MavenColorRenderer(false).renderMessage(event(INFO, ansi().fgBright(RED).a("message").toString()), result);

        assertThat(result.toString()).isEqualTo("message");
    }

    private static Event anyEvent() {
        return event(INFO, "message");
    }

    private static Event event(Level level, String message) {
        return new Event(LOGGER, level, message, null);
    }
}
