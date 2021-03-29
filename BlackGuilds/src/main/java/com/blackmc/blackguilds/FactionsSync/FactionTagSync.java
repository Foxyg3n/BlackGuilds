package com.blackmc.blackguilds.FactionsSync;

import com.blackmc.blackguilds.App;
import com.massivecraft.factions.FPlayer;
import com.massivecraft.factions.FPlayers;
import com.massivecraft.factions.event.FPlayerJoinEvent;
import com.massivecraft.factions.event.FPlayerLeaveEvent;
import com.massivecraft.factions.event.FactionCreateEvent;
import com.massivecraft.factions.event.FactionDisbandEvent;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import me.neznamy.tab.api.EnumProperty;
import me.neznamy.tab.api.TABAPI;
import me.neznamy.tab.api.TabPlayer;
import net.md_5.bungee.api.ChatColor;

public class FactionTagSync implements Listener {
 
    public FactionTagSync(App plugin) {

        Bukkit.getPluginManager().registerEvents(this, plugin);

    }

    @EventHandler
    public void onServerJoin(PlayerJoinEvent e) {

        FPlayer p = FPlayers.getInstance().getByPlayer(e.getPlayer());

        syncTag(p, p.hasFaction() ? p.getFaction().getTag() : null);

    }

    @EventHandler
    public void onJoin(FPlayerJoinEvent e) {

        FPlayer p = e.getfPlayer();

        syncTag(p, e.getFaction().getTag());

    }

    @EventHandler
    public void onLeave(FPlayerLeaveEvent e) {

        FPlayer p = e.getfPlayer();

        syncTag(p, null);

    }

    @EventHandler
    public void onCreate(FactionCreateEvent e) {

        FPlayer p = e.getFPlayer();

        syncTag(p, e.getFactionTag());

    }

    @EventHandler
    public void onDisband(FactionDisbandEvent e) {

        FPlayer p = e.getFPlayer();

        syncTag(p, null);

    }

    public void syncTag(FPlayer p, String faction) {
        
        TabPlayer tabPlayer = TABAPI.getPlayer(p.getPlayer().getUniqueId());

        if(faction != null) {
            tabPlayer.setValuePermanently(EnumProperty.BELOWNAME, ChatColor.RED + "%health% HP " + ChatColor.DARK_GRAY + "{" + ChatColor.GOLD + faction + ChatColor.DARK_GRAY + "}");
        } else {
            tabPlayer.setValuePermanently(EnumProperty.BELOWNAME, ChatColor.RED + "%health% HP");
        }
        
    }

}