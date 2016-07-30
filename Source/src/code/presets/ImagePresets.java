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
        public static BufferedImage TRACER = ImagePresets.loadImg("textures/projectiles/tracer.png");
        public static BufferedImage SMOKE = ImagePresets.loadImg("textures/weapons_beta/shots/smoke.png");
        public static BufferedImage BLACK_SQUARE = ImagePresets.loadImg("textures/test.png");
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

        public static BufferedImage WATER = ImagePresets.loadImg("textures/world/surface_alpha/water.png");
        public static BufferedImage GRASS = ImagePresets.loadImg("textures/world/surface_alpha/grass.png");
        public static BufferedImage DIRT = ImagePresets.loadImg("textures/world/surface_alpha/dirt.png");
        public static BufferedImage CONCRETE = ImagePresets.loadImg("textures/world/surface_alpha/concrete.png");
        public static BufferedImage SAND = ImagePresets.loadImg("textures/world/surface_alpha/sand.png");
        public static BufferedImage ROAD = ImagePresets.loadImg("textures/world/surface_alpha/road.png");

        private Surface() {
        }
    }

    public static class Vehicle {

        public static class Air {

            public static BufferedImage HARRIER_R = ImagePresets.loadImg("textures/vehicles/ig/air/harrier_r.png");
            public static BufferedImage APACHE_B2 = ImagePresets.loadImg("textures/vehicles/ig/air/apache_b2.png");

            private Air() {
            }
        }

        public static class Amphibious {

            public static BufferedImage LAV300_B = ImagePresets.loadImg("textures/vehicles/ig/amphibious/lav300_b.png");

            private Amphibious() {
            }
        }

        public static class Land {

            public static BufferedImage M1128 = ImagePresets.loadImg("textures/vehicles/ig/land/m1128.png");
            public static BufferedImage ABRAMS_R = ImagePresets.loadImg("textures/vehicles/ig/land/abrams_r.png");
            public static BufferedImage TECHNICAL_B = ImagePresets.loadImg("textures/vehicles/ig/land/technical_b.png");

            private Land() {
            }
        }

        public static class Water {

            private Water() {
            }
        }

        private Vehicle() {
        }
    }

    public static class Weapon {

        public static BufferedImage MG762 = ImagePresets.loadImg("textures/weapons/mg762.png");
        public static BufferedImage MINIG = ImagePresets.loadImg("textures/weapons/minigun.png");
        
        
        public static BufferedImage SHELL = ImagePresets.loadImg("textures/weapons/turretA.png");

        private Weapon() {
        }
    }

    public static class Projectile {
        
        public static BufferedImage MG762 = ImagePresets.loadImg("textures/projectiles/mg762.png");
        public static BufferedImage MINIG = ImagePresets.loadImg("textures/projectiles/minig.png");
        public static BufferedImage HELCN = ImagePresets.loadImg("textures/projectiles/helcn.png");
        public static BufferedImage GLKPR = ImagePresets.loadImg("textures/projectiles/glkpr.png");
        public static BufferedImage SHTGN = ImagePresets.loadImg("textures/projectiles/shtgn.png");
        
        public static BufferedImage MK19 = ImagePresets.loadImg("textures/projectiles/mk19.png");
        public static BufferedImage ABOMB = ImagePresets.loadImg("textures/projectiles/abomb.png");
        
        public static BufferedImage MISSILE = ImagePresets.loadImg("textures/projectiles/missile.png");
        public static BufferedImage PROJECTILE = ImagePresets.loadImg("textures/projectiles/missile.png");
        public static BufferedImage MACHINEGUN = ImagePresets.loadImg("textures/projectiles/projectile_mini.png");
        public static BufferedImage MACHINEGUN2 = ImagePresets.loadImg("textures/projectiles/projectile_mini2.png");
        public static BufferedImage SHELL = ImagePresets.loadImg("textures/projectiles/projectile_mini.png");

        private Projectile() {
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
