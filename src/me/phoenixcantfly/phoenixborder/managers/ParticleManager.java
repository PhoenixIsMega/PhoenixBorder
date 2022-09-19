package me.phoenixcantfly.phoenixborder.managers;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.scheduler.BukkitRunnable;

import javax.lang.model.element.NestingKind;

public class ParticleManager {
    private final ClassManager classManager;
    public ParticleManager(ClassManager classManager) {
        this.classManager = classManager;
    }

    private int r;
    private int g;
    private int b;
    private float size;

    private double distanceBetweenPoints;

    private double distanceBetweenPointsVertical;

    //
    public void playBorderParticles(){
        if (!classManager.getBorderManager().isBorderOn()) { return; }
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
        int pointCount = Math.max(minPointCount, (int)Math.floor((2*radius*Math.PI)/distanceBetweenPoints));

        int ringCount = (int)Math.floor(cylHeight/distanceBetweenPointsVertical);
        double ringBuffer = (ceilingHeight - (floorHeight + (ringCount*distanceBetweenPointsVertical)))/2;

        double particleSpeed = 100;
        Particle.DustOptions dustOptions = new Particle.DustOptions(Color.fromRGB(r, g, b), size);

        for (double posY = (floorHeight + ringBuffer); posY <= ceilingHeight; posY += distanceBetweenPointsVertical) {
            for (int i = 1; i <= pointCount; i++) {
                double posX = radius * Math.sin(i * ((2 * Math.PI) / pointCount)) + x;
                double posZ = radius * Math.cos(i * ((2 * Math.PI) / pointCount)) + z;
                double currentPosY = posY;
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        world.spawnParticle(Particle.REDSTONE, posX, currentPosY, posZ, 1, 0, 0, 0, particleSpeed, dustOptions); //make forced?
                    }
                }.runTask(classManager.getPlugin());
            }
        }

        double stretch = 0.225*distanceBetweenPoints + 0.375;
        double pattern = 1;
        int capPointCount = (int)Math.floor(Math.pow((1/stretch), 2)*Math.pow(radius, 2));

        for (int i = 1; i <= capPointCount; i++){
            double posX = ((stretch*Math.sqrt(i))*Math.cos(((1+Math.sqrt(5)/pattern)*Math.PI)*i) + x);
            double posZ = ((stretch*Math.sqrt(i))*Math.sin(((1+Math.sqrt(5)/pattern)*Math.PI)*i) + z);
            new BukkitRunnable() {
                @Override
                public void run() {
                    world.spawnParticle(Particle.REDSTONE, posX, floorHeight, posZ, 1, 0, 0, 0, particleSpeed, dustOptions); //make forced?
                }
            }.runTask(classManager.getPlugin());
        }

        for (int i = 1; i <= capPointCount; i++){
            double posX = ((stretch*Math.sqrt(i))*Math.cos(((1+Math.sqrt(5)/pattern)*Math.PI)*i) + x);
            double posZ = ((stretch*Math.sqrt(i))*Math.sin(((1+Math.sqrt(5)/pattern)*Math.PI)*i) + z);
            new BukkitRunnable() {
                @Override
                public void run() {
                    world.spawnParticle(Particle.REDSTONE, posX, ceilingHeight, posZ, 1, 0, 0, 0, particleSpeed, dustOptions); //make forced?
                }
            }.runTask(classManager.getPlugin());
        }
    }

    public int getR() {
        return r;
    }

    public void setR(int r) {
        this.r = r;
    }

    public int getG() {
        return g;
    }

    public void setG(int g) {
        this.g = g;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }

    public float getSize() {
        return size;
    }

    public void setSize(float size) {
        this.size = size;
    }

    public double getDistanceBetweenPoints() {
        return distanceBetweenPoints;
    }

    public void setDistanceBetweenPoints(double distanceBetweenPoints) {
        this.distanceBetweenPoints = distanceBetweenPoints;
    }

    public double getDistanceBetweenPointsVertical() {
        return distanceBetweenPointsVertical;
    }

    public void setDistanceBetweenPointsVertical(double distanceBetweenPointsVertical) {
        this.distanceBetweenPointsVertical = distanceBetweenPointsVertical;
    }
}
