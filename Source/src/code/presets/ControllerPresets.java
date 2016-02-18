/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package code.presets;

import code.data.DataObject;
import code.game.World;
import code.game.tank.Chassis;
import code.game.tank.Drive;
import code.game.tank.Weapon;
import java.awt.event.KeyEvent;
import yansuen.controller.ControllerInterface;
import yansuen.game.GameObject;
import yansuen.key.KeyManager;
import yansuen.physic.PolarVector;

/**
 *
 * @author Eris
 */
public class ControllerPresets {

    public static ControllerInterface LINEAR_MOVEMENT = (GameObject gameObject, long tick, World world1, KeyManager manager) -> {
        DataObject data = (DataObject) gameObject.getData();

        int velocity = 3; //projektilgeschwindigkeit in px per sec           
        PolarVector pv = new PolarVector(data.getPositionData().getRotation(), velocity);
        data.getMovementData().setMovementX(PolarVector.xFromPolar(pv));
        data.getMovementData().setMovementY(PolarVector.yFromPolar(pv));
    };

    public static ControllerInterface PLAYER = (GameObject gameObject, long tick, World w, KeyManager manager) -> {
        Chassis c = ((Chassis) gameObject);
        Drive d = c.getDrive();
        d.setAccelerate(manager.isKeyPressed(KeyEvent.VK_W));
        d.setDecelerate(manager.isKeyPressed(KeyEvent.VK_S));
        d.setBreaks(manager.isKeyPressed(KeyEvent.VK_SPACE));
        d.setTurnLeft(manager.isKeyPressed(KeyEvent.VK_A));
        d.setTurnRight(manager.isKeyPressed(KeyEvent.VK_D));
        if (c.getWeapons().get(0) != null) {
            Weapon w0 = c.getWeapons().get(0);
            w0.setShoot(manager.isKeyPressed(KeyEvent.VK_1));
        }
    };
}
