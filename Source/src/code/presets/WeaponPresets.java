package code.presets;

import code.data.WeaponData;
import code.game.World;
import code.game.tank.Vehicle;
import code.game.tank.Weapon;
import code.game.tank.projectile.Projectile;
import yansuen.key.MasterKeyManager;
import yansuen.game.GameObject;
import yansuen.graphics.GraphicsInterface;
import yansuen.logic.LogicInterface;

/**
 * @author Base
 */
public class WeaponPresets {
    
    public static Weapon createWeaponPerID(WeaponIdentification identification, Vehicle v) {
        switch (identification) {
            case MG762:
                return createWeaponMG762(v);
            case MINIG:
                return createWeaponMinig(v);
            case HELCN:
                return createWeaponHelcn(v);
            case GLKPR:
            case SHTGN:
            case RSHGN:
            case MK19:
            case ABOMB:
            case SMAW:
            case TANKS:
            case ATGM:
            case PTRKT:
            case MLTGM:
            case FFRKT:
            case FLMTH:
            case EMPGN:
            case FLSGN:
            case WLDTL:
            case TRACR:
            
            default:
                break;
        }
        return null;
    }
    
    public static LogicInterface SINGLE_RELOAD = (GameObject gameObject, long tick, World world, MasterKeyManager manager) -> {
        
        WeaponData data = (WeaponData) gameObject.getData();
        data.setNextShotReadyTick(tick + data.getMagazineLoadTicks());
        data.setRoundsInMagazine(data.getRoundsInMagazine() + 1);        
    };
    
    public static LogicInterface FULL_RELOAD = (GameObject gameObject, long tick, World world, MasterKeyManager manager) -> {
        
        WeaponData data = (WeaponData) gameObject.getData();
        data.setNextShotReadyTick(tick + data.getMagazineLoadTicks());
        data.setRoundsInMagazine(data.getMagazineSize());
    };

    //createWeapon1(new WeaponData(ganzerBullshit), restlicherBullshit, UseProjectilePreset, UseShotInterfacePreset)
    public static Weapon createWeaponMG762(Vehicle v) {
        
        Projectile p = new Projectile(ProjectilePresets.MG762);
        WeaponData wd = new WeaponData(v, 25, ImagePresets.Weapon.MG762, 1, 0, 0, 0, 0, false, 25, 50, 200, true, 0.005f, p);
        return new Weapon(wd, GraphicsPresets.ROTATION, ControllerPresets.PLAYER, ShotInterfacePresets.SINGLE_PROJECTILE, FULL_RELOAD, null);
    }
    
    public static Weapon createWeaponMinig(Vehicle v) {
        
        Projectile p = new Projectile(ProjectilePresets.MINIG);
        WeaponData wd = new WeaponData(v, 20, ImagePresets.Weapon.MINIG, 1, 0, 0, 0, 0, false, 20, 200, 2000, false, 0.009f, p);
        return new Weapon(wd, GraphicsPresets.ROTATION, ControllerPresets.PLAYER, ShotInterfacePresets.SINGLE_PROJECTILE, FULL_RELOAD, null);
    }
    
    public static Weapon createWeaponHelcn(Vehicle v) {
        
        Projectile p = new Projectile(ProjectilePresets.HELCN);
        WeaponData wd = new WeaponData(v, 0, ImagePresets.Default.NO_IMAGE, 1, 0, 0, 0, 0, false, 15, 7, 300, true, 0.003f, p);
        return new Weapon(wd, GraphicsPresets.ROTATION, ControllerPresets.PLAYER, ShotInterfacePresets.SINGLE_PROJECTILE, FULL_RELOAD, null);
    }
    
    public static Weapon createWeaponGlkpr(Vehicle v) {
        
        Projectile p = new Projectile(ProjectilePresets.GLKPR);
        WeaponData wd = new WeaponData(v, 0, ImagePresets.Default.NO_IMAGE, 1, 0, 0, 0, 0, false, 10, 50, 1000, true, 0.006f, p);
        return new Weapon(wd, GraphicsPresets.ROTATION, ControllerPresets.PLAYER, ShotInterfacePresets.SINGLE_PROJECTILE, FULL_RELOAD, null);
    }
    
