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

    private static Drive SIMPLE_CONTROLS;

    public static Drive getDrive(int penis) {
        if (SIMPLE_CONTROLS == null) {
            SIMPLE_CONTROLS = new Drive(
                    (GameObject gameObject, long tick, World w, KeyManager manager) -> {
                        DataObject data = (DataObject) gameObject.getData();

                        double ang = data.getPositionData().getRotation();
                        PolarVector mv = new PolarVector(ang, 0.001f);
                        data.getMovementData().setMovementX(PolarVector.xFromPolar(mv) + data.getMovementData().getMovementX());
                        data.getMovementData().setMovementY(PolarVector.yFromPolar(mv) + data.getMovementData().getMovementY());
                    },
                    (GameObject gameObject, long tick, World w, KeyManager manager) -> {
                        DataObject data = (DataObject) gameObject.getData();

                        double ang = data.getPositionData().getRotation();
                        PolarVector mv = new PolarVector(ang, 0.001f);

                        data.getMovementData().setMovementX(data.getMovementData().getMovementX() - PolarVector.xFromPolar(mv));
                        data.getMovementData().setMovementY(data.getMovementData().getMovementY() - PolarVector.yFromPolar(mv));
                    },
                    (GameObject gameObject, long tick, World w, KeyManager manager) -> {
                        DataObject data = (DataObject) gameObject.getData();

                        double ang = data.getPositionData().getRotation();
                        PolarVector mv = new PolarVector(ang, 0.001f);

                        data.getMovementData().setMovementX(data.getMovementData().getMovementX() * 0.992f);
                        data.getMovementData().setMovementY(data.getMovementData().getMovementY() * 0.992f);
                    },
                    (GameObject gameObject, long tick, World w, KeyManager manager) -> {
                        DataObject data = (DataObject) gameObject.getData();

                        data.getPositionData().increaseRotation(-0.004);
                    },
                    (GameObject gameObject, long tick, World w, KeyManager manager) -> {
                        DataObject data = (DataObject) gameObject.getData();

                        data.getPositionData().increaseRotation(+0.004);
                        CartesianVector vector = new CartesianVector(data.getMovementData().getMovementX(),
                                data.getMovementData().getMovementY());
                        PolarVector pv = vector.toPolarVector();
                        pv.angle += 0.004;
                        //pv.updateAngleRangePi();

                        data.getMovementData().setMovementX(PolarVector.xFromPolar(pv));
                        data.getMovementData().setMovementY(PolarVector.yFromPolar(pv));
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
        }
        return SIMPLE_CONTROLS;
    }
}
