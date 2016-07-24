package code.game.tank;

import code.game.World;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import code.data.VehicleData;
import yansuen.game.GameObject;
import yansuen.graphics.GraphicsInterface;
import yansuen.key.MasterKeyManager;
import yansuen.data.GameData;
import yansuen.data.GameDataListener;
import yansuen.data.GameDataListenerAdapter;
import yansuen.logic.LogicInterface;

/**
 * @author Link
 */
public class Vehicle extends GameObject {

    protected Drive drive;
    protected ArrayList<Weapon> weapons = new ArrayList<>();
    protected GameDataListener weaponUpdater = new GameDataListenerAdapter() {
        @Override
        public void onPositionChanged(GameData data, float xOld, float yOld) {
            GameData d = ((GameData) data);
            for (Weapon weapon : weapons) {
                GameData wd = ((GameData) weapon.getData());
                wd.setX(d.getX() + d.getWidth() / 2
                        + weapon.getRelativeX() - ((GameData) weapon.getData()).getWidth() / 2);
                wd.setY(d.getY() + d.getHeight() / 2
                        + weapon.getRelativeY() - ((GameData) weapon.getData()).getHeight() / 2);
            }
        }

        @Override
        public void onRotationChanged(GameData data, double rOld) {
            GameData d = ((GameData) data);
            for (Weapon weapon : weapons) {
                GameData wd = ((GameData) weapon.getData());
                wd.setRotation(d.getRotation() - weapon.getRelativeRotation());
            }
        }

    };

    public Vehicle() {
        super();
        ((GameData) data).addDataObjectListener(weaponUpdater);
    }

    public Vehicle(float x, float y, BufferedImage img,
            GraphicsInterface graphicsInterface, LogicInterface controllerInterface) {
        this(x, y, img.getWidth(), img.getHeight(), img, graphicsInterface, controllerInterface);
    }

    public Vehicle(float x, float y, float w, float h, BufferedImage img,
            GraphicsInterface graphicsInterface, LogicInterface controllerInterface) {
        this(new VehicleData(x, y, w, h, img), graphicsInterface, controllerInterface);
    }

    public Vehicle(GameData dataObject,
            GraphicsInterface graphicsInterface, LogicInterface controllerInterface) {
        super(dataObject, graphicsInterface, controllerInterface);
        ((GameData) dataObject).addDataObjectListener(weaponUpdater);
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
