package com.blackmc.blackguilds.Commands;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import com.blackmc.blackguilds.App;
import com.blackmc.blackguilds.utils.Messanger;
import com.massivecraft.factions.FPlayer;
import com.massivecraft.factions.FPlayers;
import com.massivecraft.factions.Faction;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

public class Raid implements CommandExecutor, TabCompleter {

    public static HashMap<String, String> phases = new HashMap<String, String>();
    public static HashMap<String, ArrayList<FPlayer>> raidPlayers = new HashMap<String, ArrayList<FPlayer>>();
    public static ArrayList<Location> raidLocations = new ArrayList<Location>();

    App plugin;

    Messanger messanger = new Messanger();
    FPlayers fPlayers = FPlayers.getInstance();

    public Raid(App plugin) {

        this.plugin = plugin;
        plugin.getCommand("raid").setExecutor(this);

        World world = Bukkit.getServer().getWorld("world_nether");

        raidLocations.add(new Location(world, 269, 159, -606));
        raidLocations.add(new Location(world, 261, 159, -590));
        raidLocations.add(new Location(world, 273, 159, -575));
        raidLocations.add(new Location(world, 287, 159, -609));
        raidLocations.add(new Location(world, 304, 159, -612));
        raidLocations.add(new Location(world, 181, 159, -505));
        raidLocations.add(new Location(world, 162, 159, -541));
        raidLocations.add(new Location(world, 149, 159, -528));
        raidLocations.add(new Location(world, 164, 159, -487));
        raidLocations.add(new Location(world, 112, 159, -589));
        raidLocations.add(new Location(world, 109, 159, -604));
        raidLocations.add(new Location(world, 88, 159, -607));
        raidLocations.add(new Location(world, 84, 159, -569));
        raidLocations.add(new Location(world, 212, 159, -639));
        raidLocations.add(new Location(world, 232, 159, -636));
        raidLocations.add(new Location(world, 250, 159, -648));
        raidLocations.add(new Location(world, 203, 159, -659));

    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {

            Player p = (Player) sender;
            FPlayer fp = fPlayers.getByPlayer(p);

            if (args.length == 0) {

                messanger.warning(fp, "/rajd <opcja>");
                messanger.info(fp, "Wpisz /rajd help, aby otrzymać pomoc");
                return true;

            } else {

                if (args[0].equals("start")) {

                    if(fp.getFaction().getFPlayerLeader() != null) {

                        if (fp.getFaction().getFPlayerLeader().equals(fp)) {

                            String phase = getPhase(fp.getFaction().getTag());

                            if (phase == "stopped" || !raidStarted()) {

                                if (fp.hasMoney(150000)) {

                                    phases.replace(fp.getFaction().getTag(), "awaiting");

                                    messanger.confirm(fp, "Zacząłeś rajd w gildii");

                                    Bukkit.getScheduler().runTaskTimer(plugin, new Runnable() {

                                        Integer cooldown = 180;

                                        @Override
                                        public void run() {

                                            Faction faction = fp.getFaction();

                                            if (getPhase(faction.getTag()).equals("awaiting")) {

                                                if (cooldown == 180) {
                                                    messanger.factionMessage(fp.getFaction(),
                                                            "Za 3 minuty rozpocznie się rajd");
                                                    messanger.factionMessage(fp.getFaction(),
                                                            "Napisz /raid join, aby dołączyć do rajdu");
                                                    messanger.info(fp, "Napisz /raid start, aby rozpocząć raid");
                                                } else if (cooldown == 120) {
                                                    messanger.factionMessage(fp.getFaction(),
                                                            "Za 2 minuty rozpocznie się rajd");
                                                } else if (cooldown == 60) {
                                                    messanger.factionMessage(fp.getFaction(),
                                                            "Za 1 minutę rozpocznie się rajd");
                                                } else if (cooldown == 10) {
                                                    messanger.factionMessage(fp.getFaction(),
                                                            "Zostało 10 sekund do rozpoczęcia rajdu");
                                                } else if (cooldown == 0) {
                                                    boolean isRaid = startRaid(fp);
                                                    if (isRaid) {
                                                        phases.replace(faction.getTag(), "started");
                                                    } else {
                                                        phases.replace(faction.getTag(), "stopped");
                                                        if (!raidPlayers.get(fp.getFaction().getTag()).isEmpty())
                                                            raidPlayers.get(fp.getFaction().getTag()).clear();
                                                    }
                                                }

                                                cooldown -= 10;

                                            } else {

                                                return;

                                            }

                                        }

                                    }, 0, 10 * 20);

                                    addRaidPlayer(fp);
                                    messanger.factionMessage(fp, "Gracz dołączył do rajdu, aktualni gracze:");
                                    messanger.factionMessage(fp, getRaidPlayersString(fp));

                                } else {

                                    messanger.warning(fp,
                                            "Stan Twojego konta nie jest wystarczający, aby zaczać rajd (150 000)");

                                }

                            } else if (phase == "awaiting") {

                                Boolean isRaid = startRaid(fp);

                                if (isRaid) {
                                    phases.replace(fp.getFaction().getTag(), "started");
                                } else {
                                    phases.replace(fp.getFaction().getTag(), "stopped");
                                    if (!raidPlayers.get(fp.getFaction().getTag()).isEmpty())
                                        raidPlayers.get(fp.getFaction().getTag()).clear();
                                }

                            } else if (phase == "started") {

                                messanger.warning(fp, "Rajd już się zaczął");

                            }

                        } else {

                            messanger.warning(fp,
                                    "Nie masz uprawnień gildii, aby rozpoczynać rajd lub nie należysz do gildii.");

                        }
                        
                    }

                } else if (args[0].equals("join")) {

                    String phase = phases.get(fp.getFaction().getTag());

                    if (phase == "stopped") {

                        messanger.warning(fp, "Rajd jeszcze się nie zaczął");

                    } else if (phase == "awaiting") {

                        addRaidPlayer(fp);
                        messanger.confirm(fp, "Dołączono do rajdu");
                        messanger.factionMessage(fp, "Gracz dołączył do rajdu, aktualni gracze:");
                        messanger.factionMessage(fp, getRaidPlayersString(fp));

                    } else if (phase == "started") {

                        messanger.warning(fp, "Rajd jest już w trakcie");

                    }

                } else if (args[0].equals("remove")) {

                    String phase = phases.get(fp.getFaction().getTag());

                    if (phase == "stopped") {

                        messanger.warning(fp, "Rajd jeszcze się nie zaczął");

                    } else if (phase == "awaiting") {

                        if (fp.getFaction().getFPlayerLeader().equals(fp)) {

                            messanger.warning(fp, "Nie możesz wyjść z rajdu jako leader");
                            messanger.info(fp, "Jeżeli chcesz zatrzymać rajd, wpisz /rajd stop");

                        } else {

                            removeRaidPlayer(fp);
                            messanger.confirm(fp, "Usunięto z rajdu");
                            messanger.factionMessage(fp, "Gracz odłączył się do rajdu, aktualni gracze:");
                            messanger.factionMessage(fp, getRaidPlayersString(fp));

                        }

                    } else if (phase == "started") {

                        messanger.warning(fp, "Rajd jest już w trakcie");

                    }

                } else if (args[0].equals("stop")) {

                    if(fp.getFaction().getFPlayerLeader() != null) {

                        if (fp.getFaction().getFPlayerLeader().equals(fp)) {

                            String phase = getPhase(fp.getFaction().getTag());

                            if (phase == "stopped") {

                                messanger.warning(fp, "Rajd w gildii już jest zatrzymany");

                            } else if (phase == "awaiting") {

                                phases.replace(fp.getFaction().getTag(), "stopped");

                                messanger.factionMessage(fp, "Rajd w gildii został zatrzymany");

                            } else if (phase == "started") {

                                messanger.warning(fp, "Nie możesz zatrzymać już rozpoczętego rajdu");

                            }

                        } else {

                            messanger.warning(fp,
                                    "Nie masz uprawnień gildii, aby rozpoczynać rajd lub nie należysz do gildii.");

                        }
                        
                    }

                } else if (args[0].equals("help")) {

                    messanger.message(fp, "Pomoc do rajdów");
                    messanger.message(fp,
                            "W gildii można rozpocząć rajd jako lider.\nMogą do niego dołączyć gracze z całej gildii, konieczne są jednak przygotowania,\ngdyż boss będzie silny a po drodze będą czekały na gracza wyzwania oraz pułapki\n(ps. przydadzą się potki na lawę :)");
                    messanger.message(fp, "Komendy do rajdów");
                    messanger.message(fp,
                            "/rajd start - rozpoczyna zbieranie graczy na rajd (lub w fazie zbierania graczy rozpoczyna rajd)");
                    messanger.message(fp, "/rajd stop - zatrzymuje rajd");
                    messanger.message(fp, "/rajd join - dołącza gracza do rajdu gildii");
                    messanger.message(fp, "/rajd remove - usuwa gracza z rajdu gildii");

                }

            }

        }

        return true;
    }

