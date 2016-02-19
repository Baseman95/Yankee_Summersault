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
import java.util.Random;
import yansuen.controller.ControllerInterface;
import yansuen.game.GameObject;
import yansuen.logic.LogicInterface;
import yansuen.physic.PolarVector;

/**
 *
 * @author Eris
 */
public class WeaponPresets {

    static int WEAPON_MG_LENGTH = 25;

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
        Weapon weapon = new Weapon(chassis, 20, WeaponPresets.createSingleShot(500, 4, ImagePresets.Test.SHOT_1),
                fastReload, bulletImpact,
                null, 10,
                10, ImagePresets.Test.TURRET_A,
                GraphicsPresets.ROTATION, null);
        return weapon;
    }

    public static Weapon createHSRocket(Chassis chassis) {
        Weapon weapon = new Weapon(chassis, 100,
                WeaponPresets.createGuidedShot(700, ImagePresets.Test.SHOT_2, 4, 0.05f, ControllerPresets.createMoveToController(800, 600)),
                fastReload, bulletImpact, null, 10, 10, ImagePresets.Test.TURRET_A, GraphicsPresets.ROTATION, null);
        return weapon;
    }

    public static Weapon createFlameThrower(Chassis chassis) {
        Weapon weapon = new Weapon(chassis, 10, WeaponPresets.createSingleShot(100, 3, ImagePresets.Test.SHOT_3),
                fastReload, bulletImpact,
                null, 10,
                10, ImagePresets.Test.TURRET_A,
                GraphicsPresets.ROTATION, null);
        return weapon;
    }
    //NEWSTUFFBASE  

    /*        
        MG+
        FlameThrower
        EMP
        Rocket
        HSRocket
        LGRocket
        UCRocket    
     */

    /** 
     * @param chassis
     * @param weaponOffsetX
     * @param weaponOffsetY
     * @param weaponLength
     * @param weaponImage
     * @param weaponController
     * @param weaponDrive
     * @param cooldown in ticks
     * @param deviationPerSide percent
     * @param travelspeed
     * @param ticksToLive
     * @param projectileImage
     * @param projectileController
     * @param projectileDrive
     * @return 
     */
    public static Weapon createSingleShotWeapon(Chassis chassis,
            Float weaponOffsetX, Float weaponOffsetY, Integer weaponLength, BufferedImage weaponImage,
            ControllerInterface weaponController, Drive weaponDrive,
            Long cooldown, Float deviationPerSide, Float travelspeed, Long ticksToLive, BufferedImage projectileImage,
            ControllerInterface projectileController, Drive projectileDrive) {

        if (weaponOffsetX == null)
            weaponOffsetX = 0f;

        if (weaponOffsetY == null)
            weaponOffsetY = 0f;

        if (weaponLength == null)
            weaponLength = 0;

        if (weaponImage == null)
            weaponImage = ImagePresets.Default.NO_IMAGE;

        //Controller
        if (cooldown == null)
            cooldown = 10L;

        if (deviationPerSide == null)
            deviationPerSide = 0.1f;

        if (travelspeed == null)
            travelspeed = 3f;

        if (ticksToLive == null)
            ticksToLive = 500L;

        if (projectileImage == null)
            projectileImage = ImagePresets.Default.NO_IMAGE;

        //Controller       
        ShotInterface si = createSingleShot(projectileImage, travelspeed, ticksToLive, deviationPerSide, weaponLength);

        Weapon mg = new Weapon(chassis, cooldown, si, fastReload, bulletImpact, ControllerPresets.HOLD_ACCELERATE,
                weaponOffsetX, weaponOffsetY, weaponImage, GraphicsPresets.ROTATION, weaponController);

        return mg;
    }

    public static ShotInterface createSingleShot(BufferedImage texture, float travelSpeed, long travelDistance, float deviationPerSide, int weaponLength) {
        ShotInterface simpleShot = (Weapon weapon, long tick, ImpactInterface impactInterface, World world) -> {
            PositionData pd = ((DataObject) weapon.getData()).getPositionData();

            PolarVector pv = new PolarVector(pd.getRotation(), weaponLength);

            Projectile p = new Projectile(weapon, tick + travelDistance, impactInterface,
                    PolarVector.xFromPolar(pv) + pd.getX() + pd.getWidth() / 2 - texture.getWidth() / 2, PolarVector.yFromPolar(pv) + pd.getY() + pd.getHeight() / 2 - texture.getHeight() / 2,
                    texture, GraphicsPresets.ROTATION, null);

            double random = generateGaussianRandom(deviationPerSide);

            ((DataObject) p.getData()).getPositionData().setRotation(pd.getRotation() + random);
            p.setDrive(DrivePresets.createStraightDrive(travelSpeed, pd.getRotation() + random));
            world.addGameObject(p);

        };
        return simpleShot;
    }

    public static double generateGaussianRandom(double averageDeviationInPi) {
        return new Random().nextGaussian() * averageDeviationInPi * Math.PI - averageDeviationInPi * Math.PI / 2.0;
    }

    public static Weapon createMG(Chassis chassis) {
        return createSingleShotWeapon(chassis, null, null, WEAPON_MG_LENGTH, ImagePresets.Weapon.SHOT_MG_1,
                null, null,
                null, 0.01f, null, null, ImagePresets.Weapon.SHOT_MG_1,
                null, null);
    }

    public static Weapon createRocketLauncher(Chassis chassis) {
        return createSingleShotWeapon(chassis, null, null, WEAPON_MG_LENGTH, ImagePresets.Weapon.WEAPON_RL_1,
                null, null,
                100L, 0.01f, 2f, 1000L, ImagePresets.Weapon.SHOT_RL_1,
                null, null);
    }

    public static Weapon createTracer(Chassis chassis) {
        return createSingleShotWeapon(chassis, null, null, 0, ImagePresets.Default.NOTHING,
                null, null,
                5L, 0f, 0f, Long.MAX_VALUE/2, ImagePresets.Test.TRACER,
                null, null);
    }
    
    /** 
     * @param chassis
     * @param weaponOffsetX
     * @param weaponOffsetY
     * @param weaponLength
     * @param weaponImage
     * @param weaponController
     * @param weaponDrive
     * @param rof bullet per tick
     * @param deviationPerSide percent
     * @param travelspeed
     * @param traveldistance
     * @param projectileImage
     * @param projectileController
     * @param projectileDrive
     * @return 
     */
}
