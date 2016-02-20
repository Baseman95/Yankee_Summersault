package yansuen.game;

import yansuen.controller.ControllerInterface;
import code.data.DataObject;
import code.data.ImageData;
import code.data.MovementData;
import code.data.PositionData;
import code.game.World;
import yansuen.graphics.GraphicsInterface;
import yansuen.logic.LogicInterface;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import yansuen.data.Data;
import yansuen.key.MasterKeyManager;
import yansuen.network.NetworkSerializable;

/**
 *
 * @author Link162534
 */
public class GameObject implements LogicInterface, NetworkSerializable {

    protected int networkProjectionId = -1;
    protected int objectId = -1;
    protected Data data;
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
        this((Data) (new DataObject(new PositionData(x, y, w, h), new ImageData(img), new MovementData())),
             graphicsInterface, controllerInterface);
    }

    public GameObject(Data dataObject,
            GraphicsInterface graphicsInterface, ControllerInterface controllerInterface) {
        this.data = dataObject;
        this.graphicsInterface = graphicsInterface;
        this.controllerInterface = controllerInterface;
    }

    @Override
    public void doLogic(GameObject gameObject, long tick, World world, MasterKeyManager manager) {
    }

    public void destroy(World world) {
        if (data instanceof DataObject)
            ((DataObject) data).getListenerList().clear();
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
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