    public String getPhase(String faction) {

        if (!phases.containsKey(faction))
            phases.put(faction, "stopped");

        return phases.get(faction);

    }

    public void addRaidPlayer(FPlayer fPlayer) {

        if (!raidPlayers.containsKey(fPlayer.getFaction().getTag()))
            raidPlayers.put(fPlayer.getFaction().getTag(), new ArrayList<FPlayer>());

        ArrayList<FPlayer> raiders = raidPlayers.get(fPlayer.getFaction().getTag());
        if (!raiders.contains(fPlayer))
            raiders.add(fPlayer);

    }

    public void removeRaidPlayer(FPlayer fPlayer) {

        if (!raidPlayers.containsKey(fPlayer.getFaction().getTag()))
            raidPlayers.put(fPlayer.getFaction().getTag(), new ArrayList<FPlayer>());

        ArrayList<FPlayer> raiders = raidPlayers.get(fPlayer.getFaction().getTag());
        raiders.remove(fPlayer);

    }

    public boolean startRaid(FPlayer p) {

        String faction = p.getFaction().getTag();

        if (p.hasMoney(150000)) {

            if (raidPlayers.containsKey(faction)) {

                if (!raidPlayers.get(faction).isEmpty()) {

                    messanger.factionMessage(p, "Rajd się zaczął, na łowy!");

                    p.takeMoney(150000);

                    for (FPlayer player : raidPlayers.get(faction)) {
                        Location loc = new Location(Bukkit.getServer().getWorld("world_nether"), -104.5, 198, -1384.5); // raidLocations.get(randomNum(0,
                                                                                                                    // raidLocations.size()
                                                                                                                    // -
                                                                                                                    // 1));
                        player.getPlayer().teleport(loc);
                    }

                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "brutal");

                    return true;

                }

            }

            messanger.warning(p, "Do rajdu nie dołączyli żadni gracze");

        } else {

            messanger.warning(p, "Gildia nie posiada wystarczająco monet, aby zacząć rajd (150 000)");

        }

