package code;

import yansuen.controller.ControllerInterface;
import code.data.DataObject;
import code.data.ImageData;
import code.data.MovementData;
import code.data.PositionData;
import code.game.World;
import code.game.tank.Chassis;
import code.game.tank.Drive;
import code.graphics.RotationGraphicsObject;
import yansuen.graphics.GraphicsInterface;
import yansuen.graphics.GraphicsLoop;
import yansuen.key.KeyManager;
import yansuen.logic.LogicInterface;
import yansuen.logic.LogicLoop;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import yansuen.game.GameObject;
import yansuen.physic.CartesianVector;
import yansuen.physic.PolarVector;

/**
 *
 * @author Link162534
 */
public class TrifkoTest {

    public static long bulletTick = 0;

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
        GraphicsInterface defaultGraphics = new RotationGraphicsObject();
        BufferedImage bulletImg = ImageIO.read(new File("mypanzer.png"));

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
                },
                null,
                null,
                (GameObject gameObject, long tick, World w, KeyManager manager) -> {
                    DataObject data = (DataObject) gameObject.getData();
                    CartesianVector vector = new CartesianVector(data.getMovementData().getMovementX(),
                            data.getMovementData().getMovementY());
                    PolarVector pv = vector.toPolarVector();
                    pv.updateAngleRange2Pi();
                    System.out.println(data.getPositionData().getRotation()+ " - "+
                            pv.length + " - "+ pv.angle);
                    
                    float dif = (float)(data.getPositionData().getRotation() - pv.angle);
                    System.out.println(dif);
                    
                    pv.angle = data.getPositionData().getRotation();
                     data.getMovementData().setMovementX(PolarVector.xFromPolar(pv));
                    data.getMovementData().setMovementY(PolarVector.yFromPolar(pv));
                    
                }
                );

        LogicInterface tankLogic = (GameObject gameObject, long tick, World w, KeyManager manager) -> {
            DataObject data = (DataObject) gameObject.getData();
            if (manager.isKeyPressed(KeyEvent.VK_SPACE) && tick - bulletTick > 100) {
                DataObject dataO = new DataObject(new PositionData(data.getPositionData().getX()
                        + data.getPositionData().getWidth() / 2
                        - 8,
                        data.getPositionData().getY()
                        + data.getPositionData().getHeight() / 2
                        - 8, 16, 16),
                        new ImageData(bulletImg),
                        new MovementData());
                final GameObject bullet = new GameObject(dataO, defaultGraphics, null);
                /*bullet.setLogicInterface((GameObject gameObject1, long t2, World w2, KeyManager m2) -> {
                    if (t2 - tick > 200) {
                        w2.removeGameObject(bullet);
                    }
                });*/
                CartesianVector hostMovement = new CartesianVector(data.getMovementData().getMovementX(),
                        data.getMovementData().getMovementY());
                CartesianVector bulletTrajectory = new CartesianVector(
                        new PolarVector(data.getPositionData().getRotation()
                                + (Math.random() * Math.PI / 8 - Math.random() * Math.PI / 16),
                                4));
                dataO.getMovementData().setMovementX(bulletTrajectory.x + hostMovement.x);
                dataO.getMovementData().setMovementY(bulletTrajectory.y + hostMovement.y);

                w.addGameObject(bullet);
                bulletTick = tick;
            }

        };
        BufferedImage tankImg = ImageIO.read(new File("cool_tank.png"));

        ControllerInterface playerController = (GameObject gameObject, long tick, World w, KeyManager manager) -> {

            if (manager.isKeyPressed(KeyEvent.VK_W)) {
                ((Chassis) gameObject).getDrive().setAccelerate(true);
            } else {
                ((Chassis) gameObject).getDrive().setAccelerate(false);
            }
            if (manager.isKeyPressed(KeyEvent.VK_S)) {
                ((Chassis) gameObject).getDrive().setDecelerate(true);
            } else {
                ((Chassis) gameObject).getDrive().setDecelerate(false);
            }
            if (manager.isKeyPressed(KeyEvent.VK_SPACE)) {
                ((Chassis) gameObject).getDrive().setBreaks(true);
            } else {
                ((Chassis) gameObject).getDrive().setBreaks(false);
            }
            if (manager.isKeyPressed(KeyEvent.VK_A)) {
                ((Chassis) gameObject).getDrive().setTurnLeft(true);
            } else {
                ((Chassis) gameObject).getDrive().setTurnLeft(false);
            }
            if (manager.isKeyPressed(KeyEvent.VK_D)) {
                ((Chassis) gameObject).getDrive().setTurnRight(true);
            } else {
                ((Chassis) gameObject).getDrive().setTurnRight(false);
            }
            /*
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
            mData.setMovementY(mData.getMovementY() * 0.90f);*/

        };

        Chassis tank = new Chassis(50, 50, tankImg, defaultGraphics, playerController);
        tank.setDrive(track);

        //GameObject tank = new GameObject(10, 10, tankImg, tankLogic, defaultGraphics, playerController);
        GameObject tank2 = new GameObject(500, 300, tankImg, defaultGraphics, null);
        world.getGameObjects().add(tank);
        world.getGameObjects().add(tank2);
    }
}
