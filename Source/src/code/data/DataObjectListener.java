/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package code.data;

import code.data.DataObject;
import java.awt.image.BufferedImage;

/**
 *
 * @author Link
 */
public interface DataObjectListener {

    void onPositionChanged(DataObject data, float xOld, float yOld);

    void onWidthChanged(DataObject data, float wOld, float hOld);

    void onRotationChanged(DataObject data, double rOld);

    void onMovementChanged(DataObject data, float xOld, float yOld);

    void onImageChanged(DataObject data, BufferedImage imgOld);
}
