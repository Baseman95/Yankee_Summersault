package code.presets;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import yansuen.graphics.Camera;
import yansuen.graphics.GraphicsInterface;
import yansuen.data.GameData;

/**
 *
 * @author Eris
 */
public class GraphicsPresets {

    public static int RENDER_QUALITY = 2;

    static Graphics2D configureGraphics2D(Graphics2D g2d) {
        switch (RENDER_QUALITY) {
            case -1:
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
                g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);
                g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
                break;
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

    public static GraphicsInterface DEFAULT = (GameData data, Camera camera, Graphics2D g2d) -> {
        GameData d = (GameData) data;
        configureGraphics2D(g2d);
        AffineTransform at = new AffineTransform();
        at.rotate(d.getRotation(), d.getWidth() / 2, d.getHeight() / 2);
        AffineTransformOp ato = new AffineTransformOp(at, AffineTransformOp.TYPE_BICUBIC);
        BufferedImage after = new BufferedImage(d.getImage().getColorModel(),
                d.getImage().copyData(null),
                d.getImage().isAlphaPremultiplied(), null);
        ato.filter(d.getImage(), after);
        g2d.drawImage(after, (int) d.getX(),
                (int) d.getY(),
                (int) d.getWidth(),
                (int) d.getHeight(), null);
    };

    public static GraphicsInterface ROTATION = (GameData data, Camera camera, Graphics2D g2d) -> {
        GameData d = (GameData) data;
        configureGraphics2D(g2d);
        AffineTransform at = new AffineTransform();
        at.rotate(d.getRotation(), d.getWidth() / 2, d.getHeight() / 2);
        AffineTransformOp ato = new AffineTransformOp(at, AffineTransformOp.TYPE_BICUBIC);
        BufferedImage after = new BufferedImage(d.getImage().getColorModel(),
                d.getImage().copyData(null),
                d.getImage().isAlphaPremultiplied(), null);
        ato.filter(d.getImage(), after);
        g2d.drawImage(after, (int) d.getX(),
                (int) d.getY(),
                (int) d.getWidth(),
                (int) d.getHeight(), null);
    };

}
