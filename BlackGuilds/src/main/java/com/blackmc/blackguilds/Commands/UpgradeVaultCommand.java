package com.blackmc.blackguilds.Commands;

import java.util.Arrays;
import java.util.Iterator;

import com.blackmc.blackguilds.App;
import com.blackmc.blackguilds.Vault.Vault;
import com.blackmc.blackguilds.utils.Messanger;
import com.google.gson.Gson;
import com.massivecraft.factions.FPlayer;
import com.massivecraft.factions.FPlayers;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class UpgradeVaultCommand implements CommandExecutor {

    Gson gson = new Gson();
    Vault vault = new Vault();
    Messanger messanger = new Messanger();
    FPlayers fPlayers = FPlayers.getInstance();

    public UpgradeVaultCommand(App plugin) {

        plugin.getCommand("ulepszskarbiec").setExecutor(this);

    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Player p = (Player) sender;
        FPlayer fp = fPlayers.getByPlayer(p);
 
        if(args.length < 2) {

            messanger.warning(fp, "Podałeś złą liczbę argumentów");

        } else {
        
            Player player = Bukkit.getPlayer(args[0]);
            FPlayer fPlayer = fPlayers.getByPlayer(player);
            Integer level = Integer.parseInt(args[1]);
            Integer slotSize = (level +1) * 9;

            if(fPlayer.hasFaction()) {
                if(level == 0) {

                    Inventory newInventory = null;
                    vault.setInventory(fPlayer.getFaction().getTag(), newInventory);

                } else if(level >= 1 && level <= 4) {

                    Inventory newInventory = Bukkit.createInventory(null, slotSize);

                    if(vault.getInventory(fPlayer.getFaction().getTag()) != null) {
                        ItemStack[] contents = vault.getInventory(fPlayer.getFaction().getTag()).getContents();
                        contents = Arrays.copyOfRange(contents, 0, newInventory.getSize());
                        newInventory.setContents(contents);
                    }

                    vault.setInventory(fPlayer.getFaction().getTag(), newInventory);
                    
                } else {
                    messanger.warning(fPlayer, "Nie możesz ulepszyć skarbca gildii do tego poziomu. (0 - 4)");
                }
                
            } else {
                messanger.warning(fPlayer, "Ten gracz nie jest w żadnej gildii.");
            }
            
        }

        return true;
    }

} 