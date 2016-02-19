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

    public static BufferedImage NO_IMAGE = loadImg("textures/empty.png");
    ;
    public static BufferedImage TANK = loadImg("textures/vehicles_beta/m1128.png");
    public static BufferedImage TANK2 = loadImg("tank2.png");
    public static BufferedImage OLD_TANK = loadImg("cool_tank.png");
    public static BufferedImage TURRET_A = loadImg("textures/weapons_beta/turrets/turretA.png");
    @Deprecated
    public static BufferedImage SHOT_1 = loadImg("textures/weapons_beta/shots/gun_plasma_shot.png");
    @Deprecated
    public static BufferedImage SHOT_2 = loadImg("textures/weapons_beta/shots/missile.png");
    @Deprecated
    public static BufferedImage SHOT_3 = loadImg("textures/weapons_beta/shots/gun_magnum_shot.png");

    //STUFF
    //Surfaces START
    public static BufferedImage SURFACE_CONCRETE = loadImg("textures/world/surface_alpha/concrete.png");
    public static BufferedImage SURFACE_DIRT = loadImg("textures/world/surface_alpha/dirt.png");
    public static BufferedImage SURFACE_GRASS = loadImg("textures/world/surface_alpha/grass.png");
    public static BufferedImage SURFACE_ROAD = loadImg("textures/world/surface_alpha/road.png");
    public static BufferedImage SURFACE_SAND = loadImg("textures/world/surface_alpha/sand.png");
    public static BufferedImage SURFACE_WATER = loadImg("textures/world/surface_alpha/water.png");
    //Surfaces END
    //Vehicles START    
    public static BufferedImage TANK_ABRAMS = loadImg("textures/vehicles_beta/abrams.png");
    //Vehicles END 
    //Weapons START
    //Guns
    public static BufferedImage WEAPON_MG_1 = loadImg("textures/weapons_beta/turrets/turretA.png");
    public static BufferedImage WEAPON_RL_1 = loadImg("textures/weapons_beta/turrets/turretB.png");
    //Bullets
    public static BufferedImage SHOT_MG_1 = loadImg("textures/weapons_beta/shots/projectile.png");
    public static BufferedImage SHOT_RL_1 = loadImg("textures/weapons_beta/shots/missile.png");
    //Weapons END

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
