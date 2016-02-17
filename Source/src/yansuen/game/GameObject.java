package yansuen.game;

import yansuen.controller.ControllerInterface;
import code.data.DataObject;
import code.data.ImageData;
import code.data.MovementData;
import code.data.PositionData;
import yansuen.graphics.GraphicsInterface;
import yansuen.logic.LogicInterface;
import java.awt.image.BufferedImage;
import yansuen.data.Data;

/**
 *
 * @author Link162534
 */
public class GameObject {

    protected Data dataObject;
    protected LogicInterface logicInterface;
    protected GraphicsInterface graphicsInterface;
    protected ControllerInterface controllerInterface;

    public GameObject() {
    }

    /**
     * Width and height are taken from the img.
     */
    public GameObject(float x, float y, BufferedImage img, LogicInterface logicInterface,
            GraphicsInterface graphicsInterface, ControllerInterface controllerInterface) {
        this(x, y, img.getWidth(), img.getHeight(), img, logicInterface, graphicsInterface, controllerInterface);
    }

    public GameObject(float x, float y, float w, float h, BufferedImage img, LogicInterface logicInterface,
            GraphicsInterface graphicsInterface, ControllerInterface controllerInterface) {
        this((Data) (new DataObject(new PositionData(x, y, w, h), new ImageData(img), new MovementData())),
                logicInterface, graphicsInterface, controllerInterface);
    }

    public GameObject(Data dataObject, LogicInterface logicInterface,
            GraphicsInterface graphicsInterface, ControllerInterface controllerInterface) {
        this.dataObject = dataObject;
        this.logicInterface = logicInterface;
        this.graphicsInterface = graphicsInterface;
        this.controllerInterface = controllerInterface;
    }

    public Data getData() {
        return dataObject;
    }

    public void setDataObject(Data dataObject) {
        this.dataObject = dataObject;
    }

    public LogicInterface getLogicInterface() {
        return logicInterface;
    }

    public void setLogicInterface(LogicInterface logicInterface) {
        this.logicInterface = logicInterface;
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
