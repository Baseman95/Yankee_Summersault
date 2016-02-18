/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package code.data;

import java.awt.image.BufferedImage;

/**
 *
 * @author Link
 */
public class DataObjectListenerAdapter implements DataObjectListener {

    @Override
    public void onPositionChanged(DataObject data, float xOld, float yOld) {
    }

    @Override
    public void onWidthChanged(DataObject data, float wOld, float hOld) {
    }

    @Override
    public void onRotationChanged(DataObject data, double rOld) {
    }

    @Override
    public void onMovementChanged(DataObject data, float xOld, float yOld) {
    }

    @Override
    public void onImageChanged(DataObject data, BufferedImage imgOld) {
    }

}
