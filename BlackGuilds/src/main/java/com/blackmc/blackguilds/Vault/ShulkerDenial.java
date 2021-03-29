package com.blackmc.blackguilds.Vault;

import java.util.Iterator;

import com.blackmc.blackguilds.App;
import com.massivecraft.factions.FPlayer;
import com.massivecraft.factions.FPlayers;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.ShulkerBox;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BlockStateMeta;

public class ShulkerDenial implements Listener {

    Vault vault = new Vault();

    public ShulkerDenial(App plugin) {

        Bukkit.getPluginManager().registerEvents(this, plugin);

    }

    @EventHandler
    public void shulkerBlock(InventoryClickEvent e) {

        if(e.getCurrentItem() != null) {

            Player p = Bukkit.getPlayer(e.getWhoClicked().getName());
            FPlayer fp = getFPlayer(p.getName());

            Inventory inv = e.getView().getTopInventory();
            ItemStack t = e.getCurrentItem();
            ItemStack k = e.getCursor();

            if(t.getItemMeta() instanceof BlockStateMeta || k.getItemMeta() instanceof BlockStateMeta) {
                BlockStateMeta tStateMeta = (BlockStateMeta) t.getItemMeta();
                BlockStateMeta kStateMeta = (BlockStateMeta) k.getItemMeta();
                if(tStateMeta == null) {
                    
                    if(kStateMeta.getBlockState() instanceof ShulkerBox) {
                        if (t != null || t.getType() != Material.AIR || k != null || k.getType() != Material.AIR) {
                            if(vault.getInventory(fp.getFaction().getTag()).equals(inv)) {
                                e.setCancelled(true);
                            }
                        }
                    }

                } else if(kStateMeta == null) {

                    if(tStateMeta.getBlockState() instanceof ShulkerBox) {
                        if (t != null || t.getType() != Material.AIR || k != null || k.getType() != Material.AIR) {
                            if(vault.getInventory(fp.getFaction().getTag()).equals(inv)) {
                                e.setCancelled(true);
                            }
                        }
                    }
                    
                }
            }

        } else {

            Player p = Bukkit.getPlayer(e.getWhoClicked().getName());
            FPlayer fp = getFPlayer(p.getName());

            Inventory inv = e.getView().getTopInventory();
            ItemStack k = e.getCursor();

            if(k.getItemMeta() instanceof BlockStateMeta) {
                BlockStateMeta kStateMeta = (BlockStateMeta) k.getItemMeta();
                if(kStateMeta.getBlockState() instanceof ShulkerBox) {
                    if (k != null || k.getType() != Material.AIR) {
                        if(vault.getInventory(fp.getFaction().getTag()).equals(inv)) {
                            e.setCancelled(true);
                        }
                    }
                }
            }

        }

    }

    private FPlayer getFPlayer(String nickname) {

        Iterator players = FPlayers.getInstance().getAllFPlayers().iterator();
        while (players.hasNext()) {
            FPlayer fPlayer = (FPlayer) players.next();
            if (fPlayer.getName().equals(nickname)) return fPlayer;
        }

        return null;

    }

}