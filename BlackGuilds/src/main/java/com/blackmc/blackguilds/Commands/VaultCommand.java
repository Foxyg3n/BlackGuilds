package com.blackmc.blackguilds.Commands;

import java.util.Iterator;

import com.blackmc.blackguilds.App;
import com.blackmc.blackguilds.Vault.Vault;
import com.blackmc.blackguilds.utils.Messanger;
import com.massivecraft.factions.FPlayer;
import com.massivecraft.factions.FPlayers;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class VaultCommand implements CommandExecutor {

    Vault vault = new Vault();
    Messanger messanger = new Messanger();
    FPlayers fPlayers = FPlayers.getInstance();

    public VaultCommand(App plugin) {
        plugin.getCommand("skarbiec").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        
        if(sender instanceof Player) {
            Player p = (Player) sender;
            FPlayer fp = fPlayers.getByPlayer(p);

            if(vault.getInventory(fp.getFaction().getTag()) != null) {
                if(fp.hasFaction()) {
                    p.openInventory(vault.getInventory(fp.getFaction().getTag()));
                } else {
                  messanger.message(fp, "Nie należysz do żadnej gildii");
                }
            } else {
                messanger.message(fp, "Twoja gildia nie posiada skarbca");
            }

        } 

        return true;
    }
    
}