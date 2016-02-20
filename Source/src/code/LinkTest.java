package code;

import code.game.tank.Chassis;
import code.menu.Screen;
import code.presets.ControllerPresets;
import code.presets.DrivePresets;
import code.presets.GraphicsPresets;
import code.presets.ImagePresets;
import code.presets.WeaponPresets;
import code.game.Application;
import code.network.CommandList;
import code.network.KeyPressedCommand;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;
import java.text.DecimalFormat;
import java.util.ArrayList;
import yansuen.game.GameObject;

/**
 *
 * @author Base
 */
public class LinkTest extends Application {

    public static void main(String args[]) throws Exception {
        Screen screen = new Screen();
        Application application = new LinkTest(screen);
        screen.setApplication(application);
        screen.setVisible(true);
    }

    public LinkTest(Screen screen) {
        super(screen);
    }

    @Override
    public void start() {
        super.start();
        Chassis zank = new Chassis(10, 10, ImagePresets.Vehicle.WTANK_LAV300_R, GraphicsPresets.ROTATION, ControllerPresets.PLAYER);
        zank.getWeapons().add(WeaponPresets.createRocketLauncher(zank));
        zank.setDrive(DrivePresets.createSimpleDrive());
        world.addGameObject(zank);

        Chassis julia = new Chassis(50, 50, ImagePresets.Test.TANK, GraphicsPresets.ROTATION, ControllerPresets.PLAYER);
        julia.setDrive(DrivePresets.createSimpleDrive());
        world.addGameObject(julia);

        Chassis peter = new Chassis(50, 50, ImagePresets.Vehicle.HELI_APACHE_B, GraphicsPresets.ROTATION, ControllerPresets.PLAYER);
        peter.setDrive(DrivePresets.createSimpleDrive());
        world.addGameObject(peter);

        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher((KeyEvent e) -> {
            new Thread(() -> {
                int pressed = 0;
                switch (e.paramString().split(",")[0]) {
                    case "KEY_PRESSED":
                        pressed = 1;
                    case "KEY_RELEASED":
                        ArrayList<String> args = new ArrayList<>();
                        args.add(0, network.getId() + "");
                        args.add(1, pressed + "");
                        args.add(2, e.getKeyCode() + "");
                        //System.out.println("Sending(" + network.getId() + "): " + pressed + ":" + e.getKeyCode());
                        network.sendBroadcastCommand(CommandList.getCommandId(KeyPressedCommand.class), args.toArray(new String[0]));
                        break;
                }
            }).start();
            return true;
        });
        keyManager.setNetwork(network);
        zank.setNetworkProjectionId(0);
        zank.setObjectId(GameObject.getNewObjectID());
        julia.setNetworkProjectionId(1);
        julia.setObjectId(GameObject.getNewObjectID());
        peter.setNetworkProjectionId(2);
        peter.setObjectId(GameObject.getNewObjectID());

    }

}
