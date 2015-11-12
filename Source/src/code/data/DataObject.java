package code.data;

import yansuen.data.DataInterface;

/**
 *
 * @author Link162534
 */
public class DataObject implements DataInterface {

    protected Object[] dataList = new Object[3];

    protected PositionData positionData;
    protected ImageData imageData;
    protected MovementData movementData;

    public DataObject(PositionData positionData, ImageData imageData, MovementData movementData) {
        this.positionData = positionData;
        this.imageData = imageData;
        this.movementData = movementData;
        setPositionData(positionData);
        setImageData(imageData);
        setMovementData(movementData);
    }

    public final void setPositionData(PositionData positionData) {
        this.positionData = positionData;
        dataList[0] = positionData;
    }

    public final void setImageData(ImageData imageData) {
        this.imageData = imageData;
        dataList[1] = imageData;
    }

    public final void setMovementData(MovementData movementData) {
        this.movementData = movementData;
        dataList[2] = movementData;
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
    public Object getData(int i) {
        if (i < 0 || i >= dataList.length)
            return null;
        return dataList[i];
    }

    public Object getData(Class c) {
        return getData(getDataClassInt(c));
    }

    public int getDataClassInt(Class c) {
        for (int i = 0; i < dataList.length; i++) {
            if (dataList[i].getClass() == c)
                return i;
        }
        return -1;
    }
}
