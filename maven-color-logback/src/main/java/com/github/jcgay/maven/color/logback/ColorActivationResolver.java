package com.github.jcgay.maven.color.logback;

import ch.qos.logback.core.PropertyDefinerBase;
import com.github.jcgay.maven.color.core.ColorActivation;

public class ColorActivationResolver extends PropertyDefinerBase {

    @Override
    public String getPropertyValue() {
        return String.valueOf(ColorActivation.isActivated());
    }
}
