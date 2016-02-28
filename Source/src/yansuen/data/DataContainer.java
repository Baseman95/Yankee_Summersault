package yansuen.data;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import yansuen.network.NetworkSerializable;

/**
 *
 * @author Link162534
 */
public class DataContainer implements DataListener, NetworkSerializable {

    protected PositionData positionData;
    protected ImageData imageData;
    protected ArrayList<DataListener> listenerList = new ArrayList<>();

    public DataContainer(PositionData positionData, ImageData imageData) {
        this.positionData = positionData;
        this.imageData = imageData;
    }

    @Override
    public void onPositionChanged(DataContainer data, float xOld, float yOld) {
        for (DataListener dol : listenerList) {
            dol.onPositionChanged(data, xOld, yOld);
        }
    }

    @Override
    public void onWidthChanged(DataContainer data, float wOld, float hOld) {
        for (DataListener dol : listenerList) {
            dol.onWidthChanged(data, wOld, hOld);
        }
    }

    @Override
    public void onRotationChanged(DataContainer data, double rOld) {
        for (DataListener dol : listenerList) {
            dol.onRotationChanged(data, rOld);
        }
    }

    @Override
    public void onMovementChanged(DataContainer data, float xOld, float yOld) {
        for (DataListener dol : listenerList) {
            dol.onMovementChanged(data, xOld, yOld);
        }
    }

    @Override
    public void onImageChanged(DataContainer data, BufferedImage imgOld) {
        for (DataListener dol : listenerList) {
            dol.onImageChanged(data, imgOld);
        }
    }

    public ArrayList<DataListener> getListenerList() {
        return listenerList;
    }

    public boolean addDataObjectListener(DataListener listener) {
        if (listenerList.isEmpty())
            setListening(true);
        boolean r = listenerList.add(listener);
        return r;
    }

    public boolean removeDataObjectListener(DataListener listener) {
        boolean r = listenerList.remove(listener);
        if (listenerList.isEmpty())
            setListening(false);
        return r;
    }

    protected void setListening(boolean on) {
        if (on) {
            positionData.parent = this;
            imageData.parent = this;
        } else {
            positionData.parent = null;
            imageData.parent = null;
        }
    }

    public final PositionData getPositionData() {
        return positionData;
    }

    public final ImageData getImageData() {
        return imageData;
    }

    @Override
    public String toString() {
        return "DataObject[" + positionData + ", " + imageData + ']';
    }

    @Override
    public String[] networkSerialize() {
        String[] ps = positionData.networkSerialize();
        String[] is = imageData.networkSerialize();
        String[] args = new String[ps.length + is.length];
        System.arraycopy(ps, 0, args, 0, ps.length);
        System.arraycopy(is, 0, args, ps.length, is.length);
        return args;
    }

    @Override
    public void networkDeserialize(String[] args) {
        positionData.networkDeserialize(args);
        imageData.networkDeserialize(Arrays.copyOfRange(args, positionData.networkSerializeArgumentCount(), args.length));
    }

    @Override
    public int networkSerializeArgumentCount() {
        return positionData.networkSerializeArgumentCount()
               + imageData.networkSerializeArgumentCount();
    }

}
