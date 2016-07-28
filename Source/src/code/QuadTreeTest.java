
package code;

import code.game.Application;
import code.game.QuadTree;
import code.game.tank.Vehicle;
import code.menu.Screen;
import code.presets.ControllerPresets;
import code.presets.GraphicsPresets;
import code.presets.ImagePresets;
import yansuen.game.GameObject;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author voki
 */
public class QuadTreeTest extends Application {

    public static void main(String[] args) {
        Screen screen = new Screen();
        QuadTreeTest test = new QuadTreeTest(screen);
        screen.setApplication(test);
        screen.setVisible(true);
    }

    public QuadTreeTest(Screen screen) {
        super(screen);
    }

    @Override
    public void start() {
        super.start();
        world.addGameObject(new Vehicle(0, 0, 10, 10, ImagePresets.Test.BLACK_SQUARE, GraphicsPresets.DEFAULT, ControllerPresets.PLAYER));
        world.addGameObject(new Vehicle(-70, -30, 10, 10, ImagePresets.Test.BLACK_SQUARE, GraphicsPresets.DEFAULT, ControllerPresets.PLAYER));
        world.addGameObject(new Vehicle(77, -66, 10, 10, ImagePresets.Test.BLACK_SQUARE, GraphicsPresets.DEFAULT, ControllerPresets.PLAYER));
        world.addGameObject(new Vehicle(-4, 4, 10, 10, ImagePresets.Test.BLACK_SQUARE, GraphicsPresets.DEFAULT, ControllerPresets.PLAYER));
        world.addGameObject(new Vehicle(8, 10, 10, 10, ImagePresets.Test.BLACK_SQUARE, GraphicsPresets.DEFAULT, ControllerPresets.PLAYER));
        world.addGameObject(new Vehicle(-3, -50, 10, 10, ImagePresets.Test.BLACK_SQUARE, GraphicsPresets.DEFAULT, ControllerPresets.PLAYER));
        world.addGameObject(new Vehicle(-30, 20, 10, 10, ImagePresets.Test.BLACK_SQUARE, GraphicsPresets.DEFAULT, ControllerPresets.PLAYER));
        world.addGameObject(new Vehicle(80, -90, 10, 10, ImagePresets.Test.BLACK_SQUARE, GraphicsPresets.DEFAULT, ControllerPresets.PLAYER));
        world.addGameObject(new Vehicle(40, 60, 10, 10, ImagePresets.Test.BLACK_SQUARE, GraphicsPresets.DEFAULT, ControllerPresets.PLAYER));
        world.addGameObject(new Vehicle(40, 20, 10, 10, ImagePresets.Test.BLACK_SQUARE, GraphicsPresets.DEFAULT, ControllerPresets.PLAYER));
        Thread t = new Thread(() -> {
            try {
                Thread.sleep(500);
            } catch (InterruptedException ex) {
                Logger.getLogger(QuadTreeTest.class.getName()).log(Level.SEVERE, null, ex);
            }

            QuadTree quadTree = new QuadTree(0, -100, -100, 200, 200);
            for (int i = 0; i < world.getGameObjects().size(); i++) {
                quadTree.insert(world.getGameObjects().get(i));
            }

            ArrayList possibleCollisions = new ArrayList();
            quadTree.retrieve(possibleCollisions, world.getGameObjects().get(0));

            for (int i = 0; i < possibleCollisions.size(); i++) {
                System.out.println(possibleCollisions.get(i).toString());

            }

            quadTree.printTree();
        });
        t.start();

    }

}
