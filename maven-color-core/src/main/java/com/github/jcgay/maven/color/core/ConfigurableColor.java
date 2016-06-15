package com.github.jcgay.maven.color.core;

import org.fusesource.jansi.Ansi;

public interface ConfigurableColor {

    /**
     * Represent a module header.
     *
     * <pre>
     * ------------------------------------------------------------------------
     * Building maven-color-core 1.0
     * ------------------------------------------------------------------------
     * </pre>
     *
     * @return an {@link Ansi} instance with custom style
     */
    Ansi onModuleHeader();

    /**
     * Represent a plugin execution statement
     *
     * <pre>
     * --- maven-clean-plugin:2.6.1:clean (default-clean) @ maven-color-core ---
     * </pre>
     *
     * @return an {@link Ansi} instance with custom style
     */
    Ansi onPluginExecution();

    /**
     * Represent the module name in a plugin execution statement
     *
     * <pre>
     * --- maven-clean-plugin:2.6.1:clean (default-clean) @ maven-color-core ---
     * </pre>
     *
     * @return an {@link Ansi} instance with custom style
     */
    Ansi onPluginExecutionModuleName();

    /**
     * The text {@code SKIPPED} shown in Reactor Summary
     *
     * @return an {@link Ansi} instance with custom style
     */
    Ansi onSkipped();

    /**
     * The text {@code FAILURE} shown in Reactor Summary
     *
     * @return an {@link Ansi} instance with custom style
     */
    Ansi onFailure();

    /**
     * The text {@code SUCCESS} shown in Reactor Summary
     *
     * @return an {@link Ansi} instance with custom style
     */
    Ansi onSuccess();

    /**
     * The text {@code BUILD FAILURE}
     *
     * @return an {@link Ansi} instance with custom style
     */
    Ansi onBuildFailure();

    /**
     * The text {@code BUILD SUCCESS}
     *
     * @return an {@link Ansi} instance with custom style
     */
    Ansi onBuildSuccess();
}
