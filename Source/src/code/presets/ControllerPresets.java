/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package code.presets;

import yansuen.data.DataContainer;
import code.game.World;
import code.game.tank.Chassis;
import code.game.tank.Drive;
import java.awt.event.KeyEvent;
import yansuen.controller.ControllerInterface;
import yansuen.game.GameObject;
import yansuen.key.MasterKeyManager;
import yansuen.physic.CartesianVector;
import yansuen.physic.PolarVector;

/**
 *
 * @author Eris
 */
public class ControllerPresets {

    public static ControllerInterface createMoveToController(int x, int y) {
        ControllerInterface moveTo = (GameObject gameObject, long tick, World world, MasterKeyManager manager) -> {
            DataContainer data = (DataContainer) gameObject.getDataContainer();
            Chassis c = ((Chassis) gameObject);
            Drive d = c.getDrive();
            d.setAccelerate(true);

            double chassisRot = data.getPositionData().getRotation();

            int xDelta = x - (int) data.getPositionData().getX();
            int yDelta = y - (int) data.getPositionData().getY();
            System.out.println(xDelta);

            PolarVector deltaVector = new PolarVector(new CartesianVector(xDelta, yDelta));

            deltaVector.angle -= chassisRot;
            deltaVector.updateAngleRangePi();

            //System.out.println(deltaVector.angle);
            d.setTurnLeft(deltaVector.angle < 0);
            d.setTurnRight(deltaVector.angle > 0);
            //System.out.println(data);

        };
        return moveTo;
    }
    public static ControllerInterface HOLD_ACCELERATE = ControllerPresets.hold(true, false, false, false, false, false, false);

    public static ControllerInterface hold(boolean accelerate, boolean decelerate, boolean breaks,
            boolean turnLeft, boolean turnRight, boolean strafeLeft, boolean strafeRight) {
        ControllerInterface controller = (GameObject gameObject, long tick, World w, MasterKeyManager manager) -> {
            Chassis c = ((Chassis) gameObject);
            Drive d = c.getDrive();
            d.setAccelerate(accelerate);
            d.setDecelerate(decelerate);
            d.setBreaks(breaks);
            d.setTurnLeft(turnLeft);
            d.setTurnRight(turnRight);
            d.setStrafeLeft(strafeLeft);
            d.setStrafeRight(strafeRight);
        };
        return controller;
    }

    public static ControllerInterface PLAYER = (GameObject gameObject, long tick, World w, MasterKeyManager manager) -> {
        Chassis c = ((Chassis) gameObject);
        Drive d = c.getDrive();

        d.setAccelerate(manager.isKeyPressed(KeyEvent.VK_W, c.getNetworkProjectionId()));
        d.setDecelerate(manager.isKeyPressed(KeyEvent.VK_S, c.getNetworkProjectionId()));
        d.setBreaks(manager.isKeyPressed(KeyEvent.VK_SPACE, c.getNetworkProjectionId()));
        d.setTurnLeft(manager.isKeyPressed(KeyEvent.VK_A, c.getNetworkProjectionId()));
        d.setTurnRight(manager.isKeyPressed(KeyEvent.VK_D, c.getNetworkProjectionId()));
        d.setStrafeLeft(manager.isKeyPressed(KeyEvent.VK_Q, c.getNetworkProjectionId()));
        d.setStrafeRight(manager.isKeyPressed(KeyEvent.VK_E, c.getNetworkProjectionId()));
        for (int i = 0; i < c.getWeapons().size(); i++) {
            c.getWeapons().get(i).setShoot(manager.isKeyPressed(KeyEvent.VK_1 + i, gameObject.getNetworkProjectionId()));
        }
    };

}
