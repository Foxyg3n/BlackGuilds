package com.blackmc.blackguilds.Economics;

import com.blackmc.blackguilds.App;
import com.blackmc.blackguilds.data.ConfigMenager;
import com.massivecraft.factions.FPlayer;
import com.massivecraft.factions.Faction;

import org.bukkit.configuration.file.FileConfiguration;

public class Economy {

    App plugin;
    ConfigMenager configMenager;

    FileConfiguration playersGems;
    FileConfiguration factionsCrystals;

    public Economy(App plugin) {
        this.plugin = plugin;
        configMenager = new ConfigMenager(plugin);
    }

    public Integer getPlayersGems(FPlayer p) {

        if (configMenager.fileNotExists("gems.yml"))
            configMenager.createConfig("gems.yml");

        playersGems = configMenager.getConfig("gems.yml");

        if (!playersGems.isSet(p.getName()))
            playersGems.set(p.getName(), 0);

        return playersGems.getInt(p.getName());

    }

    public void setPlayersGems(FPlayer p, String value) {

        try {
            Integer.parseInt(value);
        } catch(Exception e) {
            p.sendMessage("Niepoprawna liczba klejnotow");
            return;
        }

        if (configMenager.fileNotExists("gems.yml"))
            configMenager.createConfig("gems.yml");

        playersGems = configMenager.getConfig("gems.yml");

        if (!playersGems.isSet(p.getName()))
            playersGems.set(p.getName(), 0);

        playersGems.set(p.getName(), Integer.parseInt(value));

        configMenager.saveConfig("gems.yml", playersGems);

    }

    public Integer getFactionsCrystals(Faction f) {

        if (configMenager.fileNotExists("crystals.yml"))
            configMenager.createConfig("crystals.yml");
        
        factionsCrystals = configMenager.getConfig("crystals.yml");

        if (!factionsCrystals.isSet(f.getTag()))
            factionsCrystals.set(f.getTag(), 0);

        return factionsCrystals.getInt(f.getTag());

    }

    public void setFactionsCrystals(Faction f, String value) {

        try {
            Integer.parseInt(value);
        } catch(Exception e) {
            return;
        }

        if (configMenager.fileNotExists("crystals.yml"))
            configMenager.createConfig("crystals.yml");
        
        factionsCrystals = configMenager.getConfig("crystals.yml");

        if (!factionsCrystals.isSet(f.getTag()))
            factionsCrystals.set(f.getTag(), 0);

        factionsCrystals.set(f.getTag(), Integer.parseInt(value));

        configMenager.saveConfig("crystals.yml", factionsCrystals);

    }
    
}
