package yansuen.logic;

import code.game.World;
import yansuen.data.DataInterface;
import yansuen.key.KeyManager;

/**
 *
 * @author Link162534
 */
public interface LogicInterface {

    void doLogic(DataInterface dataInterface, long tick, World world, KeyManager manager);

}
