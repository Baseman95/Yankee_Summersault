package yansuen.data;

/**
 * @author Link
 */
public interface ChassisDataListener extends GameDataListener {
    void onMovementChanged(ChassisData data, float oldMovementX, float oldMovementY);
}
