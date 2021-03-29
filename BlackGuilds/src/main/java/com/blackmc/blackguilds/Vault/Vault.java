package com.blackmc.blackguilds.Vault;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Map.Entry;

import com.blackmc.blackguilds.App;
import com.blackmc.blackguilds.data.ConfigMenager;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class Vault {

    private ConfigMenager configMenager;
    YamlConfiguration vaultConfig;

    static HashMap<String, Inventory> inventories = new HashMap<String, Inventory>();

    public Inventory getInventory(String faction) {
        if (!inventories.containsKey(faction))
            inventories.put(faction, null);
        return inventories.get(faction);
    }

    public void setInventory(String faction, Inventory inventory) {
        if (inventories.containsKey(faction)) {
            inventories.replace(faction, inventory);
        } else {
            inventories.put(faction, inventory);
        }
    }

    public void saveAllInventories(App plugin) {

        configMenager = new ConfigMenager(plugin.getInstance());

        if (configMenager.fileNotExists("vault.yml"))
            configMenager.createConfig("vault.yml");

        vaultConfig = configMenager.getConfig("vault.yml");

        Iterator<Entry<String, Inventory>> iterator = inventories.entrySet().iterator();

        while (iterator.hasNext()) {
            Entry<String, Inventory> factionInventory = iterator.next();
            if(factionInventory != null) {
                if(factionInventory.getKey() != null && factionInventory.getValue() != null) {
                    String faction = factionInventory.getKey();
                    ItemStack[] invContents = factionInventory.getValue().getContents();
                    Integer size = factionInventory.getValue().getSize();
        
                    if (!vaultConfig.isSet(faction))
                        vaultConfig.set(faction, null);
                    saveInventory(faction, "size", size);
                    saveInventory(faction, "contents", invContents);
                }
            }
        }

        configMenager.saveConfig("vault.yml", vaultConfig);

    }

    public void loadAllInventories(App plugin) {

        configMenager = new ConfigMenager(plugin);

        if (configMenager.fileNotExists("vault.yml"))
            configMenager.createConfig("vault.yml");

        vaultConfig = configMenager.getConfig("vault.yml");

        Set<String> factions = vaultConfig.getKeys(false);

        for(String faction : factions) {
            loadInventory(faction);
        }

    }

    private void saveInventory(String faction, String path, Object invContents) {

        vaultConfig.set(faction + "." + path, invContents);

    }

    public void loadInventory(String faction) {

        List<ItemStack> contents = (List<ItemStack>) vaultConfig.get(faction + ".contents");
        ItemStack[] contentsItem = new ItemStack[contents.size()];
        contentsItem = contents.toArray(contentsItem);
        Integer size = vaultConfig.getInt(faction + ".size");


        Inventory inventory = Bukkit.createInventory(null, size);
        inventory.setContents(contentsItem);

        inventories.put(faction, inventory);

    }

}