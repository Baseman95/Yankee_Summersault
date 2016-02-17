package code.graphics;

import yansuen.graphics.GraphicsInterface;
import yansuen.graphics.Camera;
import code.data.DataObject;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.VolatileImage;
import yansuen.data.Data;

/**
 *
 * @author Link162534
 */
public class DefaultGraphicsObject implements GraphicsInterface {

    int rendering = 2;

    @Override
    public synchronized void render(Data data, Camera camera, Graphics2D g2d) {
        DataObject d = (DataObject) data;
        configureGraphics2D(g2d);
        AffineTransform at = new AffineTransform();
        at.rotate(d.getPositionData().getRotation(), d.getPositionData().getWidth() / 2, d.getPositionData().getHeight() / 2);
        AffineTransformOp ato = new AffineTransformOp(at, AffineTransformOp.TYPE_BICUBIC);
        BufferedImage after = new BufferedImage(d.getImageData().getImage().getColorModel(),
                                                d.getImageData().getImage().copyData(null),
                                                d.getImageData().getImage().isAlphaPremultiplied(), null);
        ato.filter(d.getImageData().getImage(), after);
        g2d.drawImage(after, (int) d.getPositionData().getX(),
                      (int) d.getPositionData().getY(),
                      (int) d.getPositionData().getWidth(),
                      (int) d.getPositionData().getHeight(), null);

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
