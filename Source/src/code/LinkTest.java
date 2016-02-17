package code;

import code.data.DataObject;
import yansuen.game.GameObject;
import code.game.World;
import yansuen.graphics.GraphicsLoop;
import yansuen.key.KeyManager;
import yansuen.logic.LogicInterface;
import yansuen.logic.LogicLoop;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import yansuen.data.Data;

/**
 *
 * @author Base
 */
public class LinkTest {

    public static void main(String[] args) throws IOException {
        LogicLoop ll = new LogicLoop(50000000L, 10);
        GraphicsLoop gl = new GraphicsLoop(33);
        World world = new World(null);
        Screen screen = new Screen(world);

        ll.setLogic(world);
        gl.setRepaintTarget(screen);

        ll.start();
        gl.start();
        screen.setVisible(true);

        LogicInterface movingObject = (Data di, long tick) -> {
            DataObject data = (DataObject) di;
            data.getPositionData().setX(data.getPositionData().getX() + 1);
        };

        BufferedImage panzerGraphic = ImageIO.read(new File("panzer.png"));

        GameObject playerPanzer = new GameObject(12, 45, movingObject, panzerGraphic, null);

        world.getGameObjects().add(playerPanzer);
        /*
         GameObject heli = new GameObject(flyingObject, heliGraphic, aiController);
         GameObject panzer = new GameObject(movingObject, panzerGraphic, aiController);
         heli.setController(noController);
         */
    }

}
