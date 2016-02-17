package yansuen.graphics;

import java.awt.Graphics2D;
import yansuen.data.Data;

/**
 *
 * @author Link162534
 */
public interface GraphicsInterface {

    void render(Data data, Camera camera, Graphics2D g2d);

}