    public static Weapon createWeaponShtgn(Vehicle v) {
        
        Projectile p = new Projectile(ProjectilePresets.SHTGN);
        WeaponData wd = new WeaponData(v, 0, ImagePresets.Default.NO_IMAGE, 1, 0, 0, 0, 0, false, 325, 5, 350, false, 0.015f, p);
        return new Weapon(wd, GraphicsPresets.ROTATION, ControllerPresets.PLAYER, ShotInterfacePresets.MULTI_PROJECTILE, SINGLE_RELOAD, null);
    }
    
    public static Weapon createWeaponRshgn(Vehicle v) {
        
        Projectile p = new Projectile(ProjectilePresets.RSHGN);
        WeaponData wd = new WeaponData(v, 0, ImagePresets.Default.NO_IMAGE, 1, 0, 0, 0, 0, false, 325, 2, 350, false, 1f, p);
        return new Weapon(wd, GraphicsPresets.ROTATION, ControllerPresets.PLAYER, ShotInterfacePresets.MULTI_PROJECTILE, SINGLE_RELOAD, null);
    }
    
    public static Weapon createWeaponMk19(Vehicle v) {
        
        Projectile p = new Projectile(ProjectilePresets.MK19);
        WeaponData wd = new WeaponData(v, 0, ImagePresets.Default.NO_IMAGE, 1, 0, 0, 0, 0, false, 25, 4, 175, true, 0.001f, p);
        return new Weapon(wd, GraphicsPresets.ROTATION, ControllerPresets.PLAYER, ShotInterfacePresets.SINGLE_PROJECTILE, FULL_RELOAD, null);
    }
    
    public static Weapon createWeaponAbomb(Vehicle v) {
        
        Projectile p = new Projectile(ProjectilePresets.ABOMB);
        WeaponData wd = new WeaponData(v, 0, ImagePresets.Default.NO_IMAGE, 1, 0, 0, 0, 0, false, 25, 5, 500, true, 0.001f, p);
        return new Weapon(wd, GraphicsPresets.ROTATION, ControllerPresets.PLAYER, ShotInterfacePresets.AERIAL_BOMB, FULL_RELOAD, null);
    }
    
    
    
    
    
    
    public static Weapon createWaterCanon(Vehicle v, GraphicsInterface graphics) {
        
        Projectile p = new Projectile(ProjectilePresets.MINIG);
        //WeaponData(Vehicle parent, BufferedImage image, float imageSizeMultiplier,
        //float relativeX, float relativeY, float relativeZ, double relativeRotation, boolean useParentRotation,
        //long projectileLoadTicks, int magazineSize, long magazineLoadTicks, boolean autoReload, float deviationPerSide, Projectile projectile)
        WeaponData wd = new WeaponData(v, 20, ImagePresets.Default.NOTHING, 1, 0, 0, 0, 0, false, 1, 100000, 500, false, 0.01f, p);
        //public Weapon(WeaponData weaponData, GraphicsInterface graphicsInterface, LogicInterface controllerInterface,
        //LogicInterface shotInterface, LogicInterface reloadInterface, LogicInterface hitInterface)        
        Weapon w = new Weapon(wd, graphics, ControllerPresets.PLAYER, ShotInterfacePresets.SINGLE_PROJECTILE,
                null, null);
        return w;
    }
    
    public enum WeaponIdentification {
        
        MG762,
        MINIG,
        HELCN,
        GLKPR,
        SHTGN,
        RSHGN,
        MK19,
        ABOMB,
        SMAW,
        TANKS,
        ATGM,
        PTRKT,
        MLTGM,
        FFRKT,
        FLMTH,
        EMPGN,
        FLSGN,
        WLDTL,
        TRACR
    }
}
