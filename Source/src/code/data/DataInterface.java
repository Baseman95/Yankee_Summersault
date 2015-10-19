package code.data;

import java.awt.image.BufferedImage;

/**
 *
 * @author Link162534
 */
public interface DataInterface {

    public void setY(float y);

    public void setX(float x);

    public void setWidth(float w);

    public void setHeight(float h);

    public void setRotation(double rotation);

    public void setImage(BufferedImage image);

    public float getY();

    public float getX();

    public float getWidth();

    public float getHeight();

    public double getRotation();

    public BufferedImage getImage();

}
