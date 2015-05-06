package com.github.jcgay.maven.color.logback;

import ch.qos.logback.core.ConsoleAppender;
import ch.qos.logback.core.util.EnvUtil;
import org.fusesource.jansi.AnsiConsole;

import java.io.OutputStream;

public class MavenColorConsoleAppender<E> extends ConsoleAppender<E> {

    private boolean useJansi;

    @Override
    public void setOutputStream(OutputStream outputStream) {
        OutputStream targetStream = outputStream;
        if (EnvUtil.isWindows() && useJansi) {
            targetStream = AnsiConsole.wrapOutputStream(outputStream);
        }
        super.setOutputStream(targetStream);
    }

    public void setUseJansi(boolean useJansi) {
        this.useJansi = useJansi;
    }
}
