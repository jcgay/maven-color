package com.github.jcgay.maven.color.gossip;

import com.github.jcgay.maven.color.core.ColorActivation;
import com.github.jcgay.maven.color.core.ColorConfiguration;
import com.github.jcgay.maven.color.core.Colorizer;
import com.github.jcgay.maven.color.core.KeepMavenDefaultColor;
import com.github.jcgay.maven.color.core.LogLevelActivation;
import com.planet57.gossip.Event;
import com.planet57.gossip.render.PatternRenderer;
import org.fusesource.jansi.Ansi;
import org.fusesource.jansi.AnsiConsole;

import static com.planet57.gossip.Level.ERROR;
import static com.planet57.gossip.Level.INFO;
import static com.planet57.gossip.Level.WARN;
import static org.fusesource.jansi.Ansi.Color.RED;
import static org.fusesource.jansi.Ansi.Color.YELLOW;
import static org.fusesource.jansi.Ansi.ansi;

public class MavenColorRenderer extends PatternRenderer {

    private static final String MC_PATTERN = "%l%m%n%x";

    private final Colorizer colorizer;
    private final boolean isActivated;

    public MavenColorRenderer() {
        this(MC_PATTERN);
    }

    public MavenColorRenderer(boolean isActivated) {
        this(MC_PATTERN, isActivated);
    }

    public MavenColorRenderer(String pattern) {
        this(pattern, ColorActivation.isActivated());
    }

    public MavenColorRenderer(String pattern, boolean isActivated) {
        super(pattern);
        this.isActivated = isActivated;
        if (isActivated) {
            AnsiConsole.systemInstall();
            colorizer = ColorConfiguration.read();
        } else {
            colorizer = new KeepMavenDefaultColor();
        }
        Ansi.setEnabled(isActivated);
    }

    @Override
    protected void renderMessage(Event event, StringBuilder buff) {
        if (!isActivated) {
            super.renderMessage(event, buff);
            return;
        }

        if (event.getLevel() == WARN) {
            buff.append(colorize(event, YELLOW));
        } else if (event.getLevel() == INFO) {
            buff.append(colorizer.colorize(event.getMessage()));
        } else {
            super.renderMessage(event, buff);
        }
    }

    @Override
    protected void renderLevel(Event event, StringBuilder buff) {
        if (!isActivated) {
            buff.append("[");
            super.renderLevel(event, buff);
            buff.append("] ");
            return;
        }

        if (event.getLevel() == ERROR) {
            buff.append(ansi().fgBright(RED).bold().a("[" + ERROR.name() + "]").reset())
                .append(" ");
            return;
        }

        if (LogLevelActivation.isShown()) {
            if (event.getLevel() == WARN) {
                buff.append(ansi().fgBright(YELLOW).bold().a("[WARNING]").reset())
                    .append(" ");

            } else {
                buff.append("[");
                super.renderLevel(event, buff);
                buff.append("] ");
            }
        }
    }

    private static String colorize(Event event, Ansi.Color color) {
        return ansi().fgBright(color).bold().a(event.getMessage()).reset().toString();
    }
}
