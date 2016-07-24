package code.game.tank;

import code.data.WeaponData;
import yansuen.data.GameData;
import code.game.World;
import code.game.tank.projectile.ImpactInterface;
import java.awt.image.BufferedImage;
import yansuen.game.GameObject;
import yansuen.graphics.GraphicsInterface;
import yansuen.key.MasterKeyManager;
import yansuen.logic.LogicInterface;
import code.game.tank.projectile.ShotInterface;
import yansuen.network.NetworkSerializable;

/**
 * @author Link
 */
public class Weapon extends GameObject implements NetworkSerializable {

    protected ShotInterface shotFunction;
    protected LogicInterface reloadFunction;
    protected ImpactInterface impactBehavior;
    protected LogicInterface projectileBehavior;

    public Weapon(Vehicle vehicle, long cooldown, ShotInterface shotFunction, LogicInterface reloadFunction,
            ImpactInterface impactBehavior, LogicInterface projectileBehavior, float x, float y, float w, float h,
            BufferedImage img, GraphicsInterface graphicsInterface, LogicInterface controllerInterface) {
        super(new WeaponData(vehicle, cooldown, x, y, w, h, img), graphicsInterface, controllerInterface);
        this.data = new GameData(x, y, w, h, img);
        this.shotFunction = shotFunction;
        this.reloadFunction = reloadFunction;
        this.impactBehavior = impactBehavior;
        this.projectileBehavior = projectileBehavior;
    }

    public Weapon(Vehicle vehicle, long cooldown, ShotInterface shootFunction, LogicInterface reloadFunction,
            ImpactInterface impactBehavior, LogicInterface projectileBehavior, float x, float y,
            BufferedImage img, GraphicsInterface graphicsInterface, LogicInterface controllerInterface) {
        super(new WeaponData(vehicle, cooldown, x, y, img), graphicsInterface, controllerInterface);
        this.shotFunction = shootFunction;
        this.reloadFunction = reloadFunction;
        this.impactBehavior = impactBehavior;
        this.projectileBehavior = projectileBehavior;
    }

    @Override
    public void doLogic(GameObject gameObject, long tick, World world, MasterKeyManager manager) {
        super.doLogic(gameObject, tick, world, manager);
        WeaponData data = (WeaponData)getData();
        if (shotFunction != null && data.isShooting() && data.getNextShotReadyTick() < tick) {
            shotFunction.onShotCreation(this, tick, impactBehavior, world);
            data.setNextShotReadyTick(tick + data.getCooldown());
        }
        if (reloadFunction != null && data.isReloading())
            reloadFunction.doLogic(gameObject, tick, world, manager);
    }

    public ShotInterface getShotFunction() {
        return shotFunction;
    }

    public void setShotFunction(ShotInterface shotFunction) {
        this.shotFunction = shotFunction;
    }

    public LogicInterface getReloadFunction() {
        return reloadFunction;
    }

    public void setReloadFunction(LogicInterface reloadFunction) {
        this.reloadFunction = reloadFunction;
    }

    public ImpactInterface getImpactBehavior() {
        return impactBehavior;
    }

    public void setImpactBehavior(ImpactInterface impactBehavior) {
        this.impactBehavior = impactBehavior;
    }

    public LogicInterface getProjectileBehavior() {
        return projectileBehavior;
    }

    public void setProjectileBehavior(LogicInterface projectileBehavior) {
        this.projectileBehavior = projectileBehavior;
    }

}
