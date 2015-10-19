package code.game;

import code.controller.ControllerInterface;
import code.graphics.Camera;
import code.graphics.GraphicsInterface;
import code.key.KeyManager;
import code.logic.LogicInterface;
import code.logic.LogicLooper;
import java.awt.Graphics2D;
import java.util.ArrayList;

/**
 *
 * @author Link162534
 */
public class World implements LogicLooper {

    protected ArrayList<GameObject> gameObjects = new ArrayList<>();
    protected KeyManager keyManager;
    protected Camera camera;

    public World(KeyManager keyManager) {
        this.keyManager = keyManager;
    }

    @Override
    public void onLogicLoop(long tick) {
        for (GameObject gameObject : gameObjects) {
            LogicInterface li = gameObject.getLogicInterface();
            if (li != null)
                li.doLogic(gameObject.dataObject, tick);
            ControllerInterface ci = gameObject.getControllerInterface();
            if (ci != null && keyManager != null)
                ci.move(gameObject.dataObject, tick, this, keyManager);
        }
    }

    public void repaint(Graphics2D g2d) {
        for (GameObject gameObject : gameObjects) {
            GraphicsInterface gi = gameObject.getGraphicsInterface();
            if (gi != null)
                gi.render(gameObject.dataObject, camera, g2d);
        }
    }

    public Camera getCamera() {
        return camera;
    }

    public synchronized ArrayList<GameObject> getGameObjects() {
        return gameObjects;
    }

}
