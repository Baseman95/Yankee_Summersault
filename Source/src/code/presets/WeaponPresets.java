/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package code.presets;

import code.data.DataObject;
import code.data.PositionData;
import code.game.World;
import code.game.tank.Chassis;
import code.game.tank.Drive;
import code.game.tank.Weapon;
import code.game.tank.projectile.ImpactInterface;
import code.game.tank.projectile.Projectile;
import yansuen.key.KeyManager;
import code.game.tank.projectile.ShotInterface;
import java.awt.image.BufferedImage;
import yansuen.controller.ControllerInterface;
import yansuen.game.GameObject;
import yansuen.logic.LogicInterface;

/**
 *
 * @author Eris
 */
public class WeaponPresets {

    private WeaponPresets() {

    }
    static LogicInterface fastReload = (GameObject gameObject, long tick, World world1, KeyManager manager) -> {
    };

    static ImpactInterface bulletImpact = (Projectile projectile, Weapon weapon, long tick, World world1, KeyManager manager) -> {
    };

    public static ShotInterface getSingleShot(long duration, float speed, BufferedImage img) {
        ShotInterface singleShot = (Weapon weapon, long tick, ImpactInterface impactInterface, World world) -> {
            //Spawn von Projektil, + Richtung, adding projektil zu welt,
            PositionData pd = ((DataObject) weapon.getData()).getPositionData();
            Projectile p = new Projectile(weapon, tick + duration, impactInterface,
                    pd.getX() + pd.getWidth() / 2 - img.getWidth() / 2,
                    pd.getY() + pd.getHeight() / 2 - img.getHeight() / 2,
                    img, GraphicsPresets.ROTATION, null);
            ((DataObject) p.getData()).getPositionData().setRotation(pd.getRotation());
            p.setDrive(DrivePresets.createStraightDrive(speed, pd.getRotation()));
            world.addGameObject(p);

        };
        return singleShot;
    }

    public static ShotInterface getGuidedShot(long duration, BufferedImage img, Drive drive, ControllerInterface controller) {
        ShotInterface guidedShot = (Weapon weapon, long tick, ImpactInterface impactInterface, World world) -> {
            //Spawn von Projektil, + Richtung, adding projektil zu welt,
            DataObject data = (DataObject) weapon.getData();
            Projectile p = new Projectile(weapon, tick + duration,
                    impactInterface, data.getPositionData().getX(),
                    data.getPositionData().getY(), img, GraphicsPresets.ROTATION, controller);
            ((DataObject) p.getData()).getPositionData().setRotation(data.getPositionData().getRotation());
            p.setDrive(drive);
            world.addGameObject(p);
        };
        return guidedShot;
    }

    public static Weapon createPlasma(Chassis chassis) {
        Weapon weapon = new Weapon(chassis, 20, WeaponPresets.getSingleShot(500, 4, ImagePresets.SHOT_1),
                fastReload, bulletImpact,
                null, 10,
                10, ImagePresets.TURRET_A,
                GraphicsPresets.ROTATION, null);
        return weapon;
    }

    public static Weapon createGuided(Chassis chassis) {
        Weapon weapon = new Weapon(chassis, 50, WeaponPresets.getGuidedShot(700, ImagePresets.SHOT_1,
                DrivePresets.createRocketDrive(4, 0.05f), ControllerPresets.createMoveToController(800, 600)),
                fastReload, bulletImpact,
                null, 40,
                40, ImagePresets.TURRET_A,
                GraphicsPresets.ROTATION, null);
        return weapon;
    }

}
