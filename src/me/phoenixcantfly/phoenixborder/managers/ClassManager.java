package me.phoenixcantfly.phoenixborder.managers;

import me.phoenixcantfly.phoenixborder.Main;

public class ClassManager {
    private final Main plugin;
    private final BorderManager borderManager;
    public ClassManager(Main plugin) {
        this.plugin = plugin;
        this.borderManager = new BorderManager();
    }
}