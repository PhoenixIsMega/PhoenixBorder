package me.phoenixcantfly.phoenixborder;

import me.phoenixcantfly.phoenixborder.commands.*;
import me.phoenixcantfly.phoenixborder.managers.ClassManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Objects;

public class Main extends JavaPlugin {
    //add time interpolation 1/6
    //reformat messages

    //test cmd blbocks
    //test commands + variations working
    //test command misuse

    //change all method from string

    private ClassManager classManager;

    @Override
    public void onEnable() {
        this.getServer().getScheduler().cancelTasks(this);
        this.classManager = new ClassManager(this);

        this.getCommand("getworldname").setExecutor(new GetWorldName());
        this.getCommand("enableborder").setExecutor(new EnableBorder(this.classManager));
        this.getCommand("disableborder").setExecutor(new DisableBorder(this.classManager));
        this.getCommand("setborderradius").setExecutor(new SetBorderRadius(this.classManager));
        this.getCommand("setborderfloorheight").setExecutor(new SetBorderFloorHeight(this.classManager));
        this.getCommand("setborderceilingheight").setExecutor(new SetBorderCeilingHeight(this.classManager));
        this.getCommand("setbordercentre").setExecutor(new SetBorderCentre(this.classManager));
        this.getCommand("setborderparticledistance").setExecutor(new SetBorderParticleDistance(this.classManager));
        this.getCommand("setborderparticledistancevertical").setExecutor(new SetBorderParticleDistanceVertical(this.classManager));
        this.getCommand("setborderparticlesize").setExecutor(new SetBorderParticleSize(this.classManager));
        this.getCommand("setborderrgb").setExecutor(new SetBorderRGB(this.classManager));
        this.getCommand("setborderworld").setExecutor(new SetBorderWorld(this.classManager));
        this.getCommand("setborderdamage").setExecutor(new SetBorderDamage(this.classManager));

        Bukkit.getScheduler().scheduleSyncDelayedTask(this, (Runnable) () -> {
            classManager.getBorderManager().setBorderOn(classManager.getConfigManager().getConfig().getBoolean("border-enabled"));
            classManager.getBorderManager().setBorderCeiling(classManager.getConfigManager().getConfig().getDouble("ceiling-height"));
            classManager.getBorderManager().setBorderFloor(classManager.getConfigManager().getConfig().getDouble("floor-height"));
            classManager.getBorderManager().setBorderX(classManager.getConfigManager().getConfig().getDouble("x"));
            classManager.getBorderManager().setBorderZ(classManager.getConfigManager().getConfig().getDouble("z"));
            classManager.getBorderManager().setBorderRadius(classManager.getConfigManager().getConfig().getDouble("radius"));
            classManager.getBorderManager().setWorld(Bukkit.getServer().getWorld(Objects.requireNonNull(classManager.getConfigManager().getConfig().getString("world-name"))));
            classManager.getParticleManager().setR(classManager.getConfigManager().getConfig().getInt("r"));
            classManager.getParticleManager().setG(classManager.getConfigManager().getConfig().getInt("g"));
            classManager.getParticleManager().setB(classManager.getConfigManager().getConfig().getInt("b"));
            classManager.getParticleManager().setSize((float) classManager.getConfigManager().getConfig().getDouble("size"));
            classManager.getParticleManager().setDistanceBetweenPoints(classManager.getConfigManager().getConfig().getDouble("distance"));
            classManager.getParticleManager().setDistanceBetweenPointsVertical(classManager.getConfigManager().getConfig().getDouble("distance-vertical"));
            classManager.getBorderManager().setDamage((classManager.getConfigManager().getConfig().getDouble("damage")));

            Bukkit.broadcastMessage(String.valueOf(classManager.getBorderManager().isBorderOn()));

            Bukkit.getScheduler().runTaskTimerAsynchronously(this, () -> classManager.getParticleManager().playBorderParticles(), 2L, 5L); //0 Tick initial delay, 20 Tick (1 Second) between repeats
        }, 1L);
    }

    @Override
    public void onDisable() {
        this.getServer().getScheduler().cancelTasks(this);
    }

    public ClassManager getClassManager() {
        return classManager;
    }
}
