package code.game;

import code.data.DataInterface;
import code.data.DataObject;
import code.graphics.GraphicsInterface;
import code.logic.LogicInterface;
import code.physics.PhysicsInterface;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 *
 * @author Link162534
 */
public class GameObject {

    protected DataInterface dataObject;
    protected LogicInterface logicInterface;
    protected GraphicsInterface graphicsInterface;
    protected PhysicsInterface physicsInterface;

    public GameObject() {
    }

    public GameObject(DataInterface dataInterface, LogicInterface logicInterface, BufferedImage img, PhysicsInterface physicsInterface) {
        this(dataInterface, logicInterface, (DataInterface data, Graphics2D g2d) -> {
            g2d.drawImage(img, (int) data.getX(), (int) data.getY(), null);
        }, physicsInterface);
    }
    
    public GameObject(float x, float y, LogicInterface logicInterface, BufferedImage img, PhysicsInterface physicsInterface) {
        this(x, y, logicInterface, (DataInterface data, Graphics2D g2d) -> {
            g2d.drawImage(img, (int) data.getX(), (int) data.getY(), null);
        }, physicsInterface);
    }

    public GameObject(float x, float y, LogicInterface logicInterface, GraphicsInterface graphicsInterface, PhysicsInterface physicsInterface) {
        this((DataInterface) (new DataObject(x, y)), logicInterface, graphicsInterface, physicsInterface);
    }

    public GameObject(DataInterface dataInterface, LogicInterface logicInterface, GraphicsInterface graphicsInterface, PhysicsInterface physicsInterface) {
        this.dataObject = dataInterface;
        this.logicInterface = logicInterface;
        this.graphicsInterface = graphicsInterface;
        this.physicsInterface = physicsInterface;
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

    public PhysicsInterface getPhysicsInterface() {
        return physicsInterface;
    }

    public void setPhysicsInterface(PhysicsInterface physicsInterface) {
        this.physicsInterface = physicsInterface;
    }

}
