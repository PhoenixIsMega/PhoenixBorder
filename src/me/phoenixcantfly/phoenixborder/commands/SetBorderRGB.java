package me.phoenixcantfly.phoenixborder.commands;

import me.phoenixcantfly.phoenixborder.managers.ClassManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetBorderRGB implements CommandExecutor {

    private final ClassManager classManager;

    public SetBorderRGB(ClassManager classManager){
        this.classManager = classManager;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if ((commandSender instanceof Player && !(((Player) commandSender).isOp()))) { return true; }
        if(strings.length >= 3){
            classManager.getConfigManager().setBorderRGB(strings);
            if (commandSender instanceof Player) {
                classManager.getMessageManager().messagePlayer((Player) commandSender, "Set border particle RGB to " + ChatColor.RED + strings[0] + " " + ChatColor.GREEN + strings[1] + " " + ChatColor.BLUE + strings[2]);
            }
        } else {
            if (commandSender instanceof Player) {
                classManager.getMessageManager().warnPlayer((Player) commandSender, "Please specify a new particle red, green AND blue (eg: /setborderrgb 230 120 20)");
            }
        }
        return true;
    }
}
