package code;

import code.data.DataObject;
import code.data.ImageData;
import code.data.MovementData;
import code.data.PositionData;
import code.game.World;
import code.game.tank.Chassis;
import code.game.tank.Drive;
import code.game.tank.Weapon;
import code.game.tank.projectile.ImpactInterface;
import code.game.tank.projectile.Projectile;
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
import code.game.tank.projectile.ShootInterface;

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
        BufferedImage tankIMG = ImageIO.read(new File("tank2.png"));
        BufferedImage weap1IMG = ImageIO.read(new File("gun_plasma_shot.png"));
        BufferedImage weap2IMG = ImageIO.read(new File("gun_magnum_shot.png"));

        GraphicsInterface defaultGraphics = new RotationGraphicsObject();

        ControllerInterface playerController = (GameObject gameObject, long tick, World w, KeyManager manager) -> {
            Chassis c = ((Chassis) gameObject);
            Drive d = c.getDrive();
            Weapon w0 = c.getWeapons().get(0);
            d.setAccelerate(manager.isKeyPressed(KeyEvent.VK_W));
            d.setDecelerate(manager.isKeyPressed(KeyEvent.VK_S));
            d.setBreaks(manager.isKeyPressed(KeyEvent.VK_SPACE));
            d.setTurnLeft(manager.isKeyPressed(KeyEvent.VK_A));
            d.setTurnRight(manager.isKeyPressed(KeyEvent.VK_D));
            w0.setShoot(manager.isKeyPressed(KeyEvent.VK_1));
        };

        Chassis zank = new Chassis(10, 10, tankIMG, defaultGraphics, playerController);

        ControllerInterface unguidedController = (GameObject gameObject, long tick, World world1, KeyManager manager) -> {
            DataObject data = (DataObject) gameObject.getData();

            int velocity = 3; //projektilgeschwindigkeit in px per sec           
            PolarVector pv = new PolarVector(data.getPositionData().getRotation(), velocity);
            data.getMovementData().setMovementX(PolarVector.xFromPolar(pv));
            data.getMovementData().setMovementY(PolarVector.yFromPolar(pv));
        };

        ShootInterface singleShot = (Weapon weapon, long tick, ImpactInterface impactInterface, World world1, KeyManager manager) -> {
            //Spawn von Projektil, + Richtung, adding projektil zu welt,
            int duration = 1000; //Flugdauer
            DataObject data = (DataObject) zank.getData();
            Projectile p = new Projectile(weapon, tick + duration,
                    impactInterface, data.getPositionData().getX(),
                    data.getPositionData().getY(), weap1IMG, defaultGraphics, unguidedController);
            ((DataObject) p.getData()).getPositionData().setRotation(data.getPositionData().getRotation());
            world.addGameObject(p);

        };
        LogicInterface fastReload = (GameObject gameObject, long tick, World world1, KeyManager manager) -> {
            //
        };
        ImpactInterface bulletImpact = (Projectile projectile, Weapon weapon, long tick, World world1, KeyManager manager) -> {

        };
        
        int cooldown = 20; 
        Weapon weapon = new Weapon(zank, cooldown, singleShot,
                fastReload, bulletImpact,
                null, 10,
                10, tankIMG,
                defaultGraphics, playerController);

        //<editor-fold defaultstate="collapsed" desc="track">
        Drive track = new Drive(
                (GameObject gameObject, long tick, World w, KeyManager manager) -> {
                    DataObject data = (DataObject) gameObject.getData();

                    double ang = data.getPositionData().getRotation();
                    PolarVector mv = new PolarVector(ang, 0.001f);

                    data.getMovementData().setMovementX(PolarVector.xFromPolar(mv) + data.getMovementData().getMovementX());
                    data.getMovementData().setMovementY(PolarVector.yFromPolar(mv) + data.getMovementData().getMovementY());
                },
                (GameObject gameObject, long tick, World w, KeyManager manager) -> {
                    DataObject data = (DataObject) gameObject.getData();

                    double ang = data.getPositionData().getRotation();
                    PolarVector mv = new PolarVector(ang, 0.001f);

                    data.getMovementData().setMovementX(data.getMovementData().getMovementX() - PolarVector.xFromPolar(mv));
                    data.getMovementData().setMovementY(data.getMovementData().getMovementY() - PolarVector.yFromPolar(mv));
                },
                (GameObject gameObject, long tick, World w, KeyManager manager) -> {
                    DataObject data = (DataObject) gameObject.getData();

                    double ang = data.getPositionData().getRotation();
                    PolarVector mv = new PolarVector(ang, 0.001f);

                    data.getMovementData().setMovementX(data.getMovementData().getMovementX() * 0.992f);
                    data.getMovementData().setMovementY(data.getMovementData().getMovementY() * 0.992f);
                },
                (GameObject gameObject, long tick, World w, KeyManager manager) -> {
                    DataObject data = (DataObject) gameObject.getData();

                    data.getPositionData().increaseRotation(-0.004);
                },
                (GameObject gameObject, long tick, World w, KeyManager manager) -> {
                    DataObject data = (DataObject) gameObject.getData();

                    data.getPositionData().increaseRotation(+0.004);
                    CartesianVector vector = new CartesianVector(data.getMovementData().getMovementX(),
                            data.getMovementData().getMovementY());
                    PolarVector pv = vector.toPolarVector();
                    pv.angle += 0.004;
                    //pv.updateAngleRangePi();

                    data.getMovementData().setMovementX(PolarVector.xFromPolar(pv));
                    data.getMovementData().setMovementY(PolarVector.yFromPolar(pv));
                },
                null,
                null,
                (GameObject gameObject, long tick, World w, KeyManager manager) -> {
                    DataObject data = (DataObject) gameObject.getData();
                    CartesianVector vector = new CartesianVector(data.getMovementData().getMovementX(),
                            data.getMovementData().getMovementY());
                    PolarVector pv = vector.toPolarVector();
                    pv.angle = data.getPositionData().getRotation();
                    data.getMovementData().setMovementX(PolarVector.xFromPolar(pv));
                    data.getMovementData().setMovementY(PolarVector.yFromPolar(pv));

                }
        );
//</editor-fold>

        zank.getWeapons().add(weapon);
        zank.setDrive(track);
        world.getGameObjects().add(zank);

        /*
         GameObject heli = new GameObject(flyingObject, heliGraphic, aiController);
         GameObject panzer = new GameObject(movingObject, panzerGraphic, aiController);
         heli.setController(noController);*/
    }

}
