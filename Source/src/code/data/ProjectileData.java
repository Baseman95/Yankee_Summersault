/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package code.data;

import java.awt.image.BufferedImage;

/**
 *
 * @author Base
 */
public class ProjectileData extends VehicleData {

    protected float speed;
    protected long deathTick;
    protected boolean hit = false;

    public ProjectileData(float x, float y, double rotation, BufferedImage image, float speed, long deathTick) {
        super(0, 0, x, y, image.getWidth(), image.getHeight(), rotation, image);
        this.speed = speed;
        this.deathTick = deathTick;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public long getDeathTick() {
        return deathTick;
    }

    public void setDeathTick(long deathTick) {
        this.deathTick = deathTick;
    }

    public boolean isHit() {
        return hit;
    }

    public void setHit(boolean hit) {
        this.hit = hit;
    }
}
