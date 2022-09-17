package me.phoenixcantfly.phoenixborder.commands;

import me.phoenixcantfly.phoenixborder.managers.ClassManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetBorderRadius implements CommandExecutor {
    private final ClassManager classManager;

    public SetBorderRadius(ClassManager classManager){
        this.classManager = classManager;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player) {
            if (commandSender.isOp()) {
                if(strings.length > 0){
                    classManager.getConfigManager().getConfig().set("radius", Double.valueOf(strings[0]));
                    classManager.getConfigManager().saveConfig();
                    classManager.getBorderManager().setBorderRadius(classManager.getConfigManager().getConfig().getDouble("radius"));
                    classManager.getMessageManager().MessagePlayer((Player) commandSender, "Set Border Radius To " + ChatColor.GOLD + strings[0]);
                } else {
                    classManager.getMessageManager().WarnPlayer((Player) commandSender, "Please specify a new radius (eg: /setborderradius 30.0)");
                }
            }
        }
        return true;
    }
}
