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
    public static BufferedImage SHOT_3;

    static {
        TANK = loadImg("textures/vehicles_beta/m1128.png");
        TANK2 = loadImg("tank2.png");
        OLD_TANK = loadImg("cool_tank.png");
        TURRET_A = loadImg("textures/weapons_beta/turrets/turretA.png");
        SHOT_1 = loadImg("textures/weapons_beta/shots/gun_plasma_shot.png");
        SHOT_2 = loadImg("textures/weapons_beta/shots/missile.png");
        SHOT_3 = loadImg("textures/weapons_beta/shots/gun_magnum_shot.png");
    }

    private static BufferedImage loadImg(String path) {
        try {
            return ImageIO.read(new File(path));
        } catch (IOException ex) {
            Logger.getLogger(ImagePresets.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private ImagePresets() {

    }
}
