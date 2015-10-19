package code.graphics;

import code.data.DataInterface;
import java.awt.Graphics2D;

/**
 *
 * @author Link162534
 */
public interface GraphicsInterface {

    void render(DataInterface data, Camera camera, Graphics2D g2d);

}
