package me.phoenixcantfly.phoenixborder.commands;

import me.phoenixcantfly.phoenixborder.managers.ClassManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetBorderParticleSize implements CommandExecutor {
    private final ClassManager classManager;

    public SetBorderParticleSize(ClassManager classManager){
        this.classManager = classManager;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player) {
            if (commandSender.isOp()) {
                if(strings.length > 0){
                    classManager.getConfigManager().getConfig().set("size", Double.valueOf(strings[0]));
                    classManager.getConfigManager().saveConfig();
                    classManager.getParticleManager().setSize((float) classManager.getConfigManager().getConfig().getDouble("size"));
                    classManager.getMessageManager().messagePlayer((Player) commandSender, "Set border particle size to " + ChatColor.GOLD + strings[0]);
                } else {
                    classManager.getMessageManager().warnPlayer((Player) commandSender, "Please specify a new particle size (eg: /setborderparticlesize 1.0)");
                }
            }
        }
        return true;
    }
}
