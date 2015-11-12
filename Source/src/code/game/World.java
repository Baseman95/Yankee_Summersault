package code.game;

import code.data.DataObject;
import code.data.MovementData;
import code.data.PositionData;
import yansuen.controller.ControllerInterface;
import yansuen.graphics.Camera;
import yansuen.graphics.GraphicsInterface;
import yansuen.key.KeyManager;
import yansuen.logic.LogicInterface;
import yansuen.logic.LogicLooper;
import java.awt.Graphics2D;
import java.util.ArrayList;

/**
 *
 * @author Link162534
 */
public class World implements LogicLooper {

    protected ArrayList<GameObject> gameObjects = new ArrayList<>();
    protected ArrayList<GameObject> addObjects = new ArrayList<>();
    protected ArrayList<GameObject> removeObjects = new ArrayList<>();

    protected KeyManager keyManager;
    protected Camera camera;

    public World(KeyManager keyManager) {
        this.keyManager = keyManager;
    }

    @Override
    public synchronized void onLogicLoop(long tick) {
        for (GameObject gameObject : gameObjects) {
            LogicInterface li = gameObject.getLogicInterface();
            if (li != null)
                li.doLogic(gameObject.dataObject, tick, this, keyManager);
            ControllerInterface ci = gameObject.getControllerInterface();
            if (ci != null && keyManager != null)
                ci.control(gameObject.dataObject, tick, this, keyManager);
            moveGameObject(gameObject);
        }
        gameObjects.addAll(addObjects);
        addObjects.clear();
        gameObjects.removeAll(removeObjects);
        removeObjects.clear();
    }

    protected void moveGameObject(GameObject gameObject) {
        if (gameObject.getDataObject() instanceof DataObject) {
            PositionData pos = ((DataObject) gameObject.getDataObject()).getPositionData();
            MovementData move = ((DataObject) gameObject.getDataObject()).getMovementData();
            if (pos != null && move != null) {
                pos.increaseX(move.getMovementX());
                pos.increaseY(move.getMovementY());
            }
        }
    }

    public synchronized void repaint(Graphics2D g2d) {
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

    public void addGameObject(GameObject gameObject) {
        addObjects.add(gameObject);
    }

    public void removeGameObject(GameObject gameObject) {
        removeObjects.add(gameObject);
    }

}
