package yansuen.graphics;

import java.awt.Graphics2D;
import yansuen.data.DataContainer;

/**
 *
 * @author Link162534
 */
public interface GraphicsInterface {

    void render(DataContainer data, Camera camera, Graphics2D g2d);

}
