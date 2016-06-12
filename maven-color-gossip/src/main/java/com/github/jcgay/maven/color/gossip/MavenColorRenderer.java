package com.github.jcgay.maven.color.gossip;

import com.github.jcgay.maven.color.core.ColorActivation;
import com.github.jcgay.maven.color.core.ColorConfiguration;
import com.github.jcgay.maven.color.core.Colorizer;
import com.github.jcgay.maven.color.core.LogLevelActivation;
import com.planet57.gossip.Event;
import com.planet57.gossip.render.PatternRenderer;
import org.fusesource.jansi.Ansi;

import static com.github.jcgay.maven.color.core.CustomAnsi.ansi;
import static com.planet57.gossip.Level.ERROR;
import static com.planet57.gossip.Level.INFO;
import static com.planet57.gossip.Level.WARN;
import static org.fusesource.jansi.Ansi.Color.RED;
import static org.fusesource.jansi.Ansi.Color.YELLOW;

public class MavenColorRenderer extends PatternRenderer {

    private static String MC_PATTERN = "%l%m%n%x";

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
            colorizer = ColorConfiguration.read(new KeepMavenDefaultColor());
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
            switch (event.getLevel()) {
                case WARN:
                    buff.append(ansi().fgBright(YELLOW).bold().a("[WARNING]").reset())
                        .append(" ");
                    break;
                default:
                    buff.append("[");
                    super.renderLevel(event, buff);
                    buff.append("] ");
                    break;
            }
        }
    }

    private static String colorize(Event event, Ansi.Color color) {
        return ansi().fgBright(color).bold().a(event.getMessage()).reset().toString();
    }
}
