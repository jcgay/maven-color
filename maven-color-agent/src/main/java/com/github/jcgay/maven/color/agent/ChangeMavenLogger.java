package com.github.jcgay.maven.color.agent;

import org.objectweb.asm.*;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.instrument.Instrumentation;
import java.security.ProtectionDomain;

/**
 * User: jcgay
 */
public class ChangeMavenLogger {

    public static void premain(String agentArgs, Instrumentation inst) {
        inst.addTransformer(new ReplaceMavenLoggerWithAnsiLogger(), true);
    }

    private static class ReplaceMavenLoggerWithAnsiLogger implements ClassFileTransformer {

        public byte[] transform(ClassLoader classLoader, String s, Class<?> aClass, ProtectionDomain protectionDomain, byte[] bytes) throws IllegalClassFormatException {

            if (s.equals("org/apache/maven/cli/MavenCli")) {
                ClassReader reader = new ClassReader(bytes);
                ClassWriter writer = new ClassWriter(reader, 0);
                MavenCliVisitor visitor = new MavenCliVisitor(writer);
                reader.accept(visitor, 0);
                return writer.toByteArray();
            }

            return null;
        }
    }
}
