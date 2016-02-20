/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package code.game.tank;

import code.game.World;
import yansuen.game.GameObject;

/**
 *
 * @author StefaN
 */
public interface DriveLogicInterface {

    void doDriveLogic(Drive drive, GameObject gameObject, long tick, World world);

}
