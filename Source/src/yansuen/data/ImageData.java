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
public class ImageData implements Data {

    protected BufferedImage image;
    DataContainer parent = null;

    public ImageData(BufferedImage image) {
        this.image = image;
    }

    public void setImage(BufferedImage image) {
        BufferedImage old = this.image;
        this.image = image;
        if (parent != null)
            parent.onImageChanged(parent, old);
    }

    public BufferedImage getImage() {
        return image;
    }

    @Override
    public String[] networkSerialize() {
        return new String[0];
    }

    @Override
    public void networkDeserialize(String[] args) {
    }

    @Override
    public int networkSerializeArgumentCount() {
        return 0;
    }

}
