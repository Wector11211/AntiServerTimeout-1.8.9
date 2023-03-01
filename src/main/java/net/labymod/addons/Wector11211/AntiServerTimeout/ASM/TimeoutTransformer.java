package net.labymod.addons.Wector11211.AntiServerTimeout.ASM;

import net.labymod.core.asm.global.ClassEditor;
import net.minecraft.launchwrapper.IClassTransformer;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.tree.ClassNode;

public class TimeoutTransformer implements IClassTransformer {

    @Override
    public byte[] transform(String name, String transformedName, byte[] basicClass) {
            if ("ek$5".equals(name) || "net.minecraft.network.NetworkManager$5".equals(name)
                    || "ll$4".equals(name) || "net.minecraft.network.NetworkSystem$4".equals(name)) {
                System.out.println("[AntiServerTimeout] Transforming NetworkManager class (" + name + ")");
                ClassNode node = new ClassNode();
                ClassEditor editor = new ReadTimeoutEditor();
                ClassReader reader = new ClassReader(basicClass);
                reader.accept(node, 0);
                editor.accept(name, node);
                ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);
                node.accept(writer);
                return writer.toByteArray();
            }
        return basicClass;
    }
}
