package net.labymod.addons.Wector11211.AntiServerTimeout.ASM;

import net.labymod.core.asm.global.ClassEditor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.*;

public class ReadTimeoutEditor extends ClassEditor {
    public ReadTimeoutEditor() {
        super(ClassEditorType.CLASS_NODE);
    }

    @Override
    public void accept(String name, ClassNode node) {
        for(MethodNode method : node.methods){
//            if("a".equals(method.name) || "func_148833_a".equals(method.name) || "processPacket".equals(method.name)){
            if("initChannel".equals(method.name) && "(Lio/netty/channel/Channel;)V".equals(method.desc)){
                System.out.println("[AntiServerTimeout] Found initChannel, hooking.");
                InsnList list = new InsnList();
                LabelNode label = new LabelNode();
                for (AbstractInsnNode instruction : method.instructions.toArray()) {
                    list.add(instruction);
                    if (instruction.getOpcode() == Opcodes.BIPUSH) {
                        System.out.println("[AntiServerTimeout] Changing timeout value (BIPUSH 30)");
                        list.add(new MethodInsnNode(Opcodes.INVOKESTATIC,
                                "net/labymod/addons/Wector11211/AntiServerTimeout/ASM/SetTimeoutASM", "getTimeout", "(I)I", false));
//                        list.add(new InsnNode(Opcodes.ALOAD));
                    }
                }
                list.add(label);
                method.instructions.clear();

                method.instructions.insert(list);
            }
        }
    }

}
