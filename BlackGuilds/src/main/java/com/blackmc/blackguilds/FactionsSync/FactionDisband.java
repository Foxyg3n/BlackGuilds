package com.blackmc.blackguilds.FactionsSync;

import com.blackmc.blackguilds.App;
import com.massivecraft.factions.event.FactionDisbandEvent;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class FactionDisband implements Listener {

    App plugin;
    FacInfoMenager facInfoMenager;
    
    public FactionDisband(App plugin) {
        this.plugin = plugin;
        this.facInfoMenager = new FacInfoMenager(plugin);

        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onFactionDisband(FactionDisbandEvent e) {

        Bukkit.getScheduler().runTask(plugin, new Runnable() {

            @Override
            public void run() {
                facInfoMenager.saveAll();

            }

        });

    }

}