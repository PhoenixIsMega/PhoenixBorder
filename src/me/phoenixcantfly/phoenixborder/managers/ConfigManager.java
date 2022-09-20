package me.phoenixcantfly.phoenixborder.managers;

import me.phoenixcantfly.phoenixborder.Main;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;
import java.util.logging.Level;

public class ConfigManager {
    private final ClassManager classManager;
    private final Main main;
    private FileConfiguration dataConfig = null;
    private File configFile = null;

    public ConfigManager(ClassManager classManager) {
        this.classManager = classManager;
        this.main = classManager.getPlugin();
        //saves / inititalizes config
        saveDefaultConfig();
    }

    public void reloadConfig(){
        if (this.configFile == null){
            this.configFile = new File(this.main.getDataFolder(), "borderconfig.yml");
        }

        this.dataConfig = YamlConfiguration.loadConfiguration(this.configFile);

        InputStream defaultStream = this.main.getResource("borderconfig.yml");

        if (defaultStream != null){
            YamlConfiguration defaultConfig = YamlConfiguration.loadConfiguration(new InputStreamReader(defaultStream));
            this.dataConfig.setDefaults(defaultConfig);
        }
    }

    public FileConfiguration getConfig(){
        if (this.dataConfig == null){
            reloadConfig();
        }
        return this.dataConfig;
    }

    public void saveConfig(){
        if (this.dataConfig == null || this.configFile == null){
            return;
        }

        try {
            this.getConfig().save(this.configFile);
        } catch (IOException e) {
            main.getLogger().log(Level.SEVERE, "Could not save config file to " + this.configFile, e);
        }
    }

    public void saveDefaultConfig(){
        if (this.configFile == null){
            this.configFile = new File(this.main.getDataFolder(), "borderconfig.yml");
        }

        if (!this.configFile.exists()){
            this.main.saveResource("borderconfig.yml", false);
        }
    }

    //These are ways to edit the config, also channges variables in Border Manager

    public void disableBorder(){
        classManager.getConfigManager().getConfig().set("border-enabled", false);
        classManager.getConfigManager().saveConfig();
        classManager.getBorderManager().setBorderOn(classManager.getConfigManager().getConfig().getBoolean("border-enabled"));
    }

    public void enableBorder(){
        classManager.getConfigManager().getConfig().set("border-enabled", true);
        classManager.getConfigManager().saveConfig();
        classManager.getBorderManager().setBorderOn(classManager.getConfigManager().getConfig().getBoolean("border-enabled"));
    }

    public void setBorderCeilingHeight(Double height){
        classManager.getBorderManager().setBorderCeiling(height);
        classManager.getConfigManager().getConfig().set("ceiling-height", classManager.getBorderManager().getBorderCeiling());
        classManager.getConfigManager().getConfig().set("floor-height", classManager.getBorderManager().getBorderFloor());
        classManager.getConfigManager().saveConfig();
    }

    public void setBorderCentre(Double x, Double z){
        classManager.getConfigManager().getConfig().set("x", x);
        classManager.getConfigManager().getConfig().set("z", z);
        classManager.getConfigManager().saveConfig();
        classManager.getBorderManager().setBorderX(classManager.getConfigManager().getConfig().getDouble("x"));
        classManager.getBorderManager().setBorderZ(classManager.getConfigManager().getConfig().getDouble("z"));
    }

    public void setBorderDamage(Double damage){
        classManager.getConfigManager().getConfig().set("damage", damage);
        classManager.getConfigManager().saveConfig();
        classManager.getBorderManager().setDamage(classManager.getConfigManager().getConfig().getDouble("damage"));
    }

    public void setBorderFloorHeight(Double height){
        classManager.getBorderManager().setBorderFloor(height);
        classManager.getConfigManager().getConfig().set("ceiling-height", classManager.getBorderManager().getBorderCeiling());
        classManager.getConfigManager().getConfig().set("floor-height", classManager.getBorderManager().getBorderFloor());
        classManager.getConfigManager().saveConfig();
    }

    public void setBorderParticleDistance(Double distance){
        classManager.getConfigManager().getConfig().set("distance", distance);
        classManager.getConfigManager().saveConfig();
        classManager.getParticleManager().setDistanceBetweenPoints(classManager.getConfigManager().getConfig().getDouble("distance"));
    }

    public void setBorderParticleDistanceVertical(Double distance){
        classManager.getConfigManager().getConfig().set("distance-vertical", distance);
        classManager.getConfigManager().saveConfig();
        classManager.getParticleManager().setDistanceBetweenPointsVertical(classManager.getConfigManager().getConfig().getDouble("distance-vertical"));
    }

    public void setBorderParticleSize(Double size){
        classManager.getConfigManager().getConfig().set("size", size);
        classManager.getConfigManager().saveConfig();
        classManager.getParticleManager().setSize((float) classManager.getConfigManager().getConfig().getDouble("size"));
    }

    public void setBorderRadius(Double radius) {
        classManager.getConfigManager().getConfig().set("radius", radius);
        classManager.getConfigManager().saveConfig();
        classManager.getBorderManager().setBorderRadius(classManager.getConfigManager().getConfig().getDouble("radius"));
    }

    public void setBorderRGB(Integer r, Integer g, Integer b){
        classManager.getConfigManager().getConfig().set("r", r);
        classManager.getConfigManager().getConfig().set("g", g);
        classManager.getConfigManager().getConfig().set("b", b);
        classManager.getConfigManager().saveConfig();
        classManager.getParticleManager().setR(classManager.getConfigManager().getConfig().getInt("r"));
        classManager.getParticleManager().setG(classManager.getConfigManager().getConfig().getInt("g"));
        classManager.getParticleManager().setB(classManager.getConfigManager().getConfig().getInt("b"));
    }

    public void setBorderWorld(String name){
        classManager.getConfigManager().getConfig().set("world-name", name);
        classManager.getConfigManager().saveConfig();
        classManager.getBorderManager().setWorld(Bukkit.getWorld(Objects.requireNonNull(classManager.getConfigManager().getConfig().getString("world-name"))));
    }
}
