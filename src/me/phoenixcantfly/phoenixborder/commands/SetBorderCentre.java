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
        if ((commandSender instanceof Player && !(((Player) commandSender).isOp()))) { return true; }

        if (strings.length == 0) {
            if (commandSender instanceof Player) {
                if (!((Player) commandSender).getWorld().equals(classManager.getBorderManager().getWorld())) {
                    classManager.getMessageManager().warnPlayer((Player) commandSender, "Cannot set border centre to current position as border is in a different world!");
                    return true;
                }

                double playerX = ((Player) commandSender).getLocation().getX();
                double playerZ = ((Player) commandSender).getLocation().getZ();
                classManager.getConfigManager().setBorderCentre((Double) playerX, (Double) playerZ);

                classManager.getMessageManager().messagePlayer((Player) commandSender, "Set border centre to " + ChatColor.GOLD + playerX + ", " + playerZ);
            }
        } else if (strings.length >= 2){
            classManager.getConfigManager().setBorderCentre(Double.parseDouble(strings[0]), Double.parseDouble(strings[1]));

            if (commandSender instanceof Player) {
                classManager.getMessageManager().messagePlayer((Player) commandSender, "Set border centre to " + ChatColor.GOLD + strings[0] + ", " + strings[1]);
            }
        } else {
            if (commandSender instanceof Player) {
                classManager.getMessageManager().warnPlayer((Player) commandSender, "usage: /<command> <x> <z>");
            }
        }
        return true;
    }
}
