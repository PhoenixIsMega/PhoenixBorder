package me.phoenixcantfly.phoenixborder.managers;

public class BorderManager {
    //change to doubles
    private int borderX;
    private int borderZ;
    private int borderCeiling;
    private int borderFloor;
    private int borderRadius;

    public int getBorderX() {
        return borderX;
    }

    public void setBorderX(int borderX) {
        this.borderX = borderX;
    }

    public int getBorderZ() {
        return borderZ;
    }

    public void setBorderZ(int borderZ) {
        this.borderZ = borderZ;
    }

    public int getBorderCeiling() {
        return borderCeiling;
    }

    public void setBorderCeiling(int borderCeiling) {
        this.borderCeiling = borderCeiling;
    }

    public int getBorderFloor() {
        return borderFloor;
    }

    public void setBorderFloor(int borderFloor) {
        this.borderFloor = borderFloor;
    }

    public int getBorderRadius() {
        return borderRadius;
    }

    public void setBorderRadius(int borderRadius) {
        this.borderRadius = borderRadius;
    }
}
