package com.blackmc.blackguilds.GuildItems;

import com.blackmc.blackguilds.App;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

public class ItemEffects implements Listener {

    GuildItems guildItems = new GuildItems();

    public ItemEffects(App plugin) {

        plugin.getServer().getPluginManager().registerEvents(this, plugin);

    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent e) {

        ItemStack upgrader = e.getItemInHand();
        Location loc = e.getBlockPlaced().getLocation();
        Player p = e.getPlayer();

        System.out.println();

        if(upgrader.getItemMeta().getLore().equals(guildItems.getLore("upgraderLore"))) {

            e.setCancelled(true);
            if(!(p.getGameMode().equals(GameMode.CREATIVE))) upgrader.setAmount(upgrader.getAmount() - 1);

        }

    }

}
