package me.phoenixcantfly.phoenixborder.commands;

import me.phoenixcantfly.phoenixborder.managers.ClassManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetBorderCentre implements CommandExecutor {
    //add time interpolation
    private final ClassManager classManager;

    public SetBorderCentre(ClassManager classManager) {
        this.classManager = classManager;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player) {
            if (commandSender.isOp()) {
                if (strings.length == 0) {
                    if (!((Player) commandSender).getWorld().equals(classManager.getBorderManager().getWorld())){
                        classManager.getMessageManager().warnPlayer((Player) commandSender, "Cannot set border centre to current position as border is in a different world!");
                        return true;
                    }
                    double playerX = ((Player) commandSender).getLocation().getX();
                    double playerZ = ((Player) commandSender).getLocation().getZ();
                    classManager.getConfigManager().getConfig().set("x", playerX);
                    classManager.getConfigManager().getConfig().set("z", playerZ);
                    classManager.getConfigManager().saveConfig();
                    classManager.getBorderManager().setBorderX(classManager.getConfigManager().getConfig().getDouble("x"));
                    classManager.getBorderManager().setBorderZ(classManager.getConfigManager().getConfig().getDouble("z"));
                    classManager.getMessageManager().messagePlayer((Player) commandSender, "Set border centre to " + ChatColor.GOLD + playerX + ", " + playerZ);
                } else if (strings.length >= 2){
                    classManager.getConfigManager().getConfig().set("x", Double.valueOf(strings[0]));
                    classManager.getConfigManager().getConfig().set("z", Double.valueOf(strings[1]));
                    classManager.getConfigManager().saveConfig();
                    classManager.getBorderManager().setBorderX(classManager.getConfigManager().getConfig().getDouble("x"));
                    classManager.getBorderManager().setBorderZ(classManager.getConfigManager().getConfig().getDouble("z"));
                    classManager.getMessageManager().messagePlayer((Player) commandSender, "Set border centre to " + ChatColor.GOLD + strings[0] + ", " + strings[1]);
                } else {
                    classManager.getMessageManager().warnPlayer((Player) commandSender, "Please specify a new border centre (eg: /setbordercentre 0.0 10.0)");
                }
            }
        }
        return true;
    }
}
