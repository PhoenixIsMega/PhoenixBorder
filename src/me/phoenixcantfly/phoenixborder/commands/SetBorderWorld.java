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
        if (commandSender instanceof Player) {
            if (commandSender.isOp()) {
                if (strings.length > 0) {
                    if (Bukkit.getWorld(strings[0]) == null){
                        classManager.getMessageManager().warnPlayer(((Player) commandSender), "This world does not exist!");
                        return true;
                    }
                    classManager.getConfigManager().getConfig().set("world-name", strings[0]);
                    classManager.getConfigManager().saveConfig();
                    classManager.getBorderManager().setWorld(Bukkit.getWorld(Objects.requireNonNull(classManager.getConfigManager().getConfig().getString("world-name"))));
                    classManager.getMessageManager().messagePlayer((Player) commandSender, "Set border world to " + ChatColor.GOLD + strings[0]);
                } else {
                    classManager.getMessageManager().warnPlayer((Player) commandSender, "Please specify a world namme (eg: /setborderworld world)");
                }
            }
        }
        return true;
    }
}
