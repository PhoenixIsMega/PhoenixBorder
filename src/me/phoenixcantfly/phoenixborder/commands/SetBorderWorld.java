package me.phoenixcantfly.phoenixborder.commands;

import me.phoenixcantfly.phoenixborder.managers.ClassManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Objects;

public class SetBorderWorld implements CommandExecutor {
    private final ClassManager classManager;

    public SetBorderWorld(ClassManager classManager) {
        this.classManager = classManager;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if ((commandSender instanceof Player && !(((Player) commandSender).isOp()))) { return true; }
        if (strings.length > 0) {
            if (Bukkit.getWorld(strings[0]) == null){
                if (commandSender instanceof Player) {
                    classManager.getMessageManager().warnPlayer(((Player) commandSender), "This world does not exist!");
                }
                return true;
            }
            classManager.getConfigManager().setBorderWorld(strings[0]);
            if (commandSender instanceof Player) {
                classManager.getMessageManager().messagePlayer((Player) commandSender, "Set border world to " + ChatColor.GOLD + strings[0]);
            }
        } else {
            if (commandSender instanceof Player) {
                classManager.getMessageManager().warnPlayer((Player) commandSender, "usage: /<command> <world-name>");
            }
        }
        return true;
    }
}
