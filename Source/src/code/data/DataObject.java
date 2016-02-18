package code.data;

import yansuen.data.Data;

/**
 *
 * @author Link162534
 */
public class DataObject extends Data {

    protected PositionData positionData;
    protected ImageData imageData;
    protected MovementData movementData;

    public DataObject(PositionData positionData, ImageData imageData, MovementData movementData) {
        this.positionData = positionData;
        this.imageData = imageData;
        this.movementData = movementData;
    }

    public final PositionData getPositionData() {
        return positionData;
    }

    public final ImageData getImageData() {
        return imageData;
    }

    public final MovementData getMovementData() {
        return movementData;
    }

    @Override
    public String toString() {
        return "DataObject[" + positionData + ", " + movementData + ", " + imageData + ']';
    }

}
