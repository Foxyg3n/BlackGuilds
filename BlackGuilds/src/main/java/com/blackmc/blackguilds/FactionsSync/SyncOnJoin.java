package com.blackmc.blackguilds.FactionsSync;

import com.blackmc.blackguilds.App;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class SyncOnJoin implements Listener {

    App plugin;
    FacInfoMenager facInfoMenager;

    public SyncOnJoin(App plugin) {
        
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
        facInfoMenager = new FacInfoMenager(plugin);

    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {

        facInfoMenager.savePlayersInfo();

    }

}
