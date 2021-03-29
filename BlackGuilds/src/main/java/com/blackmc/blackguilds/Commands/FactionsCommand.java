package com.blackmc.blackguilds.Commands;

import com.blackmc.blackguilds.App;
import com.blackmc.blackguilds.FactionsSync.FacInfoMenager;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class FactionsCommand implements Listener {

    App plugin;
    FacInfoMenager facInfoMenager;
    
    public FactionsCommand(App plugin) {

        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);

        facInfoMenager = new FacInfoMenager(plugin);

    }

    @EventHandler
    public void onFactionsCommand(PlayerCommandPreprocessEvent e) {

        String message = e.getMessage();
        String[] array = message.split(" ");

        if(array[0].equalsIgnoreCase("/f") || array[0].equalsIgnoreCase("/fac") || array[0].equalsIgnoreCase("/facs") || array[0].equalsIgnoreCase("/factions")
        || array[0].equalsIgnoreCase("/fax") || array[0].equalsIgnoreCase("/factionsx") || array[0].equalsIgnoreCase("/factionx") || array[0].equalsIgnoreCase("/fx")
        || array[0].equalsIgnoreCase("/blackguilds") || array[0].equalsIgnoreCase("/bc")) {

            Bukkit.getScheduler().runTask(plugin, new Runnable() {

				@Override
				public void run() {

                    facInfoMenager.saveAll();
					
				}
                
            });
            
        }

    }

}
