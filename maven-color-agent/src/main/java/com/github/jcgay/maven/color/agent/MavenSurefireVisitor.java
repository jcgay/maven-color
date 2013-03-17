package com.github.jcgay.maven.color.agent;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class MavenSurefireVisitor extends ClassVisitor {

    public static enum Version {
        SUREFIRE_2_9, SUREFIRE_2_13
    }

    private Version version;

    public MavenSurefireVisitor(ClassVisitor cv, Version version) {
        super(Opcodes.ASM4, cv);
        this.version = version;
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
        if (access == Opcodes.ACC_PRIVATE && "createConsoleLogger".equals(name)) {
            return null;
        }
        return super.visitMethod(access, name, desc, signature, exceptions);
    }

    @Override
    public void visitEnd() {
        createConsolorLoggerMethod();
        super.visitEnd();
    }

    /**
     * Method to instantiate a AnsiColorConsoleLogger instead of DefaultDirectConsoleReporter.
     */
    private void createConsolorLoggerMethod() {
        MethodVisitor mv = cv.visitMethod(Opcodes.ACC_PRIVATE, "createConsoleLogger", description(), null, null);
        mv.visitCode();
        mv.visitTypeInsn(Opcodes.NEW, "com/github/jcgay/maven/color/logger/AnsiColorConsoleLogger");
        mv.visitInsn(Opcodes.DUP);
        mv.visitMethodInsn(Opcodes.INVOKESPECIAL, "com/github/jcgay/maven/color/logger/AnsiColorConsoleLogger", "<init>", "()V");
        mv.visitInsn(Opcodes.ARETURN);
        mv.visitMaxs(2, 1);
        mv.visitEnd();
    }

    private String description() {
        switch (version) {
            case SUREFIRE_2_9:
                return "()Lorg/apache/maven/surefire/report/ConsoleLogger;";
            case SUREFIRE_2_13:
                return "()Lorg/apache/maven/surefire/report/DefaultDirectConsoleReporter;";
            default:
                throw new IllegalArgumentException("Need a version to compute return type.");
        }
    }
}
