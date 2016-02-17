/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package code.game.tank;

import code.game.World;
import code.game.tank.projectile.ImpactInterface;
import java.awt.image.BufferedImage;
import yansuen.controller.ControllerInterface;
import yansuen.game.GameObject;
import yansuen.graphics.GraphicsInterface;
import yansuen.key.KeyManager;
import yansuen.logic.LogicInterface;
import code.game.tank.projectile.ShootInterface;

/**
 *
 * @author Link
 */
public class Weapon extends GameObject {

    protected Chassis chassis;
    protected ShootInterface shootFunction;
    protected LogicInterface reloadFunction;
    protected ImpactInterface impactBehavior;
    protected ControllerInterface projectileBehavior;

    protected boolean shoot = false;
    protected boolean reload = false;
    protected long cooldown = 0;
    protected long shotReady = 0;

    public Weapon(Chassis chassis, long cooldown, ShootInterface shootFunction, LogicInterface reloadFunction,
            ImpactInterface impactBehavior, ControllerInterface projectileBehavior, float x, float y,
            BufferedImage img, GraphicsInterface graphicsInterface, ControllerInterface controllerInterface) {
        super(x, y, img, graphicsInterface, controllerInterface);
        this.chassis = chassis;
        this.cooldown = cooldown;
        this.shootFunction = shootFunction;
        this.reloadFunction = reloadFunction;
        this.impactBehavior = impactBehavior;
        this.projectileBehavior = projectileBehavior;
    }

    @Override
    public void doLogic(GameObject gameObject, long tick, World world, KeyManager manager) {
        super.doLogic(gameObject, tick, world, manager);
        if (shootFunction != null && shoot && shotReady < tick) {
            shootFunction.onShoot(this, tick, impactBehavior, world, manager);
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

    public ShootInterface getShootFunction() {
        return shootFunction;
    }

    public void setShootFunction(ShootInterface shootFunction) {
        this.shootFunction = shootFunction;
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
        return shoot;
    }

    public void setShoot(boolean shoot) {
        this.shoot = shoot;
    }

    public boolean isReload() {
        return reload;
    }

    public void setReload(boolean reload) {
        this.reload = reload;
    }

}
