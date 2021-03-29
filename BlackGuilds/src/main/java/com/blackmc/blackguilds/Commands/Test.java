package com.blackmc.blackguilds.Commands;

import com.blackmc.blackguilds.App;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.neznamy.tab.api.EnumProperty;
import me.neznamy.tab.api.TABAPI;
import me.neznamy.tab.api.TabPlayer;

public class Test implements CommandExecutor {

    public Test(App plugin) {

        plugin.getCommand("test").setExecutor(this);

    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        return true;
    }

}