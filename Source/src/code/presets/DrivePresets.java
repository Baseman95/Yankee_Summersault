package code.presets;

import code.TrifkoTest;
import code.data.VehicleData;
import yansuen.data.GameData;
import code.game.World;
import code.game.tank.Drive;
import java.text.DecimalFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import yansuen.game.GameObject;
import yansuen.key.MasterKeyManager;
import yansuen.physic.CartesianVector;
import yansuen.physic.PolarVector;

/**
 * @author Eris
 */
public class DrivePresets {

    public enum DriveTypes {
        TANK, AUTO, HELICOPTER, JET, BOOT
    }

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
                (GameObject gameObject, long tick, World world, MasterKeyManager manager) -> {
                    VehicleData data = (VehicleData) gameObject.getData();

                    double ang = data.getRotation();
                    PolarVector mv = new PolarVector(ang, acceleration);
                    data.setMovementX(PolarVector.xFromPolar(mv) + data.getMovementX());
                    data.setMovementY(PolarVector.yFromPolar(mv) + data.getMovementY());
                },
                (GameObject gameObject, long tick, World world, MasterKeyManager manager) -> {
                    VehicleData data = (VehicleData) gameObject.getData();

                    double ang = data.getRotation();
                    PolarVector mv = new PolarVector(ang, deceleration);

                    data.setMovementX(data.getMovementX() - PolarVector.xFromPolar(mv));
                    data.setMovementY(data.getMovementY() - PolarVector.yFromPolar(mv));
                },
                (GameObject gameObject, long tick, World world, MasterKeyManager manager) -> {
                    VehicleData data = (VehicleData) gameObject.getData();
                    data.setMovementX(data.getMovementX() * breakMultiplicator);
                    data.setMovementY(data.getMovementY() * breakMultiplicator);
                },
                (GameObject gameObject, long tick, World world, MasterKeyManager manager) -> {
                    VehicleData data = (VehicleData) gameObject.getData();
                    data.increaseRotation(-rotation);
                },
                (GameObject gameObject, long tick, World world, MasterKeyManager manager) -> {
                    VehicleData data = (VehicleData) gameObject.getData();
                    data.increaseRotation(+rotation);
                },
                null,
                null,
                (GameObject gameObject, long tick, World world, MasterKeyManager manager) -> {
                    VehicleData data = (VehicleData) gameObject.getData();
                    CartesianVector vector = new CartesianVector(data.getMovementX(),
                            data.getMovementY());
                    PolarVector pv = vector.toPolarVector();
                    pv.angle = data.getRotation();
                    data.setMovementX(PolarVector.xFromPolar(pv));
                    data.setMovementY(PolarVector.yFromPolar(pv));

                }
        );
        return drive;
    }

    public static Drive createRocketDrive(float travelspeed, double rotationspeed) {
        Drive drive = new Drive(
                null, null, null,
                (GameObject gameObject, long tick, World world, MasterKeyManager manager) -> {
                    VehicleData data = (VehicleData) gameObject.getData();
                    data.increaseRotation(-rotationspeed);
                }, (GameObject gameObject, long tick, World world, MasterKeyManager manager) -> {
                    VehicleData data = (VehicleData) gameObject.getData();
                    data.increaseRotation(rotationspeed);
                }, null, null,
                (GameObject gameObject, long tick, World world, MasterKeyManager manager) -> {
                    VehicleData data = (VehicleData) gameObject.getData();
                    PolarVector p = new PolarVector(data.getRotation(), travelspeed);
                    data.setMovementX(PolarVector.xFromPolar(p));
                    data.setMovementY(PolarVector.yFromPolar(p));
                });
        return drive;
    }

    public static Drive createStraightDrive(float speed, double rotation) {
        Drive straight = new Drive(null, null, null, null, null, null, null,
                (GameObject gameObject, long tick, World world, MasterKeyManager manager) -> {
                    VehicleData data = (VehicleData) gameObject.getData();

                    PolarVector pv = new PolarVector(rotation, speed);
                    data.setMovementX(PolarVector.xFromPolar(pv));
                    data.setMovementY(PolarVector.yFromPolar(pv));
                });
        return straight;
    }

    public static Drive createTrack() {
        Drive track = new Drive(
                (GameObject gameObject, long tick, World world, MasterKeyManager manager) -> {
                    VehicleData data = (VehicleData) gameObject.getData();
                    PolarVector current = new PolarVector(new CartesianVector(data.getMovementX(),
                            data.getMovementY()));
                    current.updateAngleRange2Pi();
                    //        System.out.println(data.getRotation()+"------------"+current.angle);
                    if (!getDirection(data, current.angle) && current.length != 0) {
                        data.setMovementX(Math.abs(data.getMovementX()) > 0.02 ? data.getMovementX() * getBreakStrength(current.length, DriveTypes.TANK) : 0);
                        data.setMovementY(Math.abs(data.getMovementY()) > 0.02 ? data.getMovementY() * getBreakStrength(current.length, DriveTypes.TANK) : 0);
                    } else {
                        // System.out.println("CurrentSpeed: " + current.length);
                        double ang = data.getRotation();
                        PolarVector mv = new PolarVector(ang, getTankAccelerate(current.length));
                        data.setMovementX(current.length < 0.9
                                ? PolarVector.xFromPolar(mv) + data.getMovementX()
                                : data.getMovementX());

                        data.setMovementY(current.length < 0.9
                                ? PolarVector.yFromPolar(mv) + data.getMovementY()
                                : data.getMovementY());
                    }

                },
                (GameObject gameObject, long tick, World world, MasterKeyManager manager) -> {
                    VehicleData data = (VehicleData) gameObject.getData();
                    PolarVector current = new PolarVector(new CartesianVector(data.getMovementX(),
                            data.getMovementY()));

                    current.updateAngleRange2Pi();

                    if (getDirection(data, current.angle) && current.length != 0) {
                        data.setMovementX(Math.abs(data.getMovementX()) > 0.02
                                ? data.getMovementX() * getBreakStrength(current.length, DriveTypes.TANK) : 0);
                        data.setMovementY(Math.abs(data.getMovementY()) > 0.02
                                ? data.getMovementY() * getBreakStrength(current.length, DriveTypes.TANK) : 0);

                    } else {
                        //    drive.setBreaks(current.length != 0);
                        // System.out.println("CurrentSpeed: " + current.length);
                        double ang = data.getRotation();
                        PolarVector mv = new PolarVector(ang, getTankAccelerate(current.length));

                        data.setMovementX(current.length < 0.9
                                ? data.getMovementX() - PolarVector.xFromPolar(mv)
                                : data.getMovementX());

                        data.setMovementY(current.length < 0.9
                                ? data.getMovementY() - PolarVector.yFromPolar(mv)
                                : data.getMovementY());
                    }

                },
                null,
                (GameObject gameObject, long tick, World world, MasterKeyManager manager) -> {
                    VehicleData data = (VehicleData) gameObject.getData();
                    PolarVector current = new PolarVector(new CartesianVector(data.getMovementX(),
                            data.getMovementY()));

                    current.updateAngleRange2Pi();
                    if(current.length==0){
                        data.increaseRotation(-0.003);
                    }
                    if (getDirection(data, current.angle) && current.length > 0){
                        data.increaseRotation(-0.003);
                    }
                    if (!getDirection(data, current.angle) && current.length > 0){
                        data.increaseRotation(+0.003);
                    }

                    /* 

                    PolarVector current = new PolarVector(new CartesianVector(data.getMovementX(),
                            data.getMovementY()));
                  //  current.length *= 0.9995;
                    data.setMovementX(PolarVector.xFromPolar(current));
                    data.setMovementY(PolarVector.yFromPolar(current));*/
                },
                (GameObject gameObject, long tick, World world, MasterKeyManager manager) -> {
                    VehicleData data = (VehicleData) gameObject.getData();
                    PolarVector current = new PolarVector(new CartesianVector(data.getMovementX(),
                            data.getMovementY()));

                    current.updateAngleRange2Pi();
                    
                    if(current.length>=0){
                        data.increaseRotation(+0.003);
                    }
                    if (getDirection(data, current.angle) && current.length > 0.05){
                        data.increaseRotation(+0.003);
                    }
                    if (!getDirection(data, current.angle) && current.length > 0.05){
                        data.increaseRotation(-0.003);
                    }

                    /*    if (data.breaks)
                        return;

                    PolarVector current = new PolarVector(new CartesianVector(data.getMovementX(),
                            data.getMovementY()));
                   // current.length *= 0.9995;
                    data.setMovementX(PolarVector.xFromPolar(current));
                    data.setMovementY(PolarVector.yFromPolar(current)); */
                },
                null,
                null,
                (GameObject gameObject, long tick, World world, MasterKeyManager manager) -> {

                    VehicleData data = (VehicleData) gameObject.getData();
                    PolarVector current = new PolarVector(new CartesianVector(data.getMovementX(),
                            data.getMovementY()));
                    current.updateAngleRange2Pi();
                    double ang = data.getRotation();
                    PolarVector mv = new PolarVector(ang, 0.001f);
                    // pv.angle = data.getRotation();
                    double deltaAng = current.angle - data.getRotation();
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
                  /*  if (Math.abs(deltaAng) > 0.008 && current.length > 1) {
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

                    current.angle -= deltaAng;*/

                    current.length *= current.length < 0.5 ? 0.995 : 0.997;
                    if(current.length < 0.025 && !data.accelerate && !data.decelerate){
                        current.length = 0;
                    }
                    data.setMovementX(PolarVector.xFromPolar(current));
                    data.setMovementY(PolarVector.yFromPolar(current));

                    if (!getDirection(data, current.angle) && current.length > 0) {
                        data.setMovementX(data.getMovementX() + PolarVector.xFromPolar(mv));
                        data.setMovementY(data.getMovementY() + PolarVector.yFromPolar(mv));
                    }                    

                    TrifkoTest.Currentlabel.setText(decimalFormat.format(current.length));
                    TrifkoTest.Richtunglabel.setText(getDirection(data, current.angle) ? "Vorne" : "Hinten");

                }
        );

        return track;
    }

    public static Drive createHeli() {
        Drive heli = new Drive(
                (GameObject gameObject, long tick, World world, MasterKeyManager manager) -> {
                    VehicleData data = (VehicleData) gameObject.getData();
                    PolarVector current = new PolarVector(new CartesianVector(data.getMovementX(),
                            data.getMovementY()));
                    current.updateAngleRange2Pi();
                    //        System.out.println(data.getRotation()+"------------"+current.angle);
                    if (!getDirection(data, current.angle) && current.length != 0) {
                        data.setMovementX(Math.abs(data.getMovementX()) > 0.07 ? data.getMovementX() * getBreakStrength(current.length, DriveTypes.HELICOPTER) : 0);
                        data.setMovementY(Math.abs(data.getMovementY()) > 0.07 ? data.getMovementY() * getBreakStrength(current.length, DriveTypes.HELICOPTER) : 0);
                    } else {
                        // System.out.println("CurrentSpeed: " + current.length);
                        double ang = data.getRotation();
                        PolarVector mv = new PolarVector(ang, getHeliAccelerate(current.length));
                        data.setMovementX(current.length < 1.3
                                ? PolarVector.xFromPolar(mv) + data.getMovementX()
                                : data.getMovementX());

                        data.setMovementY(current.length < 1.3
                                ? PolarVector.yFromPolar(mv) + data.getMovementY()
                                : data.getMovementY());
                    }

                },
                (GameObject gameObject, long tick, World world, MasterKeyManager manager) -> {
                    VehicleData data = (VehicleData) gameObject.getData();
                    PolarVector current = new PolarVector(new CartesianVector(data.getMovementX(),
                            data.getMovementY()));

                    current.updateAngleRange2Pi();

                    if (getDirection(data, current.angle) && current.length != 0) {
                        data.setMovementX(Math.abs(data.getMovementX()) > 0.07
                                ? data.getMovementX() * getBreakStrength(current.length, DriveTypes.HELICOPTER) : 0);
                        data.setMovementY(Math.abs(data.getMovementY()) > 0.07
                                ? data.getMovementY() * getBreakStrength(current.length, DriveTypes.HELICOPTER) : 0);

                    } else {
                        //    drive.setBreaks(current.length != 0);
                        // System.out.println("CurrentSpeed: " + current.length);
                        double ang = data.getRotation();
                        PolarVector mv = new PolarVector(ang, getHeliAccelerate(current.length));

                        data.setMovementX(current.length < 0.6
                                ? data.getMovementX() - PolarVector.xFromPolar(mv)
                                : data.getMovementX());

                        data.setMovementY(current.length < 0.6
                                ? data.getMovementY() - PolarVector.yFromPolar(mv)
                                : data.getMovementY());
                    }

                },
                null,
                (GameObject gameObject, long tick, World world, MasterKeyManager manager) -> {
                    VehicleData data = (VehicleData) gameObject.getData();

                    data.increaseRotation(-0.005);

                    /* 

                    PolarVector current = new PolarVector(new CartesianVector(data.getMovementX(),
                            data.getMovementY()));
                  //  current.length *= 0.9995;
                    data.setMovementX(PolarVector.xFromPolar(current));
                    data.setMovementY(PolarVector.yFromPolar(current));*/
                },
                (GameObject gameObject, long tick, World world, MasterKeyManager manager) -> {
                    VehicleData data = (VehicleData) gameObject.getData();

                    data.increaseRotation(+0.005);

                    /*    if (data.breaks)
                        return;

                    PolarVector current = new PolarVector(new CartesianVector(data.getMovementX(),
                            data.getMovementY()));
                   // current.length *= 0.9995;
                    data.setMovementX(PolarVector.xFromPolar(current));
                    data.setMovementY(PolarVector.yFromPolar(current)); */
                },
                (GameObject gameObject, long tick, World world, MasterKeyManager manager) -> {

                    VehicleData data = (VehicleData) gameObject.getData();
                    PolarVector current = new PolarVector(new CartesianVector(data.getMovementX(),
                            data.getMovementY()));

                    double ang = data.getRotation();

                    PolarVector mv = new PolarVector(ang - Math.PI / 2, current.length < 0.3 ? 0.003f : 0.003f);

                    data.setMovementX(data.getMovementX() + PolarVector.xFromPolar(mv));
                    data.setMovementY(data.getMovementY() + PolarVector.yFromPolar(mv));
                },
                (GameObject gameObject, long tick, World world, MasterKeyManager manager) -> {

                    VehicleData data = (VehicleData) gameObject.getData();
                    PolarVector current = new PolarVector(new CartesianVector(data.getMovementX(),
                            data.getMovementY()));

                    double ang = data.getRotation();

                    PolarVector mv = new PolarVector(ang + Math.PI / 2, current.length < 0.3 ? 0.003f : 0.003f);

                    data.setMovementX(data.getMovementX() + PolarVector.xFromPolar(mv));
                    data.setMovementY(data.getMovementY() + PolarVector.yFromPolar(mv));
                },
                (GameObject gameObject, long tick, World world, MasterKeyManager manager) -> {

                    VehicleData data = (VehicleData) gameObject.getData();
                    PolarVector current = new PolarVector(new CartesianVector(data.getMovementX(),
                            data.getMovementY()));
                    current.updateAngleRange2Pi();
                    double ang = data.getRotation();
                    PolarVector mv = new PolarVector(ang, 0.001f);
                    // pv.angle = data.getRotation();
                    double deltaAng = current.angle - data.getRotation();
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
                    data.setMovementX(PolarVector.xFromPolar(current));
                    data.setMovementY(PolarVector.yFromPolar(current));

                    if (!getDirection(data, current.angle) && current.length > 0) {
                        data.setMovementX(data.getMovementX() + PolarVector.xFromPolar(mv));
                        data.setMovementY(data.getMovementY() + PolarVector.yFromPolar(mv));
                    }

                    TrifkoTest.Currentlabel.setText(decimalFormat.format(current.length));
                    TrifkoTest.Richtunglabel.setText(getDirection(data, current.angle) ? "Vorne" : "Hinten");

                }
        );

        return heli;
    }
    
    public static Drive createAuto() {
        Drive auto = new Drive(
                (GameObject gameObject, long tick, World world, MasterKeyManager manager) -> {
                    VehicleData data = (VehicleData) gameObject.getData();
                    PolarVector current = new PolarVector(new CartesianVector(data.getMovementX(),
                            data.getMovementY()));
                    current.updateAngleRange2Pi();
                    //       System.out.println(data.getRotation()+"------------"+current.angle);

                    if (!data.breaks) {
                        if (!getDirection(data, current.angle) && current.length != 0) {
                            data.setMovementX(Math.abs(data.getMovementX()) > 0.05 ? data.getMovementX() * getBreakStrength(current.length, DriveTypes.AUTO) : 0);
                            data.setMovementY(Math.abs(data.getMovementY()) > 0.05 ? data.getMovementY() * getBreakStrength(current.length, DriveTypes.AUTO) : 0);
                            //Überprüf ob man nach rückwärts fahrt
                            //Wenn ja, dann bremse bis Geschwindigkeit 0.07 und dann setz geschwindigkeit auf 0
                        } else {
                            // System.out.println("CurrentSpeed: " + current.length);
                            double ang = data.getRotation();
                            PolarVector mv = new PolarVector(ang, getAutoAccelerate(current.length));
                            data.setMovementX(current.length < 1.2
                                    ? PolarVector.xFromPolar(mv) + data.getMovementX()
                                    : data.getMovementX());

                            data.setMovementY(current.length < 1.2
                                    ? PolarVector.yFromPolar(mv) + data.getMovementY()
                                    : data.getMovementY());
                        }
                    }

                },
                (GameObject gameObject, long tick, World world, MasterKeyManager manager) -> {
                    VehicleData data = (VehicleData) gameObject.getData();
                    PolarVector current = new PolarVector(new CartesianVector(data.getMovementX(),
                            data.getMovementY()));

                    current.updateAngleRange2Pi();
                    if (!data.breaks) {
                        if (getDirection(data, current.angle) && current.length != 0) {
                            data.setMovementX(Math.abs(data.getMovementX()) > 0.05
                                    ? data.getMovementX() * getBreakStrength(current.length, DriveTypes.AUTO) : 0);
                            data.setMovementY(Math.abs(data.getMovementY()) > 0.05
                                    ? data.getMovementY() * getBreakStrength(current.length, DriveTypes.AUTO) : 0);

                        } else {
                            //    drive.setBreaks(current.length != 0);
                            // System.out.println("CurrentSpeed: " + current.length);
                            double ang = data.getRotation();
                            PolarVector mv = new PolarVector(ang, getAutoAccelerate(current.length));

                            data.setMovementX(current.length < 0.5
                                    ? data.getMovementX() - PolarVector.xFromPolar(mv)
                                    : data.getMovementX());

                            data.setMovementY(current.length < 0.5
                                    ? data.getMovementY() - PolarVector.yFromPolar(mv)
                                    : data.getMovementY());
                        }
                    }
                },
                (GameObject gameObject, long tick, World world, MasterKeyManager manager) -> {
                    VehicleData data = (VehicleData) gameObject.getData();
                    PolarVector current = new PolarVector(new CartesianVector(data.getMovementX(),
                            data.getMovementY()));

                    current.updateAngleRange2Pi();

                    data.setMovementX(Math.abs(data.getMovementX()) > 0.04
                            ? data.getMovementX() * getBreakStrength(current.length, DriveTypes.AUTO) : 0);
                    data.setMovementY(Math.abs(data.getMovementY()) > 0.04
                            ? data.getMovementY() * getBreakStrength(current.length, DriveTypes.AUTO) : 0);

                },
                (GameObject gameObject, long tick, World world, MasterKeyManager manager) -> {
                                       
                    VehicleData data = (VehicleData) gameObject.getData();

                    PolarVector current = new PolarVector(new CartesianVector(data.getMovementX(),
                            data.getMovementY()));

                    current.updateAngleRange2Pi();
                    
                    if (getDirection(data, current.angle) && current.length > 0.1){
                        data.increaseRotation(-0.006);
                    }
                    if (!getDirection(data, current.angle) && current.length > 0.1){
                        data.increaseRotation(+0.006);
                    }
                  /*  
                    if(current.length > 1){
                       data.increaseRotation(-0.004);                       
                    }else if(current.length > 0.8){                        
                       data.increaseRotation(-0.004); 
                    }else if(current.length > 0.5){
                        data.increaseRotation(-0.005);
                    }else if(current.length != 0){
                        data.increaseRotation(-0.005);
                    }
                    // 

                    

                    PolarVector current = new PolarVector(new CartesianVector(data.getMovementX(),
                            data.getMovementY()));
                  //  current.length *= 0.9995;
                    data.setMovementX(PolarVector.xFromPolar(current));
                    data.setMovementY(PolarVector.yFromPolar(current));*/
                },
                (GameObject gameObject, long tick, World world, MasterKeyManager manager) -> {
                    VehicleData data = (VehicleData) gameObject.getData();
                     
                    PolarVector current = new PolarVector(new CartesianVector(data.getMovementX(),
                            data.getMovementY()));

                    current.updateAngleRange2Pi();
                    if (getDirection(data, current.angle) && current.length > 0.1){
                        data.increaseRotation(+0.006);
                    }
                    if (!getDirection(data, current.angle) && current.length > 0.1){
                        data.increaseRotation(-0.006);
                    }
                    
                    /*if(current.length > 1){
                       data.increaseRotation(+0.006);                       
                    }else if(current.length > 0.8){                        
                       data.increaseRotation(+0.006); 
                    }else if(current.length > 0.5){
                        data.increaseRotation(+0.006);
                    }else if(current.length > 0.17){
                        data.increaseRotation(+0.006);
                    }
                        if (data.breaks)
                        return;

                    PolarVector current = new PolarVector(new CartesianVector(data.getMovementX(),
                            data.getMovementY()));
                   // current.length *= 0.9995;
                    data.setMovementX(PolarVector.xFromPolar(current));
                    data.setMovementY(PolarVector.yFromPolar(current)); */
                },
                null,
                null,
                (GameObject gameObject, long tick, World world, MasterKeyManager manager) -> {

                    VehicleData data = (VehicleData) gameObject.getData();
                    PolarVector current = new PolarVector(new CartesianVector(data.getMovementX(),
                            data.getMovementY()));
                    current.updateAngleRange2Pi();
                    double ang = data.getRotation();
                    
                    PolarVector mv = new PolarVector(ang, 0.001f);
                    // pv.angle = data.getRotation();
                    double deltaAng = current.angle - data.getRotation();
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
                    if (Math.abs(deltaAng) > 0.006 && current.length > 1.1) {
                        deltaAng *= 0.005;
                        TrifkoTest.Driftlabel.setText("Drift 1 ");
                    } else if (Math.abs(deltaAng) > 0.08 && current.length > 0.50) {
                        deltaAng *= 0.007;
                        TrifkoTest.Driftlabel.setText("Drift 2 ");
                    } else if (Math.abs(deltaAng) > 0.1) {
                        deltaAng *= 0.003;
                        TrifkoTest.Driftlabel.setText("Drift 3 ");
                    } else {
                        deltaAng *= 0.05;
                        TrifkoTest.Driftlabel.setText("Drift 4 ");
                    } 

                    current.angle -= deltaAng;

                    current.length *= current.length < 0.9 ? 0.996 : 0.998;
                    if(current.length < 0.015 && !data.accelerate && !data.decelerate){
                        current.length = 0;
                    }
                    
                    data.setMovementX(PolarVector.xFromPolar(current));
                    data.setMovementY(PolarVector.yFromPolar(current));

                    if (!getDirection(data, current.angle) && current.length > 0) {
                        data.setMovementX(data.getMovementX() + PolarVector.xFromPolar(mv));
                        data.setMovementY(data.getMovementY() + PolarVector.yFromPolar(mv));
                    }

                    TrifkoTest.Currentlabel.setText(decimalFormat.format(current.length));
                    TrifkoTest.Richtunglabel.setText(getDirection(data, current.angle) ? "Vorne" : "Hinten");

                }
        );

        return auto;
    }
    //#############################################################
    //#############################################################
    //#############################################################
    //#############################################################
    
    public static Drive createBoot() {
        Drive boot = new Drive(
                (GameObject gameObject, long tick, World world, MasterKeyManager manager) -> {
                    VehicleData data = (VehicleData) gameObject.getData();
                    PolarVector current = new PolarVector(new CartesianVector(data.getMovementX(),
                            data.getMovementY()));
                    current.updateAngleRange2Pi();
                    //       System.out.println(data.getRotation()+"------------"+current.angle);

                    
                        if (!getDirection(data, current.angle) && current.length != 0) {
                            data.setMovementX(Math.abs(data.getMovementX()) > 0.05 ? data.getMovementX() * getBreakStrength(current.length, DriveTypes.BOOT) : 0);
                            data.setMovementY(Math.abs(data.getMovementY()) > 0.05 ? data.getMovementY() * getBreakStrength(current.length, DriveTypes.BOOT) : 0);
                            //Überprüf ob man nach rückwärts fahrt
                            //Wenn ja, dann bremse bis Geschwindigkeit 0.07 und dann setz geschwindigkeit auf 0
                        } else {
                            // System.out.println("CurrentSpeed: " + current.length);
                            double ang = data.getRotation();
                            PolarVector mv = new PolarVector(ang, getBootAccelerate(current.length));
                            data.setMovementX(current.length < 1.2
                                    ? PolarVector.xFromPolar(mv) + data.getMovementX()
                                    : data.getMovementX());

                            data.setMovementY(current.length < 1.2
                                    ? PolarVector.yFromPolar(mv) + data.getMovementY()
                                    : data.getMovementY());
                        }                    
                },
                (GameObject gameObject, long tick, World world, MasterKeyManager manager) -> {
                    VehicleData data = (VehicleData) gameObject.getData();
                    PolarVector current = new PolarVector(new CartesianVector(data.getMovementX(),
                            data.getMovementY()));

                    current.updateAngleRange2Pi();
                    if (!data.breaks) {
                        if (getDirection(data, current.angle) && current.length != 0) {
                            data.setMovementX(Math.abs(data.getMovementX()) > 0.05
                                    ? data.getMovementX() * getBreakStrength(current.length, DriveTypes.BOOT) : 0);
                            data.setMovementY(Math.abs(data.getMovementY()) > 0.05
                                    ? data.getMovementY() * getBreakStrength(current.length, DriveTypes.BOOT) : 0);

                        } else {
                            //    drive.setBreaks(current.length != 0);
                            // System.out.println("CurrentSpeed: " + current.length);
                            double ang = data.getRotation();
                            PolarVector mv = new PolarVector(ang, getBootAccelerate(current.length));

                            data.setMovementX(current.length < 0.5
                                    ? data.getMovementX() - PolarVector.xFromPolar(mv)
                                    : data.getMovementX());

                            data.setMovementY(current.length < 0.5
                                    ? data.getMovementY() - PolarVector.yFromPolar(mv)
                                    : data.getMovementY());
                        }
                    }
                },
                null,
                (GameObject gameObject, long tick, World world, MasterKeyManager manager) -> {
                                       
                    VehicleData data = (VehicleData) gameObject.getData();

                    PolarVector current = new PolarVector(new CartesianVector(data.getMovementX(),
                            data.getMovementY()));

                    current.updateAngleRange2Pi();
                    
                    if (getDirection(data, current.angle) && current.length > 0.08){
                        data.increaseRotation(-0.007);
                    }
                    if (!getDirection(data, current.angle) && current.length > 0.08){
                        data.increaseRotation(+0.007);
                    }
                  /*  
                    if(current.length > 1){
                       data.increaseRotation(-0.004);                       
                    }else if(current.length > 0.8){                        
                       data.increaseRotation(-0.004); 
                    }else if(current.length > 0.5){
                        data.increaseRotation(-0.005);
                    }else if(current.length != 0){
                        data.increaseRotation(-0.005);
                    }
                    // 

                    

                    PolarVector current = new PolarVector(new CartesianVector(data.getMovementX(),
                            data.getMovementY()));
                  //  current.length *= 0.9995;
                    data.setMovementX(PolarVector.xFromPolar(current));
                    data.setMovementY(PolarVector.yFromPolar(current));*/
                },
                (GameObject gameObject, long tick, World world, MasterKeyManager manager) -> {
                    VehicleData data = (VehicleData) gameObject.getData();
                     
                    PolarVector current = new PolarVector(new CartesianVector(data.getMovementX(),
                            data.getMovementY()));

                    current.updateAngleRange2Pi();
                    if (getDirection(data, current.angle) && current.length > 0.08){
                        data.increaseRotation(+0.008);
                    }
                    if (!getDirection(data, current.angle) && current.length > 0.08){
                        data.increaseRotation(-0.008);
                    }
                    
                    /*if(current.length > 1){
                       data.increaseRotation(+0.006);                       
                    }else if(current.length > 0.8){                        
                       data.increaseRotation(+0.006); 
                    }else if(current.length > 0.5){
                        data.increaseRotation(+0.006);
                    }else if(current.length > 0.17){
                        data.increaseRotation(+0.006);
                    }
                        if (data.breaks)
                        return;

                    PolarVector current = new PolarVector(new CartesianVector(data.getMovementX(),
                            data.getMovementY()));
                   // current.length *= 0.9995;
                    data.setMovementX(PolarVector.xFromPolar(current));
                    data.setMovementY(PolarVector.yFromPolar(current)); */
                },
                null,
                null,
                (GameObject gameObject, long tick, World world, MasterKeyManager manager) -> {

                    VehicleData data = (VehicleData) gameObject.getData();
                    PolarVector current = new PolarVector(new CartesianVector(data.getMovementX(),
                            data.getMovementY()));
                    current.updateAngleRange2Pi();
                    double ang = data.getRotation();
                    
                    PolarVector mv = new PolarVector(ang, 0.001f);
                    // pv.angle = data.getRotation();
                    double deltaAng = current.angle - data.getRotation();
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
                    if (Math.abs(deltaAng) > 0.006 && current.length > 1.1) {
                        deltaAng *= 0.005;
                        TrifkoTest.Driftlabel.setText("Drift 1 ");
                    } else if (Math.abs(deltaAng) > 0.08 && current.length > 0.50) {
                        deltaAng *= 0.007;
                        TrifkoTest.Driftlabel.setText("Drift 2 ");
                    } else if (Math.abs(deltaAng) > 0.1) {
                        deltaAng *= 0.003;
                        TrifkoTest.Driftlabel.setText("Drift 3 ");
                    } else {
                        deltaAng *= 0.05;
                        TrifkoTest.Driftlabel.setText("Drift 4 ");
                    } 

                    current.angle -= deltaAng;

                    current.length *= current.length < 0.9 ? 0.996 : 0.998;
                    if(current.length < 0.02 && !data.accelerate && !data.decelerate){
                        current.length = 0;
                    }
                    
                    data.setMovementX(PolarVector.xFromPolar(current));
                    data.setMovementY(PolarVector.yFromPolar(current));

                    if (!getDirection(data, current.angle) && current.length > 0) {
                        data.setMovementX(data.getMovementX() + PolarVector.xFromPolar(mv));
                        data.setMovementY(data.getMovementY() + PolarVector.yFromPolar(mv));
                    }

                    TrifkoTest.Currentlabel.setText(decimalFormat.format(current.length));
                    TrifkoTest.Richtunglabel.setText(getDirection(data, current.angle) ? "Vorne" : "Hinten");

                }
        );

        return boot;
    }
    
    //#############################################################
    //#############################################################
    //#############################################################
    //#############################################################
    private static float getTankAccelerate(float x) {
        float a = (float) (-(x * x) * 0.015 + x * 0.012 + 0.005);
        return a >= 0 ? a : 0;
    }

    private static float getHeliAccelerate(float x) {
        float a = (float) (-(x * x) * 0.01502099954 + x * 0.02124146722 + 0.00535303989);
        return a >= 0 ? a : 0;
    }
    
    private static float getBootAccelerate(float x) {
        float a = (float) (-(x * x) * 0.009 + x * 0.01024146722 + 0.003);
        return a >= 0 ? a : 0;
    }
    
    private static float getAutoAccelerate(float x) {
        float a = (float) (-0.006*x*x+0.01*x+0.003);
                            
        return a >= 0 ? a : 0;
    }

    private static float getBreakStrength(float x, DriveTypes type) {
        float str;
        switch (type) {

            case HELICOPTER:
                str = (float) (0.01 * x + 0.98);
                break;
            case AUTO:
                str = (float) (0.019*x*x - 0.009444444444*x + 0.985);
                break;
            case TANK:
                str = (float) (0.01 * x + 0.98);
                break;
            case BOOT:
                str = (float) (0.01 * x + 0.98);
                break;
            case JET:
                str = (float) (0.01 * x + 0.98);
                break;
            default:
                System.err.println("KEIN TYP BEI BREAKSTRENGTH");
                str = 0;
                break;
        }

        return str < 0.999 ? str : 0.999f;

    }

    private static boolean getDirection(GameData d, double winkel) {
        double deltaAng = winkel - d.getRotation();
        //    System.out.println("DElta1  " + deltaAng);
        deltaAng += Math.PI / 2;
        PolarVector pv = new PolarVector(deltaAng, 1);
        pv.updateAngleRange2Pi();
        if (pv.angle <= Math.PI)
            return true; // gerade aus
        else
            return false; // rückwärts

    }
}
