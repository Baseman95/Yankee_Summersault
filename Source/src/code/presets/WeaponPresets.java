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

    /*        
        Machineguns        
        MG
        Minigun
    
        Rockets
        StraightRocket(Tank Shell)
        PointRocket
        UserControlledRocket
    
        Shotgun
        NormalShotgun
        RoundHouseShotGun(Abwehrmaßnahme)
    
        FlameThrower-
        EMP-  
        
        Special       
        Smoke(Abwehrmaßnahme)
    
     */
    
    
    public static Weapon createWeapon(Chassis chassis, Float weaponOffsetX, Float weaponOffsetY, Long cooldown, BufferedImage weaponImage,
            ControllerInterface weaponController, Drive weaponDrive, ShotInterface si) {

        if (weaponOffsetX == null)
            weaponOffsetX = 0f;

        if (weaponOffsetY == null)
            weaponOffsetY = 0f;

        if (cooldown == null)
            cooldown = 10L;

        if (weaponImage == null)
            weaponImage = ImagePresets.Default.NO_IMAGE;

        //Controller       
        Weapon mg = new Weapon(chassis, cooldown, si, fastReload, bulletImpact, ControllerPresets.HOLD_ACCELERATE,
                weaponOffsetX, weaponOffsetY, weaponImage, GraphicsPresets.ROTATION, weaponController);

        return mg;
    }

    public static ShotInterface createSimpleSingleShotInterface(BufferedImage texture, float travelSpeed, long ticksToLive, float deviationPerSide, int weaponLength) {
        ShotInterface simpleShot = (Weapon weapon, long tick, ImpactInterface impactInterface, World world) -> {
            PositionData pd = ((DataObject) weapon.getData()).getPositionData();

            PolarVector pv = new PolarVector(pd.getRotation(), weaponLength);

            Projectile p = new Projectile(weapon, tick + ticksToLive, impactInterface,
                    PolarVector.xFromPolar(pv) + pd.getX() + pd.getWidth() / 2 - texture.getWidth() / 2, PolarVector.yFromPolar(pv) + pd.getY() + pd.getHeight() / 2 - texture.getHeight() / 2,
                    texture, GraphicsPresets.ROTATION, null);

            double random = Math.abs(generateGaussianRandom(deviationPerSide));

            ((DataObject) p.getData()).getPositionData().setRotation(pd.getRotation() + random);
            p.setDrive(DrivePresets.createStraightDrive(travelSpeed, pd.getRotation() + random));
            world.addGameObject(p);

        };
        return simpleShot;
    }

    public static ShotInterface createSimpleMultiShotInterface(BufferedImage texture, float projectileSpeed, long ticksToLive, float deviationPerSide, int weaponLength, int projectileCount, Float speedDeviation) {
        ShotInterface simpleShot = (Weapon weapon, long tick, ImpactInterface impactInterface, World world) -> {
            PositionData pd = ((DataObject) weapon.getData()).getPositionData();

            PolarVector pv = new PolarVector(pd.getRotation(), weaponLength);

            Float localSpeedDeviation = speedDeviation;

            if (localSpeedDeviation == null)
                localSpeedDeviation = 0f;

            for (int i = 0; i <= projectileCount; i++) {

                Projectile p = new Projectile(weapon, tick + ticksToLive, impactInterface,
                        PolarVector.xFromPolar(pv) + pd.getX() + pd.getWidth() / 2 - texture.getWidth() / 2, PolarVector.yFromPolar(pv) + pd.getY() + pd.getHeight() / 2 - texture.getHeight() / 2,
                        texture, GraphicsPresets.ROTATION, null);

                double randomAccuracy = generateGaussianRandom(deviationPerSide);

                float randomSpeed = (float) new Random().nextGaussian() * localSpeedDeviation - localSpeedDeviation / 2;

                ((DataObject) p.getData()).getPositionData().setRotation(pd.getRotation() + randomAccuracy);
                p.setDrive(DrivePresets.createStraightDrive(projectileSpeed + randomSpeed, pd.getRotation() + randomAccuracy));
                world.addGameObject(p);
            }

        };
        return simpleShot;
    }

    public static ShotInterface createAIControlledSingleShotInterface(BufferedImage texture, float travelSpeed, long travelDistance, float deviationPerSide, int weaponLength, ControllerInterface c) {
        ShotInterface si = (Weapon weapon, long tick, ImpactInterface impactInterface, World world) -> {
            PositionData pd = ((DataObject) weapon.getData()).getPositionData();

            PolarVector pv = new PolarVector(pd.getRotation(), weaponLength);

            Projectile p = new Projectile(weapon, tick + travelDistance, impactInterface,
                    PolarVector.xFromPolar(pv) + pd.getX() + pd.getWidth() / 2 - texture.getWidth() / 2, PolarVector.yFromPolar(pv) + pd.getY() + pd.getHeight() / 2 - texture.getHeight() / 2,
                    texture, GraphicsPresets.ROTATION, c);

            double random = generateGaussianRandom(deviationPerSide);
            ((DataObject) p.getData()).getPositionData().setRotation(pd.getRotation() + random);
            Drive d = DrivePresets.createRocketDrive(travelSpeed, 0.01);
            p.setDrive(d);
            world.addGameObject(p);

        };
        return si;
    }

    /*
    public static Weapon createMG(Chassis chassis) {
        return createWeapon(chassis, null, null, null, ImagePresets.Weapon.SHOT_MG_1, null, null, MG_SHOTINTERFACE);
    }
    protected static ShotInterface MG_SHOTINTERFACE = createSingleShotInterface(projectileImage, travelspeed, ticksToLive, deviationPerSide, weaponLength);
    
     */
