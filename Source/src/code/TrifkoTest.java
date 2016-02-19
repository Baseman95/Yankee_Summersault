package code;

import code.game.Application;
import code.menu.GamePanel;
import code.game.World;
import code.game.tank.Chassis;
import code.menu.Screen;
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
public class TrifkoTest extends Application {

    public static void main(String[] args) throws IOException {
        Screen screen = new Screen();
        Application application = new TrifkoTest(screen);
        screen.setApplication(application);
        screen.setVisible(true);
    }

    public TrifkoTest(Screen screen) {
        super(screen);
    }

    @Override
    public void start() {
        super.start();
        Chassis tank = new Chassis(300, 200, ImagePresets.Test.TANK2, GraphicsPresets.ROTATION, ControllerPresets.PLAYER);
        tank.setDrive(DrivePresets.DEFAULT_TRACK);

        Chassis tank3 = new Chassis(50, 50, ImagePresets.Test.TANK2, GraphicsPresets.ROTATION, null);
        tank3.setDrive(DrivePresets.createTrack(0.004f));

        //GameObject tank = new GameObject(10, 10, tankImg, tankLogic, defaultGraphics, playerController);
        GameObject tank2 = new GameObject(500, 300, ImagePresets.Test.TANK, GraphicsPresets.ROTATION, null);
        world.getGameObjects().add(tank);
        world.getGameObjects().add(tank2);
        world.getGameObjects().add(tank3);
    }

}
