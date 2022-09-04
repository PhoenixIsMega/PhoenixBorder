package me.phoenixcantfly.phoenixborder;

import me.phoenixcantfly.phoenixborder.managers.ClassManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    //floor cant be higher than ceiling, vice verse
    //if floor below bottom of world, dont show particles

    private ClassManager classManager;

    @Override
    public void onEnable() {
        this.classManager = new ClassManager(this);

        //classManager.getBorderManager().setBorderOn(false);
        //this.getCommand("enablepvp").setExecutor(new EnablePVP(this .classmanager));

        //getServer().getPluginManager().registerEvents(new PlayerJoin(this.classManager), this);
        //Bukkit.getServer().getWorlds().get(0); //dont use!! but for now ok

        classManager.getBorderManager().setBorderOn(true);
        classManager.getBorderManager().setBorderCeiling(100.0);
        classManager.getBorderManager().setBorderFloor(0.0);
        classManager.getBorderManager().setBorderX(0.0);
        classManager.getBorderManager().setBorderZ(0.0);
        classManager.getBorderManager().setBorderRadius(7.0);
        classManager.getBorderManager().setWorld(Bukkit.getServer().getWorld("world"));

        //Bukkit.broadcastMessage("HELLO OVER HERE THE NAME IS" + Bukkit.getServer().getWorlds().get(0).getName());

        //Bukkit.getScheduler().scheduleSyncRepeatingTask(this, () -> classManager.getParticleManager().playBorderParticles(), 0L, 5L); //0 Tick initial delay, 20 Tick (1 Second) between repeats
        Bukkit.getScheduler().runTaskTimerAsynchronously(this, () -> classManager.getParticleManager().playBorderParticles(), 0L, 5L); //0 Tick initial delay, 20 Tick (1 Second) between repeats

        //HERE[CraftWorld{name=world}, CraftWorld{name=world_nether}, CraftWorld{name=world_the_end}, CraftWorld{name=partygames}, CraftWorld{name=SG}, CraftWorld{name=SkyGame}, CraftWorld{name=backrooms}]
    }

    @Override
    public void onDisable() {
    }

    public ClassManager getClassManager() {
        return classManager;
    }
}
