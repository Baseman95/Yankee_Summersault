package code;

import yansuen.controller.ControllerInterface;
import code.data.DataObject;
import code.data.ImageData;
import code.data.MovementData;
import code.data.PositionData;
import code.game.World;
import code.game.tank.Chassis;
import code.game.tank.Drive;
import code.presets.ControllerPresets;
import code.presets.DrivePresets;
import code.presets.GraphicsPresets;
import code.presets.ImagePresets;
import yansuen.graphics.GraphicsInterface;
import yansuen.graphics.GraphicsLoop;
import yansuen.key.KeyManager;
import yansuen.logic.LogicInterface;
import yansuen.logic.LogicLoop;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import yansuen.game.GameObject;
import yansuen.physic.CartesianVector;
import yansuen.physic.PolarVector;

/**
 *
 * @author Link162534
 */
public class TrifkoTest {

    public static long bulletTick = 0;

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

        

        Chassis tank = new Chassis(50, 50, ImagePresets.TANK, GraphicsPresets.ROTATION, ControllerPresets.PLAYER);
        tank.setDrive(DrivePresets.DEFAULT_TRACK);

        //GameObject tank = new GameObject(10, 10, tankImg, tankLogic, defaultGraphics, playerController);
        GameObject tank2 = new GameObject(500, 300, ImagePresets.TANK, GraphicsPresets.ROTATION, null);
        world.getGameObjects().add(tank);
        world.getGameObjects().add(tank2);
    }
}
