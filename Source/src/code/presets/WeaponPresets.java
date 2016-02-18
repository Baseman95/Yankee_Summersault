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

    static int WEAPON_MG_LENGTH = 10;

    private WeaponPresets() {

    }

    static LogicInterface fastReload = (GameObject gameObject, long tick, World world1, KeyManager manager) -> {
    };

    static ImpactInterface bulletImpact = (Projectile projectile, Weapon weapon, long tick, World world1, KeyManager manager) -> {
    };

    public static ShotInterface createSingleShot(long duration, float speed, BufferedImage img) {
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

    public static ShotInterface createGuidedShot(long duration, BufferedImage img, float speed, double rotation, ControllerInterface controller) {
        ShotInterface guidedShot = (Weapon weapon, long tick, ImpactInterface impactInterface, World world) -> {
            //Spawn von Projektil, + Richtung, adding projektil zu welt,
            DataObject data = (DataObject) weapon.getData();
            Projectile p = new Projectile(weapon, tick + duration,
                    impactInterface, data.getPositionData().getX(),
                    data.getPositionData().getY(), img, GraphicsPresets.ROTATION, controller);
            ((DataObject) p.getData()).getPositionData().setRotation(data.getPositionData().getRotation());
            p.setDrive(DrivePresets.createRocketDrive(speed, rotation));
            world.addGameObject(p);
        };
        return guidedShot;
    }

    public static Weapon createPlasma(Chassis chassis) {
        Weapon weapon = new Weapon(chassis, 20, WeaponPresets.createSingleShot(500, 4, ImagePresets.SHOT_1),
                fastReload, bulletImpact,
                null, 10,
                10, ImagePresets.TURRET_A,
                GraphicsPresets.ROTATION, null);
        return weapon;
    }

    public static Weapon createHSRocket(Chassis chassis) {
        Weapon weapon = new Weapon(chassis, 100,
                WeaponPresets.createGuidedShot(700, ImagePresets.SHOT_2, 4, 0.05f, ControllerPresets.createMoveToController(800, 600)),
                fastReload, bulletImpact, null, 10, 10, ImagePresets.TURRET_A, GraphicsPresets.ROTATION, null);
        return weapon;
    }

    public static Weapon createFlameThrower(Chassis chassis) {
        Weapon weapon = new Weapon(chassis, 10, WeaponPresets.createSingleShot(100, 3, ImagePresets.SHOT_3),
                fastReload, bulletImpact,
                null, 10,
                10, ImagePresets.TURRET_A,
                GraphicsPresets.ROTATION, null);
        return weapon;
    }
    //NEWSTUFFBASE  

    /*
        WeaponBehavior
        MG
        FlameThrower
        EMP
        Rocket
        HSRocket
        LGRocket
        UCRocket    
     */
    /**
     *
     * @param chassis
     * @param rof bullet per tick
     * @param travelspeed
     * @param traveldistance
     * @param weaponTexture
     * @param weaponOffsetX
     * @param weaponOffsetY
     * @param bulletTexture
     * @param weaponController
     * @return
     */
    public static Weapon createMG(Chassis chassis, Double rof, Float travelspeed, Long traveldistance,
            BufferedImage weaponTexture, Float weaponOffsetX, Float weaponOffsetY, BufferedImage bulletTexture, ControllerInterface weaponController) {

        if (rof == null)
            rof = 0d;

        if (travelspeed == null)
            travelspeed = 0f;

        if (traveldistance == null)
            traveldistance = 0L;

        if (weaponTexture == null)
            weaponTexture = ImagePresets.WEAPON_MG_1;

        if (weaponOffsetX == null)
            weaponOffsetX = 0f;

        if (weaponOffsetY == null)
            weaponOffsetY = 0f;

        if (bulletTexture == null)
            bulletTexture = ImagePresets.BULLET_MG_1;

        ShotInterface si = createSimpleShot(traveldistance, travelspeed, bulletTexture);

        Weapon mg = new Weapon(chassis, (long) (1.0 / rof), si, fastReload, bulletImpact, ControllerPresets.HOLD_ACCELERATE,
                weaponOffsetX, weaponOffsetY, weaponTexture, GraphicsPresets.ROTATION, weaponController);

        return null;
    }    
    public static ShotInterface createSimpleShot(BufferedImage texture, long travelspeed, float traveldistance, int weaponlengh) {
        ShotInterface simpleShot = (Weapon weapon, long tick, ImpactInterface impactInterface, World world) -> {
            PositionData pd = ((DataObject) weapon.getData()).getPositionData();
            Projectile p = new Projectile(weapon, tick + duration, impactInterface,
                    pd.getX() + pd.getWidth() / 2 - img.getWidth() / 2,
                    pd.getY() + pd.getHeight() / 2 - img.getHeight() / 2,
                    img, GraphicsPresets.ROTATION, null);
            ((DataObject) p.getData()).getPositionData().setRotation(pd.getRotation());
            p.setDrive(DrivePresets.createStraightDrive(speed, pd.getRotation()));
            world.addGameObject(p);

        };
        return simpleShot;
    }
}
