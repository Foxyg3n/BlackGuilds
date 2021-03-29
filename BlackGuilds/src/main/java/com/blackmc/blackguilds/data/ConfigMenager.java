package com.blackmc.blackguilds.data;

import java.io.File;
import java.io.IOException;

import com.blackmc.blackguilds.App;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

 
public class ConfigMenager {
 
    public App plugin;
    public String DATA;
 
    public ConfigMenager(App plugin) {
        this.plugin = plugin;
        DATA = plugin.getDataFolder().getAbsolutePath() + "/data/";
    }
 
    public YamlConfiguration createConfig(String name) {
        if (!name.endsWith(".yml")) {
            name = name + ".yml";
        }
        File file = new File(this.DATA + name);
        if (!file.exists()) {
            plugin.getDataFolder().mkdir();
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return YamlConfiguration.loadConfiguration(file);
    }
 
    public void saveConfig(String name, FileConfiguration config) {
        if (!name.endsWith(".yml")) {
            name = name + ".yml";
        }
        File file = new File(this.DATA + name);
        try {
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
 
    public YamlConfiguration getConfig(String name) {
        if (!name.endsWith(".yml")) {
            name = name + ".yml";
        }
        File file = new File(this.DATA + name);
        YamlConfiguration fileConfig = YamlConfiguration.loadConfiguration(file);
        return fileConfig;
    }

    public boolean fileNotExists(String name) {
        if (!name.endsWith(".yml")) {
            name = name + ".yml";
        }
        File file = new File(this.DATA + name);
        return !file.exists() ? true : false;
    }
 
}