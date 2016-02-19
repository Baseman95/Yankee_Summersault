package code;

import code.game.tank.Chassis;
import code.menu.Screen;
import code.presets.ControllerPresets;
import code.presets.DrivePresets;
import code.presets.GraphicsPresets;
import code.presets.ImagePresets;
import code.presets.WeaponPresets;
import code.game.Application;

/**
 *
 * @author Base
 */
public class LinkTest extends Application {

    public static void main(String args[]) {
        Screen screen = new Screen();
        Application application = new LinkTest(screen);
        screen.setApplication(application);
        screen.setVisible(true);
    }

    public LinkTest(Screen screen) {
        super(screen);
    }

    @Override
    public void start() {
        super.start();
        Chassis zank = new Chassis(10, 10, ImagePresets.TANK, GraphicsPresets.ROTATION, ControllerPresets.PLAYER);
        zank.getWeapons().add(WeaponPresets.createPlasma(zank));
        zank.getWeapons().add(WeaponPresets.createRocketLauncher(zank));
        zank.getWeapons().add(WeaponPresets.createFlameThrower(zank));
        zank.setDrive(DrivePresets.SIMPLE);
        //zank.setDrive(DrivePresets.createRocketDrive(3));
        world.addGameObject(zank);
    }

}
