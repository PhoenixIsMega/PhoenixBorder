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
        if (commandSender instanceof Player) {
            if (commandSender.isOp()) {
                if(strings.length >= 3){
                    classManager.getConfigManager().getConfig().set("r", Integer.valueOf(strings[0]));
                    classManager.getConfigManager().getConfig().set("g", Integer.valueOf(strings[1]));
                    classManager.getConfigManager().getConfig().set("b", Integer.valueOf(strings[2]));
                    classManager.getConfigManager().saveConfig();
                    classManager.getParticleManager().setR(classManager.getConfigManager().getConfig().getInt("r"));
                    classManager.getParticleManager().setG(classManager.getConfigManager().getConfig().getInt("g"));
                    classManager.getParticleManager().setB(classManager.getConfigManager().getConfig().getInt("b"));
                    classManager.getMessageManager().messagePlayer((Player) commandSender, "Set border particle RGB to " + ChatColor.RED + strings[0] + " " + ChatColor.GREEN + strings[1] + " " + ChatColor.BLUE + strings[2]);
                } else {
                    classManager.getMessageManager().warnPlayer((Player) commandSender, "Please specify a new particle red, green AND blue (eg: /setborderrgb 230 120 20)");
                }
            }
        }
        return true;
    }
}
