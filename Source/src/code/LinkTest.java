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

/**
 *
 * @author Base
 */
public class LinkTest extends Application {

    public static void main(String args[]) {
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
        Chassis john = new Chassis(10, 10, ImagePresets.TANK, GraphicsPresets.ROTATION, ControllerPresets.PLAYER);
        john.getWeapons().add(WeaponPresets.createPlasma(john));
        john.getWeapons().add(WeaponPresets.createRocketLauncher(john));
        john.getWeapons().add(WeaponPresets.createFlameThrower(john));
        john.setDrive(DrivePresets.createSimpleDrive());
        //zank.setDrive(DrivePresets.createRocketDrive(3));
        world.addGameObject(john);

        Chassis julia = new Chassis(50, 50, ImagePresets.TANK_ABRAMS, GraphicsPresets.ROTATION, ControllerPresets.PLAYER);
        julia.setDrive(DrivePresets.createSimpleDrive());
        world.addGameObject(julia);

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
                        System.out.println("Sending(" + network.getId() + "): " + pressed + ":" + e.getKeyCode());
                        network.sendBroadcastCommand(CommandList.getCommandId(KeyPressedCommand.class), args.toArray(new String[0]));
                        break;
                }
            }).start();
            return true;
        });
        keyManager.setNetwork(network);
        john.setNetworkProjectionId(0);
        julia.setNetworkProjectionId(1);

    }

}
