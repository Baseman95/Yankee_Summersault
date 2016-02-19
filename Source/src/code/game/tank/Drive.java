/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package code.game.tank;

import code.game.World;
import yansuen.game.GameObject;
import yansuen.key.KeyManager;
import yansuen.logic.LogicInterface;

/**
 *
 * @author Link
 */
public class Drive implements LogicInterface {

    protected LogicInterface accelerateFunction;
    protected LogicInterface decelerateFunction;
    protected LogicInterface breaksFunction;
    protected LogicInterface turnLeftFunction;
    protected LogicInterface turnRightFunction;
    protected LogicInterface strafeLeftFunction;
    protected LogicInterface strafeRightFunction;
    protected LogicInterface logicInterface;

    protected boolean accelerate = false;
    protected boolean decelerate = false;
    protected boolean breaks = false;
    protected boolean turnLeft = false;
    protected boolean turnRight = false;
    protected boolean strafeLeft = false;
    protected boolean strafeRight = false;

    public Drive(LogicInterface accelerateFunction, LogicInterface decelerateFunction,
            LogicInterface breaksFunction, LogicInterface turnLeftFunction,
            LogicInterface turnRightFunction, LogicInterface strafeLeftFunction,
            LogicInterface strafeRightFunction, LogicInterface logicInterface) {
        this.accelerateFunction = accelerateFunction;
        this.decelerateFunction = decelerateFunction;
        this.breaksFunction = breaksFunction;
        this.turnLeftFunction = turnLeftFunction;
        this.turnRightFunction = turnRightFunction;
        this.strafeLeftFunction = strafeLeftFunction;
        this.strafeRightFunction = strafeRightFunction;
        this.logicInterface = logicInterface;
    }

    @Override
    public void doLogic(GameObject gameObject, long tick, World world, KeyManager manager) {
        if (accelerateFunction != null && accelerate)
            accelerateFunction.doLogic(gameObject, tick, world, manager);
        if (decelerateFunction != null && decelerate)
            decelerateFunction.doLogic(gameObject, tick, world, manager);
        if (strafeLeftFunction != null && strafeLeft)
            strafeLeftFunction.doLogic(gameObject, tick, world, manager);
        if (strafeRightFunction != null && strafeRight)
            strafeRightFunction.doLogic(gameObject, tick, world, manager);
        if (turnLeftFunction != null && turnLeft)
            turnLeftFunction.doLogic(gameObject, tick, world, manager);
        if (turnRightFunction != null && turnRight)
            turnRightFunction.doLogic(gameObject, tick, world, manager);
        if (logicInterface != null)
            logicInterface.doLogic(gameObject, tick, world, manager);
        if (breaksFunction != null && breaks)
            breaksFunction.doLogic(gameObject, tick, world, manager);
    }

    public LogicInterface getLogicInterface() {
        return logicInterface;
    }

    public void setLogicInterface(LogicInterface logicInterface) {
        this.logicInterface = logicInterface;
    }

    public LogicInterface getAccelerateFunction() {
        return accelerateFunction;
    }

    public void setAccelerateFunction(LogicInterface accelerateFunction) {
        this.accelerateFunction = accelerateFunction;
    }

    public LogicInterface getDecelerateFunction() {
        return decelerateFunction;
    }

    public void setDecelerateFunction(LogicInterface deccelerateFunction) {
        this.decelerateFunction = deccelerateFunction;
    }

    public LogicInterface getBreaksFunction() {
        return breaksFunction;
    }

    public void setBreaksFunction(LogicInterface breaksFunction) {
        this.breaksFunction = breaksFunction;
    }

    public LogicInterface getTurnLeftFunction() {
        return turnLeftFunction;
    }

    public void setTurnLeftFunction(LogicInterface turnLeftFunction) {
        this.turnLeftFunction = turnLeftFunction;
    }

    public LogicInterface getTurnRightFunction() {
        return turnRightFunction;
    }

    public void setTurnRightFunction(LogicInterface turnRightFunction) {
        this.turnRightFunction = turnRightFunction;
    }

    public LogicInterface getStrafeLeftFunction() {
        return strafeLeftFunction;
    }

    public void setStrafeLeftFunction(LogicInterface strafeLeftFunction) {
        this.strafeLeftFunction = strafeLeftFunction;
    }

    public LogicInterface getStrafeRightFunction() {
        return strafeRightFunction;
    }

    public void setStrafeRightFunction(LogicInterface strafeRightFunction) {
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

}
