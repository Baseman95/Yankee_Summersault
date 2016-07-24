package code.game.tank;

import code.game.World;
import yansuen.data.ChassisData;
import yansuen.game.GameObject;
import yansuen.key.MasterKeyManager;
import yansuen.logic.LogicInterface;

/**
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
    protected LogicInterface driveLogicInterface;

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
        this.driveLogicInterface = logicInterface;
    }

    @Override
    public void doLogic(GameObject gameObject, long tick, World world, MasterKeyManager manager) {
        if (!(gameObject instanceof Chassis))
            return;

        ChassisData data = ((ChassisData) gameObject.getData());
        if (driveLogicInterface != null)
            driveLogicInterface.doLogic(gameObject, tick, world, manager);
        if (accelerateFunction != null && data.accelerate)
            accelerateFunction.doLogic(gameObject, tick, world, manager);
        if (decelerateFunction != null && data.decelerate)
            decelerateFunction.doLogic(gameObject, tick, world, manager);
        if (breaksFunction != null && data.breaks)
            breaksFunction.doLogic(gameObject, tick, world, manager);
        if (turnLeftFunction != null && data.turnLeft)
            turnLeftFunction.doLogic(gameObject, tick, world, manager);
        if (turnRightFunction != null && data.turnRight)
            turnRightFunction.doLogic(gameObject, tick, world, manager);
        if (strafeLeftFunction != null && data.strafeLeft)
            strafeLeftFunction.doLogic(gameObject, tick, world, manager);
        if (strafeRightFunction != null && data.strafeRight)
            strafeRightFunction.doLogic(gameObject, tick, world, manager);
    }

    public LogicInterface getLogicInterface() {
        return driveLogicInterface;
    }

    public void setLogicInterface(LogicInterface driveLogicInterface) {
        this.driveLogicInterface = driveLogicInterface;
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
