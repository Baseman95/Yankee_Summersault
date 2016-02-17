/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package code.game.tank.projectile;

import code.game.World;
import code.game.tank.Weapon;
import yansuen.key.KeyManager;

/**
 *
 * @author Link
 */
public interface ImpactInterface {

    void doImpact(Projectile projectile, Weapon weapon, long tick, World world, KeyManager manager);

}
