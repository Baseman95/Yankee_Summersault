package code.data;

/**
 *
 * @author Link162534
 */
public class DataObject implements DataInterface {

    protected float x;
    protected float y;
    protected float width;
    protected float height;

    public DataObject(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public DataObject(DataInterface dataObject) {
        x = dataObject.getX();
        y = dataObject.getY();
    }

    @Override
    public void setY(float y) {
        this.y = y;
    }

    @Override
    public void setX(float x) {
        this.x = x;
    }

    @Override
    public void setWidth(float width) {
        this.width = width;
    }

    @Override
    public void setHeight(float height) {
        this.height = height;
    }

    @Override
    public float getY() {
        return y;
    }

    @Override
    public float getX() {
        return x;
    }

    @Override
    public float getWidth() {
        return width;
    }

    @Override
    public float getHeight() {
        return height;
    }

}
