package yansuen.data;

import java.awt.image.BufferedImage;

/**
 * @author Link
 */
public class GameDataListenerAdapter implements GameDataListener {

    @Override
    public void onImageChanged(GameData data, BufferedImage oldImg) {
    }

    @Override
    public void onPositionChanged(GameData data, float oldX, float oldY) {
    }

    @Override
    public void onRotationChanged(GameData data, double oldR) {
    }

    @Override
    public void onSizeChanged(GameData data, float oldW, float oldH) {
    }
}
