package code.data;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import yansuen.data.Data;

/**
 *
 * @author Link162534
 */
public class DataObject extends Data implements DataObjectListener {

    protected PositionData positionData;
    protected ImageData imageData;
    protected MovementData movementData;
    protected ArrayList<DataObjectListener> listenerList = new ArrayList<>();

    public DataObject(PositionData positionData, ImageData imageData, MovementData movementData) {
        this.positionData = positionData;
        this.imageData = imageData;
        this.movementData = movementData;
    }

    @Override
    public void onPositionChanged(DataObject data, float xOld, float yOld) {
        for (DataObjectListener dol : listenerList) {
            dol.onPositionChanged(data, xOld, yOld);
        }
    }

    @Override
    public void onWidthChanged(DataObject data, float wOld, float hOld) {
        for (DataObjectListener dol : listenerList) {
            dol.onWidthChanged(data, wOld, hOld);
        }
    }

    @Override
    public void onRotationChanged(DataObject data, double rOld) {
        for (DataObjectListener dol : listenerList) {
            dol.onRotationChanged(data, rOld);
        }
    }

    @Override
    public void onMovementChanged(DataObject data, float xOld, float yOld) {
        for (DataObjectListener dol : listenerList) {
            dol.onMovementChanged(data, xOld, yOld);
        }
    }

    @Override
    public void onImageChanged(DataObject data, BufferedImage imgOld) {
        for (DataObjectListener dol : listenerList) {
            dol.onImageChanged(data, imgOld);
        }
    }

    public ArrayList<DataObjectListener> getListenerList() {
        return listenerList;
    }

    public boolean addDataObjectListener(DataObjectListener listener) {
        if (listenerList.isEmpty())
            setListening(true);
        boolean r = listenerList.add(listener);
        return r;
    }

    public boolean removeDataObjectListener(DataObjectListener listener) {
        boolean r = listenerList.remove(listener);
        if (listenerList.isEmpty())
            setListening(false);
        return r;
    }

    protected void setListening(boolean on) {
        if (on) {
            positionData.parent = this;
            movementData.parent = this;
            imageData.parent = this;
        } else {
            positionData.parent = null;
            movementData.parent = null;
            imageData.parent = null;
        }
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
