package me.phoenixcantfly.phoenixborder.commands;

import me.phoenixcantfly.phoenixborder.managers.ClassManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetBorderDamage implements CommandExecutor {
    private final ClassManager classManager;

    public SetBorderDamage(ClassManager classManager){
        this.classManager = classManager;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player) {
            if (commandSender.isOp()) {
                if(strings.length > 0){
                    classManager.getConfigManager().getConfig().set("damage", Double.valueOf(strings[0]));
                    classManager.getConfigManager().saveConfig();
                    classManager.getBorderManager().setDamage(classManager.getConfigManager().getConfig().getDouble("damage"));
                    classManager.getMessageManager().messagePlayer((Player) commandSender, "Set border damage to " + ChatColor.GOLD + strings[0]);
                } else {
                    classManager.getMessageManager().warnPlayer((Player) commandSender, "Please specify damage amount (eg: /setborderdamage 2.0)");
                }
            }
        }
        return true;
    }
}
