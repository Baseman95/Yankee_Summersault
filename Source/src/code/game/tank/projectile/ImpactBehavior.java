/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package code.game.tank.projectile;

import code.game.GameObject;
import code.game.World;

/**
 *
 * @author Eris
 */
public interface ImpactBehavior {
    
    void onImpact(World world, GameObject gameObject);
    
}
