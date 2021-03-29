package com.blackmc.blackguilds.FactionsSync;

import java.util.Iterator;

import com.blackmc.blackguilds.App;
import com.blackmc.blackguilds.Economics.Economy;
import com.blackmc.blackguilds.data.ConfigMenager;
import com.massivecraft.factions.FPlayer;
import com.massivecraft.factions.FPlayers;
import com.massivecraft.factions.Faction;
import com.massivecraft.factions.Factions;

import org.bukkit.configuration.file.FileConfiguration;

public class FacInfoMenager {

    App plugin;
    ConfigMenager configMenager;

    FileConfiguration playersConfig;
    FileConfiguration factionsConfig;
    Economy economy;
    net.milkbowl.vault.economy.Economy vaultEcon;

    public FacInfoMenager(App plugin) {
        this.plugin = plugin;
        configMenager = new ConfigMenager(plugin);
        economy = new Economy(plugin);
        vaultEcon = plugin.getEconomy();
    }

    public void savePlayersInfo() {
        if (configMenager.fileNotExists("players.yml"))
            configMenager.createConfig("players.yml");

        Iterator<FPlayer> players = FPlayers.getInstance().getAllFPlayers().iterator();

        playersConfig = configMenager.getConfig("players.yml");

        while (players.hasNext()) {
            FPlayer p = players.next();
            if (!playersConfig.isSet(p.getName()))
                playersConfig.set(p.getName(), null);
            setPlayerInfo(p, "faction", p.hasFaction() ? p.getFaction().getTag() : "");
            setPlayerInfo(p, "role", p.getRole().name());

        }

        configMenager.saveConfig("players.yml", playersConfig);

    }

    public void saveFactionsInfo() {

        if (configMenager.fileNotExists("factions.yml"))
            configMenager.createConfig("factions.yml");

        Iterator<Faction> factions = Factions.getInstance().getAllFactions().iterator();

        factionsConfig = configMenager.getConfig("factions.yml");

        while (factions.hasNext()) {
            Faction f = factions.next();
            if (!factionsConfig.isSet(f.getTag()))
                factionsConfig.set(f.getTag(), null);
            if (f.getFPlayerLeader() != null)
                setFactionInfo(f, "leader", f.getFPlayerLeader().getName());
        }

        configMenager.saveConfig("factions.yml", factionsConfig);

    }

    public void saveAll() {
        savePlayersInfo();
        saveFactionsInfo();
    }

    private void setPlayerInfo(FPlayer p, String path, Object value) {

        if (configMenager.fileNotExists("players.yml"))
            configMenager.createConfig("players.yml");

        playersConfig.set(p.getName() + "." + path, value);

    }

    private void setFactionInfo(Faction f, String path, Object value) {

        if (configMenager.fileNotExists("factions.yml"))
            configMenager.createConfig("factions.yml");

        factionsConfig.set(f.getTag() + "." + path, value);

    }

}