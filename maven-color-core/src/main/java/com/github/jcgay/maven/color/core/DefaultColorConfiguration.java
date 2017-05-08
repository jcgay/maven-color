package com.github.jcgay.maven.color.core;

import org.fusesource.jansi.Ansi;

import static org.fusesource.jansi.Ansi.Color.CYAN;
import static org.fusesource.jansi.Ansi.Color.GREEN;
import static org.fusesource.jansi.Ansi.Color.RED;
import static org.fusesource.jansi.Ansi.Color.YELLOW;
import static org.fusesource.jansi.Ansi.ansi;

public class DefaultColorConfiguration implements ConfigurableColor {

    @Override
    public Ansi onModuleHeader() {
        return ansi().fgBright(CYAN);
    }

    @Override
    public Ansi onPluginExecution() {
        return ansi().bold();
    }

    @Override
    public Ansi onPluginExecutionModuleName() {
        return ansi().fgBright(CYAN).bold();
    }

    @Override
    public Ansi onSkipped() {
        return ansi().fgBright(YELLOW).bold();
    }

    @Override
    public Ansi onFailure() {
        return ansi().fgBright(RED).bold();
    }

    @Override
    public Ansi onSuccess() {
        return ansi().fgBright(GREEN).bold();
    }

    @Override
    public Ansi onBuildFailure() {
        return ansi().fgBright(RED).bold();
    }

    @Override
    public Ansi onBuildSuccess() {
        return ansi().fgBright(GREEN).bold();
    }
}
