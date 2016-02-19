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
import yansuen.data.Data;
import yansuen.key.MasterKeyManager;

/**
 *
 * @author Link162534
 */
public class GameObject implements LogicInterface {

    protected int networkProjectionId = -1;
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

    public boolean istNetworkProjection() {
        return networkProjectionId != -1;
    }

    public int getNetworkProjectionId() {
        return networkProjectionId;
    }

    public void setNetworkProjectionId(int networkProjectionId) {
        this.networkProjectionId = networkProjectionId;
    }

}
