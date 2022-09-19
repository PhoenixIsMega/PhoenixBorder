package me.phoenixcantfly.phoenixborder.commands;

import me.phoenixcantfly.phoenixborder.managers.ClassManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class EnableBorder implements CommandExecutor {

    private final ClassManager classManager;

    public EnableBorder(ClassManager classManager){
        this.classManager = classManager;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if ((commandSender instanceof Player && !(((Player) commandSender).isOp()))) {
            return true;
        }
        classManager.getConfigManager().enableBorder();
        if (commandSender instanceof Player) {
            classManager.getMessageManager().messagePlayer((Player) commandSender, "Border Enabled!");
        }
        return true;
    }
}
