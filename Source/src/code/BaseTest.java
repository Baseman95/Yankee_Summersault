package code;

import code.game.Application;
import code.game.tank.Chassis;
import code.game.tank.Weapon;
import code.menu.Screen;
import java.io.IOException;
import yansuen.key.MasterKeyManager;
import code.presets.ImagePresets;
import code.presets.ControllerPresets;
import code.presets.DrivePresets;
import code.presets.GraphicsPresets;
import code.presets.WeaponPresets;

/**
 *
 * @author Base
 */
public class BaseTest extends Application {

    public static void main(String[] args) throws IOException {
        Screen screen = new Screen();
        Application application = new BaseTest(screen);
        screen.setApplication(application);
        screen.setVisible(true);
    }

    public BaseTest(Screen screen) {
        super(screen);
    }

    @Override
    public void start() {
        super.start();
        //<editor-fold defaultstate="collapsed" desc="SPREAD">
/*
        CartesianVector bulletTrajectory = new CartesianVector(new PolarVector(data.getPositionData().getRotation() + (Math.random() * Math.PI / 8 - Math.random() * Math.PI / 16), 4));
        dataO.getMovementData().setMovementX(bulletTrajectory.x + hostMovement.x);
        dataO.getMovementData().setMovementY(bulletTrajectory.y + hostMovement.y);
        
 </editor-fold>*/
        Chassis zank = new Chassis(10, 10, ImagePresets.Test.TANK, GraphicsPresets.ROTATION, ControllerPresets.PLAYER);
        zank.setDrive(DrivePresets.createSimpleDrive());

        Weapon mg = WeaponPresets.createMinigun(zank);
        Weapon shotgun = WeaponPresets.createShotgun(zank);
        Weapon tvmissile = WeaponPresets.createUserControlledRocketLauncher(zank);
        Weapon smoke = WeaponPresets.createSmoke(zank);

        zank.getWeapons().add(mg);
        zank.getWeapons().add(shotgun);
        zank.getWeapons().add(tvmissile);
        zank.getWeapons().add(smoke);

        world.addGameObject(zank);
        world.addGameObject(shotgun);
        world.addGameObject(tvmissile);
        world.addGameObject(smoke);

    }

}
