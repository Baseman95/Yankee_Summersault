package code;

import code.controller.ControllerInterface;
import code.data.DataInterface;
import code.game.GameObject;
import code.game.World;
import code.graphics.DefaultGraphicsObject;
import code.graphics.GraphicsInterface;
import code.graphics.GraphicsLoop;
import code.key.KeyManager;
import code.logic.LogicInterface;
import code.logic.LogicLoop;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author Link162534
 */
public class KeyTest {

    public static void main(String[] args) throws IOException {
        LogicLoop ll = new LogicLoop(5000000L, 1);
        GraphicsLoop gl = new GraphicsLoop(33);
        KeyManager keyManager = new KeyManager();
        World world = new World(keyManager);
        Screen screen = new Screen(world);

        ll.setLogic(world);
        gl.setRepaintTarget(screen);

        ll.start();
        gl.start();
        screen.addKeyListener(keyManager);

        screen.setVisible(true);

        LogicInterface movingObject = (DataInterface data, long tick) -> {

        };
        BufferedImage tankImg = ImageIO.read(new File("cool_tank.png"));
        GraphicsInterface defaultGraphics = new DefaultGraphicsObject();

        ControllerInterface playerController = (DataInterface data, long tick, World w, KeyManager manager) -> {
            float uvRotX = (float) Math.cos(data.getRotation());
            float uvRotY = (float) Math.sin(data.getRotation());

            if (manager.isKeyPressed(KeyEvent.VK_W)) {
                data.setX(data.getX() + uvRotX);
                data.setY(data.getY() - uvRotY);
            }
            if (manager.isKeyPressed(KeyEvent.VK_S)) {
                data.setX(data.getX() - uvRotX);
                data.setY(data.getY() + uvRotY);
            }
            if (manager.isKeyPressed(KeyEvent.VK_A))
                data.setRotation(data.getRotation() + 0.01);
            if (manager.isKeyPressed(KeyEvent.VK_D))
                data.setRotation(data.getRotation() - 0.01);

        };

        GameObject tank = new GameObject(10, 10, tankImg, movingObject, defaultGraphics, playerController);
        world.getGameObjects().add(tank);
    }

}
