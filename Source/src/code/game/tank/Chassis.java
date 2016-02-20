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
import code.game.World;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import yansuen.controller.ControllerInterface;
import yansuen.data.Data;
import code.data.DataObjectListener;
import code.data.DataObjectListenerAdapter;
import java.util.Arrays;
import yansuen.game.GameObject;
import yansuen.graphics.GraphicsInterface;
import yansuen.key.MasterKeyManager;

/**
 *
 * @author Link
 */
public class Chassis extends GameObject {

    protected Drive drive;
    protected ArrayList<Weapon> weapons = new ArrayList<>();
    protected DataObjectListener weaponUpdater = new DataObjectListenerAdapter() {
        @Override
        public void onPositionChanged(DataObject data, float xOld, float yOld) {
            DataObject d = ((DataObject) data);
            for (Weapon weapon : weapons) {
                DataObject wd = ((DataObject) weapon.getData());
                wd.getPositionData().setX(d.getPositionData().getX() + d.getPositionData().getWidth() / 2
                                          + weapon.getRelativeX() - ((DataObject) weapon.getData()).getPositionData().getWidth() / 2);
                wd.getPositionData().setY(d.getPositionData().getY() + d.getPositionData().getHeight() / 2
                                          + weapon.getRelativeY() - ((DataObject) weapon.getData()).getPositionData().getHeight() / 2);
            }
        }

        @Override
        public void onRotationChanged(DataObject data, double rOld) {
            DataObject d = ((DataObject) data);
            for (Weapon weapon : weapons) {
                DataObject wd = ((DataObject) weapon.getData());
                wd.getPositionData().setRotation(d.getPositionData().getRotation() - weapon.getRelativeRotation());
            }
        }

    };

    public Chassis() {
        super();
        ((DataObject) data).addDataObjectListener(weaponUpdater);
    }

    public Chassis(float x, float y, BufferedImage img,
            GraphicsInterface graphicsInterface, ControllerInterface controllerInterface) {
        this(x, y, img.getWidth(), img.getHeight(), img, graphicsInterface, controllerInterface);
    }

    public Chassis(float x, float y, float w, float h, BufferedImage img,
            GraphicsInterface graphicsInterface, ControllerInterface controllerInterface) {
        this((Data) (new DataObject(new PositionData(x, y, w, h), new ImageData(img), new MovementData())),
             graphicsInterface, controllerInterface);
    }

    public Chassis(Data dataObject,
            GraphicsInterface graphicsInterface, ControllerInterface controllerInterface) {
        super(dataObject, graphicsInterface, controllerInterface);
        ((DataObject) dataObject).addDataObjectListener(weaponUpdater);
    }

    @Override
    public void doLogic(GameObject gameObject, long tick, World world, MasterKeyManager manager) {
        super.doLogic(gameObject, tick, world, manager);
        if (drive != null)
            drive.doLogic(gameObject, tick, world, manager);
        for (Weapon weapon : weapons) {
            weapon.doLogic(gameObject, tick, world, manager);
        }
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

    @Override
    public String[] networkSerialize() {
        ArrayList<String> args = new ArrayList<>(Arrays.asList(super.networkSerialize()));
        for (int i = 0; i < weapons.size(); i++) {
            args.addAll(Arrays.asList(weapons.get(i).networkSerialize()));
        }
        return args.toArray(new String[0]);
    }

    @Override
    public void networkDeserialize(String[] args) {
        super.networkDeserialize(args);
        if (weapons.size() > 0) {
            int weaponLength = weapons.get(0).networkSerializeArgumentCount();
            for (int i = 0; i < weapons.size(); i++) {
                weapons.get(i).networkDeserialize(Arrays.copyOfRange(
                        args, super.networkSerializeArgumentCount() + i * weaponLength, args.length));
            }
        }
    }

    @Override
    public int networkSerializeArgumentCount() {
        int count = super.networkSerializeArgumentCount();
        if (weapons.isEmpty())
            return count;
        count += weapons.get(0).networkSerializeArgumentCount() * weapons.size();
        return count;
    }
}
