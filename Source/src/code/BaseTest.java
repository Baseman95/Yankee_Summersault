package code;

import code.data.DataInterface;
import code.data.DataObject;
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
public class BaseTest {

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
            //data.setX(data.getX() + 1);
            if (!(data instanceof TankData)) return;
            TankData tankData = (TankData) data;
            switch (tankData.dir) {
                case 0: {
                    data.setY(data.getY() + 10);
                    break;
                }
                case 1: {
                    data.setX(data.getX() + 10);
                    break;
                }
                case 2: {
                    data.setY(data.getY() - 10);
                    break;
                }
                case 3: {
                    data.setX(data.getX() - 10);
                    break;
                }
            }

            if (tankData.dir == 0 && data.getY() >= 450) tankData.dir++;
            if (tankData.dir == 1 && data.getX() >= 650) tankData.dir++;
            if (tankData.dir == 2 && data.getY() <= 10) tankData.dir++;
            if (tankData.dir == 3 && data.getX() <= 10) tankData.dir = 0;

        };
        BufferedImage panzerGraphic = ImageIO.read(new File("mypanzer.png"));
        //BufferedImage panzerGraphic = ImageIO.read(new File("panzer.png"));

        GameObject playerPanzer0 = new GameObject(new TankData(10, 10, 0), movingObject, panzerGraphic, null);
        GameObject playerPanzer1 = new GameObject(new TankData(10, 450, 0), movingObject, panzerGraphic, null);
        GameObject playerPanzer2 = new GameObject(new TankData(650, 450, 0), movingObject, panzerGraphic, null);
        GameObject playerPanzer3 = new GameObject(new TankData(650, 10, 0), movingObject, panzerGraphic, null);

        world.getGameObjects().add(playerPanzer0);
        world.getGameObjects().add(playerPanzer1);
        world.getGameObjects().add(playerPanzer2);
        world.getGameObjects().add(playerPanzer3);
        /*
         GameObject heli = new GameObject(flyingObject, heliGraphic, aiController);
         GameObject panzer = new GameObject(movingObject, panzerGraphic, aiController);
         heli.setController(noController);*/

    }

}
