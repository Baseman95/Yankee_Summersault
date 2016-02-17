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
import yansuen.key.KeyManager;

/**
 *
 * @author Link162534
 */
public class GameObject implements LogicInterface {

    protected Data dataObject;
    protected GraphicsInterface graphicsInterface;
    protected ControllerInterface controllerInterface;

    public GameObject() {
    }

    /**
     * Width and height are taken from the img.
     */
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
        this.dataObject = dataObject;
        this.graphicsInterface = graphicsInterface;
        this.controllerInterface = controllerInterface;
    }

    @Override
    public void doLogic(GameObject gameObject, long tick, World world, KeyManager manager) {
    }

    public Data getData() {
        return dataObject;
    }

    public void setDataObject(Data dataObject) {
        this.dataObject = dataObject;
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

}
