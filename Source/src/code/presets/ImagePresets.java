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

    public static BufferedImage NO_IMAGE;
    public static BufferedImage TANK;
    public static BufferedImage OLD_TANK;
    public static BufferedImage TANK2;
    @Deprecated
    public static BufferedImage SHOT_1;
    @Deprecated
    public static BufferedImage SHOT_2;
    public static BufferedImage TURRET_A;
    @Deprecated
    public static BufferedImage SHOT_3;

    //Surfaces START
    public static BufferedImage SURFACE_CONCRETE;
    public static BufferedImage SURFACE_DIRT;
    public static BufferedImage SURFACE_GRASS;
    public static BufferedImage SURFACE_ROAD;
    public static BufferedImage SURFACE_SAND;
    public static BufferedImage SURFACE_WATER;
    //Surfaces END
    //Vehicle START
    public static BufferedImage TANK_ABRAMS;
    //Vehicle END
    //Weapons START
    public static BufferedImage WEAPON_MG_1;
    public static BufferedImage WEAPON_RL_1;
    //Bullets
    public static BufferedImage SHOT_MG_1;
    public static BufferedImage SHOT_RL_1;
    //Weapons END

    static {
        try {
            NO_IMAGE = ImageIO.read(new File("textures/empty.png"));
            //STUFF
            TANK = ImageIO.read(new File("textures/vehicles_beta/m1128.png"));
            TANK2 = ImageIO.read(new File("tank2.png"));
            OLD_TANK = ImageIO.read(new File("cool_tank.png"));
            TURRET_A = ImageIO.read(new File("textures/weapons_beta/turrets/turretA.png"));
            SHOT_1 = ImageIO.read(new File("textures/weapons_beta/shots/gun_plasma_shot.png"));
            SHOT_2 = ImageIO.read(new File("textures/weapons_beta/shots/missile.png"));
            SHOT_3 = ImageIO.read(new File("textures/weapons_beta/shots/gun_magnum_shot.png"));
            //Surfaces START
            SURFACE_CONCRETE = ImageIO.read(new File("textures/world/surface_alpha/concrete.png"));
            SURFACE_DIRT = ImageIO.read(new File("textures/world/surface_alpha/dirt.png"));
            SURFACE_GRASS = ImageIO.read(new File("textures/world/surface_alpha/grass.png"));
            SURFACE_ROAD = ImageIO.read(new File("textures/world/surface_alpha/road.png"));
            SURFACE_SAND = ImageIO.read(new File("textures/world/surface_alpha/sand.png"));
            SURFACE_WATER = ImageIO.read(new File("textures/world/surface_alpha/water.png"));
            //Surfaces END
            //Vehicles START
            TANK_ABRAMS = ImageIO.read(new File("textures/vehicles_beta/abrams.png"));
            //Vehicles END 
            //Weapons START
            //Guns
            WEAPON_MG_1 = ImageIO.read(new File("textures/weapons_beta/turrets/turretA.png"));
            WEAPON_RL_1 = ImageIO.read(new File("textures/weapons_beta/turrets/turretB.png"));
            //Bullets
            SHOT_MG_1 = ImageIO.read(new File("textures/weapons_beta/shots/projectile.png"));
            SHOT_RL_1 = ImageIO.read(new File("textures/weapons_beta/shots/missile.png"));
            //Weapons END

        } catch (IOException ex) {
            Logger.getLogger(ImagePresets.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    private ImagePresets() {

    }
}
