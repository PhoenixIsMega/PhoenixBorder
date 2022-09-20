package me.phoenixcantfly.phoenixborder.commands;

import me.phoenixcantfly.phoenixborder.managers.ClassManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.concurrent.atomic.AtomicInteger;

public class SetBorderCeilingHeight implements CommandExecutor {
    //add time interpolation
    private final ClassManager classManager;

    private int taskID;

    public SetBorderCeilingHeight(ClassManager classManager){
        this.classManager = classManager;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if ((commandSender instanceof Player && !(((Player) commandSender).isOp()))) { return true; }
        if(strings.length == 1) {
            Bukkit.getServer().getScheduler().cancelTask(taskID);
            classManager.getConfigManager().setBorderCeilingHeight(Double.parseDouble(strings[0]));
            if (commandSender instanceof Player) {
                classManager.getMessageManager().messagePlayer((Player) commandSender, "Set border ceiling height to " + ChatColor.GOLD + strings[0]);
            }
        } else if (strings.length >= 2){
            Bukkit.getServer().getScheduler().cancelTask(taskID);
            double targetHeight = Double.parseDouble(strings[0]);
            int time = Integer.parseInt(strings[1]);
            double difference = classManager.getBorderManager().getBorderCeiling() - targetHeight;
            double moveDistance = (difference/time)/4;
            AtomicInteger currentIteration = new AtomicInteger(1);
            int maxLoops = time*4;
            taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(classManager.getPlugin(), new Runnable() {
                public void run() {
                    classManager.getConfigManager().setBorderCeilingHeight((Double) classManager.getBorderManager().getBorderCeiling()-moveDistance);
                    int currentIt = currentIteration.incrementAndGet();
                    if(currentIt > maxLoops){
                        Bukkit.getScheduler().cancelTask(taskID);
                    }
                }
            }, 0L, 5L);
        } else {
            if (commandSender instanceof Player) {
                classManager.getMessageManager().warnPlayer((Player) commandSender, "usage: /<command> <y> <time>");
            }
        }
        return true;
    }
}