//<editor-fold defaultstate="expanded" desc="Machineguns">    
    protected static ShotInterface MG_SHOTINTERFACE = createSimpleSingleShotInterface(ImagePresets.Weapon.SHOT_MG_1, 3, 500, 0.01f, WEAPON_MG_LENGTH);
    protected static ShotInterface MINIGUN_SHOTINTERFACE = createSimpleSingleShotInterface(ImagePresets.Weapon.SHOT_MG_2, 8, 400, 0.005f, WEAPON_MG_LENGTH);

    public static Weapon createMG(Chassis chassis) {
        return createWeapon(chassis, null, null, 10L, ImagePresets.Weapon.WEAPON_MG_1, null, null, MG_SHOTINTERFACE);
    }
    public static Weapon createMinigun(Chassis chassis) {
        return createWeapon(chassis, null, null, 20L, ImagePresets.Weapon.WEAPON_MG_1, null, null, MINIGUN_SHOTINTERFACE);
    }
//</editor-fold>    

//<editor-fold defaultstate="expanded" desc="RocketLaunchers">
    protected static ShotInterface RL_SHOTINTERFACE = createSimpleSingleShotInterface(ImagePresets.Weapon.SHOT_RL_1, 3, 700, 0.005f, WEAPON_MG_LENGTH);

    public static Weapon createRocketLauncher(Chassis chassis) {
        return createWeapon(chassis, null, null, 100L, ImagePresets.Weapon.WEAPON_RL_1, null, null, RL_SHOTINTERFACE);
    }

    public static Weapon createAIGuidedRocketLauncher(Chassis chassis) {

        ControllerInterface guidedController = ControllerPresets.createMoveToController(500, 500);
        ShotInterface si = createAIControlledSingleShotInterface(ImagePresets.Weapon.SHOT_RL_1, 3, 700, 0.005f, WEAPON_MG_LENGTH, guidedController);
        return createWeapon(chassis, null, null, 100L, ImagePresets.Weapon.WEAPON_RL_1, null, null, si);
    }

    public static Weapon createUserControlledRocketLauncher(Chassis chassis) {

        ShotInterface si = createAIControlledSingleShotInterface(ImagePresets.Weapon.SHOT_RL_1, 3, 700, 0.005f, WEAPON_MG_LENGTH, ControllerPresets.PLAYER);
        return createWeapon(chassis, null, null, 100L, ImagePresets.Weapon.WEAPON_RL_1, null, null, si);
    }

//</editor-fold>
    //Shotguns
    protected static ShotInterface SHOTGUN_SHOTINTERFACE = createSimpleMultiShotInterface(ImagePresets.Weapon.SHOT_MG_1, 3f, 200, 0.03f, WEAPON_MG_LENGTH, 10, 0.3f);
    protected static ShotInterface ROUNDHOUSE_SHOTINTERFACE = createSimpleMultiShotInterface(ImagePresets.Weapon.SHOT_MG_1, 3f, 200, 1f, WEAPON_MG_LENGTH, 50, null);
    
    public static Weapon createShotgun(Chassis chassis) {
        return createWeapon(chassis, null, null, 200L, ImagePresets.Default.NOTHING, null, null, ROUNDHOUSE_SHOTINTERFACE);
    }

//<editor-fold defaultstate="collapsed" desc="Specials">
    protected static ShotInterface TRACER_SHOTINTERFACE = createSimpleSingleShotInterface(ImagePresets.Test.TRACER, 0, Long.MAX_VALUE / 2, 0.005f, 0);

    protected static ShotInterface SMOKE_SHOTINTERFACE = createSimpleSingleShotInterface(ImagePresets.Test.SMOKE, 0, 3000, 0, 0);

    public static Weapon createTracer(Chassis chassis) {
        return createWeapon(chassis, null, null, 5L, ImagePresets.Default.NOTHING, null, null, TRACER_SHOTINTERFACE);
    }

    public static Weapon createSmoke(Chassis chassis) {
        return createWeapon(chassis, null, null, 20L, ImagePresets.Default.NOTHING, null, null, SMOKE_SHOTINTERFACE);
    }

//</editor-fold>
    public static double generateGaussianRandom(double averageDeviationInPi) {
        return new Random().nextGaussian() * averageDeviationInPi * Math.PI - averageDeviationInPi * Math.PI / 2.0;
    }

}
