/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package code.game.tank;

import code.data.DataObject;
import code.game.World;
import code.game.tank.projectile.ImpactInterface;
import java.awt.image.BufferedImage;
import yansuen.controller.ControllerInterface;
import yansuen.game.GameObject;
import yansuen.graphics.GraphicsInterface;
import yansuen.key.KeyManager;
import yansuen.logic.LogicInterface;
import code.game.tank.projectile.ShotInterface;

/**
 *
 * @author Link
 */
public class Weapon extends GameObject {

    protected Chassis chassis;
    protected ShotInterface shotFunction;
    protected LogicInterface reloadFunction;
    protected ImpactInterface impactBehavior;
    protected ControllerInterface projectileBehavior;

    protected boolean shot = false;
    protected boolean reload = false;
    protected long cooldown = 0;
    protected long shotReady = 0;

    public Weapon(Chassis chassis, long cooldown, ShotInterface shootFunction, LogicInterface reloadFunction,
            ImpactInterface impactBehavior, ControllerInterface projectileBehavior, float x, float y,
            BufferedImage img, GraphicsInterface graphicsInterface, ControllerInterface controllerInterface) {
        super(((DataObject) chassis.getData()).getPositionData().getX() + ((DataObject) chassis.getData()).getPositionData().getWidth() / 2
                + x - img.getWidth() / 2,
                ((DataObject) chassis.getData()).getPositionData().getY() + ((DataObject) chassis.getData()).getPositionData().getHeight() / 2
                + y - img.getHeight() / 2, img, graphicsInterface, controllerInterface);
        this.chassis = chassis;
        this.cooldown = cooldown;
        this.shotFunction = shootFunction;
        this.reloadFunction = reloadFunction;
        this.impactBehavior = impactBehavior;
        this.projectileBehavior = projectileBehavior;
    }

    @Override
    public void doLogic(GameObject gameObject, long tick, World world, KeyManager manager) {
        super.doLogic(gameObject, tick, world, manager);
        if (shotFunction != null && shot && shotReady < tick) {
            shotFunction.onShotCreation(this, tick, impactBehavior, world);
            shotReady = tick + cooldown;
        }
        if (reloadFunction != null && reload)
            reloadFunction.doLogic(gameObject, tick, world, manager);
    }

    public Chassis getChassis() {
        return chassis;
    }

    public void setChassis(Chassis chassis) {
        this.chassis = chassis;
    }

    public long getCooldown() {
        return cooldown;
    }

    public void setCooldown(long cooldown) {
        this.cooldown = cooldown;
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

    public ControllerInterface getProjectileBehavior() {
        return projectileBehavior;
    }

    public void setProjectileBehavior(ControllerInterface projectileBehavior) {
        this.projectileBehavior = projectileBehavior;
    }

    public boolean isShoot() {
        return shot;
    }

    public void setShoot(boolean shoot) {
        this.shot = shoot;
    }

    public boolean isReload() {
        return reload;
    }

    public void setReload(boolean reload) {
        this.reload = reload;
    }

}
