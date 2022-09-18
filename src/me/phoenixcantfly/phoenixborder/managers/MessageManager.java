package me.phoenixcantfly.phoenixborder.managers;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class MessageManager {
    public void warnPlayer(Player player, String message){
        player.sendMessage(ChatColor.DARK_RED + ChatColor.BOLD.toString() + "["
                + ChatColor.GOLD + ChatColor.BOLD.toString() + "Phoenix"
                + ChatColor.RED + ChatColor.BOLD.toString() + "Border" + ChatColor.DARK_RED
                + ChatColor.BOLD.toString() + "] "
                + ChatColor.RED + message);
    }

    public void messagePlayer(Player player, String message){
        player.sendMessage(ChatColor.DARK_RED + ChatColor.BOLD.toString() + "["
                + ChatColor.GOLD + ChatColor.BOLD.toString() + "Phoenix"
                + ChatColor.RED + ChatColor.BOLD.toString() + "Border" + ChatColor.DARK_RED
                + ChatColor.BOLD.toString() + "] "
                + ChatColor.GOLD + message);
    }
}
