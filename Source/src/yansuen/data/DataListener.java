/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yansuen.data;

import java.awt.image.BufferedImage;

/**
 *
 * @author Link
 */
public interface DataListener {

    void onPositionChanged(DataContainer data, float xOld, float yOld);

    void onWidthChanged(DataContainer data, float wOld, float hOld);

    void onRotationChanged(DataContainer data, double rOld);

    void onMovementChanged(DataContainer data, float xOld, float yOld);

    void onImageChanged(DataContainer data, BufferedImage imgOld);
}
