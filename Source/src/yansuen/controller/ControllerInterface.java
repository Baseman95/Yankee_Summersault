package yansuen.controller;

import yansuen.data.DataInterface;
import code.game.World;
import yansuen.key.KeyManager;

/**
 *
 * @author Link162534
 */
public interface ControllerInterface {

    void control(DataInterface data, long tick, World world, KeyManager manager);

}
