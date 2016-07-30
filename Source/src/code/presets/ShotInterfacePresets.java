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

    protected static LogicInterface SINGLE_PROJECTILE = (GameObject gameObject, long tick, World world, MasterKeyManager manager) -> {
        WeaponData weaponData = (WeaponData) gameObject.getData();

        Projectile projectileObject = new Projectile(weaponData.getProjectile());
        ProjectileData projectileData = (ProjectileData) projectileObject.getData();

        PolarVector pv = new PolarVector(weaponData.getRotation(), weaponData.getLength());

        projectileData.setX(weaponData.getX() + weaponData.getWidth() / 2 - projectileData.getWidth() / 2 + PolarVector.xFromPolar(pv));
        projectileData.setY(weaponData.getY() + weaponData.getHeight() / 2 - projectileData.getHeight() / 2 + PolarVector.yFromPolar(pv));

        double random = generateGaussianRandom(weaponData.getDeviationPerSide());
        projectileData.setRotation(weaponData.getRotation() + random);

        projectileData.setDeathTick(projectileData.getDeathTick() + tick);
        projectileObject.setDrive(DrivePresets.createStraightDrive(projectileData.getSpeed(), weaponData.getRotation() + random));
        world.addGameObject(projectileObject);
    };

    protected static LogicInterface MULTI_PROJECTILE = (GameObject gameObject, long tick, World world, MasterKeyManager manager) -> {
        WeaponData weaponData = (WeaponData) gameObject.getData();

        int projectiles = 20;
        float speedDeviation = 0.5f;

        PolarVector pv = new PolarVector(weaponData.getRotation(), weaponData.getLength());

        for (int i = 0; i < projectiles; i++) {

            Projectile projectileObject = new Projectile(weaponData.getProjectile());
            ProjectileData projectileData = (ProjectileData) projectileObject.getData();

            projectileData.setX(weaponData.getX() + weaponData.getWidth() / 2 - projectileData.getWidth() / 2 + PolarVector.xFromPolar(pv));
            projectileData.setY(weaponData.getY() + weaponData.getHeight() / 2 - projectileData.getHeight() / 2 + PolarVector.yFromPolar(pv));

            double random = generateGaussianRandom(weaponData.getDeviationPerSide());
            projectileData.setRotation(weaponData.getRotation() + random);

            projectileData.setDeathTick(projectileData.getDeathTick() + tick);

            float randomSpeed = (float) new Random().nextGaussian() * speedDeviation - speedDeviation / 2;

            projectileObject.setDrive(
                    DrivePresets.createStraightDrive(projectileData.getSpeed() + randomSpeed, weaponData.getRotation() + random));
            world.addGameObject(projectileObject);
        }
    };

    private static double generateGaussianRandom(double averageDeviationInPi) {
        return new Random().nextGaussian() * averageDeviationInPi * Math.PI - averageDeviationInPi * Math.PI / 2.0;
    }

}
