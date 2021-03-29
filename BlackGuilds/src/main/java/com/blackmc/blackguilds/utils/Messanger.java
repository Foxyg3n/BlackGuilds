package com.blackmc.blackguilds.utils;

import java.util.Set;

import com.massivecraft.factions.FPlayer;
import com.massivecraft.factions.Faction;

import net.md_5.bungee.api.ChatColor;

public class Messanger {

    public void message(FPlayer player, String text) {

        player.getPlayer().sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.GOLD + "BlackGuilds" + ChatColor.DARK_GRAY
                + "] " + ChatColor.GOLD + text);

    }

    public void info(FPlayer player, String text) {

        player.getPlayer().sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.GOLD + "BlackGuilds" + ChatColor.DARK_GRAY
                + "] " + ChatColor.BLUE + text);

    }

    public void warning(FPlayer player, String text) {

        player.getPlayer().sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.GOLD + "BlackGuilds" + ChatColor.DARK_GRAY
                + "] " + ChatColor.DARK_RED + text);

    }

    public void confirm(FPlayer player, String text) {

        player.getPlayer().sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.GOLD + "BlackGuilds" + ChatColor.DARK_GRAY
                + "] " + ChatColor.GREEN + text);

    }

    public void factionMessage(FPlayer fPlayer, String text) {

        Set<FPlayer> factionMembers = fPlayer.getFaction().getFPlayersWhereOnline(true);

        for (FPlayer player : factionMembers) {
            player.getPlayer().sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.GOLD + "BlackGuilds"
                    + ChatColor.DARK_GRAY + "] " + ChatColor.GOLD + text);
        }

    }

    public void factionMessage(Faction faction, String text) {

        Set<FPlayer> factionMembers = faction.getFPlayersWhereOnline(true);

        for (FPlayer player : factionMembers) {
            player.getPlayer().sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.GOLD + "BlackGuilds"
                    + ChatColor.DARK_GRAY + "] " + ChatColor.GOLD + text);
        }

    }

    public void factionInfo(FPlayer fPlayer, String text) {

        Set<FPlayer> factionMembers = fPlayer.getFaction().getFPlayersWhereOnline(true);

        for (FPlayer player : factionMembers) {
            player.getPlayer().sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.GOLD + "BlackGuilds"
                    + ChatColor.DARK_GRAY + "] " + ChatColor.GOLD + text);
        }

    }

    public void factionInfo(Faction faction, String text) {

        Set<FPlayer> factionMembers = faction.getFPlayersWhereOnline(true);

        for (FPlayer player : factionMembers) {
            player.getPlayer().sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.GOLD + "BlackGuilds"
                    + ChatColor.DARK_GRAY + "] " + ChatColor.GOLD + text);
        }

    }

    public void factionWarning(FPlayer fPlayer, String text) {

        Set<FPlayer> factionMembers = fPlayer.getFaction().getFPlayersWhereOnline(true);

        for (FPlayer player : factionMembers) {
            player.getPlayer().sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.GOLD + "BlackGuilds"
                    + ChatColor.DARK_GRAY + "] " + ChatColor.GOLD + text);
        }

    }

    public void factionWarning(Faction faction, String text) {

        Set<FPlayer> factionMembers = faction.getFPlayersWhereOnline(true);

        for (FPlayer player : factionMembers) {
            player.getPlayer().sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.GOLD + "BlackGuilds"
                    + ChatColor.DARK_GRAY + "] " + ChatColor.GOLD + text);
        }

    }

    public void factionConfirm(FPlayer fPlayer, String text) {

        Set<FPlayer> factionMembers = fPlayer.getFaction().getFPlayersWhereOnline(true);

        for (FPlayer player : factionMembers) {
            player.getPlayer().sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.GOLD + "BlackGuilds"
                    + ChatColor.DARK_GRAY + "] " + ChatColor.GOLD + text);
        }

    }

    public void factionConfirm(Faction faction, String text) {

        Set<FPlayer> factionMembers = faction.getFPlayersWhereOnline(true);

        for(FPlayer player : factionMembers) {
            player.getPlayer().sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.GOLD + "BlackGuilds" + ChatColor.DARK_GRAY + "] " + ChatColor.GOLD + text);
        }

    }

}