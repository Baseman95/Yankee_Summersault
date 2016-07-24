/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package code;

import code.menu.GamePanel;
import yansuen.game.GameObject;
import code.game.World;
import yansuen.graphics.GraphicsLoop;
import yansuen.logic.LogicInterface;
import yansuen.logic.LogicLoop;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import yansuen.data.GameData;

/**
 *
 * @author User
 */
public class GameTest {

    public static boolean shoot = false;

    public static void main(String[] args) throws IOException {
        LogicLoop ll = new LogicLoop(50000000L, 10);
        GraphicsLoop gl = new GraphicsLoop(33);
        World world = new World(null);
        GamePanel screen = new GamePanel(world);

        ll.setLogic(world);
        gl.setRepaintTarget(screen);

        ll.start();
        gl.start();

        screen.setVisible(true);
        screen.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_SPACE) shoot = true;
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_SPACE) shoot = false;

            }

        });

        LogicInterface movingObject = (GameData data, long tick) -> {
            //data.setX(data.getX() + 1);
            if (!(data instanceof TankData)) return;
            TankData tankData = (TankData) data;

            //data.setX(data.getX() + 1);
            /*if (shoot) {
             tankData.setX(tankData.getX() + 1);
             }*/
        };
        LogicInterface projectile = (GameData data, long tick) -> {
            //data.setX(data.getX() + 1);
            if (!(data instanceof TankData)) return;
            TankData tankData = (TankData) data;

            //data.setX(data.getX() + 1);
            if (shoot) {
                tankData.setX(tankData.getX() + 1);
            }
        };

        BufferedImage panzerGraphic = ImageIO.read(new File("panzer.png"));

        GameObject playerPanzer0 = new GameObject(new TankData(10, 10, 100, 100, 1), movingObject, panzerGraphic, null);

        world.getGameObjects().add(playerPanzer0);

    }

}
