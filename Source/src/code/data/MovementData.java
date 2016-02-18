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
    DataObject parent;

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
        float old = movementX;
        this.movementX = movementX;
        if (parent != null)
            parent.onMovementChanged(parent, old, this.movementY);
    }

    public void setMovementY(float movementY) {
        float old = movementY;
        this.movementY = movementY;
        if (parent != null)
            parent.onMovementChanged(parent, this.movementX, old);
    }

    public void increaseMovementX(float movementX) {
        float old = movementX;
        this.movementX += movementX;
        if (parent != null)
            parent.onMovementChanged(parent, old, this.movementY);
    }

    public void increaseMovementY(float movementY) {
        float old = movementY;
        this.movementY += movementY;
        if (parent != null)
            parent.onMovementChanged(parent, this.movementX, old);
    }

}
