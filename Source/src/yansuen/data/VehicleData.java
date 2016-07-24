package yansuen.data;

import java.awt.image.BufferedImage;
import java.util.Arrays;
import yansuen.network.NetworkSerializable;

/**
 * @author Link
 */
public class VehicleData extends GameData {

    protected float movementX;
    protected float movementY;
    
    public boolean accelerate = false;
    public boolean decelerate = false;
    public boolean breaks = false;
    public boolean turnLeft = false;
    public boolean turnRight = false;
    public boolean strafeLeft = false;
    public boolean strafeRight = false;

    public VehicleData(float movementX, float movementY, float x, float y, float width, float height, double rotation, BufferedImage image) {
        super(x, y, width, height, rotation, image);
        this.movementX = movementX;
        this.movementY = movementY;
    }

    public VehicleData(float x, float y, float width, float height, double rotation, BufferedImage image) {
        this(0, 0, x, y, width, height, rotation, image);
    }

    public VehicleData(float x, float y, float width, float height, BufferedImage image) {
        this(x, y, width, height, 0, image);
    }

    public void setMovementX(float movementX) {
        float old = movementX;
        this.movementX = movementX;

        if (!listening)
            return;
        listenerList.stream().forEach((dol) -> {
            if (dol instanceof VehicleDataListener)
                ((VehicleDataListener) (dol)).onMovementChanged(this, old, this.movementY);
        });
    }

    public void setMovementY(float movementY) {
        float old = movementY;
        this.movementY = movementY;

        if (!listening)
            return;
        listenerList.stream().forEach((dol) -> {
            if (dol instanceof VehicleDataListener)
                ((VehicleDataListener) (dol)).onMovementChanged(this, this.movementX, old);
        });
    }

    public void increaseMovementX(float movementX) {
        setMovementX(this.movementX + movementX);
    }

    public void increaseMovementY(float movementY) {
        setMovementX(this.movementY + movementY);
    }
    
    

    public float getMovementX() {
        return movementX;
    }

    public float getMovementY() {
        return movementY;
    }

    @Override
    public String[] networkSerialize() {
        String[] gd = super.networkSerialize();
        String[] cd = NetworkSerializable.generateArguments(movementX, movementY);
        String[] args = new String[gd.length + cd.length];
        System.arraycopy(gd, 0, args, 0, gd.length);
        System.arraycopy(cd, 0, args, gd.length, cd.length);
        return args;
    }

    @Override
    public void networkDeserialize(String[] args) {
        super.networkDeserialize(args);
        this.movementX = Float.valueOf(args[super.networkSerializeArgumentCount() + 0]);
        this.movementY = Float.valueOf(args[super.networkSerializeArgumentCount() + 1]);
    }

    @Override
    public int networkSerializeArgumentCount() {
        return super.networkSerializeArgumentCount() + 2;
    }

    @Override
    public String toString() {
        return "VehicleData{" + x + "x, " + y + "y, " + movementX + "mx, " + movementY + "my, " + width + "w, " + height + "h, " + rotation + "r, " + '}';
    }
}
