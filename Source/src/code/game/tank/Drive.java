/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package code.game.tank;

import code.game.World;
import yansuen.game.GameObject;
import yansuen.key.MasterKeyManager;
import yansuen.logic.LogicInterface;

/**
 *
 * @author Link
 */
public class Drive implements LogicInterface {

    protected DriveLogicInterface accelerateFunction;
    protected DriveLogicInterface decelerateFunction;
    protected DriveLogicInterface breaksFunction;
    protected DriveLogicInterface turnLeftFunction;
    protected DriveLogicInterface turnRightFunction;
    protected DriveLogicInterface strafeLeftFunction;
    protected DriveLogicInterface strafeRightFunction;
    protected DriveLogicInterface driveLogicInterface;

    protected boolean accelerate = false;
    protected boolean decelerate = false;
    protected boolean breaks = false;
    protected boolean turnLeft = false;
    protected boolean turnRight = false;
    protected boolean strafeLeft = false;
    protected boolean strafeRight = false;

    public Drive(DriveLogicInterface accelerateFunction, DriveLogicInterface decelerateFunction,
            DriveLogicInterface breaksFunction, DriveLogicInterface turnLeftFunction,
            DriveLogicInterface turnRightFunction, DriveLogicInterface strafeLeftFunction,
            DriveLogicInterface strafeRightFunction, DriveLogicInterface logicInterface) {
        this.accelerateFunction = accelerateFunction;
        this.decelerateFunction = decelerateFunction;
        this.breaksFunction = breaksFunction;
        this.turnLeftFunction = turnLeftFunction;
        this.turnRightFunction = turnRightFunction;
        this.strafeLeftFunction = strafeLeftFunction;
        this.strafeRightFunction = strafeRightFunction;
        this.driveLogicInterface = logicInterface;
    }

    @Override
    public void doLogic(GameObject gameObject, long tick, World world, MasterKeyManager manager) {
        if (driveLogicInterface != null)
            driveLogicInterface.doDriveLogic(this, gameObject, tick, world);
        if (accelerateFunction != null && accelerate)
            accelerateFunction.doDriveLogic(this, gameObject, tick, world);
        if (decelerateFunction != null && decelerate)
            decelerateFunction.doDriveLogic(this, gameObject, tick, world);
        if (breaksFunction != null && breaks)
            breaksFunction.doDriveLogic(this, gameObject, tick, world);
        if (turnLeftFunction != null && turnLeft)
            turnLeftFunction.doDriveLogic(this, gameObject, tick, world);
        if (turnRightFunction != null && turnRight)
            turnRightFunction.doDriveLogic(this, gameObject, tick, world);
        if (strafeLeftFunction != null && strafeLeft)
            strafeLeftFunction.doDriveLogic(this, gameObject, tick, world);
        if (strafeRightFunction != null && strafeRight)
            strafeRightFunction.doDriveLogic(this, gameObject, tick, world);
    }

    public DriveLogicInterface getDriveLogicInterface() {
        return driveLogicInterface;
    }

    public void setDriveLogicInterface(DriveLogicInterface driveLogicInterface) {
        this.driveLogicInterface = driveLogicInterface;
    }

    public DriveLogicInterface getAccelerateFunction() {
        return accelerateFunction;
    }

    public void setAccelerateFunction(DriveLogicInterface accelerateFunction) {
        this.accelerateFunction = accelerateFunction;
    }

    public DriveLogicInterface getDecelerateFunction() {
        return decelerateFunction;
    }

    public void setDecelerateFunction(DriveLogicInterface deccelerateFunction) {
        this.decelerateFunction = deccelerateFunction;
    }

    public DriveLogicInterface getBreaksFunction() {
        return breaksFunction;
    }

    public void setBreaksFunction(DriveLogicInterface breaksFunction) {
        this.breaksFunction = breaksFunction;
    }

    public DriveLogicInterface getTurnLeftFunction() {
        return turnLeftFunction;
    }

    public void setTurnLeftFunction(DriveLogicInterface turnLeftFunction) {
        this.turnLeftFunction = turnLeftFunction;
    }

    public DriveLogicInterface getTurnRightFunction() {
        return turnRightFunction;
    }

    public void setTurnRightFunction(DriveLogicInterface turnRightFunction) {
        this.turnRightFunction = turnRightFunction;
    }

    public DriveLogicInterface getStrafeLeftFunction() {
        return strafeLeftFunction;
    }

    public void setStrafeLeftFunction(DriveLogicInterface strafeLeftFunction) {
        this.strafeLeftFunction = strafeLeftFunction;
    }

    public DriveLogicInterface getStrafeRightFunction() {
        return strafeRightFunction;
    }

    public void setStrafeRightFunction(DriveLogicInterface strafeRightFunction) {
        this.strafeRightFunction = strafeRightFunction;
    }

    public boolean isAccelerate() {
        return accelerate;
    }

    public void setAccelerate(boolean accelerate) {
        this.accelerate = accelerate;
    }

    public boolean isDecelerate() {
        return decelerate;
    }

    public void setDecelerate(boolean deccelerate) {
        this.decelerate = deccelerate;
    }

    public boolean isBreaks() {
        return breaks;
    }

    public void setBreaks(boolean breaks) {
        this.breaks = breaks;
    }

    public boolean isTurnLeft() {
        return turnLeft;
    }

    public void setTurnLeft(boolean turnLeft) {
        this.turnLeft = turnLeft;
    }

    public boolean isTurnRight() {
        return turnRight;
    }

    public void setTurnRight(boolean turnRight) {
        this.turnRight = turnRight;
    }

    public boolean isStrafeLeft() {
        return strafeLeft;
    }

    public void setStrafeLeft(boolean strafeLeft) {
        this.strafeLeft = strafeLeft;
    }

    public boolean isStrafeRight() {
        return strafeRight;
    }

    public void setStrafeRight(boolean strafeRight) {
        this.strafeRight = strafeRight;
    }

    //<editor-fold defaultstate="collapsed" desc="NetworkSerializeStuff">
    /* @Override
    public String[] networkSerialize() {
    return new String[]{
    String.valueOf(accelerate),
    String.valueOf(decelerate),
    String.valueOf(breaks),
    String.valueOf(turnLeft),
    String.valueOf(turnRight),
    String.valueOf(strafeLeft),
    String.valueOf(strafeRight)};
    }
    
    @Override
    public void networkDeserialize(String[] args) {
    accelerate = Boolean.valueOf(args[0]);
    decelerate = Boolean.valueOf(args[1]);
    breaks = Boolean.valueOf(args[2]);
    turnLeft = Boolean.valueOf(args[3]);
    turnRight = Boolean.valueOf(args[4]);
    strafeLeft = Boolean.valueOf(args[5]);
    strafeRight = Boolean.valueOf(args[6]);
    }*/
//</editor-fold>
}
