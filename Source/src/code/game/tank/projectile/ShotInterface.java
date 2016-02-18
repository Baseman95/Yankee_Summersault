/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package code.game.tank.projectile;

import code.game.World;
import code.game.tank.Weapon;

/**
 *
 * @author Eris
 */
public interface ShotInterface {

    void onShotCreation(Weapon weapon, long tick, ImpactInterface impactInterface, World world);

}
