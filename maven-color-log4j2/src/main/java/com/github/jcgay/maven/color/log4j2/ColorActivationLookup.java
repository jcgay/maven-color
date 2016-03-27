package com.github.jcgay.maven.color.log4j2;

import com.github.jcgay.maven.color.core.ColorActivation;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.lookup.StrLookup;

@Plugin(name = "mc", category = "Lookup")
public class ColorActivationLookup implements StrLookup {

    @Override
    public String lookup(String key) {
        if (!"maven.color".equals(key)) {
            throw new IllegalArgumentException("mc lookup should only be used to retrieve maven.color key!");
        }
        return String.valueOf(ColorActivation.isActivated());
    }

    @Override
    public String lookup(LogEvent event, String key) {
        return lookup(key);
    }
}
