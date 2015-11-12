package code.game;

import yansuen.controller.ControllerInterface;
import yansuen.data.DataInterface;
import code.data.DataObject;
import code.data.ImageData;
import code.data.MovementData;
import code.data.PositionData;
import yansuen.graphics.GraphicsInterface;
import yansuen.logic.LogicInterface;
import java.awt.image.BufferedImage;
import yansuen.game.GameInterface;

/**
 *
 * @author Link162534
 */
public class GameObject implements GameInterface {

    protected Object[] list = new Object[4];

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
        this((DataInterface) (new DataObject(new PositionData(x, y, w, h), new ImageData(img), new MovementData())),
             logicInterface, graphicsInterface, controllerInterface);
    }

    public GameObject(DataInterface dataObject, LogicInterface logicInterface,
                      GraphicsInterface graphicsInterface, ControllerInterface controllerInterface) {
        this.dataObject = dataObject;
        this.logicInterface = logicInterface;
        this.graphicsInterface = graphicsInterface;
        this.controllerInterface = controllerInterface;
        list[0] = this.dataObject;
        list[1] = this.logicInterface;
        list[2] = this.graphicsInterface;
        list[3] = this.controllerInterface;

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

    @Override
    public Object get(int i) {
        if (i < 0 || i >= list.length)
            return null;
        return list[i];
    }

    public Object get(Class c) {
        return get(getClassInt(c));
    }

    public int getClassInt(Class c) {
        for (int i = 0; i < list.length; i++) {
            if (list[i].getClass() == c)
                return i;
        }
        return -1;
    }
}
