package code;

import code.game.World;
import code.game.tank.Chassis;
import yansuen.graphics.GraphicsLoop;
import yansuen.logic.LogicLoop;
import java.io.IOException;
import yansuen.key.KeyManager;
import code.presets.ImagePresets;
import code.presets.ControllerPresets;
import code.presets.DrivePresets;
import code.presets.GraphicsPresets;
import code.presets.WeaponPresets;

/**
 *
 * @author Base
 */
public class BaseTest {

    static long bulletTick1 = 0;
    static long bulletTick2 = 0;

    public static void main(String[] args) throws IOException {
        LogicLoop ll = new LogicLoop(5000000L, 1);
        GraphicsLoop gl = new GraphicsLoop(33);
        KeyManager keyManager = new KeyManager();
        World world = new World(keyManager);
        Screen screen = new Screen(world);

        ll.setLogic(world);
        gl.setRepaintTarget(screen);

        ll.start();
        gl.start();

        screen.addKeyListener(keyManager);
        screen.setVisible(true);

        //<editor-fold defaultstate="collapsed" desc="USELESS SHIT PLS DELETE">
        /*LogicInterface movingObject = (DataInterface data, long tick) -> {
         //data.setX(data.getX() + 1);
         if (!(data instanceof TankData)) return;
         TankData tankData = (TankData) data;
         switch (tankData.dir) {
         case 0: {
         data.setY(data.getY() + 10);
         break;
         }
         case 1: {
         data.setX(data.getX() + 10);
         break;
         }
         case 2: {
         data.setY(data.getY() - 10);
         break;
         }
         case 3: {
         data.setX(data.getX() - 10);
         break;
         }
         }
        
         if (tankData.dir == 0 && data.getY() >= 450) tankData.dir++;
         if (tankData.dir == 1 && data.getX() >= 650) tankData.dir++;
         if (tankData.dir == 2 && data.getY() <= 10) tankData.dir++;
         if (tankData.dir == 3 && data.getX() <= 10) tankData.dir = 0;
        
         };*/
//</editor-fold>
        //<editor-fold defaultstate="collapsed" desc="outdated">
        /*
        LogicInterface movingObject = (DataInterface data, long tick) -> {
            //data.setX(data.getX() + 1);
            if (!(data instanceof TankData)) return;
            TankData tankData = (TankData) data;

            //data.setX(data.getX() + 1);
            data.setX(330 * (1 + (float) (Math.sin(((double) tick  / 10 + tankData.dir )) % (Math.PI * 2))));
            data.setY(230 * (1 + (float) (Math.cos(((double) tick  / 10 + tankData.dir) ) % (Math.PI * 2))));

        };         
         */
        // Weapon weapon = new Weapon(GameObject, GameObject);

        /*LogicInterface weapon1 = (GameObject gameObject, long tick, World w, KeyManager manager) -> {
            DataObject data = (DataObject) gameObject.getData();
            if (manager.isKeyPressed(KeyEvent.VK_1) && tick - bulletTick1 > 100) {
                DataObject dataO = new DataObject(new PositionData(data.getPositionData().getX()
                        + data.getPositionData().getWidth() / 2
                        - 8,
                        data.getPositionData().getY()
                                + data.getPositionData().getHeight() / 2
                                - 8, 16, 16),
                        new ImageData(weap1IMG),
                        new MovementData());
                final GameObject bullet = new GameObject(dataO, defaultGraphics, null);
                bullet.setLogicInterface((GameObject gameObject2, long t2, World w2, KeyManager m2) -> {
                if (t2 - tick > 200) {
                w2.removeGameObject(bullet);
                }
                });
                CartesianVector hostMovement = new CartesianVector(data.getMovementData().getMovementX(),
                        data.getMovementData().getMovementY());
                CartesianVector bulletTrajectory = new CartesianVector(
                        new PolarVector(data.getPositionData().getRotation()
                                + (Math.random() * Math.PI / 8 - Math.random() * Math.PI / 16),
                                4));
                dataO.getMovementData().setMovementX(bulletTrajectory.x + hostMovement.x);
                dataO.getMovementData().setMovementY(bulletTrajectory.y + hostMovement.y);
                
                w.addGameObject(bullet);
                bulletTick1 = tick;
            }
        };
        LogicInterface weapon2 = (GameObject gameObject, long tick, World w, KeyManager manager) -> {
            DataObject data = (DataObject) gameObject.getData();
            if (manager.isKeyPressed(KeyEvent.VK_2) && tick - bulletTick2 > 1) {
                DataObject dataO = new DataObject(new PositionData(data.getPositionData().getX()
                        + data.getPositionData().getWidth() / 2
                        - 8,
                        data.getPositionData().getY()
                                + data.getPositionData().getHeight() / 2
                                - 8, 16, 16),
                        new ImageData(weap2IMG),
                        new MovementData());
                final GameObject bullet = new GameObject(dataO, defaultGraphics, null);
                
                CartesianVector hostMovement = new CartesianVector(data.getMovementData().getMovementX(),
                        data.getMovementData().getMovementY());
                CartesianVector bulletTrajectory = new CartesianVector(
                        new PolarVector(data.getPositionData().getRotation()
                                + (Math.random() * Math.PI / 8 - Math.random() * Math.PI / 16),
                                4));
                dataO.getMovementData().setMovementX(bulletTrajectory.x + hostMovement.x);
                dataO.getMovementData().setMovementY(bulletTrajectory.y + hostMovement.y);
                
                w.addGameObject(bullet);
                bulletTick2 = tick;
            }
        };
        
        LogicInterface playerTankLI = (GameObject gameObject, long tick, World w, KeyManager manager) -> {
            weapon1.doLogic(gameObject, tick, world, manager);
            weapon2.doLogic(gameObject, tick, world, manager);
        };
         */
 /*
        BufferedImage panzerGraphic = ImageIO.read(new File("mypanzer.png"));
        BufferedImage panzerGraphic1 = ImageIO.read(new File("panzer.png"));
       
        GameObject playerPanzer0 = new GameObject(new TankData(10, 10, 1), movingObject, panzerGraphic);
        GameObject playerPanzer1 = new GameObject(new TankData(10, 450, 2), movingObject, panzerGraphic1);
        GameObject playerPanzer2 = new GameObject(new TankData(650, 450, 3), movingObject, panzerGraphic);
        GameObject playerPanzer3 = new GameObject(new TankData(650, 10, 4), movingObject, panzerGraphic1);
        GameObject playerPanzer4 = new GameObject(new TankData(650, 10, 5), movingObject, panzerGraphic);
        
        world.getGameObjects().add(playerPanzer0);
        world.getGameObjects().add(playerPanzer1);
        world.getGameObjects().add(playerPanzer2);
        world.getGameObjects().add(playerPanzer3);
        world.getGameObjects().add(playerPanzer4);
         */
//</editor-fold>
        Chassis zank = new Chassis(10, 10, ImagePresets.TANK, GraphicsPresets.ROTATION, ControllerPresets.PLAYER);

        zank.getWeapons().add(WeaponPresets.createPlasma(zank));
        zank.getWeapons().add(WeaponPresets.createGuided(zank));
        zank.getWeapons().add(WeaponPresets.createFlameThrower(zank));
        zank.setDrive(DrivePresets.SIMPLE);
        //zank.setDrive(DrivePresets.createRocketDrive(3));
        world.getGameObjects().add(zank);

        /*
         GameObject heli = new GameObject(flyingObject, heliGraphic, aiController);
         GameObject panzer = new GameObject(movingObject, panzerGraphic, aiController);
         heli.setController(noController);*/
    }

}
