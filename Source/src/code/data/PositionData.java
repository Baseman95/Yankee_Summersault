/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package code.data;

import java.awt.image.BufferedImage;

/**
 *
 * @author Link
 */
public class PositionData {

    protected float x;
    protected float y;
    protected float width;
    protected float height;
    protected double rotation;

    public PositionData(float x, float y, float width, float height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.rotation = 0;
    }

    public PositionData(PositionData data) {
        x = data.getX();
        y = data.getY();
        width = data.getWidth();
        height = data.getHeight();
        rotation = data.getRotation();
    }

    public void increaseX(float x) {
        this.x += x;
    }

    public void increaseY(float y) {
        this.y += y;
    }

    public void increaseRotation(double rotation) {
        setRotation(this.rotation + rotation);
        this.rotation += rotation;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public void setRotation(double rotation) {
        if (rotation > Math.PI * 2)
            rotation = rotation % Math.PI * 2;
        while (rotation <= 0) {
            rotation += Math.PI * 2;
        }
        this.rotation = rotation;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public double getRotation() {
        return rotation;
    }

    @Override
    public String toString() {
        return "PositionData[" + x + "x|" + y + "y, " + width + "w|" + height + "h, " + rotation + "r]";
    }

}
