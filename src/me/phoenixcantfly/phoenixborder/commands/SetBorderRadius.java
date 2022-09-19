package me.phoenixcantfly.phoenixborder.commands;

import me.phoenixcantfly.phoenixborder.managers.ClassManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.concurrent.atomic.AtomicInteger;

public class SetBorderRadius implements CommandExecutor {
    //add time interpolation
    private final ClassManager classManager;

    private int taskID;

    public SetBorderRadius(ClassManager classManager){
        this.classManager = classManager;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player) {
            if (commandSender.isOp()) {
                if(strings.length == 1) {
                    Bukkit.getServer().getScheduler().cancelTask(taskID);
                    classManager.getConfigManager().getConfig().set("radius", Double.valueOf(strings[0]));
                    classManager.getConfigManager().saveConfig();
                    classManager.getBorderManager().setBorderRadius(classManager.getConfigManager().getConfig().getDouble("radius"));
                    classManager.getMessageManager().messagePlayer((Player) commandSender, "Set border radius to " + ChatColor.GOLD + strings[0]);
                } else if (strings.length >= 2) {
                    Bukkit.getServer().getScheduler().cancelTask(taskID);
                    double targetRadius = Double.parseDouble(strings[0]);
                    int time = Integer.parseInt(strings[1]);
                    double difference = classManager.getBorderManager().getBorderRadius() - targetRadius;
                    double moveDistance = (difference/time)/4;
                    Bukkit.broadcastMessage("target radius " + targetRadius + ", time " + time + ", difference" + difference + ", move distance " + moveDistance);
                    AtomicInteger currentIteration = new AtomicInteger(1);
                    int maxAttempts = time*4;
                    taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(classManager.getPlugin(), new Runnable() {
                        public void run() {
                            Bukkit.broadcastMessage(String.valueOf(currentIteration));
                            classManager.getConfigManager().getConfig().set("radius", classManager.getBorderManager().getBorderRadius()-moveDistance);
                            classManager.getConfigManager().saveConfig();
                            classManager.getBorderManager().setBorderRadius(classManager.getConfigManager().getConfig().getDouble("radius"));
                            int currentIt = currentIteration.incrementAndGet();
                            if(currentIt > maxAttempts){
                                Bukkit.getScheduler().cancelTask(taskID);
                            }
                        }
                    }, 0L, 5L);
                } else {
                    classManager.getMessageManager().warnPlayer((Player) commandSender, "Please specify a new radius (eg: /setborderradius 30.0)");
                }
            }
        }
        return true;
    }
}
