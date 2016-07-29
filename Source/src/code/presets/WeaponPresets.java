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
 * @author Eris
 */
public class WeaponPresets {

    private WeaponPresets() {
    }
    
    public static LogicInterface SINGLE_RELOAD = (GameObject gameObject, long tick, World world, MasterKeyManager manager) -> {
        
        WeaponData data = (WeaponData) gameObject.getData();
        data.setNextShotReadyTick(tick + data.getMagazineLoadTicks());
        data.setRoundsInMagazine(data.getRoundsInMagazine()+1);
    };
    
    public static LogicInterface FULL_RELOAD = (GameObject gameObject, long tick, World world, MasterKeyManager manager) -> {
        
        WeaponData data = (WeaponData) gameObject.getData();
        data.setNextShotReadyTick(tick + data.getMagazineLoadTicks());
        data.setRoundsInMagazine(data.getMagazineSize());
    };

    static LogicInterface hitInterface = (GameObject gameObject, long tick, World world1, MasterKeyManager manager) -> {
    };

    //createWeapon1(new WeaponData(ganzerBullshit), restlicherBullshit, UseProjectilePreset, UseShotInterfacePreset)
    public static Weapon createWeaponMG762(Vehicle v, GraphicsInterface graphics) {

        Projectile p = new Projectile(ProjectilePresets.MG763);
        //WeaponData(Vehicle parent, BufferedImage image, float imageSizeMultiplier,
        //float relativeX, float relativeY, float relativeZ, double relativeRotation, boolean useParentRotation,
        //long projectileLoadTicks, int magazineSize, long magazineLoadTicks, boolean autoReload, float deviationPerSide, Projectile projectile)
        WeaponData wd = new WeaponData(v,25,ImagePresets.Weapon.MG762, 1, 0, 0, 0, 0, false, 40, 20, 200, true, 0.004f, p);
        //public Weapon(WeaponData weaponData, GraphicsInterface graphicsInterface, LogicInterface controllerInterface,
        //LogicInterface shotInterface, LogicInterface reloadInterface, LogicInterface hitInterface)
        Weapon w = new Weapon(wd, graphics, ControllerPresets.PLAYER, ShotInterfacePresets.SINGLE_PROJECTILE,
                FULL_RELOAD, null);
        return w;
    }
    public static Weapon createWeaponMinig(Vehicle v, GraphicsInterface graphics) {

        Projectile p = new Projectile(ProjectilePresets.MINIG);        
        WeaponData wd = new WeaponData(v,25,ImagePresets.Weapon.MG762, 1, 0, 0, 0, 0, false, 20, 10000, 500, false, 0.01f, p);        
        Weapon w = new Weapon(wd, graphics, ControllerPresets.PLAYER, ShotInterfacePresets.SINGLE_PROJECTILE,
                null, null);
        return w;
    }
}