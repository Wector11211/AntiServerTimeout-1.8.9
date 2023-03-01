package net.labymod.addons.Wector11211.AntiServerTimeout.ASM;

import net.labymod.addons.Wector11211.AntiServerTimeout.AntiServerTimeoutAddon;

public class SetTimeoutASM {
    public static int getTimeout(int defaultValue){
        if (AntiServerTimeoutAddon.getInstance() == null || !AntiServerTimeoutAddon.getInstance().isEnabled()) return defaultValue;
        return AntiServerTimeoutAddon.getInstance().getCustomTimeout();
    }
}
