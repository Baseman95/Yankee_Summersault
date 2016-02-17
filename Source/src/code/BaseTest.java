package code;

import code.data.DataObject;
import code.data.ImageData;
import code.data.MovementData;
import code.data.PositionData;
import code.game.World;
import code.graphics.RotationGraphicsObject;
import java.awt.event.KeyEvent;
import yansuen.graphics.GraphicsLoop;
import yansuen.logic.LogicInterface;
import yansuen.logic.LogicLoop;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import yansuen.controller.ControllerInterface;
import yansuen.game.GameObject;
import yansuen.graphics.GraphicsInterface;
import yansuen.key.KeyManager;
import yansuen.physic.CartesianVector;
import yansuen.physic.PolarVector;

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
        BufferedImage tankIMG = ImageIO.read(new File("tank2.png"));
        BufferedImage weap1IMG = ImageIO.read(new File("gun_plasma_shot.png"));
        BufferedImage weap2IMG = ImageIO.read(new File("gun_magnum_shot.png"));
        GraphicsInterface defaultGraphics = new RotationGraphicsObject();
        // Weapon weapon = new Weapon(GameObject, GameObject);

        LogicInterface weapon1 = (GameObject gameObject, long tick, World w, KeyManager manager) -> {
            DataObject data = (DataObject) gameObject.getDataObject();
            if (manager.isKeyPressed(KeyEvent.VK_1) && tick - bulletTick1 > 100) {
                DataObject dataO = new DataObject(new PositionData(data.getPositionData().getX()
                        + data.getPositionData().getWidth() / 2
                        - 8,
                        data.getPositionData().getY()
                        + data.getPositionData().getHeight() / 2
                        - 8, 16, 16),
                        new ImageData(weap1IMG),
                        new MovementData());
                final GameObject bullet = new GameObject(dataO, null, defaultGraphics, null);
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
            DataObject data = (DataObject) gameObject.getDataObject();
            if (manager.isKeyPressed(KeyEvent.VK_2) && tick - bulletTick2 > 1) {
                DataObject dataO = new DataObject(new PositionData(data.getPositionData().getX()
                        + data.getPositionData().getWidth() / 2
                        - 8,
                        data.getPositionData().getY()
                        + data.getPositionData().getHeight() / 2
                        - 8, 16, 16),
                        new ImageData(weap2IMG),
                        new MovementData());
                final GameObject bullet = new GameObject(dataO, null, defaultGraphics, null);
                bullet.setLogicInterface((GameObject gameObject2, long t2, World w2, KeyManager m2) -> {
                    if (t2 - tick > 64) {
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
                bulletTick2 = tick;
            }
        };

        LogicInterface playerTankLI = (GameObject gameObject, long tick, World w, KeyManager manager) -> {
            weapon1.doLogic(gameObject, tick, world, manager);
            weapon2.doLogic(gameObject, tick, world, manager);
        };
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

        ControllerInterface playerController = (GameObject gameObject, long tick, World w, KeyManager manager) -> {
            DataObject data = (DataObject) gameObject.getDataObject();
            MovementData mData = data.getMovementData();
            double ang = data.getPositionData().getRotation();

            PolarVector mv = new PolarVector(new CartesianVector(mData.getMovementX(),
                    mData.getMovementY()));
            double deltaAng = mv.angle - ang;

            deltaAng = deltaAng > Math.PI
                    ? deltaAng - Math.PI * 2 : deltaAng < -Math.PI
                            ? deltaAng + Math.PI * 2 : deltaAng;
            if (Math.abs(deltaAng) > Math.PI / 2) {
                deltaAng -= Math.signum(deltaAng) * Math.PI;
            }

            deltaAng = Math.abs(deltaAng) < 0.005 ? deltaAng : deltaAng * 0.01;
            mv.angle -= deltaAng;

            data.getMovementData().setMovementX(PolarVector.xFromPolar(mv));
            data.getMovementData().setMovementY(PolarVector.yFromPolar(mv));

            CartesianVector uvRot = new CartesianVector(new PolarVector(ang, 0.1f));
            if (manager.isKeyPressed(KeyEvent.VK_W)) {
                mData.increaseMovementX(+uvRot.x);
                mData.increaseMovementY(+uvRot.y);
            }
            if (manager.isKeyPressed(KeyEvent.VK_S)) {
                mData.increaseMovementX(-uvRot.x);
                mData.increaseMovementY(-uvRot.y);
            }
            if (manager.isKeyPressed(KeyEvent.VK_A)) {
                data.getPositionData().increaseRotation(-0.004);
            }
            if (manager.isKeyPressed(KeyEvent.VK_D)) {
                data.getPositionData().increaseRotation(+0.004);
            }

            mData.setMovementX(mData.getMovementX() * 0.90f);
            mData.setMovementY(mData.getMovementY() * 0.90f);

        };

        GameObject tank = new GameObject(10, 10, tankIMG, playerTankLI, defaultGraphics, playerController);
        world.getGameObjects().add(tank);

        /*
         GameObject heli = new GameObject(flyingObject, heliGraphic, aiController);
         GameObject panzer = new GameObject(movingObject, panzerGraphic, aiController);
         heli.setController(noController);*/
    }

}
