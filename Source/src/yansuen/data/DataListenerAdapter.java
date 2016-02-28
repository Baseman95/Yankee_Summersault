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
public class DataListenerAdapter implements DataListener {

    @Override
    public void onPositionChanged(DataContainer data, float xOld, float yOld) {
    }

    @Override
    public void onWidthChanged(DataContainer data, float wOld, float hOld) {
    }

    @Override
    public void onRotationChanged(DataContainer data, double rOld) {
    }

    @Override
    public void onMovementChanged(DataContainer data, float xOld, float yOld) {
    }

    @Override
    public void onImageChanged(DataContainer data, BufferedImage imgOld) {
    }

}
