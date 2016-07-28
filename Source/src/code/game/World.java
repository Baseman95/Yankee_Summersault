package code.game;

import code.data.VehicleData;
import code.game.tank.Vehicle;
import code.game.tank.Weapon;
import yansuen.game.GameObject;
import code.network.CommandList;
import code.network.NetworkAction;
import code.network.UpdateObjectCommand;
import yansuen.graphics.Camera;
import yansuen.graphics.GraphicsInterface;
import yansuen.key.MasterKeyManager;
import yansuen.logic.LogicLooper;
import java.awt.Graphics2D;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;
import yansuen.logic.LogicInterface;
import yansuen.network.Network;

/**
 *
 * @author Link162534
 */
public class World implements LogicLooper {

    protected ArrayList<GameObject> gameObjects = new ArrayList<>();
    protected ArrayList<GameObject> addObjects = new ArrayList<>();
    protected ArrayList<GameObject> removeObjects = new ArrayList<>();
    protected ArrayDeque<NetworkAction> networkActions = new ArrayDeque<>();

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
            LogicInterface ci = gameObject.getLogicInterface();
            if (ci != null)
                ci.doLogic(gameObject, tick, this, keyManager);
            moveGameObject(gameObject);

            if (network == null)
                continue; 
            if (network.getClients().size() > gameObject.getNetworkProjectionId() && network.getId() != gameObject.getNetworkProjectionId())
                continue;
            if (gameObject.getObjectId() == -1)
                continue;
            if (synchronizeTick >= tick)
                continue;

            ArrayList<String> args = new ArrayList();
            args.add(String.valueOf(gameObject.getObjectId()));
            args.addAll(Arrays.asList(gameObject.networkSerialize()));
            network.sendBroadcastCommand(CommandList.getCommandId(UpdateObjectCommand.class), args.toArray(new String[0]));
        }
        if (synchronizeTick < tick)
            synchronizeTick = tick + synchronizeTickDelay;
        gameObjects.addAll(addObjects);
        addObjects.clear();
        gameObjects.removeAll(removeObjects);
        removeObjects.clear();
        while (!networkActions.isEmpty()) {
            networkActions.pop().executable.execute();
        }
    }

    protected void moveGameObject(GameObject gameObject) {
        if (gameObject.getData() instanceof VehicleData) {
            gameObject.getData().increaseX(((VehicleData) gameObject.getData()).getMovementX());
            gameObject.getData().increaseY(((VehicleData) gameObject.getData()).getMovementY());
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
        if (gameObject instanceof Vehicle) {
            ((Vehicle) gameObject).getWeapons().forEach((Weapon w) -> {
                addObjects.add(w);
            });
        }
    }

    public void removeGameObject(GameObject gameObject) {
        if (gameObject instanceof Vehicle) {
            ((Vehicle) gameObject).getWeapons().forEach((Weapon w) -> {
                removeObjects.add(w);
            });
        }
        removeObjects.add(gameObject);
        gameObject.destroy(this);
    }

    public boolean addNetworkAction(NetworkAction e) {
        return networkActions.add(e);
    }

}
