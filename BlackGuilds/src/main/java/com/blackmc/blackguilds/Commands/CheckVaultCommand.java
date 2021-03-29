package com.blackmc.blackguilds.Commands;

import com.blackmc.blackguilds.App;
import com.blackmc.blackguilds.Vault.Vault;
import com.blackmc.blackguilds.utils.Messanger;
import com.massivecraft.factions.FPlayer;
import com.massivecraft.factions.FPlayers;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CheckVaultCommand implements CommandExecutor {

    Vault vault = new Vault();
    FPlayers fPlayers = FPlayers.getInstance();
    Messanger messanger = new Messanger();

    public CheckVaultCommand(App plugin) {

        plugin.getCommand("sprawdzskarbiec").setExecutor(this);

    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Player p = (Player) sender;
        FPlayer fp = fPlayers.getByPlayer(p);
 
        if(args.length == 0) {

            messanger.warning(fp, "Podałeś złą liczbę argumentów");

        } else {
        
            Player player = Bukkit.getPlayer(args[0]);
            FPlayer fPlayer = fPlayers.getByPlayer(player);

            if(fPlayer.hasFaction() && vault.getInventory(fPlayer.getFaction().getTag()) != null) {
                player.openInventory(vault.getInventory(fPlayer.getFaction().getTag()));
            } else {
                messanger.warning(fPlayer, "Ten gracz nie jest w żadnej gildii lub gildia nie posiada skarbca");
            }
            
        }

        return true;

    }

}