/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package code.presets;

import code.data.ProjectileData;
import code.data.WeaponData;
import code.game.World;
import code.game.tank.projectile.Projectile;
import java.util.Random;
import yansuen.game.GameObject;
import yansuen.key.MasterKeyManager;
import yansuen.logic.LogicInterface;
import yansuen.physic.PolarVector;

/**
 *
 * @author Base
 */
public class ShotInterfacePresets {

    //Shotinterface wird bei schießen ausgeführt > nur sachen für 
    protected static LogicInterface SINGLE_PROJECTILE = (GameObject gameObject, long tick, World world, MasterKeyManager manager) -> {
        WeaponData weaponData = (WeaponData) gameObject.getData();
        
        Projectile projectileObject = new Projectile(weaponData.getProjectile());
        ProjectileData projectileData = (ProjectileData) projectileObject.getData();
        
        /*
        Projectile p = new Projectile(weapon, tick + ticksToLive, impactInterface,
                PolarVector.xFromPolar(pv) + data.getX() + data.getWidth() / 2 - texture.getWidth() / 2,
                PolarVector.yFromPolar(pv) + data.getY() + data.getHeight() / 2 - texture.getHeight() / 2,
                texture, GraphicsPresets.ROTATION, null);
         */
        
        /*
        PolarVector pv = new PolarVector(weaponData.getRotation(), weaponData.getLength());
        projectileData.setX(PolarVector.xFromPolar(pv) + weaponData.getX() + weaponData.getWidth() / 2 - weaponData.getImage().getWidth() / 2);
        projectileData.setY(PolarVector.yFromPolar(pv) + weaponData.getY() + weaponData.getHeight() / 2 - weaponData.getImage().getHeight() / 2);
        */
        PolarVector pv = new PolarVector(weaponData.getRotation(), weaponData.getLength());
        
        projectileData.setX(weaponData.getX() + weaponData.getWidth() / 2  - projectileData.getWidth()/ 2 + PolarVector.xFromPolar(pv));
        projectileData.setY(weaponData.getY() + weaponData.getHeight() / 2 - projectileData.getHeight() / 2 + PolarVector.yFromPolar(pv));
        
        //double random = Math.abs(generateGaussianRandom(weaponData.getDeviationPerSide()));
        double random = generateGaussianRandom(weaponData.getDeviationPerSide());
        projectileData.setRotation(weaponData.getRotation() + random);
        
        projectileData.setDeathTick(projectileData.getDeathTick()+ tick);
        projectileObject.setDrive(DrivePresets.createStraightDrive(projectileData.getSpeed(), weaponData.getRotation() + random));
        world.addGameObject(projectileObject);
    };


    protected static double generateGaussianRandom(double averageDeviationInPi) {        
        return new Random().nextGaussian() * averageDeviationInPi * Math.PI - averageDeviationInPi * Math.PI / 2.0;
    }

}
