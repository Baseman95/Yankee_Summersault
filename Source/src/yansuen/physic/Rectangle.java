package yansuen.physic;

/**
 *
 * @author Link162534
 */
public class Rectangle extends Polygon {

    public Rectangle(double x, double y, double width, double height) {
        add(new CartesianVector(x, y));
        add(new CartesianVector(x + width, y));
        add(new CartesianVector(x + width, y + height));
        add(new CartesianVector(x, y + height));
    }
}
