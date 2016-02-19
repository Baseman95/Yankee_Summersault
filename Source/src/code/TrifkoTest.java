package code;

import code.menu.GamePanel;
import code.game.World;
import code.game.tank.Chassis;
import code.presets.ControllerPresets;
import code.presets.DrivePresets;
import code.presets.GraphicsPresets;
import code.presets.ImagePresets;
import yansuen.graphics.GraphicsLoop;
import yansuen.key.MasterKeyManager;
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
        MasterKeyManager keyManager = new MasterKeyManager();
        World world = new World(keyManager);
        GamePanel screen = new GamePanel(world);

        ll.setLogic(world);
        gl.setRepaintTarget(screen);

        ll.start();
        gl.start();
        screen.addKeyListener(keyManager);

        screen.setVisible(true);

        

        Chassis tank = new Chassis(300, 200, ImagePresets.TANK2, GraphicsPresets.ROTATION, ControllerPresets.PLAYER);
        tank.setDrive(DrivePresets.DEFAULT_TRACK);
        
        Chassis tank3 = new Chassis(50, 50, ImagePresets.TANK2, GraphicsPresets.ROTATION, null);
        tank3.setDrive(DrivePresets.createTrack(0.004f));

        //GameObject tank = new GameObject(10, 10, tankImg, tankLogic, defaultGraphics, playerController);
        GameObject tank2 = new GameObject(500, 300, ImagePresets.TANK, GraphicsPresets.ROTATION, null);
        world.getGameObjects().add(tank);
        world.getGameObjects().add(tank2);
        world.getGameObjects().add(tank3);
    }
}
