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
        @Deprecated
        public static BufferedImage OLD_TANK = loadImg("cool_tank.png");
        @Deprecated
        public static BufferedImage TANK2 = loadImg("tank2.png");
        @Deprecated
        public static BufferedImage TANK = loadImg("textures/vehicles_beta/load/m1128.png");
        public static BufferedImage TURRET_A = ImagePresets.loadImg("textures/weapons_beta/turrets/turretA.png");
        public static BufferedImage TRACER = ImagePresets.loadImg("textures/weapons_beta/shots/tracer.png");
        public static BufferedImage SMOKE = ImagePresets.loadImg("textures/weapons_beta/shots/smoke.png");      
                

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
        public static BufferedImage WEAPON_MG_1 = ImagePresets.loadImg("textures/weapons_beta/turrets/turretA.png");
        public static BufferedImage SHOT_RL_1 = ImagePresets.loadImg("textures/weapons_beta/shots/missile.png");
        public static BufferedImage SHOT_RL_2 = ImagePresets.loadImg("textures/weapons_beta/shots/missile.png");        
        public static BufferedImage SHOT_MG_1 = ImagePresets.loadImg("textures/weapons_beta/shots/projectile.png");
        public static BufferedImage SHOT_MG_2 = ImagePresets.loadImg("textures/weapons_beta/shots/projectile_mini.png");

        private Weapon() {
        }
    }

    public static class Vehicle {

        public static BufferedImage TANK_ABRAMS_B = ImagePresets.loadImg("textures/vehicles_beta/load/abrams_b.png");
        public static BufferedImage TANK_ABRAMS_R = ImagePresets.loadImg("textures/vehicles_beta/load/abrams_r.png");
        public static BufferedImage HELI_APACHE_B = ImagePresets.loadImg("textures/vehicles_beta/load/apache_b.png");
        public static BufferedImage HELI_APACHE_R = ImagePresets.loadImg("textures/vehicles_beta/load/apache_r.png");
        public static BufferedImage PLANE_HARRIER_B = ImagePresets.loadImg("textures/vehicles_beta/load/harrier_b.png");
        public static BufferedImage PLANE_HARRIER_R = ImagePresets.loadImg("textures/vehicles_beta/load/harrier_r.png");
        public static BufferedImage WTANK_LAV300_B = ImagePresets.loadImg("textures/vehicles_beta/load/lav300_b.png");
        public static BufferedImage WTANK_LAV300_R = ImagePresets.loadImg("textures/vehicles_beta/load/lav300_r.png");
        public static BufferedImage CAR_TECHNICAL_B = ImagePresets.loadImg("textures/vehicles_beta/load/technical_b.png");
        public static BufferedImage CAR_TECHNICAL_R = ImagePresets.loadImg("textures/vehicles_beta/load/technical_r.png");      
        
        private Vehicle() {
        }
    }

    protected static BufferedImage loadImg(String path) {
        try {
            return ImageIO.read(new File(path));
        } catch (IOException ex) {
            try {
                return ImageIO.read(new File("textures/no_image.png"));
            } catch (IOException ex1) {
                Logger.getLogger(ImagePresets.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
        return null;
    }

    private ImagePresets() {

    }
}
