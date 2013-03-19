package com.github.jcgay.maven.color.agent;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class MavenSurefireBooterVisitor extends ClassVisitor {

    public MavenSurefireBooterVisitor(ClassVisitor cv) {
        super(Opcodes.ASM4, cv);
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
        MethodVisitor mv = super.visitMethod(access, name, desc, signature, exceptions);
        if (access == Opcodes.ACC_PRIVATE && "getForkingStreamConsumer".equals(name)) {
            return new InstantiateAnsiOutputConsumer(mv);
        }
        return mv;
    }

    /**
     * Instantiate an AnsiOutputConsumer instead of a StandardOutputConsumer.
     */
    private static class InstantiateAnsiOutputConsumer extends MethodVisitor {

        private static final String REPLACED_CONSUMER = "org/apache/maven/surefire/booter/output/StandardOutputConsumer";
        private static final String ANSI_CONSUMER = "com/github/jcgay/maven/color/logger/AnsiOutputConsumer";

        public InstantiateAnsiOutputConsumer(MethodVisitor mv) {
            super(Opcodes.ASM4, mv);
        }

        @Override
        public void visitTypeInsn(int opcode, String type) {
            if (opcode == Opcodes.NEW && REPLACED_CONSUMER.equals(type)) {
                super.visitTypeInsn(opcode, ANSI_CONSUMER);
            } else {
                super.visitTypeInsn(opcode, type);
            }
        }

        @Override
        public void visitMethodInsn(int opcode, String owner, String name, String desc) {
            if (opcode == Opcodes.INVOKESPECIAL && REPLACED_CONSUMER.equals(owner)) {
                super.visitMethodInsn(opcode, ANSI_CONSUMER, name, desc);
            } else {
                super.visitMethodInsn(opcode, owner, name, desc);
            }
        }
    }
}
