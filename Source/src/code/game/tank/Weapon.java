/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package code.game.tank;

import code.game.World;
import java.awt.image.BufferedImage;
import yansuen.controller.ControllerInterface;
import yansuen.game.GameObject;
import yansuen.graphics.GraphicsInterface;
import yansuen.key.KeyManager;
import yansuen.logic.LogicInterface;

/**
 *
 * @author Link
 */
public class Weapon extends GameObject {

    protected Chassis chassis;
    protected LogicInterface shootFunction;
    protected LogicInterface reloadFunction;
    protected LogicInterface impactBehavior;
    protected ControllerInterface projectileBehavior;

    protected boolean shoot = false;
    protected boolean reload = false;

    public Weapon(Chassis chassis, LogicInterface shootFunction, LogicInterface reloadFunction, LogicInterface impactBehavior, ControllerInterface projectileBehavior, float x, float y, BufferedImage img, GraphicsInterface graphicsInterface, ControllerInterface controllerInterface) {
        super(x, y, img, graphicsInterface, controllerInterface);
        this.chassis = chassis;
        this.shootFunction = shootFunction;
        this.reloadFunction = reloadFunction;
        this.impactBehavior = impactBehavior;
        this.projectileBehavior = projectileBehavior;
    }

    @Override
    public void doLogic(GameObject gameObject, long tick, World world, KeyManager manager) {
        super.doLogic(gameObject, tick, world, manager);
        if (shootFunction != null && shoot)
            shootFunction.doLogic(gameObject, tick, world, manager);
        if (reloadFunction != null && reload)
            reloadFunction.doLogic(gameObject, tick, world, manager);
    }

    public Chassis getChassis() {
        return chassis;
    }

    public void setChassis(Chassis chassis) {
        this.chassis = chassis;
    }

    public LogicInterface getShootFunction() {
        return shootFunction;
    }

    public void setShootFunction(LogicInterface shootFunction) {
        this.shootFunction = shootFunction;
    }

    public LogicInterface getReloadFunction() {
        return reloadFunction;
    }

    public void setReloadFunction(LogicInterface reloadFunction) {
        this.reloadFunction = reloadFunction;
    }

    public LogicInterface getImpactBehavior() {
        return impactBehavior;
    }

    public void setImpactBehavior(LogicInterface impactBehavior) {
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
