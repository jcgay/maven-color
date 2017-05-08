package com.github.jcgay.maven.color.core;

public class MavenColorException extends RuntimeException {

    public MavenColorException(String s) {
        super(s);
    }

    public MavenColorException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public MavenColorException(Throwable throwable) {
        super(throwable);
    }
}
