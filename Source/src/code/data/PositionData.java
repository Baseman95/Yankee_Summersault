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
public class PositionData {

    protected float x;
    protected float y;
    protected float width;
    protected float height;
    protected double rotation;
    DataObject parent;

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
        float old = this.x;
        this.x += x;
        if (parent != null)
            parent.onPositionChanged(parent, old, this.y);
    }

    public void increaseY(float y) {
        float old = this.y;
        this.y += y;
        if (parent != null)
            parent.onPositionChanged(parent, this.x, old);
    }

    public void increaseRotation(double rotation) {
        setRotation(this.rotation + rotation);
        this.rotation += rotation;
    }

    public void setX(float x) {
        float old = this.x;
        this.x = x;
        if (parent != null)
            parent.onPositionChanged(parent, old, this.y);
    }

    public void setY(float y) {
        float old = this.y;
        this.y = y;
        if (parent != null)
            parent.onPositionChanged(parent, this.x, old);
    }

    public void setWidth(float width) {
        float old = this.width;
        this.width = width;
        if (parent != null)
            parent.onWidthChanged(parent, old, this.height);
    }

    public void setHeight(float height) {
        float old = this.height;
        this.height = height;
        if (parent != null)
            parent.onWidthChanged(parent, this.width, old);
    }

    public void setRotation(double rotation) {
        double old = this.rotation;
        if (rotation > Math.PI * 2)
            rotation = rotation % Math.PI * 2;
        while (rotation <= 0) {
            rotation += Math.PI * 2;
        }
        this.rotation = rotation;
        if (parent != null)
            parent.onRotationChanged(parent, old);
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
