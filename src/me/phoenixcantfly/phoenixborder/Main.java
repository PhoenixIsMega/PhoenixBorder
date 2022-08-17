package me.phoenixcantfly.phoenixborder;

import me.phoenixcantfly.phoenixborder.managers.ClassManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    //floor cant be higher than ceiling, vice verse
    //if floor below bottom of world, dont show particles

    private ClassManager classManager;

    @Override
    public void onEnable() {
        this.classManager = new ClassManager(this);

        //this.getCommand("enablepvp").setExecutor(new EnablePVP(this .classmanager));

        //getServer().getPluginManager().registerEvents(new PlayerJoin(this.classManager), this);
    }

    @Override
    public void onDisable() {
    }

    public ClassManager getClassManager() {
        return classManager;
    }
}
