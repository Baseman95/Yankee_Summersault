package code.data;

import code.game.tank.Vehicle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import yansuen.data.GameData;

/**
 * @author Link
 */
public class WeaponData extends GameData {

    protected Vehicle vehicle;

    protected boolean shooting = false;
    protected boolean reloading = false;
    protected long cooldown;
    protected long nextShotReadyTick = 0;
    protected double relativeRotation = 0;
    protected float relativeX = 0;
    protected float relativeY = 0;

    public WeaponData(Vehicle vehicle, long cooldown, float x, float y, float width, float height, double rotation, BufferedImage image) {
        super(vehicle.getData().getX() + vehicle.getData().getWidth() / 2 + x - width / 2,
              vehicle.getData().getY() + vehicle.getData().getHeight() / 2 + y - height / 2,
              width, height, rotation, image);
        this.vehicle = vehicle;
        this.cooldown = cooldown;
    }

    public WeaponData(Vehicle vehicle, long cooldown, float x, float y, float width, float height, BufferedImage image) {
        this(vehicle, cooldown, x, y, width, height, 0, image);
    }

    public WeaponData(Vehicle vehicle, long cooldown, float x, float y, BufferedImage image) {
        this(vehicle, cooldown, x, y, 0, 0, image);
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public boolean isShooting() {
        return shooting;
    }

    public void setShooting(boolean shooting) {
        this.shooting = shooting;
    }

    public boolean isReloading() {
        return reloading;
    }

    public void setReload(boolean reload) {
        this.reloading = reload;
    }

    public long getCooldown() {
        return cooldown;
    }

    public void setCooldown(long cooldown) {
        this.cooldown = cooldown;
    }

    public long getNextShotReadyTick() {
        return nextShotReadyTick;
    }

    public void setNextShotReadyTick(long nextShotReadyTick) {
        this.nextShotReadyTick = nextShotReadyTick;
    }

    public double getRelativeRotation() {
        return relativeRotation;
    }

    public void setRelativeRotation(double relativeRotation) {
        this.relativeRotation = relativeRotation;
    }

    public float getRelativeX() {
        return relativeX;
    }

    public void setRelativeX(float relativeX) {
        this.relativeX = relativeX;
    }

    public float getRelativeY() {
        return relativeY;
    }

    public void setRelativeY(float relativeY) {
        this.relativeY = relativeY;
    }

    @Override
    public String[] networkSerialize() {
        ArrayList<String> args = new ArrayList<>();
        args.addAll(Arrays.asList(super.networkSerialize()));
        args.add(String.valueOf(relativeRotation));
        args.add(String.valueOf(relativeX));
        args.add(String.valueOf(relativeY));
        return args.toArray(new String[0]);
    }

    @Override
    public void networkDeserialize(String[] args) {
        super.networkDeserialize(args);
        int i = args.length;
        relativeY = Float.valueOf(args[--i]);
        relativeX = Float.valueOf(args[--i]);
        relativeRotation = Double.valueOf(args[--i]);
    }

    @Override
    public int networkSerializeArgumentCount() {
        return 3 + super.networkSerializeArgumentCount();
    }

}
