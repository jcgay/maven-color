package com.github.jcgay.maven.color.core;

import org.fusesource.jansi.Ansi;

public class CustomAnsi {

    public static Ansi ansi() {
        String currentOs = System.getProperty("os.name");
        if (currentOs != null && currentOs.toLowerCase().indexOf("win") != -1) {
            return NonBrightAnsi.ansi();
        }
        return Ansi.ansi();
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
