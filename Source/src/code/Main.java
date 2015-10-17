package code;

import code.graphics.GraphicsInterface;
import code.logic.LogicInterface;
import code.logic.LogicLoop;

/**
 *
 * @author Base
 */
public class Main {

    public static void main(String[] args) {
        LogicLoop ll = new LogicLoop();
        ll.start();
        ll.addLogic((long tick) -> (System.out.println(tick)));

        LogicInterface movingObject = new LogicInterface() {

            @Override
            public void doLogic(long tick) {

            }

        };

        GraphicsInterface panzerGraphic = new GraphicsInterface() {

            @Override
            public void doGraphic() {

            }

        };

        GraphicsInterface heliGraphic = new GraphicsInterface() {

            @Override
            public void doGraphic() {

            }

        };
        
        
        GameObject playerPanzer = new GameObject(movingObject, panzerGraphic, playerController);
        GameObject heli = new GameObject(flyingObject, heliGraphic, aiController);
        GameObject panzer = new GameObject(movingObject, panzerGraphic, aiController);
        heli.setController(noController);

    }

}
