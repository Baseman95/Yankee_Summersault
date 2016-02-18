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
    public static BufferedImage WEAPON_1;
    public static BufferedImage WEAPON_2;

    static {
        try {
            TANK = ImageIO.read(new File("tank2.png"));
            OLD_TANK = ImageIO.read(new File("cool_tank.png"));
            WEAPON_1 = ImageIO.read(new File("gun_plasma_shot.png"));
            WEAPON_2 = ImageIO.read(new File("gun_magnum_shot.png"));
        } catch (IOException ex) {
            Logger.getLogger(ImagePresets.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private ImagePresets() {

    }
}
