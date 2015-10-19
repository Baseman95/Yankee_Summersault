package code;

import code.data.DataInterface;
import code.game.GameObject;
import code.game.World;
import code.graphics.GraphicsLoop;
import code.logic.LogicInterface;
import code.logic.LogicLoop;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author Base
 */
public class LinkTest {

    public static void main(String[] args) throws IOException {
        LogicLoop ll = new LogicLoop(50000000L, 10);
        GraphicsLoop gl = new GraphicsLoop(33);
        World world = new World();
        Screen screen = new Screen(world);

        ll.setLogic(world);
        gl.setRepaintTarget(screen);

        ll.start();
        gl.start();

        screen.setVisible(true);

        LogicInterface movingObject = (DataInterface data, long tick) -> {
            data.setX(data.getX()+1);
        };

        BufferedImage panzerGraphic = ImageIO.read(new File("panzer.png"));

        GameObject playerPanzer = new GameObject(12, 45, movingObject, panzerGraphic, null);

        world.getGameObjects().add(playerPanzer);
        /*
         GameObject heli = new GameObject(flyingObject, heliGraphic, aiController);
         GameObject panzer = new GameObject(movingObject, panzerGraphic, aiController);
         heli.setController(noController);*/
    }

}
