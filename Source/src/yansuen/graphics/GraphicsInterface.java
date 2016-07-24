package yansuen.graphics;

import java.awt.Graphics2D;
import yansuen.data.GameData;

/**
 *
 * @author Link162534
 */
public interface GraphicsInterface {

    void render(GameData data, Camera camera, Graphics2D g2d);

}
