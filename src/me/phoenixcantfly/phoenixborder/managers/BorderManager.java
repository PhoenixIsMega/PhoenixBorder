package me.phoenixcantfly.phoenixborder.managers;

import org.bukkit.World;

public class BorderManager {

    private boolean borderOn; //when border is turned on, set the world to the world command was run in (and other parameters// )
    private World world;

    //change to doubles
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
}
