package com.github.jcgay.maven.color.core;

import org.fusesource.jansi.Ansi;

/**
 * Template to extends when customizing colors. <br />
 *
 * The default implementation is used when a method is not overridden. <br />
 * The {@link Ansi} returned must be created by calling the convenience method
 * {@link #ansi()} in your custom implementation.
 */
public abstract class CustomColorization implements Colorizer, ConfigurableColor {

    private DefaultColorConfiguration defaultColorConfiguration = new DefaultColorConfiguration();
    private DefaultColorization defaultColor = new DefaultColorization(this);

    @Override
    public String colorize(String message) {
        return defaultColor.colorize(message);
    }

    @Override
    public Ansi onModuleHeader() {
        return defaultColorConfiguration.onModuleHeader();
    }

    @Override
    public Ansi onPluginExecution() {
        return defaultColorConfiguration.onPluginExecution();
    }

    @Override
    public Ansi onPluginExecutionModuleName() {
        return defaultColorConfiguration.onPluginExecutionModuleName();
    }

    @Override
    public Ansi onSkipped() {
        return defaultColorConfiguration.onSkipped();
    }

    @Override
    public Ansi onFailure() {
        return defaultColorConfiguration.onFailure();
    }

    @Override
    public Ansi onSuccess() {
        return defaultColorConfiguration.onSuccess();
    }

    @Override
    public Ansi onBuildFailure() {
        return defaultColorConfiguration.onBuildFailure();
    }

    @Override
    public Ansi onBuildSuccess() {
        return defaultColorConfiguration.onBuildSuccess();
    }

    protected final Ansi ansi() {
        return Ansi.ansi();
    }
}
