package yansuen.data;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import yansuen.network.NetworkSerializable;

/**
 * @author Link162534
 */
public class GameData implements NetworkSerializable {

    protected float x;
    protected float y;
    protected float width;
    protected float height;
    protected double rotation;
    protected BufferedImage image;

    protected ArrayList<GameDataListener> listenerList = new ArrayList<>();
    protected boolean listening = false;

    public GameData(float x, float y, float width, float height, double rotation, BufferedImage image) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.rotation = rotation;
        this.image = image;
    }

    public GameData(float x, float y, float width, float height, BufferedImage image) {
        this(x, y, width, height, 0, image);
    }

    public GameData(GameData gameData) {
        this(gameData.x, gameData.y, gameData.width, gameData.height, gameData.rotation, gameData.image);
    }

    //<editor-fold defaultstate="collapsed" desc="listenerList">
    public ArrayList<GameDataListener> getListenerList() {
        return listenerList;
    }

    public boolean addDataObjectListener(GameDataListener listener) {
        if (listenerList.isEmpty())
            setListening(true);
        boolean r = listenerList.add(listener);
        return r;
    }

    public boolean removeDataObjectListener(GameDataListener listener) {
        boolean r = listenerList.remove(listener);
        if (listenerList.isEmpty())
            setListening(false);
        return r;
    }

    protected void setListening(boolean listen) {
        listening = listen;
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="setter">
    public void setX(float x) {
        float old = this.x;
        this.x = x;

        if (!listening)
            return;
        listenerList.stream().forEach((dol) -> {
            dol.onPositionChanged(this, old, this.y);
        });
    }

    public void setY(float y) {
        float old = this.y;
        this.y = y;

        if (!listening)
            return;
        listenerList.stream().forEach((dol) -> {
            dol.onPositionChanged(this, this.y, old);
        });
    }   

    public void increaseX(float x) {
        setX(this.x + x);
    }

    public void increaseY(float y) {
        setY(this.y + y);
    }

    public void setRotation(double rotation) {
        double old = this.rotation;
        if (rotation > Math.PI * 2)
            rotation = rotation % Math.PI * 2;
        while (rotation <= 0) {
            rotation += Math.PI * 2;
        }
        this.rotation = rotation;

        if (!listening)
            return;
        listenerList.stream().forEach((dol) -> {
            dol.onRotationChanged(this, old);
        });
    }

    public void increaseRotation(double rotation) {
        setRotation(this.rotation + rotation);
    }

    public void setWidth(float width) {
        float old = this.width;
        this.width = width;

        if (!listening)
            return;
        listenerList.stream().forEach((dol) -> {
            dol.onSizeChanged(this, old, this.height);
        });
    }

    public void setHeight(float height) {
        float old = this.height;
        this.height = height;

        if (!listening)
            return;
        listenerList.stream().forEach((dol) -> {
            dol.onSizeChanged(this, this.width, old);
        });
    }

    public void setImage(BufferedImage image) {
        BufferedImage old = this.image;
        this.image = image;

        if (!listening)
            return;
        listenerList.stream().forEach((dol) -> {
            dol.onImageChanged(this, old);
        });
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="getter">
    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public double getRotation() {
        return rotation;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public BufferedImage getImage() {
        return image;
    }
//</editor-fold>

    @Override
    public String toString() {
        return "GameData{" + x + "x, " + y + "y, " + width + "w, " + height + "h, " + rotation + "r" + '}';
    }

    //<editor-fold defaultstate="collapsed" desc="network">
    @Override
    public String[] networkSerialize() {
        String[] args = NetworkSerializable.generateArguments(x, y, width, height, rotation);
        return args;
    }

    @Override
    public void networkDeserialize(String[] args) {
        x = Float.valueOf(args[0]);
        y = Float.valueOf(args[1]);
        width = Float.valueOf(args[2]);
        height = Float.valueOf(args[3]);
        rotation = Double.valueOf(args[4]);
    }

    @Override
    public int networkSerializeArgumentCount() {
        return 5;
    }
//</editor-fold>
}
