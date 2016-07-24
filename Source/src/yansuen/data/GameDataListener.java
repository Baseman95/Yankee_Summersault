package yansuen.data;

import java.awt.image.BufferedImage;

/**
 * @author Link
 */
public interface GameDataListener {

    void onPositionChanged(GameData data, float oldX, float oldY);

    void onSizeChanged(GameData data, float oldW, float oldH);

    void onRotationChanged(GameData data, double oldR);

    void onImageChanged(GameData data, BufferedImage oldImg);
}
