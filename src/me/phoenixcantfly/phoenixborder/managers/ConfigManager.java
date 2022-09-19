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

    public void setCeilingHeight(String[] strings){
        classManager.getBorderManager().setBorderCeiling(Double.parseDouble(strings[0]));
        classManager.getConfigManager().getConfig().set("ceiling-height", classManager.getBorderManager().getBorderCeiling());
        classManager.getConfigManager().getConfig().set("floor-height", classManager.getBorderManager().getBorderFloor());
        classManager.getConfigManager().saveConfig();
    }

    public void setBorderCentre(String[] strings){
        classManager.getConfigManager().getConfig().set("x", Double.valueOf(strings[0]));
        classManager.getConfigManager().getConfig().set("z", Double.valueOf(strings[1]));
        classManager.getConfigManager().saveConfig();
        classManager.getBorderManager().setBorderX(classManager.getConfigManager().getConfig().getDouble("x"));
        classManager.getBorderManager().setBorderZ(classManager.getConfigManager().getConfig().getDouble("z"));
    }

    public void setBorderDamage(String[] strings){
        classManager.getConfigManager().getConfig().set("damage", Double.valueOf(strings[0]));
        classManager.getConfigManager().saveConfig();
        classManager.getBorderManager().setDamage(classManager.getConfigManager().getConfig().getDouble("damage"));
    }

    public void setBorderFloorHeight(String[] strings){
        classManager.getBorderManager().setBorderFloor(Double.parseDouble(strings[0]));
        classManager.getConfigManager().getConfig().set("ceiling-height", classManager.getBorderManager().getBorderCeiling());
        classManager.getConfigManager().getConfig().set("floor-height", classManager.getBorderManager().getBorderFloor());
        classManager.getConfigManager().saveConfig();
    }

    public void setBorderParticleDistance(String[] strings){
        classManager.getConfigManager().getConfig().set("distance", Double.valueOf(strings[0]));
        classManager.getConfigManager().saveConfig();
        classManager.getParticleManager().setDistanceBetweenPoints(classManager.getConfigManager().getConfig().getDouble("distance"));
    }

    public void setBorderParticleSize(String[] strings){
        classManager.getConfigManager().getConfig().set("size", Double.valueOf(strings[0]));
        classManager.getConfigManager().saveConfig();
        classManager.getParticleManager().setSize((float) classManager.getConfigManager().getConfig().getDouble("size"));
    }

    public void setBorderRadius(String[] strings) {
        classManager.getConfigManager().getConfig().set("radius", Double.valueOf(strings[0]));
        classManager.getConfigManager().saveConfig();
        classManager.getBorderManager().setBorderRadius(classManager.getConfigManager().getConfig().getDouble("radius"));
    }

    public void setBorderRGB(String[] strings){
        classManager.getConfigManager().getConfig().set("r", Integer.valueOf(strings[0]));
        classManager.getConfigManager().getConfig().set("g", Integer.valueOf(strings[1]));
        classManager.getConfigManager().getConfig().set("b", Integer.valueOf(strings[2]));
        classManager.getConfigManager().saveConfig();
        classManager.getParticleManager().setR(classManager.getConfigManager().getConfig().getInt("r"));
        classManager.getParticleManager().setG(classManager.getConfigManager().getConfig().getInt("g"));
        classManager.getParticleManager().setB(classManager.getConfigManager().getConfig().getInt("b"));
    }

    public void setBorderWorld(String[] strings){
        classManager.getConfigManager().getConfig().set("world-name", strings[0]);
        classManager.getConfigManager().saveConfig();
        classManager.getBorderManager().setWorld(Bukkit.getWorld(Objects.requireNonNull(classManager.getConfigManager().getConfig().getString("world-name"))));
    }
}
