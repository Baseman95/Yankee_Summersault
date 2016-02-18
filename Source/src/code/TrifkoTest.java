package code;

import code.game.World;
import code.game.tank.Chassis;
import code.presets.ControllerPresets;
import code.presets.DrivePresets;
import code.presets.GraphicsPresets;
import code.presets.ImagePresets;
import yansuen.graphics.GraphicsLoop;
import yansuen.key.KeyManager;
import yansuen.logic.LogicLoop;
import java.io.IOException;
import yansuen.game.GameObject;

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
