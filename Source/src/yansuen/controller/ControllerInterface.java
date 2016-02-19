package yansuen.controller;

import yansuen.game.GameObject;
import code.game.World;
import yansuen.key.MasterKeyManager;

/**
 *
 * @author Link162534
 */
public interface ControllerInterface {

    void control(GameObject gameObject, long tick, World world, MasterKeyManager manager);

}
