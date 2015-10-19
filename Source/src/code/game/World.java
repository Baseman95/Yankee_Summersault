package code.game;

import code.graphics.GraphicsInterface;
import code.logic.LogicInterface;
import code.logic.LogicLooper;
import java.awt.Graphics2D;
import java.util.ArrayList;

/**
 *
 * @author Link162534
 */
public class World implements LogicLooper {

    protected ArrayList<GameObject> gameObjects = new ArrayList<>();

    @Override
    public void onLogicLoop(long tick) {
        for (GameObject gameObject : gameObjects) {
            LogicInterface li = gameObject.getLogicInterface();
            if (li != null)
                li.doLogic(gameObject.dataObject, tick);
        }
    }

    public void repaint(Graphics2D g2d) {
        for (GameObject gameObject : gameObjects) {
            GraphicsInterface gi = gameObject.getGraphicsInterface();
            if (gi != null)
                gi.render(gameObject.dataObject, g2d);
        }
    }

    public synchronized ArrayList<GameObject> getGameObjects() {
        return gameObjects;
    }

}
