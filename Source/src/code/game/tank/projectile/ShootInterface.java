/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package code.game.tank.projectile;

import code.game.World;
import code.game.tank.Weapon;
import yansuen.game.GameObject;
import yansuen.key.KeyManager;

/**
 *
 * @author Eris
 */
public interface ShootInterface {

    void onShoot(Weapon weapon, long tick, ImpactInterface impactInterface, World world, KeyManager manager);

}
