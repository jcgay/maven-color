package com.github.jcgay.maven.color.plugin;

import com.google.common.annotations.VisibleForTesting;
import org.apache.maven.execution.MavenSession;
import org.apache.maven.plugin.BuildPluginManager;
import org.apache.maven.plugin.DefaultBuildPluginManager;
import org.apache.maven.plugin.PluginManagerException;
import org.apache.maven.plugin.PluginResolutionException;
import org.apache.maven.plugin.descriptor.PluginDescriptor;
import org.codehaus.plexus.classworlds.realm.ClassRealm;
import org.codehaus.plexus.component.annotations.Component;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * User: jcgay
 */
@Component(role = BuildPluginManager.class)
public class ClassRealmModifierBuildPluginManager extends DefaultBuildPluginManager {

    @Override
    public ClassRealm getPluginRealm(MavenSession session, PluginDescriptor pluginDescriptor) throws PluginResolutionException, PluginManagerException {
        ClassRealm result = super.getPluginRealm(session, pluginDescriptor);

        URL jar = getJarUrl(this.getClass());
        if (jar != null) {
            result.addURL(jar);
        }

        return result;
    }

    @VisibleForTesting URL getJarUrl(Class<?> clazz) {
        URL resource = clazz.getResource("/" + clazz.getName().replace('.', '/') + ".class");
        if (resource == null || !resource.getProtocol().equals("jar")) {
            return null;
        }

        String resourceAsString = resource.toString();
        int index = resourceAsString.indexOf(".jar");

        try {
            return new URL(resourceAsString.substring(4, index + 4));
        } catch (MalformedURLException e) {
            return null;
        }
    }
}
