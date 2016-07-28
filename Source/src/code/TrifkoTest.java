package code;

import code.game.Application;
import code.game.tank.Vehicle;
import code.game.tank.Weapon;
import code.menu.Screen;
import code.presets.ControllerPresets;
import code.presets.DrivePresets;
import code.presets.GraphicsPresets;
import code.presets.ImagePresets;
import code.presets.WeaponPresets;
import java.io.IOException;
import javax.swing.JLabel;
import yansuen.game.GameObject;

/**
 *
 * @author Link162534
 */
public class TrifkoTest extends Application {

    public static JLabel Currentlabel = new JLabel();
    public static JLabel Richtunglabel = new JLabel();
    public static JLabel Driftlabel = new JLabel();
    public static JLabel Deltalabel = new JLabel();
    
    public static void main(String[] args) throws IOException {
        Screen screen = new Screen();
        Application application = new TrifkoTest(screen);
        screen.setApplication(application);
        screen.setVisible(true);
    }

    public TrifkoTest(Screen screen) {
        super(screen);
    }

    @Override
    public void start() {
        super.start();
        Vehicle heli = new Vehicle(300, 200, ImagePresets.Vehicle.Land.TECHNICAL_B, GraphicsPresets.ROTATION, ControllerPresets.PLAYER);
        heli.setDrive(DrivePresets.createHeli(0.006f));

      //  Weapon tracer = WeaponPresets.createTracer(heli);
       // heli.getWeapons().add(tracer);
        //world.addGameObject(tracer);
        /*Weapon mg = WeaponPresets.createMG(heli);
        heli.getWeapons().add(mg);
        world.addGameObject(mg);*/
        
        

        //GameObject tank = new GameObject(10, 10, tankImg, tankLogic, defaultGraphics, playerController);
        GameObject tank2 = new GameObject(500, 300, ImagePresets.Test.TANK, GraphicsPresets.ROTATION, null);
        world.getGameObjects().add(heli);
        world.getGameObjects().add(tank2);
        gamePanel.add(Currentlabel);
        gamePanel.add(Richtunglabel);
        gamePanel.add(Driftlabel);
        gamePanel.add(Deltalabel);
        
    }

}
