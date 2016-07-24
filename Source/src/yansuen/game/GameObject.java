package yansuen.game;

import yansuen.controller.ControllerInterface;
import code.game.World;
import yansuen.graphics.GraphicsInterface;
import yansuen.logic.LogicInterface;
import java.awt.image.BufferedImage;
import yansuen.key.MasterKeyManager;
import yansuen.network.NetworkSerializable;
import yansuen.data.GameData;

/**
 * @author Link162534
 */
public class GameObject implements LogicInterface, NetworkSerializable {

    protected int networkProjectionId = -1;
    protected int objectId = -1;
    protected GameData data;
    protected GraphicsInterface graphicsInterface;
    protected ControllerInterface controllerInterface;

    public GameObject() {
    }

    public GameObject(float x, float y, BufferedImage img,
            GraphicsInterface graphicsInterface, ControllerInterface controllerInterface) {
        this(x, y, img.getWidth(), img.getHeight(), img, graphicsInterface, controllerInterface);
    }

    public GameObject(float x, float y, float w, float h, BufferedImage img,
            GraphicsInterface graphicsInterface, ControllerInterface controllerInterface) {
        this(new GameData(x, y, w, h, img), graphicsInterface, controllerInterface);
    }

    public GameObject(GameData dataObject,
            GraphicsInterface graphicsInterface, ControllerInterface controllerInterface) {
        this.data = dataObject;
        this.graphicsInterface = graphicsInterface;
        this.controllerInterface = controllerInterface;
    }

    @Override
    public void doLogic(GameObject gameObject, long tick, World world, MasterKeyManager manager) {
    }

    public void destroy(World world) {
        if (data instanceof GameData)
            ((GameData) data).getListenerList().clear();
    }

    public GameData getData() {
        return data;
    }

    public void setData(GameData data) {
        this.data = data;
    }

    public GraphicsInterface getGraphicsInterface() {
        return graphicsInterface;
    }

    public void setGraphicsInterface(GraphicsInterface graphicsInterface) {
        this.graphicsInterface = graphicsInterface;
    }

    public ControllerInterface getControllerInterface() {
        return controllerInterface;
    }

    public void setControllerInterface(ControllerInterface controllerInterface) {
        this.controllerInterface = controllerInterface;
    }

    public boolean isNetworkProjection() {
        return networkProjectionId != -1;
    }

    public int getNetworkProjectionId() {
        return networkProjectionId;
    }

    public void setNetworkProjectionId(int networkProjectionId) {
        this.networkProjectionId = networkProjectionId;
    }

    public long getObjectId() {
        return objectId;
    }

    public void setObjectId(int objectId) {
        this.objectId = objectId;
    }

    public static int objectIdCounter = 0;

    public static int getNewObjectID() {
        return objectIdCounter++;
    }

    @Override
    public String[] networkSerialize() {
        return data.networkSerialize();
    }

    @Override
    public void networkDeserialize(String[] args) {
        data.networkDeserialize(args);
    }

    @Override
    public int networkSerializeArgumentCount() {
        return data.networkSerializeArgumentCount();
    }

}
