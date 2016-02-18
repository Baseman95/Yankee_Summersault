/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package code.presets;

import code.data.DataObject;
import code.game.World;
import code.game.tank.Drive;
import yansuen.game.GameObject;
import yansuen.key.KeyManager;
import yansuen.physic.CartesianVector;
import yansuen.physic.PolarVector;

/**
 *
 * @author Eris
 */
public class DrivePresets {

    public static Drive SIMPLE = createDrive(0.001f, 0.001f, 0.004f, 0.95f);

    private static Drive createDrive(float acceleration, float deceleration,
            float rotation, float breakMultiplicator) {
        Drive drive;
        drive = new Drive(
                (GameObject gameObject, long tick, World w, KeyManager manager) -> {
                    DataObject data = (DataObject) gameObject.getData();

                    double ang = data.getPositionData().getRotation();
                    PolarVector mv = new PolarVector(ang, acceleration);
                    data.getMovementData().setMovementX(PolarVector.xFromPolar(mv) + data.getMovementData().getMovementX());
                    data.getMovementData().setMovementY(PolarVector.yFromPolar(mv) + data.getMovementData().getMovementY());
                },
                (GameObject gameObject, long tick, World w, KeyManager manager) -> {
                    DataObject data = (DataObject) gameObject.getData();

                    double ang = data.getPositionData().getRotation();
                    PolarVector mv = new PolarVector(ang, deceleration);

                    data.getMovementData().setMovementX(data.getMovementData().getMovementX() - PolarVector.xFromPolar(mv));
                    data.getMovementData().setMovementY(data.getMovementData().getMovementY() - PolarVector.yFromPolar(mv));
                },
                (GameObject gameObject, long tick, World w, KeyManager manager) -> {
                    DataObject data = (DataObject) gameObject.getData();
                    data.getMovementData().setMovementX(data.getMovementData().getMovementX() * breakMultiplicator);
                    data.getMovementData().setMovementY(data.getMovementData().getMovementY() * breakMultiplicator);
                },
                (GameObject gameObject, long tick, World w, KeyManager manager) -> {
                    DataObject data = (DataObject) gameObject.getData();
                    data.getPositionData().increaseRotation(-rotation);
                },
                (GameObject gameObject, long tick, World w, KeyManager manager) -> {
                    DataObject data = (DataObject) gameObject.getData();
                    data.getPositionData().increaseRotation(+rotation);
                },
                null,
                null,
                (GameObject gameObject, long tick, World w, KeyManager manager) -> {
                    DataObject data = (DataObject) gameObject.getData();
                    CartesianVector vector = new CartesianVector(data.getMovementData().getMovementX(),
                            data.getMovementData().getMovementY());
                    PolarVector pv = vector.toPolarVector();
                    pv.angle = data.getPositionData().getRotation();
                    data.getMovementData().setMovementX(PolarVector.xFromPolar(pv));
                    data.getMovementData().setMovementY(PolarVector.yFromPolar(pv));

                }
        );
        return drive;
    }

    public static Drive createStraightDrive(float speed, double rotation) {
        Drive straight = new Drive(null, null, null, null, null, null, null,
                (GameObject gameObject, long tick, World world1, KeyManager manager) -> {
                    DataObject data = (DataObject) gameObject.getData();

                    PolarVector pv = new PolarVector(rotation, speed);
                    data.getMovementData().setMovementX(PolarVector.xFromPolar(pv));
                    data.getMovementData().setMovementY(PolarVector.yFromPolar(pv));
                });
        return straight;
    }
}
