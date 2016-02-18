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
import yansuen.physic.CartesianVector;
import yansuen.physic.PolarVector;

/**
 *
 * @author Eris
 */
public class ControllerPresets {

    public static ControllerInterface createMoveToController(int x, int y) {
        ControllerInterface moveTo = (GameObject gameObject, long tick, World world, KeyManager manager) -> {
            DataObject data = (DataObject) gameObject.getData();
            System.out.println(gameObject);
            Chassis c = ((Chassis) gameObject);
            Drive d = c.getDrive();
            d.setAccelerate(true);

            double chassisRot = data.getPositionData().getRotation();

            int xDelta = x - (int) data.getPositionData().getX();
            int yDelta = y - (int) data.getPositionData().getY();

            PolarVector deltaVector = new PolarVector(new CartesianVector(xDelta, yDelta));

            deltaVector.angle -= chassisRot;
            deltaVector.updateAngleRangePi();
            
            d.setTurnLeft(deltaVector.angle < 0);
            d.setTurnRight(deltaVector.angle > 0);
           
        };
        return moveTo;
    }
    public static ControllerInterface PLAYER = (GameObject gameObject, long tick, World w, KeyManager manager) -> {
        Chassis c = ((Chassis) gameObject);
        Drive d = c.getDrive();
        d.setAccelerate(manager.isKeyPressed(KeyEvent.VK_W));
        d.setDecelerate(manager.isKeyPressed(KeyEvent.VK_S));
        d.setBreaks(manager.isKeyPressed(KeyEvent.VK_SPACE));
        d.setTurnLeft(manager.isKeyPressed(KeyEvent.VK_A));
        d.setTurnRight(manager.isKeyPressed(KeyEvent.VK_D));
        for (int i = 0; i < c.getWeapons().size(); i++) {
            c.getWeapons().get(i).setShoot(manager.isKeyPressed(KeyEvent.VK_1 + i));
        }
    };
}
