package me.phoenixcantfly.phoenixborder.managers;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.Objects;

public class BorderManager {
    private final ClassManager classManager;
    public BorderManager(ClassManager classManager) {
        this.classManager = classManager;
        Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(classManager.getPlugin(), () -> {
            for (Player player : Bukkit.getServer().getOnlinePlayers()) {
                if (!classManager.getBorderManager().isBorderOn()) {continue;} //if border isnt on return
                if (!player.getWorld().equals(classManager.getBorderManager().getWorld())) {continue;}
                if (!(player.getGameMode().equals(GameMode.SURVIVAL) || player.getGameMode().equals(GameMode.ADVENTURE))) {continue;}
                Location playerLocation = player.getLocation();
                if ((playerLocation.getY() >= classManager.getBorderManager().getBorderFloor() && playerLocation.getY() <= classManager.getBorderManager().getBorderCeiling())){
                    playerLocation.setY(0);
                    Location borderCentreXZ = new Location(classManager.getBorderManager().getWorld(), classManager.getBorderManager().getBorderX(), 0.0, classManager.getBorderManager().getBorderZ());
                    if (!(playerLocation.distanceSquared(borderCentreXZ) >= Math.pow(classManager.getBorderManager().getBorderRadius(),2))){
                        continue;
                    }
                }
                player.damage(damage);
            }
        }, 1, 25);
    }

    private boolean borderOn; //when border is turned on, set the world to the world command was run in (and other parameters// )
    private World world;

    //change to doubles
    private double damage;
    private double borderX;
    private double borderZ;
    private double borderCeiling;
    private double borderFloor;
    private double borderRadius; //square it or make squared variable

    public double getBorderX() {
        return borderX;
    } 

    public void setBorderX(double borderX) {
        this.borderX = borderX;
    }

    public double getBorderZ() {
        return borderZ;
    }

    public void setBorderZ(double borderZ) {
        this.borderZ = borderZ;
    }

    public double getBorderCeiling() {
        return borderCeiling;
    }

    //add console logs?

    public void setBorderCeiling(double borderCeiling) {
        //if new ceiling below current floor
        if (borderCeiling < this.borderFloor){
            //set ceiling to where floor was
            this.borderCeiling = this.borderFloor;
            //set floor to new ceiling
            //(this basically means they got it the wrong way round)
            this.borderFloor = borderCeiling;
        } else {
            //otherwise proceed as normal and set cieling to new cieling
            this.borderCeiling = borderCeiling;
        }
    }

    public double getBorderFloor() {
        return borderFloor;
    }

    //add console logs?
    public void setBorderFloor(double borderFloor) {
        //if new floor above current ceiling
        if (borderFloor > this.borderCeiling){
            //set floor to where ceiling was
            this.borderFloor = this.borderCeiling;
            //set ceiling to new floor
            //(this basically means they got it the wrong way round)
            this.borderCeiling = borderFloor;
        } else {
            //otherwise proceed as normal and set cieling to new cieling
            this.borderFloor = borderFloor;
        }
    }

    public double getBorderRadius() {
        return borderRadius;
    }

    public void setBorderRadius(double borderRadius) {
        this.borderRadius = borderRadius;
    }

    public boolean isBorderOn() {
        return borderOn;
    }

    public void setBorderOn(boolean borderOn) {
        this.borderOn = borderOn;
    }

    public World getWorld() {
        return world;
    }

    public void setWorld(World world) {
        this.world = world;
    }

    public double getDamage() {
        return damage;
    }

    public void setDamage(double damage) {
        this.damage = damage;
    }
}
