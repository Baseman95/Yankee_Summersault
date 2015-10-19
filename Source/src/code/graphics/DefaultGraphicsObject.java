package code.graphics;

import code.data.DataInterface;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

/**
 *
 * @author Link162534
 */
public class DefaultGraphicsObject implements GraphicsInterface {

    int rendering = 2;

    @Override
    public void render(DataInterface data, Camera camera, Graphics2D g2d) {
        configureGraphics2D(g2d);
        g2d.rotate(-data.getRotation(), data.getX() + data.getWidth() / 2, data.getY() + data.getHeight() / 2);
        g2d.drawImage(data.getImage(), (int) data.getX(), (int) data.getY(), (int) data.getWidth(), (int) data.getHeight(), null);
        g2d.rotate(data.getRotation(), data.getX() + data.getWidth() / 2, data.getY() + data.getHeight() / 2);
    }

    public Graphics2D configureGraphics2D(Graphics2D g2d) {
        switch (rendering) {
            case -1:
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
                g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);

            case 0:
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);
                g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
            case 1:
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
                g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
                break;
            case 2:
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
                g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
                break;

        }
        return g2d;
    }

}
