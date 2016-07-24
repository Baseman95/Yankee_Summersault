/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package code.game.tank.projectile;

import code.game.World;
import code.game.tank.Vehicle;
import code.game.tank.Weapon;
import java.awt.image.BufferedImage;
import yansuen.game.GameObject;
import yansuen.graphics.GraphicsInterface;
import yansuen.key.MasterKeyManager;
import yansuen.logic.LogicInterface;

/**
 *
 * @author Eris
 */
public class Projectile extends Vehicle {

    protected Weapon weapon;
    protected long deathtick;
    protected ImpactInterface impactInterface;

    protected boolean impact = false;

    public Projectile(Weapon weapon, long deathtick, ImpactInterface impactInterface, float x, float y, BufferedImage img,
            GraphicsInterface graphicsInterface, LogicInterface controllerInterface) {
        super(x, y, img, graphicsInterface, controllerInterface);
        this.weapon = weapon;
        this.impactInterface = impactInterface;
        this.deathtick = deathtick;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public ImpactInterface getImpactBehavior() {
        return impactInterface;
    }

    public void setImpactInterface(ImpactInterface impactInterface) {
        this.impactInterface = impactInterface;
    }

    public void setDeathtick(long deathtick) {
        this.deathtick = deathtick;
    }

    public long getDeathtick() {
        return deathtick;
    }

    public ImpactInterface getImpactInterface() {
        return impactInterface;
    }

    public void setImpact(boolean impact) {
        this.impact = impact;
    }

    @Override
    public void doLogic(GameObject gameObject, long tick, World world, MasterKeyManager manager) {
        super.doLogic(gameObject, tick, world, manager);
        if (deathtick != 0 && tick > deathtick)
            world.removeGameObject(gameObject);
        else if (impact)
            impactInterface.doImpact(this, weapon, tick, world, manager);
    }

}
