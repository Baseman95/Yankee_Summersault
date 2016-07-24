package yansuen.data;

/**
 * @author Link
 */
public interface VehicleDataListener extends GameDataListener {
    void onMovementChanged(VehicleData data, float oldMovementX, float oldMovementY);
}
