package code.presets;

import code.game.World;
import code.game.tank.Vehicle;
import java.awt.event.KeyEvent;
import yansuen.data.VehicleData;
import yansuen.game.GameObject;
import yansuen.key.MasterKeyManager;
import yansuen.logic.LogicInterface;
import yansuen.physic.CartesianVector;
import yansuen.physic.PolarVector;

/**
 * @author Eris
 */
public class ControllerPresets {

    public static LogicInterface createMoveToController(int x, int y) {
        LogicInterface moveTo = (GameObject gameObject, long tick, World world, MasterKeyManager manager) -> {
            Vehicle c = ((Vehicle) gameObject);
            VehicleData data = ((VehicleData) c.getData());
            data.accelerate = true;

            double vehicleRot = data.getRotation();

            int xDelta = x - (int) data.getX();
            int yDelta = y - (int) data.getY();
            System.out.println(xDelta);

            PolarVector deltaVector = new PolarVector(new CartesianVector(xDelta, yDelta));

            deltaVector.angle -= vehicleRot;
            deltaVector.updateAngleRangePi();

            //System.out.println(deltaVector.angle);
            data.turnLeft = deltaVector.angle < 0;
            data.turnRight = deltaVector.angle > 0;
            //System.out.println(data);

        };
        return moveTo;
    }
    public static LogicInterface HOLD_ACCELERATE = ControllerPresets.hold(true, false, false, false, false, false, false);

    public static LogicInterface hold(boolean accelerate, boolean decelerate, boolean breaks,
            boolean turnLeft, boolean turnRight, boolean strafeLeft, boolean strafeRight) {
        LogicInterface controller = (GameObject gameObject, long tick, World w, MasterKeyManager manager) -> {
            Vehicle c = ((Vehicle) gameObject);
            VehicleData data = ((VehicleData) c.getData());
            data.accelerate = accelerate;
            data.decelerate = decelerate;
            data.breaks = breaks;
            data.turnLeft = turnLeft;
            data.turnRight = turnRight;
            data.strafeLeft = strafeLeft;
            data.strafeRight = strafeRight;
        };
        return controller;
    }

    public static LogicInterface PLAYER = (GameObject gameObject, long tick, World w, MasterKeyManager manager) -> {
        Vehicle c = ((Vehicle) gameObject);
        VehicleData data = ((VehicleData) c.getData());

        data.accelerate = manager.isKeyPressed(KeyEvent.VK_W, c.getNetworkProjectionId());
        data.decelerate = manager.isKeyPressed(KeyEvent.VK_S, c.getNetworkProjectionId());
        data.breaks = manager.isKeyPressed(KeyEvent.VK_SPACE, c.getNetworkProjectionId());
        data.turnLeft = manager.isKeyPressed(KeyEvent.VK_A, c.getNetworkProjectionId());
        data.turnRight = manager.isKeyPressed(KeyEvent.VK_D, c.getNetworkProjectionId());
        data.strafeLeft = manager.isKeyPressed(KeyEvent.VK_Q, c.getNetworkProjectionId());
        data.strafeRight = manager.isKeyPressed(KeyEvent.VK_E, c.getNetworkProjectionId());
        for (int i = 0; i < c.getWeapons().size(); i++) {
            c.getWeapons().get(i).setShoot(manager.isKeyPressed(KeyEvent.VK_1 + i, gameObject.getNetworkProjectionId()));
        }
    };

}
