/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package code.presets;

import code.data.DataObject;
import code.game.World;
import code.game.tank.Chassis;
import code.game.tank.Weapon;
import code.game.tank.projectile.ImpactInterface;
import code.game.tank.projectile.Projectile;
import yansuen.key.KeyManager;
import code.game.tank.projectile.ShotInterface;
import yansuen.game.GameObject;
import yansuen.logic.LogicInterface;

/**
 *
 * @author Eris
 */
public class WeaponPresets {

    private WeaponPresets() {

    }

    public static ShotInterface getSingleShot(long duration) {
        ShotInterface singleShot = (Weapon weapon, long tick, ImpactInterface impactInterface, World world) -> {
            //Spawn von Projektil, + Richtung, adding projektil zu welt,
            DataObject data = (DataObject) weapon.getData();
            Projectile p = new Projectile(weapon, tick + duration,
                    impactInterface, data.getPositionData().getX(),
                    data.getPositionData().getY(), ImagePresets.WEAPON_1, GraphicsPresets.ROTATION, ControllerPresets.LINEAR_MOVEMENT);
            ((DataObject) p.getData()).getPositionData().setRotation(data.getPositionData().getRotation());
            world.addGameObject(p);

        };
        return singleShot;
    }

    static LogicInterface fastReload = (GameObject gameObject, long tick, World world1, KeyManager manager) -> {
    };

    static ImpactInterface bulletImpact = (Projectile projectile, Weapon weapon, long tick, World world1, KeyManager manager) -> {
    };

    public static Weapon createPlasma(Chassis chassis) {
        Weapon weapon = new Weapon(chassis, 20, WeaponPresets.getSingleShot(1000),
                fastReload, bulletImpact,
                null, 10,
                10, ImagePresets.TANK,
                GraphicsPresets.ROTATION, ControllerPresets.PLAYER);
        return weapon;
    }
}
