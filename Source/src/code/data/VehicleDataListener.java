package code.data;

import yansuen.data.GameDataListener;

/**
 * @author Link
 */
public interface VehicleDataListener extends GameDataListener {
    void onMovementChanged(VehicleData data, float oldMovementX, float oldMovementY);
}
