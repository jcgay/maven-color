package com.github.jcgay.maven.color.core;

import org.fusesource.jansi.Ansi;

import static org.fusesource.jansi.Ansi.isEnabled;

public class CustomAnsi {

    private CustomAnsi() {
        // hide construction
    }

    public static Ansi ansi() {
        if (!isEnabled() || !isWindows()) {
            return Ansi.ansi();
        }

        return NonBrightAnsi.ansi();
    }

    private static boolean isWindows() {
        String currentOs = System.getProperty("os.name");
        return currentOs != null && currentOs.toLowerCase().contains("win");
    }

    public static class NonBrightAnsi extends Ansi {

        public static Ansi ansi() {
            return new NonBrightAnsi();
        }

        @Override
        public Ansi fgBright(Color color) {
            return fg(color);
        }
    }
}
