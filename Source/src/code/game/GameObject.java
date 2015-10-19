package code.game;

import code.controller.ControllerInterface;
import code.data.DataInterface;
import code.data.DataObject;
import code.graphics.GraphicsInterface;
import code.logic.LogicInterface;
import java.awt.image.BufferedImage;

/**
 *
 * @author Link162534
 */
public class GameObject {

    protected DataInterface dataObject;
    protected LogicInterface logicInterface;
    protected GraphicsInterface graphicsInterface;
    protected ControllerInterface controllerInterface;

    public GameObject() {
    }

    /**
     Width and height are taken from the img.
     */
    public GameObject(float x, float y, BufferedImage img, LogicInterface logicInterface,
                      GraphicsInterface graphicsInterface, ControllerInterface controllerInterface) {
        this(x, y, img.getWidth(), img.getHeight(), img, logicInterface, graphicsInterface, controllerInterface);
    }

    public GameObject(float x, float y, float w, float h, BufferedImage img, LogicInterface logicInterface,
                      GraphicsInterface graphicsInterface, ControllerInterface controllerInterface) {
        this((DataInterface) (new DataObject(x, y, w, h, img)), logicInterface, graphicsInterface, controllerInterface);
    }

    public GameObject(DataInterface dataObject, LogicInterface logicInterface,
                      GraphicsInterface graphicsInterface, ControllerInterface controllerInterface) {
        this.dataObject = dataObject;
        this.logicInterface = logicInterface;
        this.graphicsInterface = graphicsInterface;
        this.controllerInterface = controllerInterface;
    }

    public DataInterface getDataObject() {
        return dataObject;
    }

    public void setDataObject(DataInterface dataObject) {
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
