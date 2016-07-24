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
import java.util.ArrayList;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import yansuen.game.GameObject;

/**
 *
 * @author Base
 */
public class LinkTest extends Application {

    public static void main(String args[]) throws Exception {
        for (Handler handler : Logger.getLogger("").getHandlers()) {
            handler.setLevel(Level.ALL);
        }
        Screen screen = new Screen();
        Application application = new LinkTest(screen);
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher((KeyEvent e) -> {
            Thread t = new Thread(() -> {
                int pressed = 0;
                switch (e.paramString().split(",")[0]) {
                    case "KEY_PRESSED":
                        if (application.getKeyManager().isKeyPressed(e.getKeyCode()))
                            break;
                        pressed = 1;
                    case "KEY_RELEASED":
                        ArrayList<String> arguments = new ArrayList<>();
                        arguments.add(0, application.getNetwork().getId() + "");
                        arguments.add(1, pressed + "");
                        arguments.add(2, e.getKeyCode() + "");
                        //System.out.println("Sending(" + network.getId() + "): " + pressed + ":" + e.getKeyCode());
                        application.getNetwork().sendBroadcastCommand(CommandList.getCommandId(KeyPressedCommand.class), arguments.toArray(new String[0]));
                        break;
                }
            });
            t.setName("KeySynchronizer");
            t.start();
            return false;
        });
        screen.setApplication(application);
        screen.setVisible(true);
    }

    public LinkTest(Screen screen) {
        super(screen);
    }

    @Override
    public void start() {
        super.start();
        Chassis zank = new Chassis(50, 50, ImagePresets.Vehicle.Land.TECHNICAL_B, GraphicsPresets.ROTATION, ControllerPresets.PLAYER);
        zank.getWeapons().add(WeaponPresets.createRocketLauncher(zank));
        zank.setDrive(DrivePresets.createSimpleDrive());
        world.addGameObject(zank);

        Chassis julia = new Chassis(150, 50, ImagePresets.Test.TANK, GraphicsPresets.ROTATION, ControllerPresets.PLAYER);
        julia.setDrive(DrivePresets.createSimpleDrive());
        world.addGameObject(julia);

        Chassis peter = new Chassis(250, 50, ImagePresets.Vehicle.Air.APACHE_B2, GraphicsPresets.ROTATION, ControllerPresets.PLAYER);
        peter.setDrive(DrivePresets.createSimpleDrive());
        world.addGameObject(peter);

        keyManager.setNetwork(network);
        zank.setNetworkProjectionId(0);
        zank.setObjectId(GameObject.getNewObjectID());
        julia.setNetworkProjectionId(1);
        julia.setObjectId(GameObject.getNewObjectID());
        peter.setNetworkProjectionId(2);
        peter.setObjectId(GameObject.getNewObjectID());

    }

}
