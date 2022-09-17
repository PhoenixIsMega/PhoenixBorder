package me.phoenixcantfly.phoenixborder.managers;

import me.phoenixcantfly.phoenixborder.Main;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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
}
