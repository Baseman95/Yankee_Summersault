/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package code.presets;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Eris
 */
public class ImagePresets {

    public static BufferedImage TANK;
    public static BufferedImage OLD_TANK;
    public static BufferedImage TANK2;
    public static BufferedImage SHOT_1;
    public static BufferedImage SHOT_2;
    public static BufferedImage TURRET_A;

    static {
        try {
            TANK = ImageIO.read(new File("textures/vehicles_beta/m1128.png"));
            TANK2 =ImageIO.read(new File("tank2.png"));
            OLD_TANK = ImageIO.read(new File("cool_tank.png"));
            TURRET_A = ImageIO.read(new File("textures/weapons_beta/turretA.png"));
            SHOT_1 = ImageIO.read(new File("gun_plasma_shot.png"));
            SHOT_2 = ImageIO.read(new File("gun_magnum_shot.png"));
        } catch (IOException ex) {
            Logger.getLogger(ImagePresets.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private ImagePresets() {

    }
}
