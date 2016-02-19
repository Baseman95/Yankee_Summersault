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
 * @author Baseman
 */
public class ImagePresets {

    public static class Test {

        @Deprecated
        public static BufferedImage SHOT_2 = ImagePresets.loadImg("textures/weapons_beta/shots/missile.png");
        @Deprecated
        public static BufferedImage SHOT_3 = ImagePresets.loadImg("textures/weapons_beta/shots/gun_magnum_shot.png");
        @Deprecated
        public static BufferedImage SHOT_1 = ImagePresets.loadImg("textures/weapons_beta/shots/gun_plasma_shot.png");
        public static BufferedImage OLD_TANK = loadImg("cool_tank.png");
        public static BufferedImage TANK2 = loadImg("tank2.png");
        public static BufferedImage TANK = loadImg("textures/vehicles_beta/m1128.png");
        public static BufferedImage TURRET_A = ImagePresets.loadImg("textures/weapons_beta/turrets/turretA.png");

        private Test() {
        }
    }

    public static class Default {

        public static BufferedImage NOTHING = ImagePresets.loadImg("textures/empty.png");
        public static BufferedImage NO_IMAGE = ImagePresets.loadImg("textures/no_image.png");

        private Default() {
        }
    }

    public static class Surface {

        public static BufferedImage SURFACE_WATER = ImagePresets.loadImg("textures/world/surface_alpha/water.png");
        public static BufferedImage SURFACE_GRASS = ImagePresets.loadImg("textures/world/surface_alpha/grass.png");
        public static BufferedImage SURFACE_DIRT = ImagePresets.loadImg("textures/world/surface_alpha/dirt.png");
        public static BufferedImage SURFACE_CONCRETE = ImagePresets.loadImg("textures/world/surface_alpha/concrete.png");
        public static BufferedImage SURFACE_SAND = ImagePresets.loadImg("textures/world/surface_alpha/sand.png");
        public static BufferedImage SURFACE_ROAD = ImagePresets.loadImg("textures/world/surface_alpha/road.png");

        private Surface() {
        }
    }

    public static class Weapon {

        public static BufferedImage WEAPON_RL_1 = ImagePresets.loadImg("textures/weapons_beta/turrets/turretB.png");
        public static BufferedImage SHOT_RL_1 = ImagePresets.loadImg("textures/weapons_beta/shots/missile.png");
        public static BufferedImage WEAPON_MG_1 = ImagePresets.loadImg("textures/weapons_beta/turrets/turretA.png");
        public static BufferedImage SHOT_MG_1 = ImagePresets.loadImg("textures/weapons_beta/shots/projectile.png");

        private Weapon() {
        }
    }

    public static class Vehicle {

        public static BufferedImage TANK_ABRAMS = ImagePresets.loadImg("textures/vehicles_beta/abrams.png");
        public static BufferedImage HELI_APACHE = ImagePresets.loadImg("textures/vehicles_beta/apache.png");

        private Vehicle() {
        }
    }

    protected static BufferedImage loadImg(String path) {
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
