/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package code;

import code.data.DataObject;
import code.data.ImageData;
import code.data.PositionData;
import java.awt.image.BufferedImage;

/**
 *
 * @author User
 */
public class TankData extends DataObject {

    protected int dir = 0;

    public TankData(float x, float y, float w, float h, BufferedImage img, int dir) {
        super(new PositionData(x, y, w, h), new ImageData(img));
        this.dir = dir;
    }

    public TankData(TankData tankData) {
        super(new PositionData(tankData.positionData), tankData.getImageData());
        this.dir = tankData.getDir();
    }

    public int getDir() {
        return dir;
    }

    public void setDir(int dir) {
        this.dir = dir;
    }

}
