package me.phoenixcantfly.phoenixborder.commands;

import me.phoenixcantfly.phoenixborder.managers.ClassManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetBorderParticleDistanceVertical implements CommandExecutor {
    //add interpolation
    private final ClassManager classManager;

    public SetBorderParticleDistanceVertical(ClassManager classManager){
        this.classManager = classManager;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if ((commandSender instanceof Player && !(((Player) commandSender).isOp()))) { return true; }
        if(strings.length > 0){
            classManager.getConfigManager().setBorderParticleDistanceVertical(Double.parseDouble(strings[0]));
            if (commandSender instanceof Player) {
                classManager.getMessageManager().messagePlayer((Player) commandSender, "Set distance between border particles vertically to " + ChatColor.GOLD + strings[0]);
            }
        } else {
            if (commandSender instanceof Player) {
                classManager.getMessageManager().warnPlayer((Player) commandSender, "Please specify a new vertical distance between particles (eg: /setborderparticledistance 5.0)");
            }
        }
        return true;
    }
}
