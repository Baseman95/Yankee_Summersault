/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package code.game.tank.projectile;

import code.data.ProjectileData;
import code.game.World;
import code.game.tank.Vehicle;
import java.awt.image.BufferedImage;
import yansuen.game.GameObject;
import yansuen.graphics.GraphicsInterface;
import yansuen.key.MasterKeyManager;
import yansuen.logic.LogicInterface;

/**
 *
 * @author Base
 */
public class Projectile extends Vehicle {

    public LogicInterface hitInterface;

    public Projectile(float x, float y, double rotation, BufferedImage image, long speed, long deathTick,
            LogicInterface impactInterface, LogicInterface projectileController, LogicInterface projectileDrive, GraphicsInterface graphics) {
        super(new ProjectileData(x, y, rotation, image, speed, deathTick), graphics, projectileController);
        this.graphicsInterface = graphics;
    }

    public Projectile(Projectile oldProjectile) {
        this(((ProjectileData) oldProjectile.getData()).getX(), ((ProjectileData) oldProjectile.getData()).getY(),
                ((ProjectileData) oldProjectile.getData()).getRotation(), ((ProjectileData) oldProjectile.getData()).getImage(),
                ((ProjectileData) oldProjectile.getData()).getSpeed(), ((ProjectileData) oldProjectile.getData()).getDeathTick(),
                oldProjectile.hitInterface, oldProjectile.controllerInterface, oldProjectile.drive, oldProjectile.graphicsInterface);
    }

    public LogicInterface getHitInterface() {
        return hitInterface;
    }

    public void setHitInterface(LogicInterface hitInterface) {
        this.hitInterface = hitInterface;
    }

    @Override
    public void doLogic(GameObject gameObject, long tick, World world, MasterKeyManager manager) {
        super.doLogic(gameObject, tick, world, manager);
        ProjectileData data = (ProjectileData) gameObject.getData();
        if (data.getDeathTick() != 0 && tick > data.getDeathTick())
            world.removeGameObject(gameObject);
        else if (data.isHit())
            hitInterface.doLogic(this, tick, world, manager);
    }

}
