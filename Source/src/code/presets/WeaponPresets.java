package code.presets;

import code.data.ProjectileData;
import code.data.WeaponData;
import yansuen.data.GameData;
import code.game.World;
import code.game.tank.Vehicle;
import code.game.tank.Drive;
import code.game.tank.Weapon;
import code.game.tank.projectile.Projectile;
import yansuen.key.MasterKeyManager;
import java.awt.image.BufferedImage;
import java.util.Random;
import yansuen.game.GameObject;
import yansuen.graphics.GraphicsInterface;
import yansuen.logic.LogicInterface;
import yansuen.physic.PolarVector;

/**
 * @author Eris
 */
public class WeaponPresets {

    private WeaponPresets() {
    }

    static LogicInterface fastReload = (GameObject gameObject, long tick, World world1, MasterKeyManager manager) -> {
    };

    static LogicInterface hitInterface = (GameObject gameObject, long tick, World world1, MasterKeyManager manager) -> {
    };

    //createWeapon1(new WeaponData(ganzerBullshit), restlicherBullshit, UseProjectilePreset, UseShotInterfacePreset)
    public static Weapon createWeaponMG762(Vehicle v, GraphicsInterface graphics) {

        Projectile p = new Projectile(ProjectilePresets.MASCHYNENGWER);
        //WeaponData(Vehicle parent, BufferedImage image, float imageSizeMultiplier,
        //float relativeX, float relativeY, float relativeZ, double relativeRotation, boolean useParentRotation,
        //long projectileLoadTicks, int magazineSize, long magazineLoadTicks, boolean autoReload, float deviationPerSide, Projectile projectile)
        WeaponData wd = new WeaponData(v,25,ImagePresets.Weapon.MG762, 1, 0, 0, 0, 0, false, 50, 10000, 200, false, 0.006f, p);
        //public Weapon(WeaponData weaponData, GraphicsInterface graphicsInterface, LogicInterface controllerInterface,
        //LogicInterface shotInterface, LogicInterface reloadInterface, LogicInterface hitInterface)
        Weapon w = new Weapon(wd, graphics, ControllerPresets.PLAYER, ShotInterfacePresets.SINGLE_PROJECTILE,
                null, null);
        return w;
    }
}