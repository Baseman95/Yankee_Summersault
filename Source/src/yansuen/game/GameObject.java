package yansuen.game;

import yansuen.controller.ControllerInterface;
import yansuen.data.ImageData;
import yansuen.data.MovementData;
import yansuen.data.PositionData;
import code.game.World;
import yansuen.graphics.GraphicsInterface;
import yansuen.logic.LogicInterface;
import java.awt.image.BufferedImage;
import yansuen.key.MasterKeyManager;
import yansuen.network.NetworkSerializable;
import yansuen.data.DataContainer;

/**
 *
 * @author Link162534
 */
public class GameObject implements LogicInterface, NetworkSerializable {

    protected int networkProjectionId = -1;
    protected int objectId = -1;
    protected DataContainer data;
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
        this((DataContainer) (new DataContainer(new PositionData(x, y, w, h), new ImageData(img))),
             graphicsInterface, controllerInterface);
    }

    public GameObject(DataContainer dataObject,
            GraphicsInterface graphicsInterface, ControllerInterface controllerInterface) {
        this.data = dataObject;
        this.graphicsInterface = graphicsInterface;
        this.controllerInterface = controllerInterface;
    }

    @Override
    public void doLogic(GameObject gameObject, long tick, World world, MasterKeyManager manager) {
    }

    public void destroy(World world) {
        if (data instanceof DataContainer)
            ((DataContainer) data).getListenerList().clear();
    }

    public DataContainer getDataContainer() {
        return data;
    }

    public void setData(DataContainer data) {
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
