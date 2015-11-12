/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package code.data;

/**
 *
 * @author Link
 */
public class MovementData {

    protected float movementX;
    protected float movementY;

    public MovementData() {
        movementX = 0;
        movementY = 0;
    }

    public MovementData(float movementX, float movementY) {
        this.movementX = movementX;
        this.movementY = movementY;
    }

    public float getMovementX() {
        return movementX;
    }

    public float getMovementY() {
        return movementY;
    }

    public void setMovementX(float movementX) {
        this.movementX = movementX;
    }

    public void setMovementY(float movementY) {
        this.movementY = movementY;
    }

    public void increaseMovementX(float movementX) {
        this.movementX += movementX;
    }

    public void increaseMovementY(float movementY) {
        this.movementY += movementY;
    }

}
