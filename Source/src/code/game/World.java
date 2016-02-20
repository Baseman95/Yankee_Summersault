package code.game;

import yansuen.game.GameObject;
import code.data.DataObject;
import code.data.MovementData;
import code.data.PositionData;
import code.game.tank.Chassis;
import code.network.CommandList;
import code.network.UpdateObjectCommand;
import yansuen.controller.ControllerInterface;
import yansuen.graphics.Camera;
import yansuen.graphics.GraphicsInterface;
import yansuen.key.MasterKeyManager;
import yansuen.logic.LogicLooper;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Arrays;
import yansuen.network.Network;

/**
 *
 * @author Link162534
 */
public class World implements LogicLooper {

    protected ArrayList<GameObject> gameObjects = new ArrayList<>();
    protected ArrayList<GameObject> addObjects = new ArrayList<>();
    protected ArrayList<GameObject> removeObjects = new ArrayList<>();

    protected MasterKeyManager keyManager;
    protected Camera camera;
    protected Network network;
    protected long synchronizeTickDelay = 200;
    protected long synchronizeTick = 0;

    public World(MasterKeyManager keyManager, Network network) {
        this.keyManager = keyManager;
        this.network = network;
    }

    @Override
    public synchronized void onLogicLoop(long tick) {
        for (GameObject gameObject : gameObjects) {
            gameObject.doLogic(gameObject, tick, this, keyManager);
            ControllerInterface ci = gameObject.getControllerInterface();
            if (ci != null)
                ci.control(gameObject, tick, this, keyManager);
            moveGameObject(gameObject);
            if (network != null && network.getId() == 0 && gameObject.getObjectId() != -1 && synchronizeTick < tick) {
                ArrayList<String> args = new ArrayList();
                args.add(String.valueOf(gameObject.getObjectId()));
                args.addAll(Arrays.asList(gameObject.networkSerialize()));
                network.sendBroadcastCommand(CommandList.getCommandId(UpdateObjectCommand.class), args.toArray(new String[0]));
            }
        }
        if (synchronizeTick < tick)
            synchronizeTick = tick + synchronizeTickDelay;
        gameObjects.addAll(addObjects);
        addObjects.clear();
        gameObjects.removeAll(removeObjects);
        removeObjects.clear();
    }

    protected void moveGameObject(GameObject gameObject) {
        if (gameObject.getData() instanceof DataObject) {
            PositionData pos = ((DataObject) gameObject.getData()).getPositionData();
            MovementData move = ((DataObject) gameObject.getData()).getMovementData();
            if (pos != null && move != null) {
                pos.increaseX(move.getMovementX());
                pos.increaseY(move.getMovementY());
            }
        }
    }

    public synchronized void repaint(Graphics2D g2d) {
        for (GameObject gameObject : gameObjects) {
            GraphicsInterface gi = gameObject.getGraphicsInterface();
            if (gi != null) {
                gi.render(gameObject.getData(), camera, g2d);
            }
        }
    }

    public GameObject getGameObject(int objectId) {
        return (GameObject) gameObjects.stream().filter((gameObject) -> (gameObject.getObjectId() == objectId)).findAny().get();
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
        gameObject.destroy(this);
    }

}
