package com.github.jcgay.maven.color.agent;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.lang.instrument.Instrumentation;

import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;

@RunWith(MockitoJUnitRunner.class)
public class ChangeMavenLoggerTest {

    @Mock
    private Instrumentation instrumentation;

    @Test
    public void should_not_instrument_classes_if_color_is_not_enable() throws Exception {

        System.setProperty("maven.color", "false");

        ChangeMavenLogger.premain("", instrumentation);

        verifyZeroInteractions(instrumentation);
    }

    @Test
    public void should_instrument_classes_if_color_is_enable() throws Exception {

        System.setProperty("maven.color", "true");

        ChangeMavenLogger.premain("", instrumentation);

        verify(instrumentation).addTransformer(isA(ChangeMavenLogger.ReplaceMavenLoggerWithAnsiLogger.class));
    }
}
