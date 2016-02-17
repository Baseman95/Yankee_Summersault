package code;

import yansuen.controller.ControllerInterface;
import code.data.DataObject;
import code.data.ImageData;
import code.data.MovementData;
import code.data.PositionData;
import code.game.World;
import code.game.tank.Chassis;
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
public class KeyTest {

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
                /*bullet.setLogicInterface((GameObject gameObject2, long t2, World w2, KeyManager m2) -> {
                    if (t2 - tick > 200)
                        w2.removeGameObject(bullet);
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
            DataObject data = (DataObject) gameObject.getData();
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
            if (manager.isKeyPressed(KeyEvent.VK_A))
                data.getPositionData().increaseRotation(-0.004);
            if (manager.isKeyPressed(KeyEvent.VK_D))
                data.getPositionData().increaseRotation(+0.004);

            mData.setMovementX(mData.getMovementX() * 0.90f);
            mData.setMovementY(mData.getMovementY() * 0.90f);

        };

        Chassis tank = new Chassis(10, 10, tankImg, defaultGraphics, playerController);
        GameObject tank2 = new GameObject(500, 300, tankImg, defaultGraphics, null);
        world.getGameObjects().add(tank);
        world.getGameObjects().add(tank2);
    }
}
