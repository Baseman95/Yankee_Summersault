/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yansuen.data;

import yansuen.data.DataContainer;
import yansuen.data.ImageData;
import yansuen.data.PositionData;
import java.util.Arrays;

/**
 *
 * @author Link
 */
public class MovableDataContainer extends DataContainer {

    public MovementData movementData;

    public MovableDataContainer(PositionData positionData, ImageData imageData, MovementData movementData) {
        super(positionData, imageData);
        this.movementData = movementData;
    }

    @Override
    protected void setListening(boolean on) {
        super.setListening(on);
        if (on)
            movementData.parent = this;
        else
            movementData.parent = null;
    }

    public final MovementData getMovementData() {
        return movementData;
    }

    @Override
    public String[] networkSerialize() {
        String[] ps = positionData.networkSerialize();
        String[] ms = movementData.networkSerialize();
        String[] is = imageData.networkSerialize();
        String[] args = new String[ps.length + ms.length + is.length];
        System.arraycopy(ps, 0, args, 0, ps.length);
        System.arraycopy(ms, 0, args, ps.length, ms.length);
        System.arraycopy(is, 0, args, ps.length + ms.length, is.length);
        return args;
    }

    @Override
    public void networkDeserialize(String[] args) {
        positionData.networkDeserialize(args);
        movementData.networkDeserialize(Arrays.copyOfRange(args, positionData.networkSerializeArgumentCount(), args.length));
        imageData.networkDeserialize(Arrays.copyOfRange(args, positionData.networkSerializeArgumentCount()
                                                              + movementData.networkSerializeArgumentCount(), args.length));
    }

    @Override
    public int networkSerializeArgumentCount() {
        return positionData.networkSerializeArgumentCount()
               + movementData.networkSerializeArgumentCount()
               + imageData.networkSerializeArgumentCount();
    }

    @Override
    public String toString() {
        return "DataObject[" + positionData + ", " + movementData + ", " + imageData + ']';
    }

}
