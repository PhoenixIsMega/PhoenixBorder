package me.phoenixcantfly.phoenixborder.managers;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.scheduler.BukkitRunnable;

public class ParticleManager {
    private final ClassManager classManager;
    public ParticleManager(ClassManager classManager) {
        this.classManager = classManager;
    }

    //
    public void playBorderParticles(){
        World world = classManager.getBorderManager().getWorld();

        //walls
        double x = classManager.getBorderManager().getBorderX();
        double z = classManager.getBorderManager().getBorderZ();
        double radius = classManager.getBorderManager().getBorderRadius();

        double ceilingHeight = classManager.getBorderManager().getBorderCeiling();
        double floorHeight = classManager.getBorderManager().getBorderFloor();

        double cylHeight = ceilingHeight - floorHeight;

        if (cylHeight < 0) {cylHeight *= -1;}

        int minPointCount = 5; //change to be variable in settings later
        int distanceBetweenPoints = 5; //change to be variable and double later
        int pointCount = Math.max(minPointCount, (int)Math.floor((2*radius*Math.PI)/distanceBetweenPoints));

        int ringCount = (int)Math.floor(cylHeight/distanceBetweenPoints);
        double ringBuffer = ((cylHeight/distanceBetweenPoints) - Math.floor(cylHeight/distanceBetweenPoints))/2;

        Particle.DustOptions dustOptions = new Particle.DustOptions(Color.fromRGB(16, 88, 74), 1.5F);

        //could stack to make spiral patter but too lazy
        for (double posY = floorHeight + ringBuffer; posY <= ceilingHeight; posY += distanceBetweenPoints) {
            for (int i = 1; i <= pointCount; i++) { //floor height is a temp, do other y levels
                double posX = radius * Math.sin(i * ((2 * Math.PI) / pointCount)) + x;
                double posZ = radius * Math.cos(i * ((2 * Math.PI) / pointCount)) + z;
                double currentPosY = posY;
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        world.spawnParticle(Particle.REDSTONE, posX, currentPosY, posZ, 1, dustOptions); //make forced?
                        //Bukkit.broadcastMessage("HERE" + Bukkit.getServer().getWorlds());
                    }
                }.runTask(classManager.getPlugin());
            }
        }

        //Bukkit.broadcastMessage("Particle Incoming");
        //caps1
        //caps2
    }
}
