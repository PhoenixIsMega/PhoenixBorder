package me.phoenixcantfly.phoenixborder.managers;

import me.phoenixcantfly.phoenixborder.Main;

public class ClassManager {
    private final Main plugin;
    private final BorderManager borderManager;
    private final ParticleManager particleManager;
    private final MessageManager messageManager;
    private final ConfigManager configManager;
    public ClassManager(Main plugin) {
        this.plugin = plugin;
        this.borderManager = new BorderManager(this);
        this.particleManager = new ParticleManager(this);
        this.configManager = new ConfigManager(this);
        this.messageManager = new MessageManager();
    }

    public Main getPlugin() {
        return plugin;
    }

    public BorderManager getBorderManager() {
        return borderManager;
    }

    public ParticleManager getParticleManager() {
        return particleManager;
    }

    public ConfigManager getConfigManager() { return configManager; }

    public MessageManager getMessageManager() {
        return messageManager;
    }
}