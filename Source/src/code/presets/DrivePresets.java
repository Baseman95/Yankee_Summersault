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

    public static Drive TRACK = new Drive(
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

                data.getMovementData().setMovementX(Math.abs(data.getMovementData().getMovementX()) > 0.08 ? data.getMovementData().getMovementX() * 0.992f : 0);
                data.getMovementData().setMovementY(Math.abs(data.getMovementData().getMovementX()) > 0.08 ? data.getMovementData().getMovementY() * 0.992f : 0);

            },
            (GameObject gameObject, long tick, World w, KeyManager manager) -> {
                DataObject data = (DataObject) gameObject.getData();

                data.getPositionData().increaseRotation(-0.004);
            },
            (GameObject gameObject, long tick, World w, KeyManager manager) -> {
                DataObject data = (DataObject) gameObject.getData();

                data.getPositionData().increaseRotation(+0.004);
            },
            null,
            null,
            (GameObject gameObject, long tick, World w, KeyManager manager) -> {
                DataObject data = (DataObject) gameObject.getData();
                CartesianVector vector = new CartesianVector(data.getMovementData().getMovementX(),
                        data.getMovementData().getMovementY());
                PolarVector pv = vector.toPolarVector();
                pv.updateAngleRange2Pi();
                System.out.println(data.getPositionData().getRotation() + " - "
                        + pv.length + " - " + pv.angle);

                /* float dif = (float)((data.getPositionData().getRotation() - pv.angle)%(2*Math.PI));
                    System.out.println(dif);
                    dif = (float)(dif >= Math.PI ? 2*Math.PI-dif : dif);
                    if(dif < 0){
                        pv.angle = pv.angle+(dif*0.05);
                    }else{
                        pv.angle = pv.angle+(dif*0.05);
                    }*/
                // pv.angle = data.getPositionData().getRotation();
                double deltaAng = pv.angle - data.getPositionData().getRotation();

                deltaAng = deltaAng > Math.PI
                        ? deltaAng - Math.PI * 2 : deltaAng < -Math.PI
                                ? deltaAng + Math.PI * 2 : deltaAng;
                if (Math.abs(deltaAng) > Math.PI / 2) {
                    deltaAng -= Math.signum(deltaAng) * Math.PI;
                }

                // deltaAng = Math.abs(deltaAng) < 0.005 ? deltaAng : deltaAng * 0.01; //deltaAng= Absolutdiffrenzwert
                System.out.println(Math.abs(deltaAng) < 0.005 ? "Delta" : "*0.01");
                System.out.println(Math.abs(deltaAng));
                double multipli = 1;
                if (Math.abs(deltaAng) < 0.003) {
                    multipli = 1;
                } else if (Math.abs(deltaAng) > 0.003 && Math.abs(deltaAng) < 0.01)
                    multipli = 0.05;
                else if (Math.abs(deltaAng) > 0.01 && Math.abs(deltaAng) < 0.020)
                    multipli = 0.04;
                else if (Math.abs(deltaAng) > 0.021 && Math.abs(deltaAng) < 0.04)
                    multipli = 0.03;
                else if (Math.abs(deltaAng) > 0.041 && Math.abs(deltaAng) < 0.009)
                    multipli = 0.01;

                deltaAng *= multipli;
                pv.angle -= deltaAng;

                data.getMovementData().setMovementX(PolarVector.xFromPolar(pv));
                data.getMovementData().setMovementY(PolarVector.yFromPolar(pv));

            }
    );
}
