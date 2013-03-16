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
        inst.addTransformer(new ReplaceMavenCliSetupLogger(), true);
    }

    private static class ReplaceMavenCliSetupLogger implements ClassFileTransformer {

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

    private static class MavenCliVisitor extends ClassVisitor {

        public MavenCliVisitor(ClassWriter writer) {
            super(Opcodes.ASM4, writer);
        }

        @Override
        public MethodVisitor visitMethod(int access, String name,
                                         String desc, String signature, String[] exceptions) {
            if (access == Opcodes.ACC_PRIVATE && "setupLogger".equals(name)) {
                return null;
            }
            MethodVisitor mv = super.visitMethod(access, name, desc, signature, exceptions);
            if (access == Opcodes.ACC_PRIVATE && "container".equals(name)) {
                return new RenameCallToSetupLoggerVisitor(mv);
            }
            return mv;
        }

        @Override
        public void visitEnd() {
            createSetupAnsiColorLoggerMethod();
            super.visitEnd();
        }

        private void createSetupAnsiColorLoggerMethod() {
            MethodVisitor mv = cv.visitMethod(Opcodes.ACC_PRIVATE, "setupLogger", "(Lorg/apache/maven/cli/MavenCli$CliRequest;)Lorg/codehaus/plexus/logging/Logger;", null, null);
            mv.visitCode();
            mv.visitTypeInsn(Opcodes.NEW, "com/github/jcgay/maven/color/logger/AnsiColorLogger");
            mv.visitInsn(Opcodes.DUP);
            mv.visitMethodInsn(Opcodes.INVOKESPECIAL, "com/github/jcgay/maven/color/logger/AnsiColorLogger", "<init>", "()V");
            mv.visitVarInsn(Opcodes.ASTORE, 2);
            mv.visitVarInsn(Opcodes.ALOAD, 2);
            mv.visitVarInsn(Opcodes.ALOAD, 1);
            mv.visitFieldInsn(Opcodes.GETFIELD, "org/apache/maven/cli/MavenCli$CliRequest", "request", "Lorg/apache/maven/execution/MavenExecutionRequest;");
            mv.visitMethodInsn(Opcodes.INVOKEINTERFACE, "org/apache/maven/execution/MavenExecutionRequest", "getLoggingLevel", "()I");
            mv.visitMethodInsn(Opcodes.INVOKEINTERFACE, "org/codehaus/plexus/logging/Logger", "setThreshold", "(I)V");
            mv.visitVarInsn(Opcodes.ALOAD, 2);
            mv.visitInsn(Opcodes.ARETURN);
            mv.visitMaxs(2, 3);
            mv.visitEnd();
        }
    }

    private static class RenameCallToSetupLoggerVisitor extends MethodVisitor {

        public RenameCallToSetupLoggerVisitor(MethodVisitor mv) {
            super(Opcodes.ASM4, mv);
        }

        @Override
        public void visitMethodInsn(int opcode, String owner, String name, String desc) {
            if ("setupLogger".equals(name)) {
                super.visitMethodInsn(opcode, owner, name, "(Lorg/apache/maven/cli/MavenCli$CliRequest;)Lorg/codehaus/plexus/logging/Logger;");
            } else {
                super.visitMethodInsn(opcode, owner, name, desc);
            }
        }
    }
}
