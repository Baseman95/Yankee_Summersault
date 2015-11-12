package code;

import yansuen.data.DataInterface;
import code.data.DataObject;
import code.game.GameObject;
import code.game.World;
import yansuen.graphics.GraphicsLoop;
import yansuen.logic.LogicInterface;
import yansuen.logic.LogicLoop;
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

        //<editor-fold defaultstate="collapsed" desc="USELESS SHIT PLS DELETE">
        /*LogicInterface movingObject = (DataInterface data, long tick) -> {
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
        
         };*/
//</editor-fold>
        LogicInterface movingObject = (DataInterface data, long tick) -> {
            //data.setX(data.getX() + 1);
            if (!(data instanceof TankData)) return;
            TankData tankData = (TankData) data;

            //data.setX(data.getX() + 1);
            data.setX(330 * (1 + (float) (Math.sin(((double) tick  / 10 + tankData.dir )) % (Math.PI * 2))));
            data.setY(230 * (1 + (float) (Math.cos(((double) tick  / 10 + tankData.dir) ) % (Math.PI * 2))));

        };

        BufferedImage panzerGraphic = ImageIO.read(new File("mypanzer.png"));
        BufferedImage panzerGraphic1 = ImageIO.read(new File("panzer.png"));

        GameObject playerPanzer0 = new GameObject(new TankData(10, 10, 1), movingObject, panzerGraphic);
        GameObject playerPanzer1 = new GameObject(new TankData(10, 450, 2), movingObject, panzerGraphic1);
        GameObject playerPanzer2 = new GameObject(new TankData(650, 450, 3), movingObject, panzerGraphic);
        GameObject playerPanzer3 = new GameObject(new TankData(650, 10, 4), movingObject, panzerGraphic1);
        GameObject playerPanzer4 = new GameObject(new TankData(650, 10, 5), movingObject, panzerGraphic);

        world.getGameObjects().add(playerPanzer0);
        world.getGameObjects().add(playerPanzer1);
        world.getGameObjects().add(playerPanzer2);
        world.getGameObjects().add(playerPanzer3);
        world.getGameObjects().add(playerPanzer4);
        /*
         GameObject heli = new GameObject(flyingObject, heliGraphic, aiController);
         GameObject panzer = new GameObject(movingObject, panzerGraphic, aiController);
         heli.setController(noController);*/

    }

}
