package me.phoenixcantfly.phoenixborder.managers;

import me.phoenixcantfly.phoenixborder.Main;

public class ClassManager {
    private final Main plugin;
    private final BorderManager borderManager;
    private final ParticleManager particleManager;
    public ClassManager(Main plugin) {
        this.plugin = plugin;
        this.borderManager = new BorderManager();
        this.particleManager = new ParticleManager(this);
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
}