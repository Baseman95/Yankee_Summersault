package code.controller;

import code.data.DataInterface;
import code.game.World;
import code.key.KeyManager;

/**
 *
 * @author Link162534
 */
public interface ControllerInterface {

    void move(DataInterface data, long tick, World world, KeyManager manager);

}
