package code.game.tank;

import yansuen.data.GameData;
import code.game.World;
import code.game.tank.projectile.ImpactInterface;
import java.awt.image.BufferedImage;
import yansuen.game.GameObject;
import yansuen.graphics.GraphicsInterface;
import yansuen.key.MasterKeyManager;
import yansuen.logic.LogicInterface;
import code.game.tank.projectile.ShotInterface;
import java.util.ArrayList;
import java.util.Arrays;
import yansuen.network.NetworkSerializable;

/**
 * @author Link
 */
public class Weapon extends GameObject implements NetworkSerializable {

    protected Vehicle vehicle;
    protected ShotInterface shotFunction;
    protected LogicInterface reloadFunction;
    protected ImpactInterface impactBehavior;
    protected LogicInterface projectileBehavior;

    protected boolean shot = false;
    protected boolean reload = false;
    protected long cooldown;
    protected long shotReady = 0;
    protected double relativeRotation = 0;
    protected float xRelative = 0;
    protected float yRelative = 0;

    public Weapon(Vehicle vehicle, long cooldown, ShotInterface shotFunction, LogicInterface reloadFunction,
            ImpactInterface impactBehavior, LogicInterface projectileBehavior, float x, float y, float w, float h,
            BufferedImage img, GraphicsInterface graphicsInterface, LogicInterface controllerInterface) {
        super(((GameData) vehicle.getData()).getX() + ((GameData) vehicle.getData()).getWidth() / 2
              + x - w / 2,
              ((GameData) vehicle.getData()).getY() + ((GameData) vehicle.getData()).getHeight() / 2
              + y - h / 2, w, h, img, graphicsInterface, controllerInterface);
        this.vehicle = vehicle;
        this.cooldown = cooldown;
        this.shotFunction = shotFunction;
        this.reloadFunction = reloadFunction;
        this.impactBehavior = impactBehavior;
        this.projectileBehavior = projectileBehavior;
    }

    public Weapon(Vehicle vehicle, long cooldown, ShotInterface shootFunction, LogicInterface reloadFunction,
            ImpactInterface impactBehavior, LogicInterface projectileBehavior, float x, float y,
            BufferedImage img, GraphicsInterface graphicsInterface, LogicInterface controllerInterface) {
        super(((GameData) vehicle.getData()).getX() + ((GameData) vehicle.getData()).getWidth() / 2
              + x - img.getWidth() / 2,
              ((GameData) vehicle.getData()).getY() + ((GameData) vehicle.getData()).getHeight() / 2
              + y - img.getHeight() / 2, img, graphicsInterface, controllerInterface);
        this.vehicle = vehicle;
        this.cooldown = cooldown;
        this.shotFunction = shootFunction;
        this.reloadFunction = reloadFunction;
        this.impactBehavior = impactBehavior;
        this.projectileBehavior = projectileBehavior;
    }

    @Override
    public void doLogic(GameObject gameObject, long tick, World world, MasterKeyManager manager) {
        super.doLogic(gameObject, tick, world, manager);
        if (shotFunction != null && shot && shotReady < tick) {
            shotFunction.onShotCreation(this, tick, impactBehavior, world);
            shotReady = tick + cooldown;
        }
        if (reloadFunction != null && reload)
            reloadFunction.doLogic(gameObject, tick, world, manager);
    }

    public double getRelativeRotation() {
        return relativeRotation;
    }

    public void setRelativeRotation(double relativeRotation) {
        this.relativeRotation = relativeRotation;
    }

    public float getRelativeX() {
        return xRelative;
    }

    public void setxRelative(float xRelative) {
        this.xRelative = xRelative;
    }

    public float getRelativeY() {
        return yRelative;
    }

    public void setyRelativeY(float yRelativeY) {
        this.yRelative = yRelativeY;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public long getCooldown() {
        return cooldown;
    }

    public void setCooldown(long cooldown) {
        this.cooldown = cooldown;
    }

    public ShotInterface getShotFunction() {
        return shotFunction;
    }

    public void setShotFunction(ShotInterface shotFunction) {
        this.shotFunction = shotFunction;
    }

    public LogicInterface getReloadFunction() {
        return reloadFunction;
    }

    public void setReloadFunction(LogicInterface reloadFunction) {
        this.reloadFunction = reloadFunction;
    }

    public ImpactInterface getImpactBehavior() {
        return impactBehavior;
    }

    public void setImpactBehavior(ImpactInterface impactBehavior) {
        this.impactBehavior = impactBehavior;
    }

    public LogicInterface getProjectileBehavior() {
        return projectileBehavior;
    }

    public void setProjectileBehavior(LogicInterface projectileBehavior) {
        this.projectileBehavior = projectileBehavior;
    }

    public boolean isShoot() {
        return shot;
    }

    public void setShoot(boolean shoot) {
        this.shot = shoot;
    }

    public boolean isReload() {
        return reload;
    }

    public void setReload(boolean reload) {
        this.reload = reload;
    }

    @Override
    public String[] networkSerialize() {
        ArrayList<String> args = new ArrayList<>();
        args.addAll(Arrays.asList(super.networkSerialize()));
        args.add(String.valueOf(relativeRotation));
        args.add(String.valueOf(xRelative));
        args.add(String.valueOf(yRelative));
        return args.toArray(new String[0]);
    }

    @Override
    public void networkDeserialize(String[] args) {
        super.networkDeserialize(args);
        int i = args.length;
        yRelative = Float.valueOf(args[--i]);
        xRelative = Float.valueOf(args[--i]);
        relativeRotation = Double.valueOf(args[--i]);
    }

    @Override
    public int networkSerializeArgumentCount() {
        return 3 + super.networkSerializeArgumentCount();
    }
}
