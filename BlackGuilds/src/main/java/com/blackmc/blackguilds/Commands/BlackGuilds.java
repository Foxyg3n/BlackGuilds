package com.blackmc.blackguilds.Commands;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.blackmc.blackguilds.App;
import com.blackmc.blackguilds.Economics.Economy;
import com.blackmc.blackguilds.GuildItems.GuildItems;
import com.massivecraft.factions.FPlayer;
import com.massivecraft.factions.FPlayers;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class BlackGuilds implements CommandExecutor, TabCompleter {

    GuildItems guildItems = new GuildItems();
    FPlayers fPlayers = FPlayers.getInstance();

    Economy economy;

    public BlackGuilds(App plugin) {

        plugin.getCommand("blackguilds").setExecutor(this);
        plugin.getCommand("blackguilds").setTabCompleter(this);

        economy = new Economy(plugin);

    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (sender instanceof Player) {

            Player p = (Player) sender;

            if (args.length == 0) { // * 0 argumentów

                p.sendMessage("/blackguilds <option>");

            } else if (args.length == 1) { // * 1 argument

                if (args[0].equals("items")) { // ITEMS

                    p.sendMessage("/blackguilds items [get|give]");

                } else if (args[0].equals("gems")) { // GEMS

                    p.sendMessage("/blackguilds gems [reset|set|add|remove|get]");

                } else if (args[0].equals("crystals")) { // CRYSTALS

                    p.sendMessage("/blackguilds crystals [reset|set|add|remove|get]");

                }

            } else if (args.length == 2) { // * 2 argumenty

                if (args[0].equals("items")) { // ITEMS

                    if (args[1].equals("get")) {

                        p.sendMessage("/blackguilds items get <item>");

                    } else if (args[1].equals("give")) {

                        p.sendMessage("/blackguilds items give <item> <player>");

                    }

                } else if (args[0].equals("gems")) { // GEMS

                    if (args[1].equals("set")) {

                        p.sendMessage("/blackguilds gems set <player> <amount>");

                    } else if (args[1].equals("reset")) {

                        p.sendMessage("/blackguilds gems reset <player>");

                    } else if (args[1].equals("add")) {

                        p.sendMessage("/blackguilds gems add <player> <amount>");

                    } else if (args[1].equals("remove")) {

                        p.sendMessage("/blackguilds gems remove <player> <amount>");

                    } else if (args[1].equals("get")) {

                        p.sendMessage("/blackguilds gems get <player> <amount>");

                    }

                } else if (args[0].equals("crystals")) { // CRYSTALS

                    if (args[1].equals("set")) {

                        p.sendMessage("/blackguilds crystals set <player> <amount>");

                    } else if (args[1].equals("reset")) {

                        p.sendMessage("/blackguilds crystals reset <player>");

                    } else if (args[1].equals("add")) {

                        p.sendMessage("/blackguilds crystals add <player> <amount>");

                    } else if (args[1].equals("remove")) {

                        p.sendMessage("/blackguilds crystals removet <player> <amount>");

                    } else if (args[1].equals("get")) {

                        p.sendMessage("/blackguilds crystals get <player>");

                    }

                }

            } else if (args.length == 3) { // * 3 argumenty

                if (args[0].equals("items")) { // ITEMS

                    if (args[1].equals("get")) {

                        if (args[2].equals("upgrader")) {

                            ItemStack upgrader = guildItems.getItem("upgrader");
                            p.getInventory().addItem(upgrader);

                        }

                    } else if (args[1].equals("give")) {

                        if (args[2].equals("upgrader")) {

                            ItemStack upgrader = guildItems.getItem("upgrader");
                            p.getInventory().addItem(upgrader);

                        }

                    }

                } else if (args[0].equals("gems")) { // GEMS

                    if (args[1].equals("set")) {

                        String nickname = args[2];

                        p.sendMessage("/blackguilds gems set " + nickname + " <amount>");

                    } else if (args[1].equals("reset")) {

                        String nickname = args[2];
                        FPlayer fplayer = fPlayers.getByPlayer(Bukkit.getPlayer(nickname));

                        if (fplayer == null) {
                            p.sendMessage("Nie ma takiego gracza");
                        } else {
                                economy.setPlayersGems(fplayer, "0");
                                return true;
                        }

                    } else if (args[1].equals("remove")) {

                        String nickname = args[2];

                        p.sendMessage("/blackguilds gems remove " + nickname + " <amount>");

                    } else if (args[1].equals("add")) {

                        String nickname = args[2];

                        p.sendMessage("/blackguilds gems add " + nickname + " <amount>");

                    } else if (args[1].equals("get")) {

                        String nickname = args[2];
                        FPlayer fPlayer = fPlayers.getByPlayer(Bukkit.getPlayer(nickname));

                        if (fPlayer == null) {
                            p.sendMessage("Nie ma takiego gracza");
                        } else {
                            p.sendMessage("Stan klejnotow gracza " + nickname + ": " + economy.getPlayersGems(fPlayer));
                        }

                    }

                } else if (args[0].equals("crystals")) { // CRYSTALS

                    if (args[1].equals("set")) {

                        String nickname = args[2];

                        p.sendMessage("/blackguilds crystals set " + nickname + " <amount>");

                    } else if (args[1].equals("reset")) {

                        String nickname = args[2];
                        FPlayer fplayer = fPlayers.getByPlayer(Bukkit.getPlayer(nickname));

                        if (fplayer == null) {
                            p.sendMessage("Nie ma takiego gracza lub gracz nie jest w gildii");
                        } else {
                            economy.setFactionsCrystals(fplayer.getFaction(), "0");
                            return true;
                        }

                    } else if (args[1].equals("remove")) {

                        String nickname = args[2];

                        p.sendMessage("/blackguilds crystals remove " + nickname + " <amount>");

                    } else if (args[1].equals("add")) {

                        String nickname = args[2];

                        p.sendMessage("/blackguilds crystals add " + nickname + " <amount>");

                    } else if (args[1].equals("get")) {

                        String nickname = args[2];
                        FPlayer fPlayer = fPlayers.getByPlayer(Bukkit.getPlayer(nickname));

                        if (fPlayer == null) {
                            p.sendMessage("Nie ma takiego gracza lub gracz nie jest w gildii");
                        } else {
                            p.sendMessage("Stan klejnotow gracza " + nickname + ": " + economy.getFactionsCrystals(fPlayer.getFaction()));
                        }

                    }

                }

            } else if (args.length == 4) { // * 4 argumenty

                if (args[0].equals("gems")) { // GEMS

                    if (args[1].equals("set")) {

                        if(isNotNumber(args[3])) {
                            p.sendMessage("Niepoprawna liczba klejnotów");
                            return true;
                        }

                        String nickname = args[2];
                        String value = args[3];
                        FPlayer fPlayer = fPlayers.getByPlayer(Bukkit.getPlayer(nickname));

                        if (fPlayer == null) {
                            p.sendMessage("Nie ma takiego gracza");
                        } else {
                            economy.setPlayersGems(fPlayer, value);
                        }

                    } else if(args[1].equals("add")) {

                        if(isNotNumber(args[3])) {
                            p.sendMessage("Niepoprawna liczba klejnotów");
                            return true;
                        }

                        String nickname = args[2];
                        FPlayer fPlayer = fPlayers.getByPlayer(Bukkit.getPlayer(nickname));
                        Integer value = Integer.parseInt(args[3]);

                        if (fPlayer == null) {
                            p.sendMessage("Nie ma takiego gracza");
                        } else {
                            Integer baseValue = economy.getPlayersGems(fPlayer);
                            economy.setPlayersGems(fPlayer, Integer.toString(baseValue + value));
                        }

                    } else if(args[1].equals("remove")) {

                        if(isNotNumber(args[3])) {
                            p.sendMessage("Niepoprawna liczba klejnotów");
                            return true;
                        }

                        String nickname = args[2];
                        FPlayer fPlayer = fPlayers.getByPlayer(Bukkit.getPlayer(nickname));
                        Integer value = Integer.parseInt(args[3]);

                        if (fPlayer == null) {
                            p.sendMessage("Nie ma takiego gracza");
                        } else {
                            Integer baseValue = economy.getPlayersGems(fPlayer);
                            economy.setPlayersGems(fPlayer, Integer.toString(baseValue - value));
                        }

                    }

                } else if (args[0].equals("crystals")) { // CRYSTALS

                    if (args[1].equals("set")) {

                        if(isNotNumber(args[3])) {
                            p.sendMessage("Niepoprawna liczba kryształów");
                            return true;
                        }

                        String nickname = args[2];
                        String value = args[3];
                        FPlayer fPlayer = fPlayers.getByPlayer(Bukkit.getPlayer(nickname));

                        if (fPlayer == null) {
                            p.sendMessage("Nie ma takiego gracza lub gracz nie jest w gildii");
                        } else {
                            economy.setFactionsCrystals(fPlayer.getFaction(), value);
                        }

                    } else if(args[1].equals("add")) {

                        if(isNotNumber(args[3])) {
                            p.sendMessage("Niepoprawna liczba kryształów");
                            return true;
                        }

                        String nickname = args[2];
                        FPlayer fPlayer = fPlayers.getByPlayer(Bukkit.getPlayer(nickname));
                        Integer value = Integer.parseInt(args[3]);

                        if (fPlayer == null) {
                            p.sendMessage("Nie ma takiego gracza lub gracz nie jest w gildii");
                        } else {
                            Integer baseValue = economy.getFactionsCrystals(fPlayer.getFaction());
                            economy.setFactionsCrystals(fPlayer.getFaction(), Integer.toString(baseValue + value));
                        }

                    } else if(args[1].equals("remove")) {

                        if(isNotNumber(args[3])) {
                            p.sendMessage("Niepoprawna liczba kryształów");
                            return true;
                        }

                        String nickname = args[2];
                        FPlayer fPlayer = fPlayers.getByPlayer(Bukkit.getPlayer(nickname));
                        Integer value = Integer.parseInt(args[3]);

                        if (fPlayer == null) {
                            p.sendMessage("Nie ma takiego gracza lub gracz nie jest w gildii");
                        } else {
                            Integer baseValue = economy.getFactionsCrystals(fPlayer.getFaction());
                            economy.setFactionsCrystals(fPlayer.getFaction(), Integer.toString(baseValue - value));
                        }

                    }

                }

            }

        } else { // * Console

            if(args.length == 3) { // * 3 argumenty

                if(args[0].equals("gems")) { //gems

                    if (args[1].equals("reset")) {

                        String nickname = args[2];
                        FPlayer fplayer = fPlayers.getByPlayer(Bukkit.getPlayer(nickname));

                        if (fplayer != null) {
                            economy.setPlayersGems(fplayer, "0");
                            return true;
                        }

                    } else if (args[1].equals("get")) {

                        String nickname = args[2];
                        FPlayer fPlayer = fPlayers.getByPlayer(Bukkit.getPlayer(nickname));

                        if (fPlayer != null) {
                            System.out.println("Stan klejnotow gracza " + nickname + ": " + economy.getPlayersGems(fPlayer));
                        }

                    }

                } else if(args[0].equals("crystals")) {

                    if (args[1].equals("reset")) {

                        String nickname = args[2];
                        FPlayer fplayer = fPlayers.getByPlayer(Bukkit.getPlayer(nickname));

                        if (fplayer != null) {
                            economy.setFactionsCrystals(fplayer.getFaction(), "0");
                            return true;
                        }

                    } else if (args[1].equals("get")) {

                        String nickname = args[2];
                        FPlayer fPlayer = fPlayers.getByPlayer(Bukkit.getPlayer(nickname));

                        if (fPlayer != null) {
                            System.out.println("Stan klejnotow gracza " + nickname + ": " + economy.getFactionsCrystals(fPlayer.getFaction()));
                        }

                    }

                }

            } else if(args.length == 4) { // * 4 argumenty

                if(args[0].equals("gems")) { // GEMS

                    if (args[1].equals("set")) {

                        if(isNotNumber(args[3])) {
                            return true;
                        }

                        String nickname = args[2];
                        String value = args[3];
                        FPlayer fPlayer = fPlayers.getByPlayer(Bukkit.getPlayer(nickname));

                        if (fPlayer != null) {
                            economy.setPlayersGems(fPlayer, value);
                        }

                    } else if(args[1].equals("add")) {

                        if(isNotNumber(args[3])) {
                            return true;
                        }

                        String nickname = args[2];
                        FPlayer fPlayer = fPlayers.getByPlayer(Bukkit.getPlayer(nickname));
                        Integer value = Integer.parseInt(args[3]);

                        if (fPlayer != null) {
                            Integer baseValue = economy.getPlayersGems(fPlayer);
                            economy.setPlayersGems(fPlayer, Integer.toString(baseValue + value));
                        }

                    } else if(args[1].equals("remove")) {

                        if(isNotNumber(args[3])) {
                            return true;
                        }

                        String nickname = args[2];
                        FPlayer fPlayer = fPlayers.getByPlayer(Bukkit.getPlayer(nickname));
                        Integer value = Integer.parseInt(args[3]);

                        if (fPlayer != null) {
                            Integer baseValue = economy.getPlayersGems(fPlayer);
                            economy.setPlayersGems(fPlayer, Integer.toString(baseValue - value));
                        }

                    }

                } else if(args[0].equals("crystals")) { // CRYSTALS

                    if (args[1].equals("set")) {

                        if(isNotNumber(args[3])) {
                            return true;
                        }

                        String nickname = args[2];
                        String value = args[3];
                        FPlayer fPlayer = fPlayers.getByPlayer(Bukkit.getPlayer(nickname));

                        if (fPlayer != null) {
                            economy.setFactionsCrystals(fPlayer.getFaction(), value);
                        }

                    } else if(args[1].equals("add")) {

                        if(isNotNumber(args[3])) {
                            return true;
                        }

                        String nickname = args[2];
                        FPlayer fPlayer = fPlayers.getByPlayer(Bukkit.getPlayer(nickname));
                        Integer value = Integer.parseInt(args[3]);

                        if (fPlayer != null) {
                            Integer baseValue = economy.getFactionsCrystals(fPlayer.getFaction());
                            economy.setFactionsCrystals(fPlayer.getFaction(), Integer.toString(baseValue + value));
                        }

                    } else if(args[1].equals("remove")) {

                        if(isNotNumber(args[3])) {
                            return true;
                        }

                        String nickname = args[2];
                        FPlayer fPlayer = fPlayers.getByPlayer(Bukkit.getPlayer(nickname));
                        Integer value = Integer.parseInt(args[3]);

                        if (fPlayer != null) {
                            Integer baseValue = economy.getFactionsCrystals(fPlayer.getFaction());
                            economy.setFactionsCrystals(fPlayer.getFaction(), Integer.toString(baseValue - value));
                        }

                    }

                }

            }

        }

        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String alias, String[] args) {
        List<String> completions = new ArrayList<String>();

        if(args.length == 1) { // * 1 argument

            completions.add("gems");
            completions.add("crystals");
            completions.add("items");

            return completions;

        } else if(args.length == 2) { // * 2 argumenty

            if(args[0].equals("gems") || args[0].equals("crystals")) {

                completions.add("set");
                completions.add("reset");
                completions.add("add");
                completions.add("remove");
                completions.add("get");

            } else if(args[1].equals("items")) {

                completions.add("get");

            }

            return completions;

        } else if(args.length == 3) { // * 3 argumenty

            if(args[0].equals("gems") || args[0].equals("crystals")) {

                Iterator<? extends Player> players = Bukkit.getOnlinePlayers().iterator();
                while(players.hasNext()) {
                    Player player = players.next();
                    completions.add(player.getName());
                }

            } else if(args[1].equals("items")) {

                completions.add("upgrader");

            }

            return completions;

        } else if(args.length == 4) { // * 4 argumenty

            if((args[0].equals("gems") || args[0].equals("crystals"))) {

                if(!(args[1].equals("remove") || args[1].equals("get"))) {

                    completions.add("0");

                }

            }

            return completions;

        }

        return null;
    }

    private boolean isNotNumber(String number) {

        try {
            Integer.parseInt(number);
            return false;
        } catch (Exception e) {
            return true;
        }

    }

}