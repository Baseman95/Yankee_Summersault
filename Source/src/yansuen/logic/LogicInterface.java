package yansuen.logic;

import code.game.World;
import yansuen.key.KeyManager;
import yansuen.game.GameObject;

/**
 *
 * @author Link162534
 */
public interface LogicInterface {

    void doLogic(GameObject gameObject, long tick, World world, KeyManager manager);

}
