package com.github.jcgay.maven.color.env;

public class Environment {

    public String getValue(String variable) {
        return System.getenv(variable);
    }
}
