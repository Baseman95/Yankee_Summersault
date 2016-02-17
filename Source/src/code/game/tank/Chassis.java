/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package code.game.tank;

import code.data.DataObject;
import code.data.ImageData;
import code.data.MovementData;
import code.data.PositionData;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import yansuen.controller.ControllerInterface;
import yansuen.data.Data;
import yansuen.game.GameObject;
import yansuen.graphics.GraphicsInterface;
import yansuen.logic.LogicInterface;

/**
 *
 * @author Link
 */
public class Chassis extends GameObject {

    protected Drive drive;
    protected ArrayList<Weapon> weapons = new ArrayList<>();

    public Chassis() {
        super();
    }

    public Chassis(float x, float y, BufferedImage img, LogicInterface logicInterface,
            GraphicsInterface graphicsInterface, ControllerInterface controllerInterface) {
        this(x, y, img.getWidth(), img.getHeight(), img, logicInterface, graphicsInterface, controllerInterface);
    }

    public Chassis(float x, float y, float w, float h, BufferedImage img, LogicInterface logicInterface,
            GraphicsInterface graphicsInterface, ControllerInterface controllerInterface) {
        this((Data) (new DataObject(new PositionData(x, y, w, h), new ImageData(img), new MovementData())),
                logicInterface, graphicsInterface, controllerInterface);
    }

    public Chassis(Data dataObject, LogicInterface logicInterface,
            GraphicsInterface graphicsInterface, ControllerInterface controllerInterface) {
        super(dataObject, logicInterface, graphicsInterface, controllerInterface);
    }

    public ArrayList<Weapon> getWeapons() {
        return weapons;
    }

    public void setDrive(Drive drive) {
        this.drive = drive;
    }

    public Drive getDrive() {
        return drive;
    }

}
