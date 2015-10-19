/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package code;

import code.data.DataObject;
import java.awt.image.BufferedImage;

/**
 *
 * @author User
 */
public class TankData extends DataObject {

    protected int dir = 0;

    public TankData(float x, float y, float w, float h, BufferedImage img, int dir) {
        super(x, y, w, h, img);
        this.dir = dir;
    }

    public TankData(TankData tankData) {
        super(tankData);
        this.dir = tankData.getDir();
    }

    public int getDir() {
        return dir;
    }

    public void setDir(int dir) {
        this.dir = dir;
    }

}
