package code;

import code.game.World;
import code.graphics.GraphicsLoop;
import code.logic.LogicInterface;
import code.logic.LogicLoop;

/**
 *
 * @author Link162534
 */
public class BaseTest {

    public static void main(String[] args) {
        LogicLoop ll = new LogicLoop(50000000L, 10);
        GraphicsLoop gl = new GraphicsLoop(33);
        World world = new World();
        Screen screen = new Screen(world);

        ll.setLogic(world);
        gl.setRepaintTarget(screen);

        ll.start();
        gl.start();

        screen.setVisible(true);

        LogicInterface movingObject = new LogicInterface() {

            @Override
            public void doLogic(long tick) {

            }

        };


        /* GameObject playerPanzer = new GameObject(movingObject, panzerGraphic, playerController);
         GameObject heli = new GameObject(flyingObject, heliGraphic, aiController);
         GameObject panzer = new GameObject(movingObject, panzerGraphic, aiController);
         heli.setController(noController);*/
    }

}
