package me.phoenixcantfly.phoenixborder;

import me.phoenixcantfly.phoenixborder.commands.GetWorldName;
import me.phoenixcantfly.phoenixborder.listeners.PlayerMove;
import me.phoenixcantfly.phoenixborder.managers.ClassManager;
import me.phoenixcantfly.phoenixborder.managers.ConfigManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Objects;

public class Main extends JavaPlugin {
    //floor cant be higher than ceiling, vice verse
    //if floor below bottom of world, dont show particles

    private ClassManager classManager;

    @Override
    public void onEnable() {
        this.classManager = new ClassManager(this);

        this.getCommand("getworldname").setExecutor(new GetWorldName());

        getServer().getPluginManager().registerEvents(new PlayerMove(this.classManager), this);

        Bukkit.getScheduler().scheduleSyncDelayedTask(this, (Runnable) () -> {
            classManager.getBorderManager().setBorderOn(true);
            classManager.getBorderManager().setBorderCeiling(classManager.getConfigManager().getConfig().getDouble("ceiling-height"));
            classManager.getBorderManager().setBorderFloor(classManager.getConfigManager().getConfig().getDouble("floor-height"));
            classManager.getBorderManager().setBorderX(classManager.getConfigManager().getConfig().getDouble("x"));
            classManager.getBorderManager().setBorderZ(classManager.getConfigManager().getConfig().getDouble("z"));
            classManager.getBorderManager().setBorderRadius(classManager.getConfigManager().getConfig().getDouble("radius"));
            classManager.getBorderManager().setWorld(Bukkit.getServer().getWorld(Objects.requireNonNull(classManager.getConfigManager().getConfig().getString("world-name"))));

            Bukkit.getScheduler().runTaskTimerAsynchronously(this, () -> classManager.getParticleManager().playBorderParticles(), 2L, 5L); //0 Tick initial delay, 20 Tick (1 Second) between repeats
        }, 1L);
    }

    @Override
    public void onDisable() {
    }

    public ClassManager getClassManager() {
        return classManager;
    }
}
