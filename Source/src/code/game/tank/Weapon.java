package code.game.tank;

import code.data.WeaponData;
import code.game.World;
import yansuen.game.GameObject;
import yansuen.graphics.GraphicsInterface;
import yansuen.key.MasterKeyManager;
import yansuen.logic.LogicInterface;
import yansuen.network.NetworkSerializable;

/**
 * @author Link
 */
public class Weapon extends Vehicle implements NetworkSerializable {

    protected LogicInterface shotInterface;
    protected LogicInterface reloadInterface;
    protected LogicInterface hitInterface;

    public Weapon(WeaponData weaponData, GraphicsInterface graphicsInterface, LogicInterface controllerInterface,
            LogicInterface shotInterface, LogicInterface reloadInterface, LogicInterface hitInterface) {
        super(weaponData, graphicsInterface, controllerInterface);
        this.shotInterface = shotInterface;
        this.reloadInterface = reloadInterface;
        this.hitInterface = hitInterface;
    }

    //Weapon(VehicleData,)
    //MACH KONSTRUKTORRRRRR MIT WEAPON DATA ALS ÜBERGABEPARAMENTER; GGF ANDERE LÖSCHEN
    /*
    public Weapon(Vehicle parent, long cooldown, ShotInterface shotFunction, LogicInterface reloadFunction,
            LogicInterface impactBehavior, LogicInterface projectileBehavior, float x, float y,
            BufferedImage img, GraphicsInterface graphicsInterface, LogicInterface controllerInterface) {        
        this(parent, cooldown, shotFunction, reloadFunction, impactBehavior, projectileBehavior, x, y, img.getWidth(), img.getHeight(), img, graphicsInterface, controllerInterface);
    }
     */
    @Override
    public void doLogic(GameObject gameObject, long tick, World world, MasterKeyManager manager) {
        super.doLogic(gameObject, tick, world, manager);
        
        checkAmmo(gameObject, tick, world, manager);

        /*if (shotInterface != null && data.isShooting() && data.getNextShotReadyTick() < tick) {
            shotInterface.doLogic(this, tick, world, manager);
            //shotInterface.(this, tick, hitInterface, world);
            data.setNextShotReadyTick(tick + data.getCooldown());*/
    }

    private void checkAmmo(GameObject gameObject, long tick, World world, MasterKeyManager manager) {
        WeaponData data = (WeaponData) gameObject.getData();
        if (shotInterface == null)
            return;
        if (!data.isShooting())
            return;
        if (data.getRoundsInMagazine() < 1)
            return;
        if (data.getNextShotReadyTick() > tick)
            return;
        shotInterface.doLogic(this, tick, world, manager);
        data.setRoundsInMagazine(data.getRoundsInMagazine() - 1);
        data.setNextShotReadyTick(tick + data.getProjectileLoadTicks()); //Wird überschrieben wenn nxt. if nicht zutrifft
        if (data.getRoundsInMagazine() > 0)
            return;
        if (data.hasAutoReload())            
            //data.setNextShotReadyTick(tick + data.getMagazineLoadTicks());        
            reloadInterface.doLogic(gameObject, tick, world, manager);
    }

    /*if (reloadInterface!= null && data.isReloading ()){
            reloadInterface.doLogic(gameObject, tick, world, manager);

        // System.out.println(data);
    }*/
}
