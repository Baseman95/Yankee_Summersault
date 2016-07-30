package code.data;

import code.game.tank.Vehicle;
import code.game.tank.projectile.Projectile;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author Link
 */
public class WeaponData extends VehicleData {

    //WeaponData check
    protected Vehicle parent;
    protected float length;
    protected float relativeX;
    protected float relativeY;
    protected float relativeZ;
    protected double relativeRotation;
    protected boolean useParentRotation;
    protected long projectileLoadTicks;
    protected int magazineSize;
    protected long magazineLoadTicks;
    protected boolean autoReload;
    protected float deviationPerSide;

    protected Projectile projectile;

    protected boolean shooting = false;
    protected boolean reloading = false;
    protected long nextShotReadyTick;
    protected int roundsInMagazine;

    public WeaponData(Vehicle parent, float length, BufferedImage image, float imageSizeMultiplier, 
            float relativeX, float relativeY, float relativeZ, double relativeRotation, boolean useParentRotation,
            long projectileLoadTicks, int magazineSize, long magazineLoadTicks, boolean autoReload, float deviationPerSide, Projectile projectile) {
        super(0, 0, 0, 0, imageSizeMultiplier * image.getWidth(), imageSizeMultiplier * image.getHeight(), relativeRotation, image);

        this.parent = parent;
        this.length = length;
        this.relativeX = relativeX;
        this.relativeY = relativeY;
        this.relativeZ = relativeZ;
        this.relativeRotation = relativeRotation;
        this.useParentRotation = useParentRotation;
        this.projectileLoadTicks = projectileLoadTicks;
        this.magazineSize = magazineSize;
        this.magazineLoadTicks = magazineLoadTicks;
        this.autoReload=autoReload;
        this.deviationPerSide = deviationPerSide;
        
        this.projectile = projectile;

        this.roundsInMagazine = magazineSize;

    }

    /*
    public WeaponData(Vehicle parent, long cooldown, float x, float y, float width, float height, double rotation, BufferedImage image) {
        super(parent.getData().getX() + parent.getData().getWidth() / 2 + x - width / 2,
                parent.getData().getY() + parent.getData().getHeight() / 2 + y - height / 2,
                width, height, rotation, image);
        this.parent = parent;
        this.cooldown = cooldown;
    }
     */
 /*
    public WeaponData(Vehicle parent, long cooldown, float x, float y, float width, float height, BufferedImage image) {
        this(parent, cooldown, x, y, width, height, 0, image);
    }
     */
 /*
    public WeaponData(Vehicle parent, long cooldown, float x, float y, BufferedImage image) {
        this(parent, cooldown, x, y, image.getWidth(), image.getHeight(), image);
    }
     */
    public Vehicle getParent() {
        return parent;
    }

    public void setParent(Vehicle parent) {
        this.parent = parent;
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

    public void setReloading(boolean reload) {
        this.reloading = reload;
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

    public float getLength() {
        return length;
    }

    public void setLength(float length) {
        this.length = length;
    }

    public float getRelativeZ() {
        return relativeZ;
    }

    public void setRelativeZ(float relativeZ) {
        this.relativeZ = relativeZ;
    }

    public boolean isUseParentRotation() {
        return useParentRotation;
    }

    public void setUseParentRotation(boolean useParentRotation) {
        this.useParentRotation = useParentRotation;
    }

    public long getProjectileLoadTicks() {
        return projectileLoadTicks;
    }

    public void setProjectileLoadTicks(long projectileLoadTicks) {
        this.projectileLoadTicks = projectileLoadTicks;
    }

    public int getMagazineSize() {
        return magazineSize;
    }

    public void setMagazineSize(int magazineSize) {
        this.magazineSize = magazineSize;
    }

    public long getMagazineLoadTicks() {
        return magazineLoadTicks;
    }

    public void setMagazineLoadTicks(long magazineLoadTicks) {
        this.magazineLoadTicks = magazineLoadTicks;
    }

    public float getDeviationPerSide() {
        return deviationPerSide;
    }

    public void setDeviationPerSide(float deviationPerSide) {
        this.deviationPerSide = deviationPerSide;
    }

    public Projectile getProjectile() {
        return projectile;
    }

    public void setProjectile(Projectile projectile) {
        this.projectile = projectile;
    }

    public long getNextShotReadyTick() {
        return nextShotReadyTick;
    }

    public void setNextShotReadyTick(long nextShotReadyTick) {
        this.nextShotReadyTick = nextShotReadyTick;
    }

    public int getRoundsInMagazine() {
        return roundsInMagazine;
    }

    public void setRoundsInMagazine(int roundsInMagazine) {
        this.roundsInMagazine = roundsInMagazine;
    }

    public boolean hasAutoReload() {
        return autoReload;
    }

    public void setAutoReload(boolean autoReload) {
        this.autoReload = autoReload;
    }   

    @Override
    public String toString() {
        return "WeaponData{" + super.toString() + "vehicle=" + parent + ", shooting=" + shooting + ", reloading=" + reloading + ", cooldown=" + projectileLoadTicks + ", nextShotReadyTick=" + magazineLoadTicks + ", relativeRotation=" + relativeRotation + ", relativeX=" + relativeX + ", relativeY=" + relativeY + '}';
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
