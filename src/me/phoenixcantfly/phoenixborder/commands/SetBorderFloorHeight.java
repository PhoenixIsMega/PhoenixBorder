package me.phoenixcantfly.phoenixborder.commands;

import me.phoenixcantfly.phoenixborder.managers.ClassManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetBorderFloorHeight implements CommandExecutor {
    //add time interpolation
    private final ClassManager classManager;

    public SetBorderFloorHeight(ClassManager classManager){
        this.classManager = classManager;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player) {
            if (commandSender.isOp()) {
                if(strings.length > 0){
                    classManager.getBorderManager().setBorderFloor(Double.parseDouble(strings[0]));
                    classManager.getConfigManager().getConfig().set("ceiling-height", classManager.getBorderManager().getBorderCeiling());
                    classManager.getConfigManager().getConfig().set("floor-height", classManager.getBorderManager().getBorderFloor());
                    classManager.getConfigManager().saveConfig();
                    classManager.getMessageManager().messagePlayer((Player) commandSender, "Set border floor height to " + ChatColor.GOLD + strings[0]);
                } else {
                    classManager.getMessageManager().warnPlayer((Player) commandSender, "Please specify a new border floor height (eg: /setborderfloorheight 30.0)");
                }
            }
        }
        return true;
    }
}
