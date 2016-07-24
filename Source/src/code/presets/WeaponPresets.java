package code.presets;

import yansuen.data.GameData;
import code.game.World;
import code.game.tank.Vehicle;
import code.game.tank.Drive;
import code.game.tank.Weapon;
import code.game.tank.projectile.ImpactInterface;
import code.game.tank.projectile.Projectile;
import yansuen.key.MasterKeyManager;
import code.game.tank.projectile.ShotInterface;
import java.awt.image.BufferedImage;
import java.util.Random;
import yansuen.game.GameObject;
import yansuen.logic.LogicInterface;
import yansuen.physic.PolarVector;

/**
 * @author Eris
 */
public class WeaponPresets {

    static int WEAPON_MG_LENGTH = 25;

    private WeaponPresets() {
    }

    static LogicInterface fastReload = (GameObject gameObject, long tick, World world1, MasterKeyManager manager) -> {
    };

    static ImpactInterface bulletImpact = (Projectile projectile, Weapon weapon, long tick, World world1, MasterKeyManager manager) -> {
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
    public static Weapon createWeapon(Vehicle vehicle, Float weaponOffsetX, Float weaponOffsetY, Long cooldown, BufferedImage weaponImage,
            LogicInterface weaponController, Drive weaponDrive, ShotInterface si) {

        if (weaponOffsetX == null)
            weaponOffsetX = 0f;

        if (weaponOffsetY == null)
            weaponOffsetY = 0f;

        if (cooldown == null)
            cooldown = 10L;

        if (weaponImage == null)
            weaponImage = ImagePresets.Default.NO_IMAGE;

        //Controller       
        Weapon mg = new Weapon(vehicle, cooldown, si, fastReload, bulletImpact, ControllerPresets.HOLD_ACCELERATE,
                               weaponOffsetX, weaponOffsetY, weaponImage, GraphicsPresets.ROTATION, weaponController);

        return mg;
    }

    //<editor-fold defaultstate="collapsed" desc="create ShotInterfaces">
    public static ShotInterface createSimpleSingleShotInterface(BufferedImage texture, float travelSpeed, long ticksToLive, float deviationPerSide, int weaponLength) {
        ShotInterface simpleShot = (Weapon weapon, long tick, ImpactInterface impactInterface, World world) -> {
            GameData data = ((GameData) weapon.getData());

            PolarVector pv = new PolarVector(data.getRotation(), weaponLength);

            Projectile p = new Projectile(weapon, tick + ticksToLive, impactInterface,
                                          PolarVector.xFromPolar(pv) + data.getX() + data.getWidth() / 2 - texture.getWidth() / 2, PolarVector.yFromPolar(pv) + data.getY() + data.getHeight() / 2 - texture.getHeight() / 2,
                                          texture, GraphicsPresets.ROTATION, null);

            double random = Math.abs(generateGaussianRandom(deviationPerSide));

            data.setRotation(data.getRotation() + random);
            p.setDrive(DrivePresets.createStraightDrive(travelSpeed, data.getRotation() + random));
            world.addGameObject(p);

        };
        return simpleShot;
    }

    public static ShotInterface createSimpleMultiShotInterface(BufferedImage texture, float projectileSpeed, long ticksToLive, float deviationPerSide, int weaponLength, int projectileCount, Float speedDeviation) {
        ShotInterface simpleShot = (Weapon weapon, long tick, ImpactInterface impactInterface, World world) -> {
            GameData data = ((GameData) weapon.getData());

            PolarVector pv = new PolarVector(data.getRotation(), weaponLength);

            Float localSpeedDeviation = speedDeviation;

            if (localSpeedDeviation == null)
                localSpeedDeviation = 0f;

            for (int i = 0; i <= projectileCount; i++) {

                Projectile p = new Projectile(weapon, tick + ticksToLive, impactInterface,
                                              PolarVector.xFromPolar(pv) + data.getX() + data.getWidth() / 2 - texture.getWidth() / 2, PolarVector.yFromPolar(pv) + data.getY() + data.getHeight() / 2 - texture.getHeight() / 2,
                                              texture, GraphicsPresets.ROTATION, null);

                double randomAccuracy = generateGaussianRandom(deviationPerSide);

                float randomSpeed = (float) new Random().nextGaussian() * localSpeedDeviation - localSpeedDeviation / 2;

                data.setRotation(data.getRotation() + randomAccuracy);
                p.setDrive(DrivePresets.createStraightDrive(projectileSpeed + randomSpeed, data.getRotation() + randomAccuracy));
                world.addGameObject(p);
            }

        };
        return simpleShot;
    }

    public static ShotInterface createAIControlledSingleShotInterface(BufferedImage texture, float travelSpeed, long travelDistance, float deviationPerSide, int weaponLength, LogicInterface c) {
        ShotInterface si = (Weapon weapon, long tick, ImpactInterface impactInterface, World world) -> {
            GameData data = ((GameData) weapon.getData());

            PolarVector pv = new PolarVector(data.getRotation(), weaponLength);

            Projectile p = new Projectile(weapon, tick + travelDistance, impactInterface,
                                          PolarVector.xFromPolar(pv) + data.getX() + data.getWidth() / 2 - texture.getWidth() / 2, PolarVector.yFromPolar(pv) + data.getY() + data.getHeight() / 2 - texture.getHeight() / 2,
                                          texture, GraphicsPresets.ROTATION, c);

            double random = generateGaussianRandom(deviationPerSide);
            data.setRotation(data.getRotation() + random);
            Drive d = DrivePresets.createRocketDrive(travelSpeed, 0.01);
            p.setDrive(d);
            world.addGameObject(p);

        };
        return si;
    }
//</editor-fold>

    /*
    public static Weapon createMG(Vehicle vehicle) {
        return createWeapon(vehicle, null, null, null, ImagePresets.Projectile.MACHINEGUN, null, null, MG_SHOTINTERFACE);
    }
    protected static ShotInterface MG_SHOTINTERFACE = createSingleShotInterface(projectileImage, travelspeed, ticksToLive, deviationPerSide, weaponLength);
    
     */
//<editor-fold defaultstate="expanded" desc="Machineguns">    
    protected static ShotInterface MG_SHOTINTERFACE = createSimpleSingleShotInterface(ImagePresets.Projectile.MACHINEGUN, 3, 500, 0.01f, WEAPON_MG_LENGTH);
    protected static ShotInterface MINIGUN_SHOTINTERFACE = createSimpleSingleShotInterface(ImagePresets.Projectile.MACHINEGUN, 8, 400, 0.005f, WEAPON_MG_LENGTH);

    public static Weapon createMG(Vehicle vehicle) {
        return createWeapon(vehicle, null, null, 10L, ImagePresets.Projectile.MACHINEGUN, null, null, MG_SHOTINTERFACE);
    }

    public static Weapon createMinigun(Vehicle vehicle) {
        return createWeapon(vehicle, null, null, 20L, ImagePresets.Projectile.MACHINEGUN, null, null, MINIGUN_SHOTINTERFACE);
    }
//</editor-fold>    

//<editor-fold defaultstate="expanded" desc="RocketLaunchers">
    protected static ShotInterface RL_SHOTINTERFACE = createSimpleSingleShotInterface(ImagePresets.Projectile.SHELL, 3, 700, 0.005f, WEAPON_MG_LENGTH);

    public static Weapon createRocketLauncher(Vehicle vehicle) {
        return createWeapon(vehicle, null, null, 100L, ImagePresets.Weapon.SHELL, null, null, RL_SHOTINTERFACE);
    }

    public static Weapon createAIGuidedRocketLauncher(Vehicle vehicle) {

        LogicInterface guidedController = ControllerPresets.createMoveToController(500, 500);
        ShotInterface si = createAIControlledSingleShotInterface(ImagePresets.Projectile.SHELL, 3, 700, 0.005f, WEAPON_MG_LENGTH, guidedController);
        return createWeapon(vehicle, null, null, 100L, ImagePresets.Weapon.SHELL, null, null, si);
    }

    public static Weapon createUserControlledRocketLauncher(Vehicle vehicle) {

        ShotInterface si = createAIControlledSingleShotInterface(ImagePresets.Projectile.SHELL, 3, 700, 0.005f, WEAPON_MG_LENGTH, ControllerPresets.PLAYER);
        return createWeapon(vehicle, null, null, 100L, ImagePresets.Weapon.SHELL, null, null, si);
    }

//</editor-fold>
//<editor-fold defaultstate="expanded" desc="Shotguns">    
    protected static ShotInterface SHOTGUN_SHOTINTERFACE = createSimpleMultiShotInterface(ImagePresets.Projectile.MACHINEGUN, 3f, 200, 0.03f, WEAPON_MG_LENGTH, 10, 0.3f);
    protected static ShotInterface ROUNDHOUSE_SHOTINTERFACE = createSimpleMultiShotInterface(ImagePresets.Projectile.MACHINEGUN, 3f, 200, 1f, WEAPON_MG_LENGTH, 50, null);

    public static Weapon createShotgun(Vehicle vehicle) {
        return createWeapon(vehicle, null, null, 200L, ImagePresets.Default.NOTHING, null, null, SHOTGUN_SHOTINTERFACE);
    }

    public static Weapon createRoundShotGund(Vehicle vehicle) {
        return createWeapon(vehicle, null, null, 400L, ImagePresets.Default.NOTHING, null, null, ROUNDHOUSE_SHOTINTERFACE);
    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Specials">
    protected static ShotInterface TRACER_SHOTINTERFACE = createSimpleSingleShotInterface(ImagePresets.Test.TRACER, 0, Long.MAX_VALUE / 2, 0.005f, 0);

    protected static ShotInterface SMOKE_SHOTINTERFACE = createSimpleSingleShotInterface(ImagePresets.Test.SMOKE, 0, 500, 0, 0);

    public static Weapon createTracer(Vehicle vehicle) {
        return createWeapon(vehicle, null, null, 5L, ImagePresets.Default.NOTHING, null, null, TRACER_SHOTINTERFACE);
    }

    public static Weapon createSmoke(Vehicle vehicle) {
        return createWeapon(vehicle, null, null, 20L, ImagePresets.Default.NOTHING, null, null, SMOKE_SHOTINTERFACE);
    }

//</editor-fold>
    public static double generateGaussianRandom(double averageDeviationInPi) {
        return new Random().nextGaussian() * averageDeviationInPi * Math.PI - averageDeviationInPi * Math.PI / 2.0;
    }

}
