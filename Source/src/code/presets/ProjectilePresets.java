/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package code.presets;

import code.game.tank.projectile.Projectile;

/**
 *
 * @author Base
 */
public class ProjectilePresets {

    /*
    Projectile(float x, float y, double rotation, BufferedImage image, long speed, long deathTick,
    LogicInterface impactInterface, LogicInterface projectileController, LogicInterface projectileDrive, GraphicsInterface graphics)
     */
    public static final Projectile MG762 = new Projectile(0, 0, 0, ImagePresets.Projectile.MG762,
            5, 250, HitInterfacePresets.NORMAL, ControllerPresets.HOLD_ACCELERATE,
            null, GraphicsPresets.ROTATION);

    public static final Projectile MINIG = new Projectile(0, 0, 0, ImagePresets.Projectile.MINIG,
            4.5f, 250, HitInterfacePresets.NORMAL, ControllerPresets.HOLD_ACCELERATE,
            null, GraphicsPresets.ROTATION);
    
    public static final Projectile HELCN = new Projectile(0, 0, 0, ImagePresets.Projectile.HELCN,
            4.5f, 250, HitInterfacePresets.NORMAL, ControllerPresets.HOLD_ACCELERATE,
            null, GraphicsPresets.ROTATION);
    
    public static final Projectile GLKPR = new Projectile(0, 0, 0, ImagePresets.Projectile.GLKPR,
            6, 300, HitInterfacePresets.NORMAL, ControllerPresets.HOLD_ACCELERATE,
            null, GraphicsPresets.ROTATION);
    
    public static final Projectile SHTGN = new Projectile(0, 0, 0, ImagePresets.Projectile.SHTGN,
            5, 200, HitInterfacePresets.NORMAL, ControllerPresets.HOLD_ACCELERATE,
            null, GraphicsPresets.ROTATION);
    
    
    
    
    
    
    
    
    
    
    
    public static final Projectile MASCHYNENGWER = new Projectile(0, 0, 0, ImagePresets.Projectile.MACHINEGUN,
            3, 500, HitInterfacePresets.NORMAL, ControllerPresets.HOLD_ACCELERATE,
            null, GraphicsPresets.ROTATION);

}
