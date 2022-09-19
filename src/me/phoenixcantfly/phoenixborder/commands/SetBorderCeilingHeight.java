package me.phoenixcantfly.phoenixborder.commands;

import me.phoenixcantfly.phoenixborder.managers.ClassManager;
import org.bukkit.ChatColor;
import org.bukkit.block.CommandBlock;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetBorderCeilingHeight implements CommandExecutor {
    //add time interpolation
    private final ClassManager classManager;

    public SetBorderCeilingHeight(ClassManager classManager) {
        this.classManager = classManager;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if ((commandSender instanceof Player && !(((Player) commandSender).isOp()))) { return true; }
        if (strings.length > 0){
            classManager.getConfigManager().setCeilingHeight(strings);
            if (commandSender instanceof Player) {
                classManager.getMessageManager().messagePlayer((Player) commandSender, "Set border ceiling height to " + ChatColor.GOLD + strings[0]);
            }
        } else {
            if (commandSender instanceof Player) {
                classManager.getMessageManager().warnPlayer((Player) commandSender, "Please specify a new border ceiling height (eg: /setbordercielingheight 30.0)");
            }
        }
        return true;
    }
}