        return false;

    }

    public void stopRaid(FPlayer fp, String state) {

        if (state.equals("win")) {

            ArrayList<FPlayer> players = raidPlayers.get(fp.getFaction().getTag());
            World world = Bukkit.getWorld("world");

            messanger.factionMessage(fp, "Twoja gildia wygrała rajd!");

            for (FPlayer player : players) {

                player.getPlayer().teleport(new Location(world, -92.5, 88, 161.5));

            }

            players.clear();

        } else if(state.equals("lose")) {

            messanger.factionMessage(fp, "Twoja gildia przegrała rajd!");

        }
        
        phases.replace(fp.getFaction().getTag(), "stopped");

    }

    public boolean raidStarted() {

        Collection<String> phasesStates = phases.values();

        for(Object phase : phasesStates) {

            if(!phase.equals("stopped")) {
                return true;
            }

        }

        return false;

    }

    public String getRaidPlayersString(FPlayer fPlayer) {

        String raidPlayersString = null;
        ArrayList<FPlayer> players = raidPlayers.get(fPlayer.getFaction().getTag());

        if(players.size() == 0) {

            return "\"Żaden gracz nie dołączył do rajdu\"";

        } else {

            for(FPlayer player : players) {
    
                if(raidPlayersString == null) {
                    raidPlayersString = player.getName();
                } else {
                    raidPlayersString += ", " + player.getName();
                }
    
            }

        }

        return raidPlayersString;

    }

    public int randomNum(int min, int max) {
        int result = (int)(Math.random() * (max - min + 1) + min);
        return result;
    }

    public Integer randomNumStandalone(int min, int max, List<Integer> previousResult) {

        if (previousResult.size() > max) {
            return null;
        }

        Integer result = randomNum(min, max);

        if(previousResult.contains(result)) {
            while(previousResult.contains(result)) {
                result = randomNum(min, max);
            }
            previousResult.add(result);
            return result;
        } else {
            previousResult.add(result);
            return result;
        }

    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> completions = new ArrayList<String>();

        if(args.length == 1) { // * 1 argument

            completions.add("start");
            completions.add("stop");
            completions.add("join");
            completions.add("remove");

            return completions;

        }

        return null;
    }

}

//raid start

// raidState = awaiting;