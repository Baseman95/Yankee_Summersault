package code;

import code.game.Application;
import code.game.tank.Vehicle;
import code.game.tank.Weapon;
import code.menu.Screen;
import java.io.IOException;
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
        Vehicle zank = new Vehicle(10, 10, ImagePresets.Test.TANK, GraphicsPresets.ROTATION, ControllerPresets.PLAYER);
        zank.setDrive(DrivePresets.createSimpleDrive());

        Vehicle choppah = new Vehicle(300, 300, ImagePresets.Vehicle.Air.APACHE_B2, GraphicsPresets.ROTATION, null);
        //choppah.setDrive(DrivePresets.createHeli(0.006f));

        Vehicle hydra = new Vehicle(300, 350, ImagePresets.Vehicle.Air.HARRIER_R, GraphicsPresets.ROTATION, null);

        Weapon mg = WeaponPresets.createMinigun(zank);
        Weapon shotgun = WeaponPresets.createShotgun(zank);
        Weapon tvmissile = WeaponPresets.createUserControlledRocketLauncher(zank);
        Weapon smoke = WeaponPresets.createSmoke(zank);

        zank.getWeapons().add(mg);
        zank.getWeapons().add(shotgun);
        zank.getWeapons().add(tvmissile);
        zank.getWeapons().add(smoke);

        world.addGameObject(zank);
        world.addGameObject(choppah);
        world.addGameObject(hydra);

        world.addGameObject(shotgun);
        world.addGameObject(tvmissile);
        world.addGameObject(smoke);

    }

}
