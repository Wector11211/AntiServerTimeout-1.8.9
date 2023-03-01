package net.labymod.addons.Wector11211.AntiServerTimeout;

import net.labymod.api.LabyModAddon;
import net.labymod.settings.elements.BooleanElement;
import net.labymod.settings.elements.ControlElement;
import net.labymod.settings.elements.NumberElement;
import net.labymod.settings.elements.SettingsElement;
import net.labymod.utils.Material;

import java.util.List;

public class AntiServerTimeoutAddon extends LabyModAddon {
    public static AntiServerTimeoutAddon INSTANCE;
    private boolean addonEnabled;
    private int customTimeout;

    public static AntiServerTimeoutAddon getInstance(){
        return INSTANCE;
    }
    public boolean isEnabled(){
        return this.addonEnabled;
    }


    @Override
    public void onEnable() {
        INSTANCE = this;
    }

    @Override
    public void loadConfig() {
        this.addonEnabled = getConfig().has( "enabled" ) ? getConfig().get("enabled").getAsBoolean() : true;
        this.customTimeout = getConfig().has( "customTimeout" ) ? getConfig().get("customTimeout").getAsInt() : 60;
    }


    @Override
    protected void fillSettings(List<SettingsElement> options) {

        BooleanElement addonEnabledElement = new BooleanElement(
                "Enabled",
                this,
                new ControlElement.IconData(Material.LEVER),
                "enabled", this.addonEnabled);

        NumberElement customTimeout = new NumberElement(
                "Custom Timeout",
                new ControlElement.IconData(Material.WATCH), this.customTimeout);
                customTimeout.setRange(30, 30000);
                customTimeout.addCallback(timeout -> {
                    this.customTimeout = timeout;
                    getConfig().addProperty("customTimeout", timeout);
                    saveConfig();
                });

        options.add( addonEnabledElement );
        options.add( customTimeout );
    }

    public int getCustomTimeout() {
        return customTimeout;
    }
}
