package com.blackmc.blackguilds.utils;

import org.bukkit.Bukkit;

public class Color {

    public static String RESET = "\033[0m";
    public static String BLACK = "\033[30m";
    public static String RED = "\033[31m";
    public static String GREEN = "\033[32m";
    public static String YELLOW = "\033[33m";
    public static String BLUE = "\033[34m";
    public static String MAGENTA = "\033[35m";
    public static String CYAN = "\033[36m";
    public static String WHITE = "\033[37m";

    private String getColor(String color) {

        switch(color) {
            case "reset":
                return RESET;
            case "black":
                return BLACK;
            case "red":
                return RED;
            case "green":
                return GREEN;
            case "yellow":
                return YELLOW;
            case "blue":
                return BLUE;
            case "magenta":
                return MAGENTA;
            case "cyan":
                return CYAN;
            case "white":
                return WHITE;
            default:
                return RESET;

        }

    }

    public void log(String text) {

        Bukkit.getLogger().info("( " + Color.MAGENTA + "BlackGuilds" + RESET + " ) " + RESET + text + RESET);

    }

    public void log(String text, String color) {

        color = getColor(color);

        Bukkit.getLogger().info("( " + Color.MAGENTA + "BlackGuilds" + RESET + " ) " + color + text + RESET);

    }

    public void log(String text, String color, String reset) {

        color = getColor(color);
        reset = getColor(reset);

        Bukkit.getLogger().info("( " + Color.MAGENTA + "BlackGuilds" + RESET + " ) " + color + text + reset);

    }

}
