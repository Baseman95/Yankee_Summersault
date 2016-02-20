/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package code.presets;

import code.TrifkoTest;
import code.data.DataObject;
import code.game.World;
import code.game.tank.Chassis;
import code.game.tank.Drive;
import java.text.DecimalFormat;
import yansuen.game.GameObject;
import yansuen.key.MasterKeyManager;
import yansuen.physic.CartesianVector;
import yansuen.physic.PolarVector;

/**
 *
 * @author Eris
 */
public class DrivePresets {

    public static Drive createSimpleDrive() {
        return createDrive(0.001f, 0.001f, 0.004f, 0.95f);
    }

    public static Drive createSimpleFastDrive() {
        return createDrive(0.01f, 0.001f, 0.004f, 0.95f);
    }

    public static Drive createDrive(float acceleration, float deceleration,
            float rotation, float breakMultiplicator) {
        Drive drive;
        drive = new Drive(
                (Drive drive1, GameObject gameObject, long tick, World world) -> {
                    DataObject data = (DataObject) gameObject.getData();

                    double ang = data.getPositionData().getRotation();
                    PolarVector mv = new PolarVector(ang, acceleration);
                    data.getMovementData().setMovementX(PolarVector.xFromPolar(mv) + data.getMovementData().getMovementX());
                    data.getMovementData().setMovementY(PolarVector.yFromPolar(mv) + data.getMovementData().getMovementY());
                },
                (Drive drive1, GameObject gameObject, long tick, World world) -> {
                    DataObject data = (DataObject) gameObject.getData();

                    double ang = data.getPositionData().getRotation();
                    PolarVector mv = new PolarVector(ang, deceleration);

                    data.getMovementData().setMovementX(data.getMovementData().getMovementX() - PolarVector.xFromPolar(mv));
                    data.getMovementData().setMovementY(data.getMovementData().getMovementY() - PolarVector.yFromPolar(mv));
                },
                (Drive drive1, GameObject gameObject, long tick, World world) -> {
                    DataObject data = (DataObject) gameObject.getData();
                    data.getMovementData().setMovementX(data.getMovementData().getMovementX() * breakMultiplicator);
                    data.getMovementData().setMovementY(data.getMovementData().getMovementY() * breakMultiplicator);
                },
                (Drive drive1, GameObject gameObject, long tick, World world) -> {
                    DataObject data = (DataObject) gameObject.getData();
                    data.getPositionData().increaseRotation(-rotation);
                },
                (Drive drive1, GameObject gameObject, long tick, World world) -> {
                    DataObject data = (DataObject) gameObject.getData();
                    data.getPositionData().increaseRotation(+rotation);
                },
                null,
                null,
                (Drive drive1, GameObject gameObject, long tick, World world) -> {
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

    public static Drive createRocketDrive(float travelspeed, double rotationspeed) {
        Drive drive = new Drive(
                null, null, null,
                (Drive drive1, GameObject gameObject, long tick, World w) -> {
                    DataObject data = (DataObject) gameObject.getData();
                    data.getPositionData().increaseRotation(-rotationspeed);
                }, (Drive drive1, GameObject gameObject, long tick, World w) -> {
                    DataObject data = (DataObject) gameObject.getData();
                    data.getPositionData().increaseRotation(rotationspeed);
                }, null, null,
                (Drive drive1, GameObject gameObject, long tick, World w) -> {
                    DataObject data = (DataObject) gameObject.getData();
                    PolarVector p = new PolarVector(data.getPositionData().getRotation(),
                                                    CartesianVector.lengthFromCartesian(new CartesianVector(data.getMovementData().getMovementX(), data.getMovementData().getMovementY())));
                    data.getMovementData().setMovementX(PolarVector.xFromPolar(p));
                    data.getMovementData().setMovementY(PolarVector.yFromPolar(p));
                });
        return drive;
    }

    public static Drive createStraightDrive(float speed, double rotation) {
        Drive straight = new Drive(null, null, null, null, null, null, null,
                                   (Drive drive, GameObject gameObject, long tick, World world) -> {
                                       DataObject data = (DataObject) gameObject.getData();

                                       PolarVector pv = new PolarVector(rotation, speed);
                                       data.getMovementData().setMovementX(PolarVector.xFromPolar(pv));
                                       data.getMovementData().setMovementY(PolarVector.yFromPolar(pv));
                                   });
        return straight;
    }

    public static Drive createTrack(float rotation) {
        Drive track = new Drive(
                (Drive drive, GameObject gameObject, long tick, World world) -> {
                    DataObject data = (DataObject) gameObject.getData();
                    PolarVector current = new PolarVector(new CartesianVector(data.getMovementData().getMovementX(),
                                                                              data.getMovementData().getMovementY()));
                    current.updateAngleRange2Pi();
                    //        System.out.println(data.getPositionData().getRotation()+"------------"+current.angle);
                    if (!getRichtung(data, current.angle))
                        drive.setBreaks(current.length != 0);

                    if (drive.isBreaks())
                        return;

                    // System.out.println("CurrentSpeed: " + current.length);
                    double ang = data.getPositionData().getRotation();
                    PolarVector mv = new PolarVector(ang, getTankAccerate(current.length));
                    data.getMovementData().setMovementX(current.length < 1.2
                                                        ? PolarVector.xFromPolar(mv) + data.getMovementData().getMovementX()
                                                        : data.getMovementData().getMovementX());

                    data.getMovementData().setMovementY(current.length < 1.2
                                                        ? PolarVector.yFromPolar(mv) + data.getMovementData().getMovementY()
                                                        : data.getMovementData().getMovementY());
                },
                (Drive drive, GameObject gameObject, long tick, World world) -> {
                    DataObject data = (DataObject) gameObject.getData();
                    PolarVector current = new PolarVector(new CartesianVector(data.getMovementData().getMovementX(),
                                                                              data.getMovementData().getMovementY()));

                    current.updateAngleRange2Pi();

                    if (getRichtung(data, current.angle))
                        drive.setBreaks(current.length != 0);

                    if (drive.isBreaks())
                        return;

                    // System.out.println("CurrentSpeed: " + current.length);
                    double ang = data.getPositionData().getRotation();
                    PolarVector mv = new PolarVector(ang, getTankAccerate(current.length));

                    data.getMovementData().setMovementX(data.getMovementData().getMovementX() - PolarVector.xFromPolar(mv));
                    data.getMovementData().setMovementY(data.getMovementData().getMovementY() - PolarVector.yFromPolar(mv));
                },
                (Drive drive, GameObject gameObject, long tick, World world) -> {
                    DataObject data = (DataObject) gameObject.getData();

                    PolarVector current = new PolarVector(new CartesianVector(data.getMovementData().getMovementX(),
                                                                              data.getMovementData().getMovementY()));

                    data.getMovementData().setMovementX(Math.abs(data.getMovementData().getMovementX()) > 0.07 ? data.getMovementData().getMovementX() * getBreakStrength(current.length) : 0);
                    data.getMovementData().setMovementY(Math.abs(data.getMovementData().getMovementY()) > 0.07 ? data.getMovementData().getMovementY() * getBreakStrength(current.length) : 0);

                },
                (Drive drive, GameObject gameObject, long tick, World world) -> {
                    DataObject data = (DataObject) gameObject.getData();

                    data.getPositionData().increaseRotation(-0.004);

                    if (drive.isBreaks())
                        return;

                    PolarVector current = new PolarVector(new CartesianVector(data.getMovementData().getMovementX(),
                                                                              data.getMovementData().getMovementY()));
                    current.length *= 0.9985;
                    data.getMovementData().setMovementX(PolarVector.xFromPolar(current));
                    data.getMovementData().setMovementY(PolarVector.yFromPolar(current));
                },
                (Drive drive, GameObject gameObject, long tick, World world) -> {
                    DataObject data = (DataObject) gameObject.getData();

                    data.getPositionData().increaseRotation(+0.004);

                    if (drive.isBreaks())
                        return;

                    PolarVector current = new PolarVector(new CartesianVector(data.getMovementData().getMovementX(),
                                                                              data.getMovementData().getMovementY()));
                    current.length *= 0.9985;
                    data.getMovementData().setMovementX(PolarVector.xFromPolar(current));
                    data.getMovementData().setMovementY(PolarVector.yFromPolar(current));
                },
                null,
                null,
                (Drive drive, GameObject gameObject, long tick, World world) -> {
                    DataObject data = (DataObject) gameObject.getData();
                    CartesianVector vector = new CartesianVector(data.getMovementData().getMovementX(),
                                                                 data.getMovementData().getMovementY());
                    PolarVector pv = vector.toPolarVector();
                    pv.updateAngleRange2Pi();

                    //    System.out.println(data.getPositionData().getRotation() + " - "
                    //           + pv.length + " - " + pv.angle);

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
                    //    System.out.println("DElta1  " + deltaAng);
                    deltaAng = deltaAng > Math.PI
                               ? deltaAng - Math.PI * 2 : deltaAng < -Math.PI
                                                          ? deltaAng + Math.PI * 2 : deltaAng;
                    if (Math.abs(deltaAng) > Math.PI / 2) {
                        deltaAng -= Math.signum(deltaAng) * Math.PI;
                    }

                    PolarVector current = new PolarVector(new CartesianVector(data.getMovementData().getMovementX(),
                                                                              data.getMovementData().getMovementY()));
                    //   deltaAng = Math.abs(deltaAng) < 0.005 ? deltaAng : deltaAng * 0.01; //deltaAng= Absolutdiffrenzwert
                    //  System.out.println(Math.abs(deltaAng) < 0.005 ? "Delta" : "*0.01");
                    System.out.println(Math.abs(deltaAng) + "--------");
                    System.out.println("CurrentSpeed: " + current.length);
                    if (Math.abs(deltaAng) > 0.008 && current.length > 0.93) {
                        deltaAng *= 0.015;
                        //System.out.println("5");
                    } else if (Math.abs(deltaAng) > 0.08 && current.length > 0.50) {
                        deltaAng *= 0.018;
                        //  System.out.println("4");
                    } else if (Math.abs(deltaAng) > 0.02) {
                        deltaAng *= 0.05;
                        // System.out.println("3");
                    } else {
                        deltaAng *= 1;
                        //System.out.println("1");
                    }

                    pv.angle -= deltaAng;
                    if (!drive.isBreaks())
                        pv.length *= current.length > 0.08 ? (current.length < 1 ? 0.9965 : 0.9975) : 0.88;

                    data.getMovementData().setMovementX(PolarVector.xFromPolar(pv));
                    data.getMovementData().setMovementY(PolarVector.yFromPolar(pv));

                }
        );

        return track;
    }

    public static Drive createHeli(float rotation) {
        Drive heli = new Drive(
                (Drive drive, GameObject gameObject, long tick, World w) -> {
                    DataObject data = (DataObject) gameObject.getData();
                    PolarVector current = new PolarVector(new CartesianVector(data.getMovementData().getMovementX(),
                                                                              data.getMovementData().getMovementY()));
                    current.updateAngleRange2Pi();
                    //        System.out.println(data.getPositionData().getRotation()+"------------"+current.angle);
                    if (!getRichtung(data, current.angle) && current.length != 0) {
                        data.getMovementData().setMovementX(Math.abs(data.getMovementData().getMovementX()) > 0.07 ? data.getMovementData().getMovementX() * getBreakStrength(current.length) : 0);
                        data.getMovementData().setMovementY(Math.abs(data.getMovementData().getMovementY()) > 0.07 ? data.getMovementData().getMovementY() * getBreakStrength(current.length) : 0);
                    } else {
                        // System.out.println("CurrentSpeed: " + current.length);
                        double ang = data.getPositionData().getRotation();
                        PolarVector mv = new PolarVector(ang, getHeliAccerate(current.length));
                        data.getMovementData().setMovementX(current.length < 1.3
                                                            ? PolarVector.xFromPolar(mv) + data.getMovementData().getMovementX()
                                                            : data.getMovementData().getMovementX());

                        data.getMovementData().setMovementY(current.length < 1.3
                                                            ? PolarVector.yFromPolar(mv) + data.getMovementData().getMovementY()
                                                            : data.getMovementData().getMovementY());
                    }

                },
                (Drive drive, GameObject gameObject, long tick, World w) -> {
                    DataObject data = (DataObject) gameObject.getData();
                    PolarVector current = new PolarVector(new CartesianVector(data.getMovementData().getMovementX(),
                                                                              data.getMovementData().getMovementY()));

                    current.updateAngleRange2Pi();

                    if (getRichtung(data, current.angle) && current.length != 0) {
                        data.getMovementData().setMovementX(Math.abs(data.getMovementData().getMovementX()) > 0.07
                                                            ? data.getMovementData().getMovementX() * getBreakStrength(current.length) : 0);
                        data.getMovementData().setMovementY(Math.abs(data.getMovementData().getMovementY()) > 0.07
                                                            ? data.getMovementData().getMovementY() * getBreakStrength(current.length) : 0);

                    } else {
                        //    drive.setBreaks(current.length != 0);
                        // System.out.println("CurrentSpeed: " + current.length);
                        double ang = data.getPositionData().getRotation();
                        PolarVector mv = new PolarVector(ang, getHeliAccerate(current.length));

                        data.getMovementData().setMovementX(current.length < 0.6
                                                            ? data.getMovementData().getMovementX() - PolarVector.xFromPolar(mv)
                                                            : data.getMovementData().getMovementX());

                        data.getMovementData().setMovementY(current.length < 0.6
                                                            ? data.getMovementData().getMovementY() - PolarVector.yFromPolar(mv)
                                                            : data.getMovementData().getMovementY());
                    }

                },
                null,
                (Drive drive, GameObject gameObject, long tick, World w) -> {
                    DataObject data = (DataObject) gameObject.getData();

                    data.getPositionData().increaseRotation(-0.005);

                    /* 

                    PolarVector current = new PolarVector(new CartesianVector(data.getMovementData().getMovementX(),
                            data.getMovementData().getMovementY()));
                  //  current.length *= 0.9995;
                    data.getMovementData().setMovementX(PolarVector.xFromPolar(current));
                    data.getMovementData().setMovementY(PolarVector.yFromPolar(current));*/
                },
                (Drive drive, GameObject gameObject, long tick, World w) -> {
                    DataObject data = (DataObject) gameObject.getData();

                    data.getPositionData().increaseRotation(+0.005);

                    /*    if (drive.isBreaks())
                        return;

                    PolarVector current = new PolarVector(new CartesianVector(data.getMovementData().getMovementX(),
                            data.getMovementData().getMovementY()));
                   // current.length *= 0.9995;
                    data.getMovementData().setMovementX(PolarVector.xFromPolar(current));
                    data.getMovementData().setMovementY(PolarVector.yFromPolar(current)); */
                },
                (Drive drive, GameObject gameObject, long tick, World w) -> {

                    DataObject data = (DataObject) gameObject.getData();
                    PolarVector current = new PolarVector(new CartesianVector(data.getMovementData().getMovementX(),
                                                                              data.getMovementData().getMovementY()));

                    double ang = data.getPositionData().getRotation();

                    PolarVector mv = new PolarVector(ang - Math.PI / 2, current.length < 0.3 ? 0.003f : 0.003f);

                    data.getMovementData().setMovementX(data.getMovementData().getMovementX() + PolarVector.xFromPolar(mv));
                    data.getMovementData().setMovementY(data.getMovementData().getMovementY() + PolarVector.yFromPolar(mv));
                },
                (Drive drive, GameObject gameObject, long tick, World w) -> {

                    DataObject data = (DataObject) gameObject.getData();
                    PolarVector current = new PolarVector(new CartesianVector(data.getMovementData().getMovementX(),
                                                                              data.getMovementData().getMovementY()));

                    double ang = data.getPositionData().getRotation();

                    PolarVector mv = new PolarVector(ang + Math.PI / 2, current.length < 0.3 ? 0.003f : 0.003f);

                    data.getMovementData().setMovementX(data.getMovementData().getMovementX() + PolarVector.xFromPolar(mv));
                    data.getMovementData().setMovementY(data.getMovementData().getMovementY() + PolarVector.yFromPolar(mv));
                },
                (Drive drive, GameObject gameObject, long tick, World w) -> {

                    DataObject data = (DataObject) gameObject.getData();
                    PolarVector current = new PolarVector(new CartesianVector(data.getMovementData().getMovementX(),
                                                                              data.getMovementData().getMovementY()));
                    current.updateAngleRange2Pi();
                    double ang = data.getPositionData().getRotation();
                    PolarVector mv = new PolarVector(ang, 0.001f);
                    // pv.angle = data.getPositionData().getRotation();
                    double deltaAng = current.angle - data.getPositionData().getRotation();
                    //    System.out.println("DElta1  " + deltaAng);
                    deltaAng = deltaAng > Math.PI
                               ? deltaAng - Math.PI * 2 : deltaAng < -Math.PI
                                                          ? deltaAng + Math.PI * 2 : deltaAng;
                    if (Math.abs(deltaAng) > Math.PI / 2) {
                        deltaAng -= Math.signum(deltaAng) * Math.PI;
                    }

                    // deltaAng = Math.abs(deltaAng) < 0.005 ? deltaAng : deltaAng * 0.01; //deltaAng= Absolutdiffrenzwert
                    //  System.out.println(Math.abs(deltaAng) < 0.005 ? "Delta" : "*0.01");
                    //       System.out.println(Math.abs(deltaAng) + "--------");
                    //     System.out.println("CurrentSpeed: " + current.length);
                    DecimalFormat decimalFormat = new DecimalFormat(" .0000");
                    TrifkoTest.Deltalabel.setText(decimalFormat.format(deltaAng));
                    if (Math.abs(deltaAng) > 0.008 && current.length > 1) {
                        deltaAng *= 0.0005;
                        TrifkoTest.Driftlabel.setText("Drift 1 ");
                    } else if (Math.abs(deltaAng) > 0.08 && current.length > 0.50) {
                        deltaAng *= 0.001;
                        TrifkoTest.Driftlabel.setText("Drift 2 ");
                    } else if (Math.abs(deltaAng) > 0.02) {
                        deltaAng *= 0.002;
                        TrifkoTest.Driftlabel.setText("Drift 3 ");
                    } else {
                        deltaAng *= 0.005;
                        TrifkoTest.Driftlabel.setText("Drift 4 ");
                    }

                    current.angle -= deltaAng;

                    current.length *= current.length < 0.9 ? 0.997 : 0.997;
                    current.length *= current.length > 0.05 ? 1 : 1.5;
                    data.getMovementData().setMovementX(PolarVector.xFromPolar(current));
                    data.getMovementData().setMovementY(PolarVector.yFromPolar(current));

                    if (!getRichtung(data, current.angle) && current.length > 0) {
                        data.getMovementData().setMovementX(data.getMovementData().getMovementX() + PolarVector.xFromPolar(mv));
                        data.getMovementData().setMovementY(data.getMovementData().getMovementY() + PolarVector.yFromPolar(mv));
                    }

                    TrifkoTest.Currentlabel.setText(decimalFormat.format(current.length));
                    TrifkoTest.Richtunglabel.setText(getRichtung(data, current.angle) ? "Vorne" : "Hinten");

                }
        );

        return heli;
    }

    private static float getTankAccerate(float current) {
        float a = (float) ((Math.pow(current, 8)) * 0.0015 - (current * current) * 0.0167 - current * 0.0001 + 0.02);
        return a >= 0 ? a : 0;

    }

    private static float getHeliAccerate(float x) {
        float a = (float) (-(x * x) * 0.01502099954 + x * 0.02124146722 + 0.00535303989);
        return a >= 0 ? a : 0;

    }

    private static float getBreakStrength(float current) {
        float a = (float) (0.01 * current + 0.98);
        return a < 0.999 ? a : 0.999f;

    }

    private static boolean getRichtung(DataObject d, double winkel) {
        double deltaAng = winkel - d.getPositionData().getRotation();
        //    System.out.println("DElta1  " + deltaAng);
        deltaAng += Math.PI / 2;
        PolarVector pv = new PolarVector(deltaAng, 1);
        pv.updateAngleRange2Pi();
        if (pv.angle <= Math.PI)
            return true;
        else
            return false;

    }
}
