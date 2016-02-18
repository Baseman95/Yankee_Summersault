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
                    
                    
                    data.getMovementData().setMovementX(Math.abs(data.getMovementData().getMovementX()) > 0.08 ? data.getMovementData().getMovementX() * 0.992f : 0);
                    data.getMovementData().setMovementY(Math.abs(data.getMovementData().getMovementX()) > 0.08 ? data.getMovementData().getMovementY() * 0.992f : 0);
                    
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
                    
                   /* float dif = (float)((data.getPositionData().getRotation() - pv.angle)%(2*Math.PI));
                    System.out.println(dif);
                    dif = (float)(dif >= Math.PI ? 2*Math.PI-dif : dif);
                    if(dif < 0){
                        pv.angle = pv.angle+(dif*0.05);
                    }else{
                        pv.angle = pv.angle+(dif*0.05);
                    }*/
                   // pv.angle = data.getPositionData().getRotation();
                   double deltaAng = pv.angle - data.getPositionData().getRotation();

                    deltaAng = deltaAng > Math.PI
                             ? deltaAng - Math.PI * 2 : deltaAng < -Math.PI
                             ? deltaAng + Math.PI * 2 : deltaAng;
                    if (Math.abs(deltaAng) > Math.PI / 2) {
                        deltaAng -= Math.signum(deltaAng) * Math.PI;
                     }

                    // deltaAng = Math.abs(deltaAng) < 0.005 ? deltaAng : deltaAng * 0.01; //deltaAng= Absolutdiffrenzwert
                     System.out.println(Math.abs(deltaAng) < 0.005 ? "Delta": "*0.01");
                     System.out.println(Math.abs(deltaAng));
                   double multipli=1;
                     if(Math.abs(deltaAng)<0.003){
                         multipli=1;
                     }else if(Math.abs(deltaAng)>0.003 && Math.abs(deltaAng)<0.01)
                         multipli=0.05;
                     else if(Math.abs(deltaAng)>0.01 && Math.abs(deltaAng)<0.020)
                         multipli=0.04;
                     else if(Math.abs(deltaAng)>0.021 && Math.abs(deltaAng)<0.04)
                         multipli=0.03;
                     else if(Math.abs(deltaAng)>0.041 && Math.abs(deltaAng)<0.009)
                         multipli=0.01;
                     
                     deltaAng *= multipli;
                     pv.angle -= deltaAng;
                     
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

            Chassis c = ((Chassis) gameObject);
            Drive d = c.getDrive();
            d.setAccelerate(manager.isKeyPressed(KeyEvent.VK_W));
            d.setDecelerate(manager.isKeyPressed(KeyEvent.VK_S));
            d.setBreaks(manager.isKeyPressed(KeyEvent.VK_SPACE));
            d.setTurnLeft(manager.isKeyPressed(KeyEvent.VK_A));
            d.setTurnRight(manager.isKeyPressed(KeyEvent.VK_D));

        };

        Chassis tank = new Chassis(50, 50, tankImg, defaultGraphics, playerController);
        tank.setDrive(track);

        //GameObject tank = new GameObject(10, 10, tankImg, tankLogic, defaultGraphics, playerController);
        GameObject tank2 = new GameObject(500, 300, tankImg, defaultGraphics, null);
        world.getGameObjects().add(tank);
        world.getGameObjects().add(tank2);
    }
}
