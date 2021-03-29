package com.blackmc.blackguilds.GuildItems;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.block.Chest;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.json.simple.JSONObject;

public class GuildItems {

    JSONObject specialItems = new JSONObject();
    JSONObject specialItemsLore = new JSONObject();

    public GuildItems() {

        //Chest
        ItemStack upgrader = new ItemStack(Material.CHEST);
        ArrayList<String> upgraderLore = new ArrayList<String>();
        ItemMeta upgraderMeta = upgrader.getItemMeta();

        upgraderLore.add("upgrader");

        upgraderMeta.setLore(upgraderLore);
        upgrader.setItemMeta(upgraderMeta);

        //SpecialItems
        specialItems.put("upgrader", upgrader);

        //SpecialLore
        specialItemsLore.put("upgraderLore", upgraderLore);

    }

    // FUNCTIONS

    public ItemStack getItem(String name) {
        return (ItemStack) specialItems.get(name);
    }

    public ArrayList<String> getLore(String name) {
        return (ArrayList) specialItemsLore.get(name);
    }

}
