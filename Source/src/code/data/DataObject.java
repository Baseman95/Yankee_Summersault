package code.data;

import java.awt.image.BufferedImage;

/**
 *
 * @author Link162534
 */
public class DataObject implements DataInterface {

    protected float x;
    protected float y;
    protected float width;
    protected float height;
    protected double rotation;
    protected BufferedImage image;

    public DataObject(float x, float y, float width, float height, BufferedImage image) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.rotation = 0;
        this.image = image;
    }

    public DataObject(DataInterface dataObject) {
        x = dataObject.getX();
        y = dataObject.getY();
        width = dataObject.getWidth();
        height = dataObject.getHeight();
        rotation = dataObject.getRotation();
        image = dataObject.getImage();
    }

    @Override
    public void setY(float y) {
        this.y = y;
    }

    @Override
    public void setX(float x) {
        this.x = x;
    }

    @Override
    public void setWidth(float width) {
        this.width = width;
    }

    @Override
    public void setHeight(float height) {
        this.height = height;
    }

    @Override
    public void setRotation(double rotation) {
        this.rotation = rotation;
    }

    @Override
    public void setImage(BufferedImage image) {
        this.image = image;
    }

    @Override
    public float getY() {
        return y;
    }

    @Override
    public float getX() {
        return x;
    }

    @Override
    public float getWidth() {
        return width;
    }

    @Override
    public float getHeight() {
        return height;
    }

    @Override
    public double getRotation() {
        return rotation;
    }

    @Override
    public BufferedImage getImage() {
        return image;
    }

}
