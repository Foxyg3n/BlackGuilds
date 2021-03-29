package com.blackmc.blackguilds.utils;

import java.io.File;

import org.bukkit.Bukkit;

public class FileMenager {

    public static File ROOT = new File(Bukkit.getServer().getPluginManager().getPlugin("BlackGuilds").getDataFolder() + File.separator + "data" + File.separator);
    public static File USERDATA = new File(ROOT + "userdata/");
    public static File FACTIONS = new File(ROOT + "factionsdata/");

    public File getFile(String path) {

        return new File(ROOT + path);

    }

    public File getFile(File root, String path) {

        return new File(root + path);

    }

    public void saveFile() {

        

    }

}
