package code.data;

/**
 *
 * @author Link162534
 */
public class DataObject implements DataInterface {

    protected float x;
    protected float y;

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
    public float getY() {
        return y;
    }

    @Override
    public float getX() {
        return x;
    }

}
