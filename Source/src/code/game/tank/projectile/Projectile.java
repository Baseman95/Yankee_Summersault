/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package code.game.tank.projectile;

import code.data.DataObject;
import java.awt.image.BufferedImage;
import yansuen.controller.ControllerInterface;
import yansuen.game.GameObject;
import yansuen.graphics.GraphicsInterface;
import yansuen.logic.LogicInterface;

/**
 *
 * @author Eris
 */
public class Projectile extends GameObject {

    protected LogicInterface impactBehavior;

    public Projectile(float x, float y, BufferedImage img, LogicInterface logicInterface,
            GraphicsInterface graphicsInterface, ControllerInterface controllerInterface,
            LogicInterface impactBehavior) {
        super(x, y, img, logicInterface, graphicsInterface, controllerInterface);
        this.impactBehavior = impactBehavior;
    }

}
