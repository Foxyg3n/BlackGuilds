package com.blackmc.blackguilds;

import java.util.logging.Logger;

import com.blackmc.blackguilds.Commands.BlackGuilds;
import com.blackmc.blackguilds.Commands.FactionsCommand;
import com.blackmc.blackguilds.Commands.Raid;
import com.blackmc.blackguilds.FactionsSync.FacInfoMenager;
import com.blackmc.blackguilds.FactionsSync.FacKillSync;
import com.blackmc.blackguilds.FactionsSync.FactionDisband;
import com.blackmc.blackguilds.FactionsSync.FactionTagSync;
import com.blackmc.blackguilds.FactionsSync.SyncOnJoin;
import com.blackmc.blackguilds.Raid.RaidEnd;
import com.blackmc.blackguilds.Vault.ShulkerDenial;
import com.blackmc.blackguilds.Vault.Vault;
import com.blackmc.blackguilds.utils.Color;

import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import net.milkbowl.vault.economy.Economy;

public class App extends JavaPlugin {

    private static Vault vault = new Vault();
    private static Economy econ = null;
    private static final Logger log = Logger.getLogger("Minecraft");

    FacInfoMenager facInfoMenager = new FacInfoMenager(this);

    public App getInstance() {
        return this;
    }

    Color color = new Color();

    @Override
    public void onEnable() {

        vault.loadAllInventories(this);

        if (!setupEconomy() ) {
            log.severe(String.format("[%s] - Disabled due to no Vault dependency found!", getDescription().getName()));
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        color.log("");
        color.log("------------------------", "yellow");
        color.log("Hello, It's BlackGuilds!", "yellow");
        color.log("------------------------", "yellow");
        color.log("");

        // Commands
        new BlackGuilds(this);
        // new VaultCommand(this);
        // new CheckVaultCommand(this);
        // new UpgradeVaultCommand(this);
        // new Test(this);

        // Item Effects
        // new ItemEffects(this);

        // FactionsX Synchronization
        // new FactionsCommand(this);
        // new SyncOnJoin(this);
        // new FactionDisband(this);
        new FactionTagSync(this);
        // new FacKillSync(this);
        new Raid(this);
        new RaidEnd(this);
        // new ShulkerDenial(this);

        // facInfoMenager.saveAll();

        //!Faction has Leader, Administrator, Moderator and Recruit Roles

        color.log("");
        color.log("|--/");
        color.log("|/");
        color.log("|");
        color.log("| " + Color.YELLOW + "Item Effects:");
        color.log("| " + Color.YELLOW + " - ItemEffects.");
        color.log("|");
        color.log("| " + Color.YELLOW + "Commands:");
        color.log("| " + Color.YELLOW + " - BlackGuilds. (base command)");
        color.log("|");
        color.log("| " + Color.GREEN + "BlackGuilds Successfully Loaded.");
        color.log("|");
        color.log("|\\");
        color.log("|--\\");
        color.log("");

        color.log("-----------------------------------");

    }

    @Override
    public void onDisable() {

        vault.saveAllInventories(this);
        
    }

    // Vault Economy setup

    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }

    public static Economy getEconomy() {
        return econ;
    }

}
