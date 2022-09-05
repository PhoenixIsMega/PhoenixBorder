package me.phoenixcantfly.phoenixborder.listeners;

import me.phoenixcantfly.phoenixborder.managers.ClassManager;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.Locale;

public class PlayerMove implements Listener {
    private final ClassManager classManager;

    public PlayerMove(ClassManager classManager) {
        this.classManager = classManager;
    }

    @EventHandler
    public void PlayerMove(PlayerMoveEvent e){
        if (!classManager.getBorderManager().isBorderOn()) {return;} //if border isnt on return
        if (!e.getPlayer().getWorld().equals(classManager.getBorderManager().getWorld())) {return;}
        Location playerLocation = e.getPlayer().getLocation();
        //use squared distance to save processing powerr
        //Integer distanceFromCentre =
        //figure out distance
    }
}
