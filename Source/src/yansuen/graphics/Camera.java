package yansuen.graphics;

import code.menu.GamePanel;
import yansuen.game.GameObject;

/**
 *
 * @author Link162534
 */
public class Camera {

    protected GamePanel gamePanel;
    protected GameObject gameObject = null;

    public Camera(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public int transformX(double x) {
        if (gameObject == null)
            return (int) (x + gamePanel.getWidth() / 2.0);
        return (int) (x - (gameObject.getData().getX() + gameObject.getData().getWidth() / 2.0 - gamePanel.getWidth() / 2.0));
    }

    public int transformY(double y) {
        if (gameObject == null)
            return (int) (y + gamePanel.getHeight() / 2.0);
        return (int) (y - (gameObject.getData().getY() + gameObject.getData().getHeight() / 2.0 - gamePanel.getHeight() / 2.0));
    }

    public int transformWidth(double width) {
        return (int) (width);
    }

    public int transformHeight(double height) {
        return (int) (height);
    }

    public void setGameObject(GameObject gameObject) {
        this.gameObject = gameObject;
    }

    public GameObject getGameObject() {
        return gameObject;
    }
}
